package com.socialportal.application.security.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginWebController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

}
