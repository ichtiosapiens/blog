package com.companyportal.inftrastructure.security;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Credentials {

    private String username;
    private String password;

    private Set<Role> roles;

}
