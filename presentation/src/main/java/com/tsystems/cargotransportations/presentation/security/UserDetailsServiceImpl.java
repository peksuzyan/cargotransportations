package com.tsystems.cargotransportations.presentation.security;

import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains single method that is needed to Spring Security.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Injected instance of a user service.
     */
    @Autowired
    private UserService userService;

    /**
     * Custom method implementation returns Spring's user by email instead of username.
     * @param s user email
     * @return user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.tsystems.cargotransportations.entity.User user = userService.getUserByEmail(s);
        System.out.println("!!! Is user found? User:" + user);
        if (user == null) throw new UsernameNotFoundException("!!!!custom message! user isn't found!");
        System.out.println("!!! PASSWORD? User:" + user.getPassword());
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    /**
     * Builds Spring user object for authentication aims.
     * @param user user entity
     * @param authorities authorities list of a user entity
     * @return Spring user object with details
     */
    private UserDetails buildUserForAuthentication(
            com.tsystems.cargotransportations.entity.User user,
            List<GrantedAuthority> authorities) {
        return new User(user.getEmail(),
                user.getPassword(), true, true, true, true, authorities);
    }

    /**
     * Builds a list with granted authorities by user role.
     * @param userRole user role
     * @return a list with granted authorities
     */
    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {
        List<GrantedAuthority> result = new ArrayList<>();
        System.out.println(userRole.toString());
        result.add(new SimpleGrantedAuthority("ROLE_" + userRole.toString()));
        return result;
    }

}
