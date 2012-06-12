package happytrails

import grails.test.mixin.*
import org.grails.comments.Comment

@TestFor(HomeController)
@Mock([Region, Comment])
class HomeControllerTests {

    void testIndex() {
        def model = controller.index()

        assert model.regions.size() == 0
        assert model.total == 0
    }
}
