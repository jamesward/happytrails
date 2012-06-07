package happytrails

import grails.test.mixin.*

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
