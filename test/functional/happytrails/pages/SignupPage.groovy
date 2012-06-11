package happytrails.pages;

class SignupPage extends ScaffoldPage {
    static url = "signup"

    static at = {
   		$('.ui-dialog-title').text() ==~ /Create Account!.+/
   	}

    static content = {
        form { $("form") }
        createAccountButton { $("#create") }
    }

}
