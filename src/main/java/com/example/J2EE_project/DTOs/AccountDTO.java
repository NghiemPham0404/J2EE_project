package com.example.J2EE_project.DTOs;

import com.example.J2EE_project.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    @Getter
    private Integer id;

    @Getter
    private String name;

    @Getter
    private String username;

    private String password;

    @Getter
    private String email;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.name = account.getName();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
    }
}
