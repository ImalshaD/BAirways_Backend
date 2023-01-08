package com.Airways.BAirways.Config;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserMapper {

    public static RegisteredUserDTO map(UserDetails user){
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO();
        registeredUserDTO.setUser_name(user.getUsername());
        registeredUserDTO.setPassword(user.getPassword());
        Collection<? extends GrantedAuthority> list = user.getAuthorities();
        boolean adimn = false;
        for (GrantedAuthority grantedAuthority : list){
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                adimn = true;
                break;
            }
        }
        if(adimn) {
            registeredUserDTO.setType_id(1);
        }else{
            registeredUserDTO.setType_id(5);
        }
        return registeredUserDTO;
    }
    public static UserDetails deMap(RegisteredUserDTO dto){
        User.UserBuilder builder = User.withUsername(dto.getUser_name());
        builder.password(dto.getPassword());
        if (dto.getUser_id()==1){
            builder.roles("ADMIN");
        }else{
            builder.roles("USER");
        }
        return builder.build();
    }
}
