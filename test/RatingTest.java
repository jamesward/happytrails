import models.Rating;
import models.User;
import org.junit.Test;
import play.Logger;
import play.data.Form;

import javax.persistence.PersistenceException;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RatingTest {

    @Test
    public void testCreate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Rating rating = new Rating(new User(), 1);
                rating.save();
                assertThat(rating.id).isNotNull();
                assertThat(rating.value).isEqualTo(1);
                assertThat(rating.creationDate).isNotNull();
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyValue() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Rating rating = new Rating();
                rating.save();
            }
        });
    }

    /*
    @Test
    public void testCreateValueBelowMin() {
        running(fakeApplication(), new Runnable() {
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
        running(fakeApplication(), new Runnable() {
            public void run() {
                Rating rating = new Rating(new User(), 6);
                rating.save();
            }
        });
    }
    */

}
