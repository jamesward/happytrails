package happytrails

import grails.test.mixin.*

@TestFor(RouteController)
@Mock(Route)
class RouteControllerTests {

    def populateValidParams(params) {
      assert params != null
      params["region.name"] = "Colorado"
      params["name"] = "Matthew Winters"
      params["seoName"] = "matthew-winters"
      params["distance"] = 12.0
      params["location"] = "Morrison, CO"
    }

    void testIndex() {
        controller.index()
        assert "/route/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.routeInstanceList.size() == 0
        assert model.routeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.routeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.routeInstance != null
        assert view == '/route/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/route/show/1'
        assert controller.flash.message != null
        assert Route.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/route/list'

        populateValidParams(params)
        def route = new Route(params)

        assert route.save() != null

        params.id = route.id

        def model = controller.show()

        assert model.routeInstance == route
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/route/list'

        populateValidParams(params)
        def route = new Route(params)

        assert route.save() != null

        params.id = route.id

        def model = controller.edit()

        assert model.routeInstance == route
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/route/list'

        response.reset()

        populateValidParams(params)
        def route = new Route(params)

        assert route.save() != null

        // test invalid parameters in update
        params.id = route.id
        params.name = ''

        controller.update()

        assert view == "/route/edit"
        assert model.routeInstance != null

        route.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/route/show/$route.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        route.clearErrors()

        populateValidParams(params)
        params.id = route.id
        params.version = -1
        controller.update()

        assert view == "/route/edit"
        assert model.routeInstance != null
        assert model.routeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/route/list'

        response.reset()

        populateValidParams(params)
        def route = new Route(params)

        assert route.save() != null
        assert Route.count() == 1

        params.id = route.id

        controller.delete()

        assert Route.count() == 0
        assert Route.get(route.id) == null
        assert response.redirectedUrl == '/region/show'
    }
}
