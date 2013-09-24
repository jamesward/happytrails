package happytrails.pages

import geb.Module

class RegionsPage extends ScaffoldPage {
	static url = "regions"
	
	static at = {
        title.startsWith('Regions')
    }
	
	static content = {
		newRegionButton(to: AddRegionPage) { $("a", text: "New Region") }
		regionTable { $(".content table", 0) }
		regionRow { module RegionRow, regionRows[it] }
		regionRows(required: false) { regionTable.find("tbody").find("tr") }
	}
}

class RegionRow extends Module {
	static content = {
		cell { $("td", it) }
		cellText { cell(it).text() }
        cellHrefText{ cell(it).find('a').text() }
		enabled { Boolean.valueOf(cellHrefText(0)) }
		name { cellText(0) }
		showLink(to: ShowRegionPage) { cell(0).find("a") }
	}
}