package com.karen.gersgarage.view;

import com.karen.gersgarage.model.Client;
import com.karen.gersgarage.model.HasVehicle;
import com.karen.gersgarage.model.RegisterVehicle;
import com.karen.gersgarage.model.Vehicle;
import com.karen.gersgarage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class MainController {
    Logger logger = Logger.getLogger(MainController.class.getName()); //Creating logger for actual class

    @Autowired //Create object automatically
    ClientRepository clientRepository;
    Client user ;
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    MakeRepository makeRepository;

    @Autowired
    EngineTypeRepository engineTypeRepository;

    @Autowired
    HasVehicleRepository hasVehicleRepository;

    public MainController() {

    }

    @GetMapping("/")
    public String index(Model model) {
        user = clientRepository.findById(2).get();
        logger.info("Index user:" + user);
        return "index";
    }//Link to index

    @GetMapping("/login")
    public String login() {
        return "login";
    }//Link to Log In Section

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    } // Link to Sign Up Section

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    } // Link to Contact Section

    @GetMapping("/manageBooking")
    public String manageBooking() {
        // codigo java
        return "manageBooking";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute Client client, Model model) { //To call data from view
        logger.info("Client: " + client); //To print info messages
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model) {
        logger.info("Test" + clientRepository.findAll());

        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        model.addAttribute("client2Vehicles",hasVehicleRepository.findByClientsIdClients(2));
        return "test";
    }

    @GetMapping("/vehicleReg")
    public String vehicleReg(Model model) {
        model.addAttribute("makes", makeRepository.findAll());
        model.addAttribute("engineTypes", engineTypeRepository.findAll());
        logger.info("user:" + user);
        return "vehicleRegistration";
    }

    @PostMapping(path = "/doVehicleReg", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String doVehicleReg(@ModelAttribute RegisterVehicle vehicleForm, @ModelAttribute Client client, Model model) {
        //logger.info("En doVehicleReg....");
        //logger.info("Formulario: " + vehicleForm); //To print info messages
        Vehicle saved = vehicleRepository.save(vehicleForm.getVehicle());
        //logger.info("result:" + saved);
        logger.info("user: " + user.toString());
        hasVehicleRepository.save(new HasVehicle(saved.getRegistrationNumber(), user.getIdClients()));
        model.addAttribute("saved", saved);
        return "vehicleRegResult";
    }



}
