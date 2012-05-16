import models.Region;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RegionTest {

    @Test
    public void testCreate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Region region = new Region("region");
                region.save();
                assertThat(region.id).isNotNull();
                assertThat(region.name).isEqualTo("region");
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyValue() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Region region = new Region();
                region.save();
            }
        });
    }

}
