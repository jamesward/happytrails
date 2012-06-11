package happytrails.pages

class EditRegionPage extends ScaffoldPage {

	static at = {
		heading.text() ==~ /Edit.+/
	}
	
	static content = {
		updateButton(to: ShowRegionPage) { $("input", value: "Update") }
		deleteButton(to: RegionsPage) { $("input", value: "Delete") }
	}

}