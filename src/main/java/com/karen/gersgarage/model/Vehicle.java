package com.karen.gersgarage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor      //Create empty constructor
@AllArgsConstructor     //Create constructors
@Setter                 //Create setters
@Getter                 //Create Getters
@ToString               //Create toString
@Entity //Entity class that uses database
@Table(name = "vehicles") //Table that will be using

public class Vehicle {
    @Id//Primary Key
    //Type of PK
    private String registrationNumber;
    private String model;
    private int year;
    private int makeIdMake;
    private int engineTypeIdEngineType;
}
