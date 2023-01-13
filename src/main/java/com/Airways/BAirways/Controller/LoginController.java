package com.Airways.BAirways.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @GetMapping(path="/login")
    public String login(){
        return "login";
    }
    @GetMapping(path="/home")
    public String home(){
        return "home";
    }

    @GetMapping(path="/mybooking")
    public String userbooking(){
        return "userBookings";
    }
}
