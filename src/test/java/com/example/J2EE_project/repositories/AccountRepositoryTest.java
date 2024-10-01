package com.example.J2EE_project.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.J2EE_project.models.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
    AccountRepository accountRepository;
    Account account;

    @BeforeEach
	void setUp() {
        account = new Account();
        account.setUsername("admin");
        account.setPassword("admin");
        accountRepository.save(account);
	}

	@AfterEach
	void tearDown() {
		account = null;
		accountRepository.deleteAll();
	}

	//TODO : Test case success
	@Test
	void listAccount() {
		List<Account> accounts = accountRepository.findAll();
        Account account1 = accounts.get(0);
        assertThat(account1.getId()).isEqualTo(account.getId());
		System.out.println(account1.getId());
	}

}
