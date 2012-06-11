package happytrails.pages

class AddRegionPage extends ScaffoldPage {

	static at = {
		title ==~ /Create.+/
	}
	
	static content = {
		createButton(to: ShowRegionPage) { create() }
	}

}