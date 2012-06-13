package happytrails.pages

class AddRoutePage extends ScaffoldPage {

	static at = {
		title ==~ /Add.+/
	}
	
	static content = {
		createButton(to: ShowRoutePage) { create() }
        name { value("Name") }
        distance { value("Distance") }
        location { value("Location") }
	}

}