package com.karen.gersgarage.services;

import com.karen.gersgarage.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> { //Create interface that inherit CrudRepository to select, insert,etc
    public Client findByEmail(String email); //method to find client by email
}
