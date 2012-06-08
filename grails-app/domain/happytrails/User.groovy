package happytrails

class User {

    transient springSecurityService

    String username
    String password
    String name
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Date creationDate

    static constraints = {
        username email: true, blank: false, unique: true
        password blank: false
        name blank: false
        creationDate nullable: true
        regionSubscriptions cascade:"all,delete-orphan"
    }

    static hasMany = [regionSubscriptions:RegionSubscription]

    static mapping = {
        table 'users'
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
        creationDate = new Date()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
