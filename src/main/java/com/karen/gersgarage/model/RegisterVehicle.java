package com.karen.gersgarage.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RegisterVehicle {
    private int make;
    private String other;
    private String model;
    private int year;
    private int engineType;
    private String registrationNumber;


    public Vehicle getVehicle(){
        return new Vehicle(registrationNumber,model, year, make, engineType );
    }
}