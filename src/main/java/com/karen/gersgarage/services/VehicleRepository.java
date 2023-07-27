package com.karen.gersgarage.services;

import com.karen.gersgarage.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
