package com.machine.coffee.service.impl;

import com.machine.coffee.dao.CleanerDAO;
import com.machine.coffee.model.Cleaner;
import com.machine.coffee.service.CleanerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleanerServiceImpl implements CleanerService {

    private final CleanerDAO cleanerDAO;

    @Override
    public Cleaner plusPreparation() {
        Cleaner cleaner = cleanerDAO.getLastCountOfPreparations();
        if(cleaner.getCountOfPreparations() >= 3){
            throw new RuntimeException ("Need cleaning");
        }
        cleaner.setCountOfPreparations(cleaner.getCountOfPreparations() + 1);
        return cleanerDAO.save(cleaner);
    }

    @Override
    public Cleaner cleanMachine() {
        Cleaner cleaner = cleanerDAO.getLastCountOfPreparations();
        cleaner.setCountOfPreparations(0);
        return cleanerDAO.save(cleaner);
    }
}
