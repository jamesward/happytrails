package happytrails.pages

class EditRoutePage extends ScaffoldPage {

	static at = {
		heading.text() ==~ /Edit.+/
	}
	
	static content = {
		updateButton(to: ShowRoutePage) { $("input", value: "Update") }
		deleteButton(to: RegionsPage) { $("input", value: "Delete") }
        addComment(to: AddCommentPage) { $("a", text: 'Add Comment') }
	}

}