package com.example.J2EE_project.services;

import com.example.J2EE_project.exceptions.InvalidPageException;
import com.example.J2EE_project.exceptions.NotFoundException;
import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.repositories.CharityEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CharityEventService {
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
        return charityEventRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException(NotFoundException.NOT_FOUND));
    }

    /**
     * TODO : Tìm kiếm charity event theo tên
     */
    public Page<CharityEvent> findCharityEventByName(int page, String query) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        Page<CharityEvent> charityEventPage = charityEventRepository.findByNameContaining(pageable, query);
        if (page + 1 > charityEventPage.getTotalPages())
            throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return charityEventPage;
    }

    /**
     * TODO : Lấy các charity event chưa có bài viết
     */
    public Page<CharityEvent> getCharityEventWithoutPost(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        Page<CharityEvent> charityEventPage = charityEventRepository.findAllCharityEventWithoutPost(pageable);
        if (page + 1 > charityEventPage.getTotalPages())
            throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return charityEventPage;
    }

    /**
     * TODO : tìm các sự kiện từ thiện đang diễn ra
     */
    public Page<CharityEvent> getCharityEventNotEnd(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        Page<CharityEvent> charityEventPage = charityEventRepository.getCharityEventNotEnd(pageable);
        if (page + 1 > charityEventPage.getTotalPages())
            throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return charityEventPage;
    }

    /**
     * TODO : Lấy các charity event đã kết thúc
     */
    public Page<CharityEvent> getCharityEventEnd(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        Page<CharityEvent> charityEventPage = charityEventRepository.getCharityEventEnd(pageable);
        if (page + 1 > charityEventPage.getTotalPages())
            throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return charityEventPage;
    }

    /**
     * TODO : Lấy các charity event đã đủ mục tiêu
     */
    public Page<CharityEvent> getCharityEventReachGoal(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        Page<CharityEvent> charityEventPage = charityEventRepository.findAll(pageable);
        if (page + 1 > charityEventPage.getTotalPages())
            throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return charityEventPage;
    }

    /**
     * TODO : Lấy các charity event chưa giải ngân dù đã đủ 1 trong 2 điều kiện
     * Điều kiện 1 : đã kết thúc
     * Điều kiện 2 : đã đạt mục tiêu quyên góp
     */
    public Page<CharityEvent> getCharityEventNotBeDisburse(int page) {
        if (page < 0) throw new InvalidPageException(InvalidPageException.PAGE_NOT_LESS_THAN_ONE);
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "startTime"));
        Page<CharityEvent> charityEventPage = charityEventRepository.getCharityEventNotBeDisbursed(pageable);
        if (page + 1 > charityEventPage.getTotalPages())
            throw new InvalidPageException(InvalidPageException.OUT_OF_BOUNDS);
        return charityEventPage;
    }

    /**
     * TODO : Giải ngân
     */
    public String disburseCharityEvent(String id) {
        get(id);
        charityEventRepository.disburseCharityEvent(UUID.fromString(id));
        return "Charity Event disbursed successfully";
    }
}

