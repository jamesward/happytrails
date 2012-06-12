package happytrails.pages

class ShowRoutePage extends ScaffoldPage {

	static at = {
		heading.text() ==~ /Show Route/
	}

	static content = {
		editButton(to: EditRegionPage) { $("a", text: "Edit") }
		//deleteButton(to: RoutesPage) { $("input", value: "Delete") }
		row { $("li.fieldcontain span.property-label", text: it).parent() }
		value { row(it).find("span.property-value").text() }
		name { value("Name") }
        comments { $('.comments .property-value', text: it) }
        avgRating { $('#avgRating', text: it) }
	}
}
