package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Service.RegisteredUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/booking")
public class BookingController {
    private RegisteredUserService registeredUserService = new RegisteredUserService();
    @PutMapping(path="/newBooking")
    public void newBooking(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        RegisteredUserDTO dto = registeredUserService.getUserByuserName(username);
        System.out.println(dto.getUser_id());
    }
}
