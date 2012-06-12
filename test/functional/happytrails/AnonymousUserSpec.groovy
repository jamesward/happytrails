package happytrails

import geb.spock.GebReportingSpec

import spock.lang.*

import happytrails.pages.*

@Stepwise
class AnonymousUserSpec extends GebReportingSpec {

    def "view homepage"() {
        when:
        to HomePage

        then:
        comments.size() == 1
        regions.size() == 4
    }

    def "browse regions"() {
        when:
        to RegionsPage

        then:
        regionRows.size() == 4
    }

    def "see routes in a region"() {
        when:
        to RegionsPage
        regionTable.find('a', text: 'Colorado Front Range').click()

        then:
        at ShowRegionPage
        name == 'Colorado Front Range'
        routeRows.size() == 2
    }

    def "sort by highest rated route"() {
        when:
        to RegionsPage
        regionTable.find('a', text: 'Colorado Front Range').click()

        then:
        at ShowRegionPage

        when:
        routeTable.find('a', text: 'Avg. Rating').click() // desc is default

        then:
        'desc' in routeTable.find('a', text: 'Avg. Rating').parent().classes()
        //routeRow(0).avgRating == '5'
    }
}