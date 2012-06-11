package happytrails.pages

class HomePage extends ScaffoldPage {

	static at = {
		heading.text() ==~ /Welcome to Happy Trails!.+/
	}
	
	static content = {
        comments { $('.sidebar').find(".comment") }
        regions { $('.content').find(".region") }
	}

}