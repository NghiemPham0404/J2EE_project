package com.example.J2EE_project.services;

import com.example.J2EE_project.models.TransferSession;
import com.example.J2EE_project.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    public String transfer(TransferSession transferSession) {
        transferRepository.save(transferSession);
        return "Transfer successfully";
    }

}
