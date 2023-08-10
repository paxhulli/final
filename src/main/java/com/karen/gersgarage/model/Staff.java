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
@Table(name = "staff") //Table that will be using
public class Staff {
    @Id // Primary Key
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) //Type of value(auto-increment)
    private int idStaff;
    private String firstName;
    private String lastName;
    private String mobileNumber;

}
