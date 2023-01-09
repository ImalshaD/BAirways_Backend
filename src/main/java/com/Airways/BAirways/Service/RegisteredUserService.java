package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.RegisteredUser;
import com.Airways.BAirways.Repositary.RegisteredUserRepo;
import com.Airways.BAirways.Utility.QueryStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {
    private RegisteredUserRepo registeredUserRepo = new RegisteredUserRepo();
    public QueryStatus saveUser(RegisteredUserDTO registeredUserDTO){
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
//        String createQ = RegisteredUser.onCreate();
//        jdbcTemplate.execute(null);
        if (1==2){
            return QueryStatus.DUPLICATE;
        }else{
            String query="INSERT INTO Registered_User (user_name,first_name,last_name,password,type_id) VALUES (?,?,?,?,?);";
            System.out.println(registeredUserDTO.getType_id());
            int success=jdbcTemplate.update(query,
                    registeredUserDTO.getUser_name(),
                    registeredUserDTO.getFirst_name(),
                    registeredUserDTO.getLast_name(),
                    registeredUserDTO.getPassword(),
                    registeredUserDTO.getType_id()
                    );
            if (success==1){
                return QueryStatus.SUCCESS;
            }else{
                return QueryStatus.FAILED;
            }
        }
    }
    public RegisteredUserDTO getUserByuserName(String userName){
        return registeredUserRepo.getByUserName(userName);
    }
}
