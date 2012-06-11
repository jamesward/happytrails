package happytrails

import com.dumbster.smtp.SimpleSmtpServer
import geb.spock.GebReportingSpec
import happytrails.pages.HomePage
import happytrails.pages.SignupPage
import spock.lang.Stepwise
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver

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

    /*def "enable account via gmail"() {
        given:
        driver = new ChromeDriver()

        when:
        to SignupPage
        form.username = "ubertracks+bar@gmail.com"
        form.email = "ubertracks+bar@gmail.com"
        form.password = "IloveGROOVY@3"
        form.password2 = "IloveGROOVY@3"
        createAccountButton.click()

        then:
        $('form').text().contains("Your account registration email was sent")

        when:
        go "http://mail.google.com/mail/h/"
        $("#Email").value("ubertracks")
        $("#Passwd").value("hpzRT7ZqhU9jkE")
        $("#signIn").click()
        $('b', text: 'Welcome to Über Tracks!').click()
        $('a', text: 'here').click()

        then:
        waitFor { title ==~ /Welcome to Happy Trails!.+/ }
        userIcon.parent().text().contains("ubertracks+bar@gmail.com")
        driver.quit()
    }*/

    def "subscribe to region using atom"() {
        // todo: implement
    }

    def "click on a route in a region"() {
        // todo: implement
    }

    def "view ratings and comments for a route"() {
        // todo: implement
    }

    def "add rating for a route"() {
        // todo: implement
    }

    def "add comment for a route"() {
        // todo: implement
    }

    def "add new route to region"() {
        // todo: implement
    }

}