package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.DTO.ResponseDTO;
import com.Airways.BAirways.Database.Initializer;
import com.Airways.BAirways.Repositary.RegisteredUserRepo;
import com.Airways.BAirways.Service.RegisteredUserService;
import com.Airways.BAirways.Utility.Exeptions.DataTypeExeption;
import com.Airways.BAirways.Utility.QueryStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController

public class RegisteredUserController {
    @Autowired
    private RegisteredUserService registeredUserService;
    @GetMapping(path="/check")
    public String check() throws ClassNotFoundException, DataTypeExeption, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return "Authorization Working";

    }
    @RequestMapping(path="/registerdUser/saveUser", method = RequestMethod.PUT)
    @CrossOrigin
    public ModelAndView saveUser(@RequestBody RegisteredUserDTO registeredUserDTO, BindingResult bindingResult, HttpServletRequest request) {
        System.out.println("RequestGot");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        RegisteredUserRepo registeredUserRepo = new RegisteredUserRepo();
        String encoded_password = passwordEncoder.encode(registeredUserDTO.getPassword());
        registeredUserDTO.setPassword(encoded_password);
        registeredUserDTO.setType_id(5);
        registeredUserRepo.insertRecord(registeredUserDTO);
        ResponseDTO responseDTO = new ResponseDTO(QueryStatus.SUCCESS.toString(),"Working",registeredUserDTO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
