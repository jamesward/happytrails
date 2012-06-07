package happytrails

class RegionSubscription {

    static constraints = {
        lastSent nullable: true
    }

    public RegionSubscription(User user, Region region) {
        this.user = user
        this.region = region
    }

    User user
    Region region
    Date lastSent
}
