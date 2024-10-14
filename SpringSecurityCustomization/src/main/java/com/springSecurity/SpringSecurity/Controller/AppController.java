package com.springSecurity.SpringSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "Welcome to the public endpoint!";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "Welcome to the admin page!";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "Welcome to the user page!";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
