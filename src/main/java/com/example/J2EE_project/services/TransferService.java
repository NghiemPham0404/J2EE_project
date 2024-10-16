package com.example.J2EE_project.services;

import com.example.J2EE_project.models.TransferSession;
import com.example.J2EE_project.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

     /**
     * TODO : Chuyển khoản vào một Charity Event
     */
    public String transfer(TransferSession transferSession) {
        transferRepository.save(transferSession);
        return "Transfer successfully";
    }

    /**
     * TODO : Sao kê
     */
    public List<TransferSession> getAllTransferSessions(String charityEventId) {
        return transferRepository.findAllByCharityEvent(UUID.fromString(charityEventId));
    }

}
