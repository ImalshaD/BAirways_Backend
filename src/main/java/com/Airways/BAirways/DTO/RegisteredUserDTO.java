package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisteredUserDTO {
    private int user_id;
    private String user_name;
    private String first_name;
    private String last_name;
    private String password;
    private int type_id;
    private String email;
}
