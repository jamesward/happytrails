package happytrails



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
class CommentTests {

    void testConstraints() {
        def comment = new Comment(value: "Comment Test")
        mockForConstraintsTests(Comment, [comment])

        // validation should fail if required properties are null
        def blankComment = new Comment()
        assert !blankComment.validate()
        assert "nullable" == blankComment.errors["value"]
    }
}
