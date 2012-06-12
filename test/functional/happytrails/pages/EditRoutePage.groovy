package happytrails.pages

class EditRoutePage extends ScaffoldPage {

	static at = {
		heading.text() ==~ /Edit.+/
	}
	
	static content = {
		updateButton(to: ShowRoutePage) { $("input", value: "Update") }
        deleteButton(to: RegionsPage) { $("input", value: "Delete") }
        comments { $('ul.comments li') }
        addComment { $("a", text: 'Post a Comment') }
        comment(required: false) { $("#commentBody") }
        postCommentButton { $("#Post") }
        star3Rating { $('#rating_star_3 a') }
	}

}