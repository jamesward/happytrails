web: target/universal/stage/bin/happytrails -Dhttp.port=${PORT} -Dconfig.file=conf/prod.conf ${JAVA_OPTS}
dailyregiondigestemailjob: java -Dhttp.port=${PORT} -Dconfig.file=conf/prod.conf -cp "target/universal/stage/lib/*" jobs.DailyRegionDigestEmailJob .
