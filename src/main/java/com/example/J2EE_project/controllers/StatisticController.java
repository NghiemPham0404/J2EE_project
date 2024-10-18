package com.example.J2EE_project.controllers;

import com.example.J2EE_project.response.ResponseBuilder;
import com.example.J2EE_project.services.EventStatisticService;
import com.example.J2EE_project.services.PostStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/api/statistic")
public class StatisticController {
    @Autowired
    PostStatisticService postStatisticService;

    @Autowired
    EventStatisticService eventStatisticService;

    @GetMapping("account/most-posts-accounts")
    public ResponseEntity<Object> statisticMostPostAccounts(@RequestParam(value = "startTime") Date startTime,
                                                            @RequestParam(value = "startTime") Date endTime){
        return ResponseBuilder.buildResponse(postStatisticService.statisticMostPostAccounts(startTime, endTime), HttpStatus.OK);
    }

    @GetMapping("post/most-viewed-posts")
     public ResponseEntity<Object> statisticMostViewedPosts(@RequestParam(value = "startTime") Date startTime,
                                                            @RequestParam(value = "startTime") Date endTime){
        return ResponseBuilder.buildResponse(postStatisticService.statisticMostViewedPosts(startTime, endTime), HttpStatus.OK);
    }

    @GetMapping("charity-event/most-donation-events")
    public ResponseEntity<Object> statisticMostDonationEvents(@RequestParam(value = "startTime") Date startTime,
                                                            @RequestParam(value = "startTime") Date endTime) {
        return ResponseBuilder.buildResponse(eventStatisticService.statisticMostDonationEvents(startTime, endTime),HttpStatus.OK);
    }

    @GetMapping("charity-event/{ce_id}/most-charitatble-person")
    public ResponseEntity<Object> statisticMostCharitablePeople(@PathVariable("ce_id") String charityEventId,
            @RequestParam(value = "startTime") Date startTime,
            @RequestParam(value = "startTime") Date endTime) {
        return ResponseBuilder.buildResponse(eventStatisticService.statisticMostCharitablePeople(charityEventId,startTime, endTime),HttpStatus.OK);
    }
}
