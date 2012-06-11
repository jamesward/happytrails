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
        // todo: implement
    }
}