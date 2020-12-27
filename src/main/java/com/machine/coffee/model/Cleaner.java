package com.machine.coffee.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cleaner")
public class Cleaner extends  BaseEntity{

    @Column(name = "count")
    private Integer countOfPreparations;
}
