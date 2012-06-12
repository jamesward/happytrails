package happytrails.pages

class AddRoutePage extends ScaffoldPage {

	static at = {
		title ==~ /Create.+/
	}
	
	static content = {
		createButton(to: ShowRoutePage) { create() }
        name { value("Name") }
        distance { value("Distance") }
        location { value("Location") }
	}

}