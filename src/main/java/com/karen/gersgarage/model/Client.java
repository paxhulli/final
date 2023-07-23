package com.karen.gersgarage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int idclients;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private String licence;
    private int profile;

}
