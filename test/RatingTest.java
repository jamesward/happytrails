import models.Rating;
import models.Region;
import models.Route;
import models.User;
import org.junit.Test;
import play.Logger;
import play.data.Form;

import javax.persistence.PersistenceException;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class RatingTest {

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
                assertThat(route.region.id).isEqualTo(region.id);
                
                Rating rating = new Rating(user, route, 1);
                rating.save();
                
                assertThat(rating.id).isNotNull();
                assertThat(rating.value).isEqualTo(1);
                assertThat(rating.creationDate).isNotNull();
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void createEmptyValue() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Rating rating = new Rating();
                rating.save();
            }
        });
    }

    // todo: test constraints
    /*
    @Test
    public void testCreateValueBelowMin() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Map<String, String> ratingMap = new HashMap<String, String>();
                ratingMap.put("value", "0");
                Form<Rating> ratingForm = new Form<Rating>(Rating.class).bind(ratingMap);
                assertThat(ratingForm.hasErrors()).isTrue();
                
                // not a db constraint so unfortunately this works
                Rating rating = ratingForm.get();
                rating.save();
                assertThat(rating.id).isNotNull();
                assertThat(rating.value).isEqualTo(0);
            }
        });
    }

    @Test
    public void testCreateValueAboveMax() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Rating rating = new Rating(new User(), 6);
                rating.save();
            }
        });
    }
    */

}
