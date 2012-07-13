import controllers.utils.AcceptExtractors
import models.{Region, User}
import org.specs2.mutable._

import play.api.http.ContentTypes
import play.api.libs.json.{JsValue, Json}
import play.api.libs.MimeTypes
import play.api.mvc.{Result, AsyncResult}
import play.api.test._
import play.api.test.Helpers._
import plugins.MongoDB

class ApplicationControllerSpec extends Specification {

  "The index page" should {
    "return all regions as JSON when the accept content type is json" in {
      implicit val app = FakeApplication()
      running(app) {
        // add a new region
        implicit val context = MongoDB.context

        val regionJson = Region.createJson(""" {"name": "Denver Front Range"} """)
        
        val region = Region.parseJson(regionJson)
        
        val fakeRequest = FakeRequest().withHeaders(ACCEPT -> AcceptExtractors.Accepts.Json.mimeType)
        val result = controllers.ApplicationController.index()(fakeRequest).asInstanceOf[AsyncResult].result.value.get
        
        status(result) must equalTo(OK)
        contentType(result) must beSome(AcceptExtractors.Accepts.Json.mimeType)
        contentAsString(result) must contain("Denver Front Range")
        
        // delete the region
        Region.sync.remove(region) must not beNull // todo: what happens when remove fails?
        
      }
    }
  }

  "The index page" should {
    "return the index HTML page when the accept content type is html" in {
      running(FakeApplication()) {
        val fakeRequest = FakeRequest().withHeaders(ACCEPT -> AcceptExtractors.Accepts.Html.mimeType)
        val result = controllers.ApplicationController.index()(fakeRequest)

        status(result) must equalTo(OK)
        contentType(result) must beSome(AcceptExtractors.Accepts.Html.mimeType)
        contentAsString(result) must contain("Uber Tracks")
      }
    }
  }

  "GET signup page" should {
    "return the index HTML page when the accept content type is html" in {
      running(FakeApplication()) {
        val fakeRequest = FakeRequest().withHeaders(ACCEPT -> AcceptExtractors.Accepts.Html.mimeType)
        val result = controllers.ApplicationController.signup()(fakeRequest)

        status(result) must equalTo(OK)
        contentType(result) must beSome(AcceptExtractors.Accepts.Html.mimeType)
        contentAsString(result) must contain("Uber Tracks")
      }
    }
  }
  
  "POST signup page with JSON" should {
    "signup a new user" in {
      running(FakeApplication()) {
        val json = Json.parse(""" {"emailAddress": "foo@bar.com", "fullname": "Foo Bar", "password": "password"} """)
        val fakeRequest = FakeRequest().withJsonBody(json)
        val result = controllers.ApplicationController.signup()(fakeRequest)
        
        //println(contentAsString(result))

        status(result) must equalTo(OK)
        contentType(result) must beSome(AcceptExtractors.Accepts.Json.mimeType)
      }
    }
  }
  
  
  
}
/*
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

public class ApplicationControllerSpec {

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
*/