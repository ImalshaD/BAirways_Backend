package com.Airways.BAirways.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path="/admin")
public class AdminController {
    @GetMapping(path="/adminview")
    public ModelAndView adminview(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage.html");
        return modelAndView;
    }
}
