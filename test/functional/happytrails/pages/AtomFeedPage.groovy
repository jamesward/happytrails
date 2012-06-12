package happytrails.pages

class AtomFeedPage extends ScaffoldPage {
    static url = "feed"

	static at = {
		$().text() ==~ /http:\/\/www.w3.org\/2005\/Atom/
	}
	
	static content = {
        entries { $().find("entry") }
	}

}