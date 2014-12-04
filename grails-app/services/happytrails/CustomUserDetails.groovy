package happytrails

import grails.plugin.springsecurity.userdetails.GrailsUser
import groovy.transform.CompileStatic

import org.springframework.security.core.GrantedAuthority

@CompileStatic
class CustomUserDetails extends GrailsUser {

   final String fullName

    CustomUserDetails(String username, String password, boolean enabled,
                 boolean accountNonExpired, boolean credentialsNonExpired,
                 boolean accountNonLocked,
                 Collection<GrantedAuthority> authorities,
                 long id, String fullName) {
      super(username, password, enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked, authorities, id)

      this.fullName = fullName
   }
}
