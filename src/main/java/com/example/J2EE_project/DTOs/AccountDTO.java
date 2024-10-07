package com.example.J2EE_project.DTOs;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {
    private Integer id;

    private String name;

    private String username;

    private String email;

    private boolean active;

    private Role role;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.name = account.getName();
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.active = account.isActive();
        this.role = account.getRole();
    }

    public Account toAccount(){
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setUsername(username);
        account.setEmail(email);
        account.setActive(active);
        account.setRole(role);
        return account;
    }
}
