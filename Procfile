web: target/start -Dhttp.port=$PORT -DapplyEvolutions.default=true
dailyregiondigestemailjob: java -DapplyEvolutions.default=true -cp "target/staged/*" jobs.DailyRegionDigestEmailJob .
