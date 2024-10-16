package com.example.J2EE_project.controllers;


import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.TransferSession;
import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.CharityEventService;
import com.example.J2EE_project.services.TransferService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/charity-events")
public class CharityEventController {

    @Autowired
    private CharityEventService charityEventService;

    @Autowired
    private TransferService transferService;

    /**
     * TODO : Thêm một charity event
     */
    @PostMapping
    public ResponseEntity<Object> createCharityEvent(@RequestBody CharityEvent charityEvent) {
        String response = charityEventService.create(charityEvent);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Cập nhật một charity event
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharityEvent(@PathVariable String id, @RequestBody CharityEvent charityEvent) {
        charityEventService.get(id);
        String response = charityEventService.update(charityEvent);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Xóa một charity event
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCharityEvent(@PathVariable String id) {
        String response = charityEventService.delete(id);
        return ResponseBuilder.buildResponse(response, HttpStatus.OK);
    }

    /**
     * TODO : Lấy charity event bằng id
     */
    @GetMapping("/{id}")
    public CharityEvent getCharityEventById(@PathVariable String id) {
        return charityEventService.get(id);
    }

    /**
     * TODO : Lấy danh sách charity event
     */
    @GetMapping
    public ResponseEntity<Object> listCharityEventsPaged(@PathParam("page") int page) {
        Page<CharityEvent> eventsPage = charityEventService.listAllNewest(page);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : Tìm kiếm charity event theo tên
     */
    @GetMapping("/search")
    public ResponseEntity<Object> searchCharityEventByName(
            @RequestParam("query") String query,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CharityEvent> eventsPage = charityEventService.findCharityEventByName(page, query);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : Tìm kiếm charity event không có bài viết
     */
    @GetMapping("/without-post")
    public ResponseEntity<Object> getCharityEventsWithoutPost(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CharityEvent> eventsPage = charityEventService.getCharityEventWithoutPost(page);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : tìm các sự kiện từ thiện đang diễn ra
     */
    @GetMapping("/ongoing")
    public ResponseEntity<Object> getOngoingCharityEvents(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CharityEvent> eventsPage = charityEventService.getCharityEventNotEnd(page);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : Lấy các charity event đã kết thúc
     */
    @GetMapping("/completed")
    public ResponseEntity<Object> getCompletedCharityEvents(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CharityEvent> eventsPage = charityEventService.getCharityEventEnd(page);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : Lấy các charity event đã đủ mục tiêu
     */
    @GetMapping("/reached-goal")
    public ResponseEntity<Object> getCharityEventsThatReachedGoal(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CharityEvent> eventsPage = charityEventService.getCharityEventReachGoal(page);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : Lấy các charity event chưa giải ngân dù đã đủ 1 trong 2 điều kiện
     * Điều kiện 1 : đã kết thúc
     * Điều kiện 2 : đã đạt mục tiêu quyên góp
     */
    @GetMapping("/not-disbursed")
    public ResponseEntity<Object> getCharityEventsNotDisbursed(
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<CharityEvent> eventsPage = charityEventService.getCharityEventNotBeDisburse(page);
        return ResponseBuilder.buildResponse(eventsPage, HttpStatus.OK);
    }

    /**
     * TODO : Giải ngân
     */
    @PostMapping("/disburse/{id}")
    public ResponseEntity<Object> disburseCharityEvent(@PathVariable String id) {
        return ResponseBuilder.buildResponse(charityEventService.disburseCharityEvent(id), HttpStatus.OK);
    }

    @GetMapping("{id}/all-transfer")
    public ResponseEntity<Object> getAllTransferOfCharityEvent(@PathVariable String id){
        List<TransferSession> transferSessions = transferService.getAllTransferSessions(id);
        return ResponseBuilder.buildResponse(transferSessions, HttpStatus.OK);
    }

    @PostMapping("transfer")
    public ResponseEntity<Object> transferToCharityEvent(@RequestBody TransferSession transferSession, @RequestParam String id) {
        CharityEvent ce =  getCharityEventById(id);
        transferSession.setCharityEvent(ce);
        return ResponseBuilder.buildResponse(transferService.transfer(transferSession), HttpStatus.OK);
    }
}

