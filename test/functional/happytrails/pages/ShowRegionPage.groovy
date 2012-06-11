package happytrails.pages

import geb.Module

class ShowRegionPage extends ScaffoldPage {

	static at = {
		title ==~ /Show Region.+/
	}
	
	static content = {
		editButton(to: EditRegionPage) { $("a", text: "Edit") }
		deleteButton(to: RegionsPage) { $("input", value: "Delete") }
		row { $("li.fieldcontain span.property-label", text: it).parent() }
		value { row(it).find("span.property-value").text() }
		enabled { Boolean.valueOf(value("Enabled")) }
		name { $("h1").text() }
        routeList { $("div.content .routes", 0) }
        routeRow { module RouteRow, routeRows[it] }
        routeRows(required: false) { routeList.find("li") }
	}
}

class RouteRow extends Module {
	static content = {
		cell { $("li", it) }
		cellText { cell(it).text() }
        cellHrefText{ cell(it).find('a').text() }
		enabled { Boolean.valueOf(cellHrefText(0)) }
		name { cellText(0) }
		showLink(to: ShowRoutePage) { cell(0).find("a") }
	}
}
