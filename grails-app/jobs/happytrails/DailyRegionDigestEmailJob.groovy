package happytrails

class DailyRegionDigestEmailJob {
    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        return "Job Run!"
    }
}
