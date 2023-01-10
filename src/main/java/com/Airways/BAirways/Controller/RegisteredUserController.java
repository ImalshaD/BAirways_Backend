package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Service.RegisteredUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class RegisteredUserController {
    private RegisteredUserService registeredUserService = new RegisteredUserService() ;
    @PutMapping(path="/registerdUser/saveUser")
    @CrossOrigin
    public ModelAndView saveUser(@RequestBody RegisteredUserDTO registeredUserDTO) {
        int res = registeredUserService.saveUser(registeredUserDTO);
        ModelAndView modelAndView = new ModelAndView();
        if (res==1) {
            modelAndView.setViewName("login");
        }else{
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
