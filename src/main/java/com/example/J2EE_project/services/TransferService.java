package com.example.J2EE_project.services;

import com.example.J2EE_project.models.CharityEvent;
import com.example.J2EE_project.models.TransferSession;
import com.example.J2EE_project.repositories.CharityEventRepository;
import com.example.J2EE_project.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    CharityEventRepository charityEventRepository;

     /**
     * TODO : Chuyển khoản vào một Charity Event
     */
    public String transfer(TransferSession transferSession, String ce_id) {
        CharityEvent charityEvent =  charityEventRepository.getById(UUID.fromString(ce_id));
        transferSession.setCharityEvent(charityEvent);
        TransferSession savedTransferSession =  transferRepository.save(transferSession);
        return savedTransferSession.getId().toString();
    }

    /**
     * TODO : Sao kê
     */
    public List<TransferSession> getAllTransferSessions(String charityEventId) {
        return transferRepository.findAllByCharityEvent(UUID.fromString(charityEventId));
    }

}
