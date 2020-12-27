package com.machine.coffee.service;

import com.machine.coffee.model.CupOfCoffee;

import java.util.List;

public interface CupOfCoffeeService {

    CupOfCoffee doCupOfCoffeeByName(String name);
    CupOfCoffee saveCupOfCoffee(CupOfCoffee cupOfCoffee);
    CupOfCoffee updateCupOfCoffee(CupOfCoffee cupOfCoffee);
    void deleteMyCupOfCoffeeById(Integer id);
    List<CupOfCoffee> getAllCoffee();
}
