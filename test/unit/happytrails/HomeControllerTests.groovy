package happytrails



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HomeController)
@Mock(Region)
class HomeControllerTests {

    void testIndex() {
        def model = controller.index()

        assert model.regions.size() == 0
        assert model.total == 0
    }
}
