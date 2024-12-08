package com.example.J2EE_project.services;

import com.example.J2EE_project.exceptions.LoginException;
import com.example.J2EE_project.exceptions.NotAuthorizedException;
import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoService {

    @Autowired
    private AccountRepository accountRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;

    public Account getPersonalInfo(int id, String username){
        Account account =  accountRepository.findById(id).get();
        if(account.getUsername().equals(username)){
            return account;
        }else{
            throw new NotAuthorizedException(NotAuthorizedException.NOT_AUTHORIZED);
        }
    }

    public Account getPersonalInfoByUsername(String extractedUsername) {
       return accountRepository.login(extractedUsername);
    }

    public String changePassword(int id,String rawPassword){
        String hashedPassword = passwordEncoder.encode(rawPassword);
        accountRepository.updatePassword(id, hashedPassword);
        return "Password changed successfully";
    }

    public boolean validateEmail(String email){
        return accountRepository.validEmail(email) != null;
    }

    public String changeEmail(int id, String email){
        accountRepository.updateEmail(id, email);
        return "Email changed successfully";
    }

    public Account login(String username, String rawPassword){
        Account account = accountRepository.login(username);
        if(account == null){
            throw new LoginException(LoginException.NON_EXIST_USER);
        }
        if(!passwordEncoder.matches(rawPassword, account.getPassword())){
            throw new LoginException(LoginException.WRONG_PASSWORD);
        }
        return account;
    }

    public String isAdmin(String username){
           Account account = accountRepository.login(username);
           return account.getRole().getName().equals("Admin") ? "Yes" : "No";
    }
}
