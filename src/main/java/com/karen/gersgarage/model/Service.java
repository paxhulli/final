package com.karen.gersgarage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor      //Create empty constructor
@AllArgsConstructor     //Create constructors
@Setter                 //Create setters
@Getter                 //Create Getters
@ToString               //Create toString
@Entity //Entity class that uses database
@Table(name = "service") //Table that will be using
public class Service {
    @Id // Primary Key
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) //Type of value(auto-increment)
    private int idService;
    private int serviceTypeIdServiceType;
    private String vehiclesRegistrationNumber;
    private int date;
    private int staffIdStaff;
    private int statusIdStatus;
}
