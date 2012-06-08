package happytrails

class RegionSubscriptionTests extends GroovyTestCase {

    void testSave() {
        assert RegionSubscription.findAll().size() == 1

        User user = new User(username: "foo@gmail.com", password: "bar",
                name: "Foo Bar", enabled: true).save(failOnError: true)
        Region region = new Region(name: "Swan Valley").save(failOnError: true)

        RegionSubscription subscription = new RegionSubscription(user, region)
        subscription.save(failOnError: true)

        assert RegionSubscription.findAll().size() == 2
    }
}
