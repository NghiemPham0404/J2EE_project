package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.MostCharitablePeopleDTO;
import com.example.J2EE_project.DTOs.MostDonationEventsDTO;
import com.example.J2EE_project.DTOs.MostPostsAccountsDTO;
import com.example.J2EE_project.models.CharityEvent;
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

    public List<MostDonationEventsDTO> statisticMostDonationEvents(Date startDate, Date endDate){
        return eventStatisticRepository.statisticMostDonationEvents(startDate, endDate);
    }

    public List<MostCharitablePeopleDTO> statisticMostCharitablePeople(String charityEventId, Date startDate, Date endDate){
        return eventStatisticRepository.statisticMostCharitablePeople(UUID.fromString(charityEventId), startDate, endDate);
    }

    public List<CharityEvent> getCharityEventBeDisbursedIn(Date startDate, Date endDate){
        return eventStatisticRepository.getCharityEventBeDisbursedIn(startDate, endDate);
    }
}
