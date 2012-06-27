import models.Comment;
import models.Region;
import models.Route;
import models.User;

import org.junit.Test;
import utils.DemoData;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class CommentTest {

    @Test
    public void create() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                User user = new User("foo@foo.com", "foo", "foo");
                user.save();

                Region region = new Region("Some place");
                region.save();

                Route route = new Route("Nowhere", "A good one", 1.1, region, "Under the rainbow");
                route.save();
                
                Comment comment = new Comment(user, route, "test comment");
                comment.save();
                
                assertThat(comment.id).isNotNull();
                assertThat(comment.value).isEqualTo("test comment");
                assertThat(comment.creationDate).isNotNull();
                assertThat(comment.user.id).isEqualTo(user.id);
                assertThat(comment.route.id).isEqualTo(route.id);
                assertThat(comment.route.region.id).isEqualTo(route.region.id);
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void createEmptyValue() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Comment comment = new Comment();
                comment.save();
            }
        });
    }

    @Test
    public void fiveMostRecent() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                
                User user = new User("foo@foo.com", "foo", "foo");
                user.save();
                
                Region region = new Region("Some place");
                region.save();
                
                Route route = new Route("Nowhere", "A good one", 1.1, region, "Under the rainbow");
                route.save();
                assertThat(route.region.id).isEqualTo(region.id);
                
                for (int i = 0; i < 10; i++) {
                    Comment comment = new Comment(user, route, "test comment " + i);
                    comment.save();

                    assertThat(comment.user.id).isEqualTo(user.id);
                    assertThat(comment.route.id).isEqualTo(route.id);
                    assertThat(comment.route.region.id).isEqualTo(route.region.id);
                }
                
                List<Comment> fiveMostRecentComments = Comment.fiveMostRecent();
                assertThat(fiveMostRecentComments.size()).isEqualTo(5);
            }
        });
    }

}
