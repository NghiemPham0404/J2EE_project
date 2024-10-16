package com.example.J2EE_project.services;

import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.TransferSession;
import com.example.J2EE_project.repositories.CharityEventRepository;
import com.example.J2EE_project.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharityEventService{
    @Autowired
    CharityEventRepository charityEventRepository;

    public String create(CharityEvent charityEvent) {
        charityEventRepository.save(charityEvent);
        return "Charity Event created";
    }

    public String update(CharityEvent charityEvent) {
        charityEventRepository.save(charityEvent);
        return "Charity Event updated";
    }

    public String delete(String id) {
        charityEventRepository.deleteById(UUID.fromString(id));
        return "Charity Event deleted";
    }

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
     * TODO : Lấy các charity event đã đủ mục tiêu
     */
    public Page<CharityEvent> getCharityEventReachGoal(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.findAll(pageable);
    }

    /**
     * TODO : Lấy các charity event chưa giải ngân dù đã đủ 1 trong 2 điều kiện
     * Điều kiện 1 : đã kết thúc
     * Điều kiện 2 : đã đạt mục tiêu quyên góp
     */
    public Page<CharityEvent> getCharityEventNotBeDisburse(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        return charityEventRepository.getCharityEventNotBeDisbursed(pageable);
    }

    /**
     * TODO : Giải ngân
     */
    public String disburseCharityEvent(String id){
        charityEventRepository.disburseCharityEvent(UUID.fromString(id));
        return "Charity Event disbursed successfully";
    }
}

