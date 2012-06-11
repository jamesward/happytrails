package happytrails.pages

class HomePage extends ScaffoldPage {

	static at = {
		$('title').text() ==~ /Welcome to Happy Trails!.+/
	}
	
	static content = {
        comments { $('.sidebar').find(".comment") }
        regions { $('.content').find(".region") }
        userIcon { ($('.icon-user')) }
        signupLink { $('.navbar').find('.signup') }
	}

}