package happytrails.pages

class ShowRoutePage extends ScaffoldPage {

	static at = {
		title ==~ /Show Route.+/
	}

	static content = {
		editButton(to: EditRegionPage) { $("a", text: "Edit") }
		//deleteButton(to: RoutesPage) { $("input", value: "Delete") }
		row { $("fieldset.control-group span.property-label", text: it).parent() }
		value { row(it).find("span.property-value").text() }
		name { $('h1').text() }
        comments { $('ul.comments li') }
        avgRating { value('Average Rating') }
	}
}
