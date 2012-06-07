package happytrails

class DailyRegionDigestEmailJobTests {

    void testExecute() {
        def dailyEmailJob = new DailyRegionDigestEmailJob()

        assert dailyEmailJob.execute() == "Job Run!"
    }
}
