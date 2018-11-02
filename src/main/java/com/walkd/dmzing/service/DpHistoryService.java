package com.walkd.dmzing.service;

import com.walkd.dmzing.repository.DpHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DpHistoryService {

    @Autowired
    DpHistoryRepository dpHistoryRepository;


    @Transactional
    public void getDpHistory(Long id) {
        dpHistoryRepository.findById(id);
    }

}
