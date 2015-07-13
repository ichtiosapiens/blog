package com.socialportal.application.security;

import com.socialportal.domain.user.model.User;
import com.socialportal.domain.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationDispatcher extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocalAuthenticationProvider localAuthenticationProvider;

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return localAuthenticationProvider.authenticate(authentication);
//    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;

        authentication = (UsernamePasswordAuthenticationToken) localAuthenticationProvider.authenticate(authentication);

        if (authentication == null) {
            throw new InternalAuthenticationServiceException("Authentication error");
        }

        try {
            User user = userRepository.findByUsername(username);
            user.setPassword((String) authentication.getCredentials());
            loadedUser = user;
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("Authentication error");
        }
        return loadedUser;
    }
}