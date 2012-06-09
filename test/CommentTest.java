import models.Comment;
import models.User;

import org.junit.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class CommentTest {

    @Test
    public void testCreate() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Comment comment = new Comment(new User(), "test comment");
                comment.save();
                assertThat(comment.id).isNotNull();
                assertThat(comment.value).isEqualTo("test comment");
                assertThat(comment.creationDate).isNotNull();
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyValue() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Comment comment = new Comment();
                comment.save();
            }
        });
    }

}
