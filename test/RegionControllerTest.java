import controllers.routes;
import models.Region;
import org.junit.Test;
import play.mvc.Result;
import utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class RegionControllerTest {

    @Test
    public void getRegionHtml() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName("Denver Front Range")));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range");
            }
        });
    }

}
