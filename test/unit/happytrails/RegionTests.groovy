package happytrails

import grails.test.mixin.TestFor

@TestFor(Region)
class RegionTests {

    void testConstraints() {
        def montana = new Region(name: "Western Montana")
        mockForConstraintsTests(Region, [montana])

        // validation should fail if required properties are null
        def region = new Region()
        assert !region.validate()
        assert "nullable" == region.errors["name"]

        // verify special characters not allowed
        region = new Region(name: ")(*)(\$")
        assert !region.validate()
        assert "matches" == region.errors["name"]

        // verify numbers are allowed
        region = new Region(name: "Up on the back 40", seoName: "up-on-the-back-40")
        assert region.validate()
    }
}
