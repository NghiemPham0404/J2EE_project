package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.AccountDTO;
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

    public AccountDTO getPersonalInfo(int id, String username){
        AccountDTO accountDTO =  new AccountDTO(accountRepository.findById(id).get());
        if(accountDTO.getUsername().equals(username)){
            return accountDTO;
        }else{
            throw new NotAuthorizedException(NotAuthorizedException.NOT_AUTHORIZED);
        }
    }

    public String changePassword(int id,String rawPassword){
        String hashedPassword = passwordEncoder.encode(rawPassword);
        accountRepository.updatePassword(id, hashedPassword);
        return "Password changed successfully";
    }

    public String changeEmail(int id, String email){
        accountRepository.updateEmail(id, email);
        return "Email changed successfully";
    }

    public AccountDTO login(String username, String rawPassword){
        Account account = accountRepository.login(username);
        if(account == null){
            throw new LoginException(LoginException.NON_EXIST_USER);
        }
        if(!passwordEncoder.matches(rawPassword, account.getPassword())){
            throw new LoginException(LoginException.WRONG_PASSWORD);
        }
        return new AccountDTO(account);
    }

}
