package com.karen.gersgarage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "has_vehicle")
@IdClass(HasVehicleId.class)
public class HasVehicle {
    @Id
    private String vehiclesRegistrationNumber;
    @Id
    private int clientsIdClients;
}
