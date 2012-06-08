web: target/start -Dhttp.port=$PORT -DapplyEvolutions.default=true -Dconfig.file=conf/prod.conf
dailyregiondigestemailjob: java -DapplyEvolutions.default=true -Dconfig.file=conf/prod.conf -cp "target/staged/*" jobs.DailyRegionDigestEmailJob .
