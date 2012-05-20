import controllers.routes;
import org.junit.Test;
import play.mvc.Result;


import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class ApplicaitonControllerTest {

    @Test
    public void testIndex() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Result result = callAction(routes.ref.ApplicationController.index());
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("Denver Front Range");
                assertThat(contentAsString(result)).contains("<li>");
            }
        });
    }

    @Test
    public void testSignupForm() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = callAction(routes.ref.ApplicationController.signupForm());
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentAsString(result)).contains("<form");
            }
        });
    }

    @Test
    public void testSignup() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String,String> data = new HashMap<java.lang.String, java.lang.String>();
                data.put("emailAddress", "foo@foo.com");
                data.put("fullName", "Foo Bar");
                data.put("password", "password");
                
                Result signupResult = callAction(routes.ref.ApplicationController.signup(), fakeRequest().withFormUrlEncodedBody(data));
                assertThat(status(signupResult)).isEqualTo(OK);
                assertThat(contentAsString(signupResult)).contains("Foo Bar");

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest().withFormUrlEncodedBody(data));
                assertThat(status(loginResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(loginResult)).isEqualTo("/");
            }
        });
    }

    @Test
    public void testLogin() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                DemoData.loadDemoData();
                
                Map<String,String> data = new HashMap<java.lang.String, java.lang.String>();
                data.put("emailAddress", "james@demo.com");
                data.put("password", "password");

                Result loginResult = callAction(routes.ref.ApplicationController.login(), fakeRequest().withFormUrlEncodedBody(data));
                assertThat(status(loginResult)).isEqualTo(SEE_OTHER);
                assertThat(redirectLocation(loginResult)).isEqualTo("/");
            }
        });
    }

    @Test
    public void testLoginWithBadPassword() {
        running(fakeApplication(), new Runnable() {
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

}
