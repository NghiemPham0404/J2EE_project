package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.MostPostsAccountsDTO;
import com.example.J2EE_project.repositories.EventStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EventStatisticService {
    @Autowired
    private EventStatisticRepository eventStatisticRepository;

    public List<MostPostsAccountsDTO> statisticMostDonationEvents(Date startDate, Date endDate){
        return eventStatisticRepository.statisticMostDonationEvents(startDate, endDate);
    }

    public List<MostPostsAccountsDTO> statisticMostCharitablePeople(String charityEventId, Date startDate, Date endDate){
        return eventStatisticRepository.statisticMostCharitablePeople(UUID.fromString(charityEventId), startDate, endDate);
    }
}
