package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.AccountDTO;
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
public class AccountService implements serviceInterface<AccountDTO, Integer>{

    @Autowired
    AccountRepository accountRepository;
    /**
     * Thêm mới một tài khoản
     */
    @Override
    public String create(AccountDTO accountDTO) {
        Account account = accountDTO.toAccount();
        accountRepository.save(account);
        return "account created successfully";
    }

    /**
     *Cập nhật thông tin tài khoản
     */
    @Override
    public String update(AccountDTO accountDTO) {
        Account account = accountDTO.toAccount();
        accountRepository.save(account);
        return "account updated successfully";
    }

    /**
     *Xóa thông tin tài khoản
     */
    @Override
    public String delete(Integer id) {
        accountRepository.deleteById(id);
        return "account deleted successfully";
    }

    /**
     *Lấy tài khoản theo id
     */
    @Override
    public AccountDTO get(Integer id) {
        Account account = accountRepository.findById(id).get();
        AccountDTO accountDTO = new AccountDTO(account);
        System.out.println("Ten la : "+ account.getName());
        return accountDTO;
    }

    public Page<AccountDTO> listAllAccount(int id, int page){
        Pageable pageable = PageRequest.of(page, 10);
        return accountRepository.listAllAccount(id, pageable).map(AccountDTO::new);
    }

    /**
     *Lấy tất cả tài khoản theo tên
     */
    public Page<AccountDTO> listByName(String name, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return accountRepository.findByNameIgnoreCase(name, pageable).map(AccountDTO::new);
    }

     /**
     *Lấy tất cả tài khoản theo role
     */
    public Page<AccountDTO> listByRole(int role_id, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return accountRepository.findByRole(role_id, pageable).map(AccountDTO::new);
    }

    /**
     *Khóa tài khoản
     */
    public void deactivateAccount(int id) {
        accountRepository.updateActiveStatus(id, false);
    }

    /**
     *Mở khóa tài khoản
     */
    public void activateAccount(int id) {
        accountRepository.updateActiveStatus(id, true);
    }

    @Override
    public List<AccountDTO> listAll() {
        return null;
    }

    @Override
    public List<AccountDTO> listAllNewest() {
        return null;
    }

    @Override
    public Page<AccountDTO> listAll(int page) {
       return null;
    }

    @Override
    public Page<AccountDTO> listAllNewest(int page) {
        return null;
    }
}
