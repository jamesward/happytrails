import controllers.routes;
import models.RegionSubscription;
import org.junit.Test;
import play.api.mvc.Cookie;
import play.api.mvc.Cookies$;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;
import scala.collection.Seq;
import utils.DemoData;
import utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class RegionControllerTest {

    @Test
    public void getRegionHtml() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName("Denver Front Range"), "name"));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range");
            }
        });
    }

    @Test
    public void getRegionHtmlLoggedIn() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<String, String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName("Denver Front Range"), "name"), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range");
                assertThat(contentAsString(result)).contains("Add a New Route");
            }
        });
    }
    
    @Test
    public void addRoute() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<String, String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Result result = callAction(routes.ref.RegionController.addRoute(UrlUtils.getUrlFriendlyName("Denver Front Range")), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range - Add New Route");
            }
        });
    }

    @Test
    public void saveRoute() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<String, String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Map<String,String> routeData = new HashMap<String, String>();
                routeData.put("name", "foo");
                routeData.put("description", "this is foo");
                routeData.put("distanceInMiles", "1");
                routeData.put("location", "nowhere");

                Result result = callAction(routes.ref.RegionController.saveRoute(UrlUtils.getUrlFriendlyName("Denver Front Range")), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies).withFormUrlEncodedBody(routeData));
                assertThat(status(result)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(result)).isEqualTo(routes.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName("Denver Front Range"), "foo").url());
            }
        });
    }

    @Test
    public void saveRouteWithDuplicateName() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<String, String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Map<String,String> routeData = new HashMap<String, String>();
                routeData.put("name", "Dakota Ridge, Red Rocks and Mathews Winters");
                routeData.put("description", "this is foo");
                routeData.put("distanceInMiles", "1");
                routeData.put("location", "nowhere");

                Result result = callAction(routes.ref.RegionController.saveRoute(UrlUtils.getUrlFriendlyName("Denver Front Range")), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies).withFormUrlEncodedBody(routeData));
                assertThat(status(result)).isEqualTo(BAD_REQUEST);
            }
        });
    }

    @Test
    public void subscribe() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                assertThat(RegionSubscription.find.all().size()).isEqualTo(0);
                
                Map<String,String> data = new HashMap<String, String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);
                
                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);
                
                Result subscribeResult = callAction(routes.ref.RegionController.subscribe(UrlUtils.getUrlFriendlyName("Denver Front Range")), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(subscribeResult)).isEqualTo(SEE_OTHER);
                assertThat(RegionSubscription.find.all().size()).isEqualTo(1);
            }
        });
    }

}
