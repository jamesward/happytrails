package happytrails

import com.dumbster.smtp.SimpleSmtpServer
import geb.spock.GebReportingSpec
import happytrails.pages.HomePage
import happytrails.pages.SignupPage
import spock.lang.Stepwise
import org.openqa.selenium.chrome.ChromeDriver
import happytrails.pages.*

@Stepwise
class AuthenticatedUserSpec extends GebReportingSpec {

    def "click signup link"() {
        when:
        to HomePage
        signupLink.click()
        then:
        title == "Register"
    }

    def "signup as a new user"() {
        given:
        def server = SimpleSmtpServer.start(1025)
        driver = new ChromeDriver()

        when:
        to SignupPage
        form.username = "ubertracks+foo@gmail.com"
        form.email = "ubertracks+foo@gmail.com"
        form.password = "IloveGROOVY@2"
        form.password2 = "IloveGROOVY@2"
        createAccountButton.click()

        then:
        $('form').text().contains("Your account registration email was sent")
        server.receivedEmailSize == 1
        def email = server.receivedEmail.next()
        String body = email.body
        int index = body.indexOf('/register/verifyRegistration?t=')
        index > -1
        int index2 = body.indexOf('"', index + 1)
        String code = body.substring(index + '/register/verifyRegistration?t='.length(), index2)
        code != null

        when:
        go 'register/verifyRegistration', t: code

        then:
        at HomePage
        waitFor { title ==~ /Welcome to Happy Trails!.+/ }
        userIcon.parent().text().contains("ubertracks+foo@gmail.com")
    }

    def "add new route to region"() {
        when:
        to RegionsPage
        regionRow(1).showLink.click()

        then:
        at ShowRegionPage
        name != null

        when:
        editButton.click()

        then:
        at EditRegionPage
        name != null

        when:
        addRouteLink().click()

        then:
        at AddRoutePage

        when:
        name = "Fun Ride"
        distance = "10.2"
        location = "Green River, UT"
        createButton.click()

        then:
        print title
        at ShowRoutePage
        name == "Fun Ride"
    }

    def "click on a route in a region"() {
        when:
        to RegionsPage
        regionRow(0).showLink.click()

        then:
        at ShowRegionPage
        name != null
    }

    def "view ratings and comments for a route"() {
        when:
        to RegionsPage
        regionRow(0).showLink.click()

        then:
        at ShowRegionPage
        name != null
        routeRows.size() > 0

        when:
        $('a', text: 'White Ranch').click()

        then:
        at ShowRoutePage
        name != null
        comments.size() > 0
        avgRating != null
    }

    def "add rating for a route"() {
        when:
        to TheHogbackPage
        editButton.click()

        then:
        at EditRoutePage
        name != null
        $('#rating_notifytext').text() == '(0 Ratings)'

        when:
        star3Rating.click()

        then:
        $('#rating_notifytext').text() ==~ /Rating saved.+/
    }

    def "add comment for a route"() {
        when:
        to TheHogbackPage
        editButton.click()

        then:
        at EditRoutePage
        name != null

        /* Couldn't figure out how to post comments
           - Unable to resolve comment as a property
        when:
        addComment.click()
        waitFor { comment.present }
        comment = "New Comment at 4 AM"
        postCommentButton.click()

        then:
        at EditRoutePage
        comments.size() == 1
        */
    }
}