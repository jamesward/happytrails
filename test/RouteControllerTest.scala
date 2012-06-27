import controllers.routes;
import models.Region;
import models.Route;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;
import utils.DemoData;
import utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class RouteControllerTest {

    @Test
    public void getRouteHtml() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION),
                        UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE)));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains(DemoData.WEST_MAROON_PASS_ROUTE);
                assertThat(contentAsString(result)).contains("Start at the Matthews Winters parking lot."); // direction
                assertThat(contentAsString(result)).contains("This route is one of the best in the Denver area!"); // comment
                assertThat(contentAsString(result)).contains("icon-star-empty"); // rating
                assertThat(contentAsString(result)).contains("icon-star"); // rating
            }
        });
    }

    @Test
    public void saveRating() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Region region = Region.findByUrlFriendlyName(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION));
                assertThat(region).overridingErrorMessage("region was null").isNotNull();
                
                // 3 + 5 = 8; 8 / 2 = 4
                Route originalRoute = Route.findByUrlFriendlyName(region, UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE));
                assertThat(originalRoute).overridingErrorMessage("originalRoute was null").isNotNull();
                assertThat(originalRoute.getAverageRating()).isEqualTo(4);

                Map<String, String> loginData = new HashMap<String, String>();
                loginData.put("emailAddress", "james@demo.com");
                loginData.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(loginData);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);
                
                Result saveRatingResult = callAction(routes.ref.RouteController.saveRating(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE), 1), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(saveRatingResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(saveRatingResult)).isEqualTo(routes.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE)).url());
                
                // 1 + 5 = 6; 6 / 2 = 3
                Route updatedRoute = Route.findByUrlFriendlyName(region, UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE));
                assertThat(updatedRoute).overridingErrorMessage("updatedRoute was null").isNotNull();
                assertThat(updatedRoute.getAverageRating()).isEqualTo(3);
            }
        });
    }

    @Test
    public void saveComment() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> loginData = new HashMap<String, String>();
                loginData.put("emailAddress", "james@demo.com");
                loginData.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(loginData);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Map<String,String> commentData = new HashMap<String, String>();
                commentData.put("value", "this trail sucks");

                Result saveCommentResult = callAction(routes.ref.RouteController.saveComment(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE)), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies).withFormUrlEncodedBody(commentData));
                assertThat(status(saveCommentResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(saveCommentResult)).isEqualTo(routes.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE)).url());

                Result result = callAction(routes.ref.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), UrlUtils.getUrlFriendlyName(DemoData.WEST_MAROON_PASS_ROUTE)));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("this trail sucks");
            }
        });
    }
}
