package happytrails.pages;

class SignupPage extends ScaffoldPage {
    static url = "signup"

    static at = {
        title == "Register"
    }

    static content = {
        form { $("form") }
        createAccountButton { $("#create") }
    }

}
