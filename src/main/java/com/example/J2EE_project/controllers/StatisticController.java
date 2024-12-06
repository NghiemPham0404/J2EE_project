package com.example.J2EE_project.controllers;

import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.EventStatisticService;
import com.example.J2EE_project.services.PostStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    @Autowired
    PostStatisticService postStatisticService;

    @Autowired
    EventStatisticService eventStatisticService;

    @Operation(summary = "Lấy ra những tài khoản với nhiều bài viết nhất")
    @GetMapping("account/most-posts-accounts")
    public ResponseEntity<Object> statisticMostPostAccounts(@RequestParam(value = "start_time") Date startTime,
                                                            @RequestParam(value = "end_time") Date endTime) {
        return ResponseBuilder.buildResponse(postStatisticService.statisticMostPostAccounts(startTime, endTime), HttpStatus.OK);
    }

    @Operation(summary = "Những bài viết được xem nhiều nhất")
    @GetMapping("post/most-viewed-posts")
    public ResponseEntity<Object> statisticMostViewedPosts(@RequestParam(value = "start_time") Date startTime,
                                                           @RequestParam(value = "end_time") Date endTime) {
        return ResponseBuilder.buildResponse(postStatisticService.statisticMostViewedPosts(startTime, endTime), HttpStatus.OK);
    }

    @Operation(summary = "Những bài viết được ủng hộ nhiều nhất")
    @GetMapping("charity-event/most-donation-events")
    public ResponseEntity<Object> statisticMostDonationEvents(@RequestParam(value = "start_time") Date startTime,
                                                              @RequestParam(value = "end_time") Date endTime) {
        return ResponseBuilder.buildResponse(eventStatisticService.statisticMostDonationEvents(startTime, endTime), HttpStatus.OK);
    }

    @Operation(summary = "Thống kê những nguời donate nhiều nhất trong một chương trình từ thiện")
    @GetMapping("charity-event/{ce_id}/most-charitatble-person")
    public ResponseEntity<Object> statisticMostCharitablePeople(@PathVariable("ce_id") String charityEventId,
                                                                @RequestParam(value = "start_time") Date startTime,
                                                                @RequestParam(value = "end_time") Date endTime) {
        return ResponseBuilder.buildResponse(eventStatisticService.statisticMostCharitablePeople(charityEventId, startTime, endTime), HttpStatus.OK);
    }

    @Operation(summary = "Thống kê những charity event được giải ngân trong một khoản thời gian nhất định")
    @GetMapping("charity-event/disburse")
    public ResponseEntity<Object> statisticMostCharitablePeople(
                                                                @RequestParam(value = "startTime") Date startTime,
                                                                @RequestParam(value = "endTime") Date endTime) {
        return ResponseBuilder.buildResponse(eventStatisticService.getCharityEventBeDisbursedIn(startTime, endTime), HttpStatus.OK);
    }
}
