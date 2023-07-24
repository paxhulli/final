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
@Entity //Entity class that uses databse
@Table(name = "clients") //Table that will be using
public class Client {
    @Id //Primary key
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) //Type of value(auto-increment)
    private int idclients;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private String license;
    private int profile;

}
