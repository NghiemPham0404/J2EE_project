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
import java.util.List;
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
        charityEvent.setStartTime(new Date());
        charityEvent.setEndTime(new Date(124, 11, 31));
        charityEventRepository.save(charityEvent);
    }


    @AfterEach
    void tearDown() {
        charityEvent = null;
        charityEventRepository.deleteAll();
    }

    /**
     * TODO : tìm các sự kiện từ thiện theo ID
     */
    @Test
    public void findByID() {
        CharityEvent charityEvent1 = charityEventRepository.findById(UUID.fromString(charityEvent.getId().toString())).get();
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent1.getStartTime()));
        assertThat(charityEvent1.getName()).isEqualTo(charityEvent.getName());
    }

    /**
     * TODO : tìm các sự kiện từ thiện theo tên
     */
    @Test
    public void findByName() {
        CharityEvent charityEvent1 = charityEventRepository.findByNameContaining(PageRequest.of(0, 10), "Article").getContent().get(0);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent1.getStartTime()));
        assertThat(charityEvent1.getName()).isEqualTo(charityEvent.getName());
    }

    /**
     * TODO : tìm các sự kiện từ thiện không có bài post
     */
    @Test
    public void findCharityEventWithOutPost() {
        CharityEvent charityEvent1 = charityEventRepository.findAllCharityEventWithoutPost(PageRequest.of(0, 10)).getContent().get(0);
        assertThat(charityEvent1.getName()).isEqualTo(charityEvent.getName());
    }

    /**
     * TODO : tìm các sự kiện từ thiện đang diễn ra
     */
    @Test
    public void findCharityEventNotEnd() {
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent.getStartTime()));
        CharityEvent charityEvent1 = charityEventRepository.getCharityEventNotEnd((PageRequest.of(0, 10))).getContent().get(0);
        assertThat(charityEvent1.getName()).isEqualTo(charityEvent.getName());
    }

    /**
     * TODO : tìm các sự kiện từ thiện đã kết thúc
     */
    @Test
    public void findCharityEventEnd() {
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent.getStartTime()));
        List<CharityEvent> charityEventList = charityEventRepository.getCharityEventEnd((PageRequest.of(0, 10))).getContent();
        assertThat(charityEventList.isEmpty()).isEqualTo(true);
    }

    /**
     * TODO : tìm các sự kiện từ thiện đã đạt mục tiêu
     */
    @Test
    public void findCharityEventReachGoal() {
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent.getStartTime()));
        List<CharityEvent> charityEventList = charityEventRepository.getCharityEventReachGoal((PageRequest.of(0, 10))).getContent();
        assertThat(charityEventList.isEmpty()).isEqualTo(true);
    }

    /**
     * TODO : tìm các sự kiện từ thiện chưa giải ngân dù đã đủ điều kiện
     */
    @Test
    public void getCharityEventNotBeDisbursed() {
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(charityEvent.getStartTime()));
        List<CharityEvent> charityEventList = charityEventRepository.getCharityEventNotBeDisbursed((PageRequest.of(0, 10))).getContent();
        assertThat(charityEventList.isEmpty()).isEqualTo(true);
    }
}
