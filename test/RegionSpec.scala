import org.specs2.mutable._

import play.api.http.ContentTypes
import play.api.test._
import play.api.test.Helpers._

class RegionSpec extends Specification {

}

/*
import models.Region;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class RegionSpec {

    @Test
    public void testCreate() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Region region = new Region("region");
                region.save();
                assertThat(region.id).isNotNull();
                assertThat(region.getName()).isEqualTo("region");
                assertThat(region.getUrlFriendlyName()).isEqualTo("region");
            }
        });
    }

    @Test(expected = PersistenceException.class)
    public void testCreateEmptyValue() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Region region = new Region();
                region.save();
            }
        });
    }

    @Test
    public void testUrlFriendlyName() {
        Region region = new Region("Foo  foo `~1!2@3#4$5%6^7&8*9(0)-_=+[{]}\\|;:'\",<.>/?");
        assertThat(region.getUrlFriendlyName()).isEqualTo("foo-foo-1234567890-");
    }

}
*/