package com.example.J2EE_project.services;

import com.example.J2EE_project.exceptions.InvalidPageException;
import com.example.J2EE_project.exceptions.NotFoundException;
import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.Role;
import com.example.J2EE_project.repositories.AccountRepository;
import com.example.J2EE_project.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Thêm mới một tài khoản
     */
    public String create(Account account) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        account.setPassword(passwordEncoder.encode(formatter.format(account.getBirthDate())));
        accountRepository.save(account);
        return "account created successfully";
    }

    /**
     * Thêm mới một tài khoản
     */
    public String create(String name, Date birthDate, String username, String email, boolean active, int role_id) {
        Account accountDTO = new Account();
        accountDTO.setName(name);
        accountDTO.setBirthDate(birthDate);
        accountDTO.setUsername(username);
        accountDTO.setEmail(email);
        accountDTO.setActive(active);

        Role role = roleRepository.findById(role_id).get();
        accountDTO.setRole(role);

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        accountDTO.setPassword(passwordEncoder.encode(formatter.format(accountDTO.getBirthDate())));
        accountRepository.save(accountDTO);
        return "account created successfully";
    }

    /**
     *Cập nhật thông tin tài khoản
     */
    public String update(Account accountDTO) {
        accountRepository.save(accountDTO);
        return "account updated successfully";
    }

    /**
     *Xóa thông tin tài khoản
     */
    public String delete(Integer id) {
        accountRepository.deleteById(id);
        return "account deleted successfully";
    }

    /**
     *Lấy tài khoản theo id
     */
    public Account get(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.NOT_FOUND));
        return account;
    }

    public Page<Account> listAllAccount(int id, int page){
        if(page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10);
        Page<Account> accountPage = accountRepository.listAllAccount(id, pageable);
        if(page + 1 > accountPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return accountPage;
    }

    /**
     *Lấy tất cả tài khoản theo tên
     */
    public Page<Account> listByName(String name, int page) {
        if(page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10);
        Page<Account> accountPage = accountRepository.findByNameIgnoreCase(name, pageable);
        if(page + 1 > accountPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return accountPage;
    }

     /**
     *Lấy tất cả tài khoản theo role
     */
    public Page<Account> listByRole(int role_id, int page) {
        if(page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10);
        Page<Account> accountPage = accountRepository.findByRole(role_id, pageable);
        if(page + 1 > accountPage.getTotalPages()) throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return accountPage;
    }

    /**
     *Khóa tài khoản
     */
    public void deactivateAccount(int id) {
        get(id); // đảm bảo tồn tại account với id
        accountRepository.updateActiveStatus(id, false);
    }

    /**
     *Mở khóa tài khoản
     */
    public void activateAccount(int id) {
        get(id); // đảm bảo tồn tại account với id
        accountRepository.updateActiveStatus(id, true);
    }
}
