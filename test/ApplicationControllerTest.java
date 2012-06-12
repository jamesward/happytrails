import controllers.routes;
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

public class ApplicationControllerTest {

    @Test
    public void index() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.ApplicationController.index());
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains(DemoData.CRESTED_BUTTE_COLORADO_REGION);
                assertThat(contentAsString(result)).contains("<li>");
            }
        });
    }

    @Test
    public void signupForm() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Result result = callAction(routes.ref.ApplicationController.signupForm());
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("<form");
            }
        });
    }

    @Test
    public void signup() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Map<String,String> data = new HashMap<java.lang.String, java.lang.String>();
                data.put("emailAddress", "foo@foo.com");
                data.put("fullName", "Foo Bar");
                data.put("password", "password");
                
                Result signupResult = callAction(routes.ref.ApplicationController.signup(), fakeRequest().withFormUrlEncodedBody(data));
                assertThat(status(signupResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(signupResult)).isEqualTo(routes.ApplicationController.index().url());

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest().withFormUrlEncodedBody(data));
                assertThat(status(loginResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(loginResult)).isEqualTo(routes.ApplicationController.index().url());
            }
        });
    }

    @Test
    public void login() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Map<String,String> data = new HashMap<java.lang.String, java.lang.String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                assertThat(status(loginResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(loginResult)).isEqualTo(routes.ApplicationController.index().url());
                String cookies = header(Http.HeaderNames.SET_COOKIE, loginResult);

                Result result = callAction(routes.ref.ApplicationController.index(), fakeRequest().withHeader(Http.HeaderNames.COOKIE, cookies));
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("James Ward");
            }
        });
    }

    @Test
    public void loginWithBadPassword() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<java.lang.String, java.lang.String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "wrong");

                Result result = callAction(routes.ref.ApplicationController.login(), fakeRequest().withFormUrlEncodedBody(data));
                assertThat(status(result)).isEqualTo(BAD_REQUEST);
            }
        });
    }

    @Test
    public void logout() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                DemoData.loadDemoData();

                Map<String,String> data = new HashMap<java.lang.String, java.lang.String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(data);

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest);
                assertThat(status(loginResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(loginResult)).isEqualTo(routes.ApplicationController.index().url());

                Result loggedInIndexResult = callAction(routes.ref.ApplicationController.index(), fakeRequest().withHeader(Http.HeaderNames.COOKIE, header(Http.HeaderNames.SET_COOKIE, loginResult)));
                assertThat(status(loggedInIndexResult)).isEqualTo(OK);
                assertThat(contentAsString(loggedInIndexResult)).contains("James Ward");

                Result logoutResult = callAction(routes.ref.ApplicationController.logout(), fakeRequest().withHeader(Http.HeaderNames.COOKIE, header(Http.HeaderNames.SET_COOKIE, loginResult)));
                assertThat(status(logoutResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(loginResult)).isEqualTo(routes.ApplicationController.index().url());

                Result loggedOutIndexResult = callAction(routes.ref.ApplicationController.index(), fakeRequest().withHeader(Http.HeaderNames.COOKIE, header(Http.HeaderNames.SET_COOKIE, logoutResult)));
                assertThat(status(loggedOutIndexResult)).isEqualTo(OK);
                assertThat(contentAsString(loggedOutIndexResult)).doesNotContain("James Ward");
            }
        });
    }

}
