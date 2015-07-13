package com.socialportal.application.security;

import com.socialportal.application.security.domain.Credentials;
import com.socialportal.application.security.domain.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LocalAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Credentials credentials = credentialsRepository.findByUsernameAndPassword((String) authentication.getPrincipal(), (String) authentication.getCredentials());
        if(credentials != null){
            return authentication;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

