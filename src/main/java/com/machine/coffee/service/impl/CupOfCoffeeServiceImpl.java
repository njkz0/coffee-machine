package com.machine.coffee.service.impl;

import com.machine.coffee.dao.CupOfCoffeeDAO;
import com.machine.coffee.model.CupOfCoffee;
import com.machine.coffee.service.CupOfCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupOfCoffeeServiceImpl  implements CupOfCoffeeService {

    private final CupOfCoffeeDAO cupOfCoffeeDAO;

    @Override
    public CupOfCoffee doCupOfCoffeeByName(String name) {
        CupOfCoffee cupOfCoffee= cupOfCoffeeDAO.findByName(name);
        if(cupOfCoffee != null){
            return cupOfCoffee;
        }
        throw new RuntimeException("Cant find coffee");

    }

    @Override
    public CupOfCoffee saveCupOfCoffee(CupOfCoffee cupOfCoffee) {
        if (cupOfCoffee.getId() == null && cupOfCoffeeDAO.findByName(cupOfCoffee.getName()) == null){
            return cupOfCoffeeDAO.save(cupOfCoffee);
        }
        throw new RuntimeException("Cant save coffee");
    }

    @Override
    public CupOfCoffee updateCupOfCoffee(CupOfCoffee cupOfCoffee) {
        if (cupOfCoffee.getId() != null && cupOfCoffeeDAO.findByName(cupOfCoffee.getName()) != null)
            return cupOfCoffeeDAO.update(cupOfCoffee);
        throw new RuntimeException("Cant Update coffee");
    }

    @Override
    public void deleteMyCupOfCoffeeById(Integer id) {
        CupOfCoffee cupOfCoffee = cupOfCoffeeDAO.findById(id);
        if(cupOfCoffee != null){
            cupOfCoffeeDAO.delete(cupOfCoffee);
        }
    }

    @Override
    public List<CupOfCoffee> getAllCoffee() {
        return cupOfCoffeeDAO.findAllExistedCoffee();
    }

}
