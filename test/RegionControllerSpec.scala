import org.specs2.mutable._

import play.api.http.ContentTypes
import play.api.test._
import play.api.test.Helpers._

class RegionControllerSpec extends Specification {

}
/*
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

public class RegionControllerSpec {

    @Test
    public void getRegionHtml() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), "name"));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains(DemoData.CRESTED_BUTTE_COLORADO_REGION);
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

                Result result = callAction(routes.ref.RegionController.getRegionHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), "name"), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains(DemoData.CRESTED_BUTTE_COLORADO_REGION);
                assertThat(contentAsString(result)).contains("Add a New Route");
            }
        });
    }

    @Test
    public void addRegion() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                // todo
            }
        });
    }

    @Test
    public void saveRegion() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                // todo
            }
        });
    }

    @Test
    public void saveRegionNonAdmin() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                // todo
            }
        });
    }

    @Test
    public void saveRegionDuplicateName() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                // todo
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

                Result result = callAction(routes.ref.RegionController.addRoute(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION)), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains(DemoData.CRESTED_BUTTE_COLORADO_REGION + " - Add New Route");
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

                // todo: add directions

                Result result = callAction(routes.ref.RegionController.saveRoute(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION)), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies).withFormUrlEncodedBody(routeData));
                assertThat(status(result)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(result)).isEqualTo(routes.RouteController.getRouteHtml(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION), "foo").url());
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
                routeData.put("name", DemoData.WEST_MAROON_PASS_ROUTE);
                routeData.put("description", "this is foo");
                routeData.put("distanceInMiles", "1");
                routeData.put("location", "nowhere");

                Result result = callAction(routes.ref.RegionController.saveRoute(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION)), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies).withFormUrlEncodedBody(routeData));
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
                
                Result subscribeResult = callAction(routes.ref.RegionController.subscribe(UrlUtils.getUrlFriendlyName(DemoData.CRESTED_BUTTE_COLORADO_REGION)), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(subscribeResult)).isEqualTo(SEE_OTHER);
                assertThat(RegionSubscription.find.all().size()).isEqualTo(1);
            }
        });
    }
    
    @Test
    public void subscribeTwice() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                // todo
            }
        });
    }

    @Test
    public void deleteRegion() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                // todo
            }
        });
    }

}
*/