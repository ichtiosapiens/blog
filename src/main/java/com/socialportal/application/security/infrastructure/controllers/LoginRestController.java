package com.socialportal.application.security.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginRestController {

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password")String password){
        return "home";
    }

}
