package net.nelevelup.proj.service;

import net.nelevelup.proj.entity.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsServiceImpl;

    public String findLoggedInUsername(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userDetails instanceof UserDetails)
            return ((UserDetails)userDetails).getUsername();
        return null;
    }
    public User autoLogin(String username, String password){
        UserDetails userDetails;
        try {
            userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        }catch (Exception e){
            return null;
        }
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
        }catch (Exception e){

        }
        if(token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
            return new User(username, (Collection<GrantedAuthority>) userDetails.getAuthorities());
        }
        return null;
    }
    public void clearContext(){
        SecurityContextHolder.clearContext();
    }
}
