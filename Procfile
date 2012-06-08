web: target/start -Dhttp.port=${PORT} -Dconfig.file=conf/prod.conf
dailyregiondigestemailjob: java -Dhttp.port=${PORT} -Dconfig.file=conf/prod.conf -cp "target/staged/*" jobs.DailyRegionDigestEmailJob .
