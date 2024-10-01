package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.repositories.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements serviceInterface<Account, Integer>{

    AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String create(Account account) {
        return "";
    }

    @Override
    public String update(Account account) {
        return "";
    }

    @Override
    public String delete(Integer id) {
        return "";
    }

    @Override
    public List<Account> listAll() {
        return List.of();
    }

    @Override
    public List<Account> listAllNewest() {
        return List.of();
    }

    @Override
    public Page<Account> listAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Account> listAllNewest(Pageable pageable) {
        return null;
    }

    @Override
    public Account get(Integer id) {
        Account account = accountRepository.findById(id).get();
        System.out.println("Ten la : "+ account.getName());
        return account;
    }
}
