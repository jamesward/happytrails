package happytrails

import org.hibernate.connection.UserSuppliedConnectionProvider

class DailyRegionDigestEmailJob {
    def mailService

    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name:'cronTrigger', startDelay:10000, cronExpression: '0 0 7 ? * MON-FRI' // 7AM Mon-Fri
    }

    def execute() {
        List<RegionUserDigest> digests = getRegionUserDigests()
        for (digest in digests) {

            StringBuilder sb = new StringBuilder()
            sb.append("Hello " + digest.getUser().getName() + ",\n")
            sb.append("This email contains new updates for the regions you've subscribed to on bike.ubertracks.com.")

            for (region in digest.getRegions()) {
                sb.append("\n\nRegion Name: " + region.name)

                for (Route route : region.getRoutes()) {
                    sb.append("\nRoute: " + route.getName())
                    for (comment in route.getComments()) {
                        sb.append("\n\t").append(comment.getValue())
                        sb.append(" -- ").append(comment.getUser().getName())
                    }
                }
            }

            sb.append("Thank you for subscribing to Über Tracks!")

            mailService.sendMail {
                to digest.getUser().getUsername()
                subject "Updates from Über Tracks " + digest.getRegions()
                body sb.toString()
            }
        }
    }

    def List<RegionUserDigest> getRegionUserDigests() {
        List<RegionUserDigest> digests = []

        for (user in User.findAll()) {
            RegionUserDigest digest = new RegionUserDigest(user)

            for (regionSubscription in user.getRegionSubscriptions()) {
                Region region = regionSubscription.getRegion()
                List newComments = []
                for (Route route : region.getRoutes()) {
                    for (comment in route.getComments()) {
                        newComments.add(comment)
                    }
                }
            }

            if (newComments.size() > 0) {
                digest.addRegion(region)
                digests.add(digest)
            }
        }

        return digests
    }

    class RegionUserDigest {
        User user
        List<Region> regions = []

        public RegionUserDigest(User user) {
            this.user = user
        }

        public addRegion(Region region) {
            regions.add(region)
        }
    }
}
