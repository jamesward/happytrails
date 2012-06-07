import models.Region;
import models.RegionSubscription;
import models.User;
import org.junit.Test;
import utils.DemoData;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class RegionSubscriptionTest {
    
    @Test
    public void create() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                assertThat(RegionSubscription.find.all().size()).isEqualTo(0);
                
                User user = User.findByEmailAddressAndPassword("james@demo.com", "password");
                Region region = Region.findByUrlFriendlyName("Denver Front Range");
                
                RegionSubscription regionSubscription = new RegionSubscription(user, region);
                regionSubscription.save();

                assertThat(RegionSubscription.find.all().size()).isEqualTo(1);
            }
        });
    }
    
}
