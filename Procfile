web: target/start -Dhttp.port=$PORT -DapplyEvolutions.default=true -Ddb.default.driver=org.postgresql.Driver -Ddb.default.url=${DATABASE_URL}
dailyregiondigestemailjob: java -DapplyEvolutions.default=true -cp -Ddb.default.driver=org.postgresql.Driver -Ddb.default.url=${DATABASE_URL} "target/staged/*" jobs.DailyRegionDigestEmailJob .
