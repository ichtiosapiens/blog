package com.companyportal.user.domain;

import com.companyportal.inftrastructure.security.Credentials;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String userName;
    private String firstName;
    private String lastName;

    private Credentials credentials;

}
