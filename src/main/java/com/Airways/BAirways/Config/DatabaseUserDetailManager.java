package com.Airways.BAirways.Config;

import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Repositary.RegisteredUserRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

public class DatabaseUserDetailManager implements UserDetailsManager, UserDetailsPasswordService {
    protected final Log logger = LogFactory.getLog(this.getClass());
    RegisteredUserRepo registeredUserRepo;
    private SecurityContextHolderStrategy securityContextHolderStrategy;
    private AuthenticationManager authenticationManager;

    public DatabaseUserDetailManager() {
        super();
        registeredUserRepo = new RegisteredUserRepo();
    }

    @Override
    public void createUser(UserDetails user) {
        registeredUserRepo.insertRecord(UserMapper.map(user));
    }

    @Override
    public void updateUser(UserDetails user) {
        RegisteredUserDTO oldDTO = registeredUserRepo.getByUserName(user.getUsername());
        RegisteredUserDTO newDTO = UserMapper.map(user);
        registeredUserRepo.updateRecord(oldDTO,newDTO);
    }

    @Override
    public void deleteUser(String username) {
        registeredUserRepo.deleteByUserName(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = this.securityContextHolderStrategy.getContext().getAuthentication();
        if (currentUser == null) {
            throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
        }else {
            String username = currentUser.getName();
            this.logger.debug(LogMessage.format("Changing password for user '%s'", username));

            if (this.authenticationManager != null) {
                this.logger.debug(LogMessage.format("Reauthenticating user '%s' for password change request.", username));
                this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(username, oldPassword));
            } else {
                this.logger.debug("No authentication manager set. Password won't be re-checked.");
            }
            RegisteredUserDTO oldDTo = registeredUserRepo.getByUserName(username);
            RegisteredUserDTO newDTo = new RegisteredUserDTO();
            newDTo.setUser_id(oldDTo.getUser_id());
            newDTo.setUser_name(oldDTo.getUser_name());
            newDTo.setPassword(newPassword);
            registeredUserRepo.updateRecord(oldDTo,newDTo);
        }
    }

    @Override
    public boolean userExists(String username) {
        return registeredUserRepo.existsByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserMapper.deMap(registeredUserRepo.getByUserName(username));
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        RegisteredUserDTO oldDTo = registeredUserRepo.getByUserName(user.getUsername());
        RegisteredUserDTO newDTo = new RegisteredUserDTO();
        newDTo.setUser_id(oldDTo.getUser_id());
        newDTo.setUser_name(oldDTo.getUser_name());
        newDTo.setPassword(newPassword);
        registeredUserRepo.updateRecord(oldDTo,newDTo);
        oldDTo.setPassword(user.getPassword());
        return UserMapper.deMap(oldDTo);
    }

    public void setSecurityContextHolderStrategy(SecurityContextHolderStrategy securityContextHolderStrategy) {
        Assert.notNull(securityContextHolderStrategy, "securityContextHolderStrategy cannot be null");
        this.securityContextHolderStrategy = securityContextHolderStrategy;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
