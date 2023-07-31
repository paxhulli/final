package com.karen.gersgarage.services;

import com.karen.gersgarage.model.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
}
