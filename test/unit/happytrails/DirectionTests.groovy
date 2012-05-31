package happytrails



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Direction)
class DirectionTests {

    void testConstraints() {
        def direction = new Direction(stepNumber: 1, instruction: "Go Right")
        mockForConstraintsTests(Direction, [direction])

        // validation should fail if required properties are null
        def emptyInstruction = new Direction(stepNumber: 1)
        assert !emptyInstruction.validate()
        assert "nullable" == emptyInstruction.errors["instruction"]
    }
}
