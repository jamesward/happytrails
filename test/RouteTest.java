import models.Rating;
import models.Route;
import models.Region;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RouteTest {

    @Test
    public void testCreate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Region region = new Region("foo region");
                region.save();
                
                Route route = new Route("foo route", "nice route", 6.1, region, "Denver");
                route.save();
                
                assertThat(route.id).isNotNull();
                assertThat(route.getName()).isEqualTo("foo route");
                assertThat(route.getUrlFriendlyName()).isEqualTo("foo-route");
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyValue() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Route route = new Route();
                route.save();
            }
        });
    }

    @Test
    public void testUrlFriendlyName() {
        Route route = new Route();
        route.setName("Foo  foo `~1!2@3#4$5%6^7&8*9(0)-_=+[{]}\\|;:'\",<.>/?");
        assertThat(route.getUrlFriendlyName()).isEqualTo("foo-foo-1234567890-");
    }
    
    @Test
    public void testAverageRating() {
        Route route = new Route();
        
        Rating rating1 = new Rating();
        rating1.value = 1;
        route.ratings.add(rating1);

        Rating rating5 = new Rating();
        rating5.value = 5;
        route.ratings.add(rating5);
        
        assertThat(route.getAverageRating()).isEqualTo(3);
    }

}
