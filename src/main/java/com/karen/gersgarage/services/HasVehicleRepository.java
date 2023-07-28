package com.karen.gersgarage.services;

import com.karen.gersgarage.model.HasVehicle;
import com.karen.gersgarage.model.HasVehicleId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HasVehicleRepository extends CrudRepository<HasVehicle, HasVehicleId> {
    List<HasVehicle> findByClientsIdClients(int clientId);
}
