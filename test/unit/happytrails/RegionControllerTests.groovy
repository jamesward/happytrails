package happytrails



import org.junit.*
import grails.test.mixin.*

@TestFor(RegionController)
@Mock(Region)
class RegionControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/region/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.regionInstanceList.size() == 0
        assert model.regionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.regionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.regionInstance != null
        assert view == '/region/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/region/show/1'
        assert controller.flash.message != null
        assert Region.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/region/list'


        populateValidParams(params)
        def region = new Region(params)

        assert region.save() != null

        params.id = region.id

        def model = controller.show()

        assert model.regionInstance == region
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/region/list'


        populateValidParams(params)
        def region = new Region(params)

        assert region.save() != null

        params.id = region.id

        def model = controller.edit()

        assert model.regionInstance == region
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/region/list'

        response.reset()


        populateValidParams(params)
        def region = new Region(params)

        assert region.save() != null

        // test invalid parameters in update
        params.id = region.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/region/edit"
        assert model.regionInstance != null

        region.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/region/show/$region.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        region.clearErrors()

        populateValidParams(params)
        params.id = region.id
        params.version = -1
        controller.update()

        assert view == "/region/edit"
        assert model.regionInstance != null
        assert model.regionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/region/list'

        response.reset()

        populateValidParams(params)
        def region = new Region(params)

        assert region.save() != null
        assert Region.count() == 1

        params.id = region.id

        controller.delete()

        assert Region.count() == 0
        assert Region.get(region.id) == null
        assert response.redirectedUrl == '/region/list'
    }
}
