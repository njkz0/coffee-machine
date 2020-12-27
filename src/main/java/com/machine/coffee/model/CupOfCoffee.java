package com.machine.coffee.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coffee")
public class CupOfCoffee extends BaseEntity{

    @Column
    private String name;
    @Column(name = "shugar_teaspoons")
    private Integer numberOfShugarTeaspoons;
    @Column(name = "coffee_teaspoons")
    private Integer numberOfCoffeeTeaspoons;
    @Column(name = "water_ml")
    private Integer mlOfWater;
    @Column(name = "milk_ml")
    private Integer mlOfMilk;
}
