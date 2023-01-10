package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.RegisteredUser;
import com.Airways.BAirways.Repositary.RegisteredUserRepo;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {
    private RegisteredUserRepo registeredUserRepo = new RegisteredUserRepo();
    public int saveUser(RegisteredUserDTO registeredUserDTO){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        RegisteredUserRepo registeredUserRepo = new RegisteredUserRepo();
        String encoded_password = passwordEncoder.encode(registeredUserDTO.getPassword());
        registeredUserDTO.setPassword(encoded_password);
        registeredUserDTO.setType_id(5);
        return registeredUserRepo.insertRecord(registeredUserDTO);


    }
    public RegisteredUserDTO getUserByuserName(String userName){
        return registeredUserRepo.getByUserName(userName);
    }
}
