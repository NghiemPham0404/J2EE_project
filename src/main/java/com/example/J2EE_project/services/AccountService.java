package com.example.J2EE_project.services;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountService implements serviceInterface<Account, Integer>{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public String create(Account account) {
        accountRepository.save(account);
        return "account created successfully";
    }

    @Override
    public String update(Account account) {
        accountRepository.save(account);
        return "account updated successfully";
    }

    @Override
    public String delete(Integer id) {
        accountRepository.deleteById(id);
        return "account deleted successfully";
    }

    @Override
    public List<Account> listAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> listAllNewest() {
        List<Account> accountList = accountRepository.findAll();
        accountList.sort(Comparator.comparingInt(Account::getId));
        return accountList;
    }

    @Override
    public Page<Account> listAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> listAllNewest(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account get(Integer id) {
        Account account = accountRepository.findById(id).get();
        System.out.println("Ten la : "+ account.getName());
        return account;
    }
}
