package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.AccountDTO;
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

    public AccountDTO getPersonalInfo(int id){
        return new AccountDTO(accountRepository.findById(id).get());
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
        String hashedPassword = passwordEncoder.encode(rawPassword);
        Account account = accountRepository.login(username);
        if(account == null) return null;
        if(!passwordEncoder.matches(hashedPassword, account.getPassword())) return null;
        return new AccountDTO(account);
    }

}
