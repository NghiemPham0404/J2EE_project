package com.example.J2EE_project.DTOs;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.Role;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {
    private Integer id;

    private String name;

    private Date birthDate;

    private String username;

    private String email;

    private boolean active;

    private Role role;

    @Getter(AccessLevel.NONE)
    private String password;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.name = account.getName();
        this.birthDate = account.getBirthDate();
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.active = account.isActive();
        this.role = account.getRole();
        this.password = account.getPassword();
    }

    public Account toAccount(){
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setBirthDate(birthDate);
        account.setUsername(username);
        account.setEmail(email);
        account.setActive(active);
        account.setRole(role);
        account.setPassword(password);
        return account;
    }
}
