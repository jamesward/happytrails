package happytrails

class DailyRegionEmailJobTests extends GroovyTestCase {
    def DailyRegionDigestEmailJob job

    void setUp() {
        job = new DailyRegionDigestEmailJob()
    }

    void testSubscriptions() {
        def subscriptions = job.getRegionUserDigests()
        assert subscriptions.size() >= 1
        String message = job.createMessage(subscriptions.get(0))
        assert message != null
        println message
    }
}
