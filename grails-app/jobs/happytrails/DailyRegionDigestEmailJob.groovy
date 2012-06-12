package happytrails

import org.grails.comments.Comment

class DailyRegionDigestEmailJob {
    def mailService

    static triggers = {
        //simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name:'cronTrigger', startDelay:10000, cronExpression: '0 0 7 ? * MON-FRI' // 7AM Mon-Fri
    }

    def execute() {
        List<RegionUserDigest> digests = getRegionUserDigests()
        for (digest in digests) {

            String message = createMessage(digest)

            println "Sending digest email to " + digest.user.username
            mailService.sendMail {
                to digest.getUser().username
                subject "Updates from Über Tracks " + digest.regions
                body message
            }

            RegionSubscription subscription = digest.regionSubscription
            subscription.lastSent = new Date()
            subscription.save()
        }
    }

    def String createMessage(RegionUserDigest digest) {
        StringBuilder sb = new StringBuilder()
        sb.append("Hello " + digest.getUser().getName() + ",\n\n")
        sb.append("Below you'll find updates for the regions you've subscribed to on bike.ubertracks.com.")

        for (region in digest.getRegions()) {
            sb.append("\n\n" + region.name)

            region.getRoutes().each {Route route ->

                List<Comment> newComments = []
                for (comment in route.comments) {
                    if (digest.lastSent == null || comment.getCreationDate().after(digest.getLastSent())) {
                        newComments.add(comment)
                    }
                }

                def numComments = newComments.size()
                if (numComments > 0) {
                    sb.append("\n  " + route.getName())
                    sb.append(" (" + numComments + " new " + ((numComments == 1) ? "comment" : "comments") + ")")
                    for (comment in newComments) {
                        sb.append "\n    \"" + comment.body + "\" --" + comment.poster
                    }
                }
            }
        }

        sb.append("\n\nThank you for subscribing to Über Tracks!\n\nhttp://bike.ubertracks.com")
        sb.toString()
    }

    def List<RegionUserDigest> getRegionUserDigests() {
        List<RegionUserDigest> digests = []

        for (user in User.findAll()) {
            println "Looking for subscriptions for " + user.getName()
            RegionUserDigest digest = new RegionUserDigest(user)

            for (regionSubscription in user.getRegionSubscriptions()) {
                Region region = regionSubscription.getRegion()

                println "Found subscription for " + region

                List newComments = new ArrayList()
                for (Route route : region.getRoutes()) {
                    if (route.getComments())
                        println "Found route " + route.getName() + ", comments: " + route.getComments().size()
                    for (comment in route.comments) {
                        if (regionSubscription.getLastSent() == null ||
                                comment.getCreationDate().after(regionSubscription.getLastSent())) {
                            println("Adding new comment: " + comment)
                            newComments.add(comment)
                        }
                    }
                }

                if (newComments.size() > 0) {
                    digest.addRegion(region)
                    digest.setLastSent(regionSubscription.getLastSent())
                    digest.regionSubscription = regionSubscription
                    digests.add(digest)
                }
            }
        }

        return digests
    }

    class RegionUserDigest {
        User user
        List<Region> regions = []
        Date lastSent
        RegionSubscription regionSubscription

        public RegionUserDigest(User user) {
            this.user = user
        }

        public addRegion(Region region) {
            regions.add(region)
        }
    }
}
