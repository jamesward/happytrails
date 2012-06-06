import happytrails.Region
import happytrails.Route
import happytrails.RegionController

class RegionControllerIntegrationTests extends GroovyTestCase {

    void testFeed() {
        def controller = new RegionController()
        def region = Region.findBySeoName('colorado-front-range')
        assert region.name != null

        controller.request.parameters = [region: 'colorado-front-range']
        controller.feed()
        println(controller.response.contentAsString)
        assert controller.response.contentAsString != null
        assert controller.response.status == 200
        assert controller.response.contentType == "application/atom+xml"
    }
}
