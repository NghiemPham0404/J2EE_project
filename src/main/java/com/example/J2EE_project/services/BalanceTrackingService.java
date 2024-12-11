package com.example.J2EE_project.services;

import com.example.J2EE_project.DTOs.BalanceTrackingDTO;
import com.example.J2EE_project.repositories.BalanceTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceTrackingService {
    @Autowired
    BalanceTrackingRepository balanceTrackingRepository;

    public List<BalanceTrackingDTO> getBalanceTrackingOfYear(int year){
        List<Object[]> data = balanceTrackingRepository.getBalanceTrackingOfYear(year);
        List<BalanceTrackingDTO> balanceTrackingDTOList = new ArrayList<>();
        for(Object[] row : data){
            BalanceTrackingDTO dto = new BalanceTrackingDTO((int)row[0], (BigDecimal) row[1], (BigDecimal)row[2]);
            System.out.printf("%d %s %s \n", dto.getMonth(), dto.getTransferTotal(), dto.getCharityEventDisburse());
            balanceTrackingDTOList.add(dto);
        }
        return balanceTrackingDTOList;
    }

    public List<Integer> getAllActiveYear(){
        return balanceTrackingRepository.getAllActiveYear();
    }

    public BigDecimal getRemainBalance(){
        return balanceTrackingRepository.getRemainBalance().isEmpty()? new BigDecimal(0) :balanceTrackingRepository.getRemainBalance().get(0);
    }
}
