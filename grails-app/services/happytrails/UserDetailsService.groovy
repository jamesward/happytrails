package happytrails

import groovy.transform.CompileStatic
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserDetailsService
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.plugin.cache.Cacheable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@CompileStatic
class UserDetailsService implements GrailsUserDetailsService {

	/**
	 * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
	 * one role, so we give a user with no granted roles this one which gets
	 * past that restriction but doesn't grant anything.
	 */
	static final List<GrantedAuthorityImpl> NO_ROLES = [new GrantedAuthorityImpl(SpringSecurityUtils.NO_ROLE)]

	UserDetails loadUserByUsername(String username, boolean loadRoles)
	throws UsernameNotFoundException {
		return loadUserByUsername(username)
	}

    @Cacheable("user")
	UserDetails loadUserByUsername(String _username) throws UsernameNotFoundException {

		UserDetails userDetails = null

		User.withSession {

			User user = User.findWhere(username: _username) as User

			if (!user) throw new UsernameNotFoundException(
					'User not found', _username)

			def authorities = user.authorities.collect { Role r ->
				new GrantedAuthorityImpl(r.authority)
			}

			userDetails = new CustomUserDetails(
					user.username,
					user.password,
					user.enabled,
					!user.accountExpired,
					!user.passwordExpired,
					!user.accountLocked,
					(Collection<GrantedAuthority>)(authorities ?: NO_ROLES),
					user.id,
					user.name)
		}

		return userDetails
	}
}