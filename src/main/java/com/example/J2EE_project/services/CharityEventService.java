package com.example.J2EE_project.services;

import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.repositories.CharityEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharityEventService implements serviceInterface<CharityEvent, String> {
    @Autowired
    CharityEventRepository charityEventRepository;

    @Override
    public String create(CharityEvent charityEvent) {
        charityEventRepository.save(charityEvent);
        return "Charity Event created";
    }

    @Override
    public String update(CharityEvent charityEvent) {
        charityEventRepository.save(charityEvent);
        return "Charity Event updated";
    }

    @Override
    public String delete(String id) {
        charityEventRepository.deleteById(UUID.fromString(id));
        return "Charity Event deleted";
    }

    @Override
    public List<CharityEvent> listAll() {
        return charityEventRepository.findAll();
    }

    @Override
    public List<CharityEvent> listAllNewest() {
        List<CharityEvent> charityEvents = charityEventRepository.findAll();
        charityEvents.sort((o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime()));
        return charityEvents;
    }

    @Override
    public Page<CharityEvent> listAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return charityEventRepository.findAll(pageable);
    }

    @Override
    public Page<CharityEvent> listAllNewest(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.findAll(pageable);
    }

    public CharityEvent get(String id) {
        return charityEventRepository.findById(UUID.fromString(id)).get();
    }

    /**
     * TODO : Tìm kiếm charity event theo tên
     */
    public Page<CharityEvent> findCharityEventByName(int page, String query) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.findByNameContaining(pageable, query);
    }

    /**
     * TODO : Lấy các charity event chưa có bài viết
     */
    public Page<CharityEvent> getCharityEventWithoutPost(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.findAllCharityEventWithoutPost(pageable);
    }

    /**
     * TODO : tìm các sự kiện từ thiện đang diễn ra
     */
    public Page<CharityEvent> getCharityEventNotEnd(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.getCharityEventNotEnd(pageable);
    }

    /**
     * TODO : Lấy các charity event đã kết thúc
     */
    public Page<CharityEvent> getCharityEventEnd(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.getCharityEventEnd(pageable);
    }

    /**
     * Lấy các charity event đã đủ mục tiêu
     */
    public Page<CharityEvent> getCharityEventReachGoal(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.findAll(pageable);
    }

    /**
     * Lấy các charity event chưa giải ngân dù đã đủ 1 trong 2 điều kiện
     * Điều kiện 1 : đã kết thúc
     * Điều kiện 2 : đã đạt mục tiêu quyên góp
     */
    public Page<CharityEvent> getCharityEventNotBeDisburse(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.getCharityEventNotBeDisbursed(pageable);
    }

    /**
     * Giải ngân
     */
    public void disburseCharityEvent(String id){
        charityEventRepository.disburseCharityEvent(UUID.fromString(id));
    }
}

