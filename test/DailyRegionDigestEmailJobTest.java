import jobs.DailyRegionDigestEmailJob;
import models.*;
import org.junit.Test;
import utils.DemoData;
import utils.UrlUtils;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class DailyRegionDigestEmailJobTest {
    
    @Test
    public void testSubscribe() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                User subscribingUser = User.findByEmailAddressAndPassword("james@demo.com", "password");
                
                Region region = Region.findByUrlFriendlyName(UrlUtils.getUrlFriendlyName("Denver Front Range"));

                RegionSubscription regionSubscription = new RegionSubscription(subscribingUser, region);
                regionSubscription.save();
                
                Route route = Route.findByUrlFriendlyName(UrlUtils.getUrlFriendlyName("Dakota Ridge, Red Rocks and Mathews Winters"));
                
                User commentingUser = User.findByEmailAddressAndPassword("matt@demo.com", "password");
                
                Comment comment = new Comment(commentingUser, "new comment");
                comment.route = route;
                comment.save();
                
                List<DailyRegionDigestEmailJob.RegionUserDigest> regionUserDigests = DailyRegionDigestEmailJob.getRegionUserDigests();
                assertThat(regionUserDigests.size()).isEqualTo(1);
                assertThat(regionUserDigests.get(0).user).isEqualTo(subscribingUser);
                assertThat(regionUserDigests.get(0).comments.size()).isEqualTo(1);
                assertThat(regionUserDigests.get(0).comments.get(0).value).isEqualTo("new comment");
                assertThat(regionUserDigests.get(0).comments.get(0).user).isEqualTo(commentingUser);
            }
        });
    }
    
}
