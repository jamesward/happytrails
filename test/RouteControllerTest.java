import controllers.routes;
import org.junit.Test;
import play.mvc.Result;
import utils.DemoData;
import utils.UrlUtils;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class RouteControllerTest {

    @Test
    public void getRouteHtml() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName("Denver Front Range"),
                        UrlUtils.getUrlFriendlyName("Dakota Ridge, Red Rocks and Mathews Winters")));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Dakota Ridge, Red Rocks and Mathews Winters");
                assertThat(contentAsString(result)).contains("Start at the Matthews Winters parking lot."); // direction
                assertThat(contentAsString(result)).contains("This route is one of the best in the Denver area!"); // comment
                assertThat(contentAsString(result)).contains("Rating: 4"); // rating
            }
        });
    }

}
