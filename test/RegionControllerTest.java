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
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName("Denver Front Range")));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range");
            }
        });
    }

    @Test
    public void getRegionHtmlLoggedIn() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<String, String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName("Denver Front Range")), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range");
                assertThat(contentAsString(result)).contains("Add a New Route");
            }
        });
    }

    @Test
    public void subscribe() {
        running(fakeApplication(), new Runnable() {
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
