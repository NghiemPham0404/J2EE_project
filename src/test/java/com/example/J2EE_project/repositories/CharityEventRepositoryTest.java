package com.example.J2EE_project.repositories;

import com.example.J2EE_project.models.Account;
import com.example.J2EE_project.models.CharityEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CharityEventRepositoryTest {
    @Autowired
    CharityEventRepository charityEventRepository;
    CharityEvent charityEvent;

    @BeforeEach
	void setUp() {
        charityEvent = new CharityEvent();
        charityEvent.setName("Article 1");
        charityEvent.setStartTime(new Date(123, 11, 12));
        charityEventRepository.save(charityEvent);
	}

	@AfterEach
	void tearDown() {
        charityEvent = null;
		charityEventRepository.deleteAll();
	}

    @Test
    public void findByID() {
        CharityEvent charityEvent1 = charityEventRepository.findById(UUID.fromString(charityEvent.getId().toString())).get();
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent1.getStartTime()));
        assertThat(charityEvent1.getName()).isEqualTo(charityEvent.getName());
    }

    @Test
    public void findCharityEventWithOutPost(){
        CharityEvent charityEvent1 = charityEventRepository.findAllCharityEventWithoutPost(PageRequest.of(0,10)).getContent().get(0);
        assertThat(charityEvent1.getName()).isEqualTo(charityEvent.getName());
    }
}
