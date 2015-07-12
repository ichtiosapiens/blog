package com.companyportal.inftrastructure.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password")String password){


        return "home";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){

        return "login";
    }

}
