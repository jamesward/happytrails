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
        sb.append "Hello " + digest.user.name + ",\n\n"
        sb.append "Below you'll find updates for the regions you've subscribed to on bike.ubertracks.com."

        for (region in digest.regions) {
            sb.append("\n\n" + region.name)

            region.routes.each {Route route ->

                List<Comment> newComments = []
                for (comment in route.comments) {
                    if (digest.lastSent == null || comment.dateCreated.after(digest.lastSent)) {
                        newComments.add(comment)
                    }
                }

                def numComments = newComments.size()
                if (numComments > 0) {
                    sb.append "\n  " + route.name
                    sb.append " (" + numComments + " new " + ((numComments == 1) ? "comment" : "comments") + ")"
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
            println "Looking for subscriptions for " + user.name
            RegionUserDigest digest = new RegionUserDigest(user)

            for (regionSubscription in user.regionSubscriptions) {
                Region region = regionSubscription.region

                println "Found subscription for " + region

                List newComments = new ArrayList()
                for (Route route : region.routes) {
                    if (route.comments) {
                        println "Found route " + route.name + ", comments: " + route.comments.size()
                        for (comment in route.comments) {
                            if (regionSubscription.lastSent == null ||
                                    comment.dateCreated.after(regionSubscription.lastSent)) {
                                println("Adding new comment: " + comment.body)
                                newComments.add(comment)
                            }
                        }
                    }
                }

                if (newComments.size() > 0) {
                    digest.addRegion(region)
                    digest.lastSent = regionSubscription.getLastSent()
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
