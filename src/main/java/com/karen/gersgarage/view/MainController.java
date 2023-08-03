package com.karen.gersgarage.view;

import com.karen.gersgarage.model.*;
import com.karen.gersgarage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class MainController {
    Logger logger = Logger.getLogger(MainController.class.getName()); //Creating logger for actual class

    @Autowired //Create object automatically
    ClientRepository clientRepository;
    Client user;
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    MakeRepository makeRepository;

    @Autowired
    EngineTypeRepository engineTypeRepository;

    @Autowired
    HasVehicleRepository hasVehicleRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ItemPartRepository itemPartRepository;

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

    @GetMapping("/vehicleTeg")
    public String vehicleReg() {
        return "vehicleReg";
    } // Link to Register a Car Section


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
        model.addAttribute("client2Vehicles", hasVehicleRepository.findByClientsIdClients(2));
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
        Vehicle saved = vehicleRepository.save(vehicleForm.getVehicle()); //Save info from form
        //logger.info("result:" + saved);
        logger.info("user: " + user.toString());
        hasVehicleRepository.save(new HasVehicle(saved.getRegistrationNumber(), user.getIdClients()));
        model.addAttribute("saved", saved);
        return "vehicleRegResult";
    }

    @GetMapping("/booking")
    public String startBooking(Model model) {
        model.addAttribute("user", user);
        List<String> hours = new ArrayList<>();
        for (int i = 9; i < 14; i++) {
            hours.add(String.format("%02d", i));
        }
        model.addAttribute("hours", hours);
        model.addAttribute("serviceTypes", serviceTypeRepository.findAll());
        List<HasVehicle> userVehicles = hasVehicleRepository.findByClientsIdClients(user.getIdClients());
        ArrayList<String> idsUserVehicles = new ArrayList<>();
        for (HasVehicle hv : userVehicles) {
            idsUserVehicles.add(hv.getVehiclesRegistrationNumber());
        }
        logger.info("idsUserVehicles: " + idsUserVehicles);
        model.addAttribute("vehicles", vehicleRepository.findAllById(idsUserVehicles));
        return "booking";
    }

    @PostMapping(path = "/dobookservice", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String doBookService(@RequestParam String idClients,
                                @RequestParam String registrationNumber,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day,
                                @RequestParam Integer time,
                                @RequestParam Integer service,
                                @RequestParam String extraNotes, Model model) {
        Service newService = new Service(0,service,registrationNumber,day,time,0,1,extraNotes);
        logger.info("En doBookService...." + newService);
        Service savedService =  serviceRepository.save(newService);
        model.addAttribute("newService", newService);
        return "bookingresult";
    }

    @GetMapping("/manageItems")
    public String manageItems(Model model){
        model.addAttribute("items", itemPartRepository.findAll());
        return "manageItems";
    }

    @GetMapping("/addItem")
    public String addItem(Model model){

        return "addItem";
    }


    @PostMapping(path = "/doAddItem", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String doAddItem(@ModelAttribute ItemPart itemForm, Model model) {
        logger.info("En doAddItem....");
        logger.info("Form: " + itemForm); //To print info messages
        ItemPart saved = itemPartRepository.save(itemForm); //Save info from form
        logger.info("result:" + saved);
        model.addAttribute("saved", saved);
        return "addItemResult";
    }

    @GetMapping("/updateItem")
    public String updateItem(Model model){

        return "updateItem";
    }

    @PostMapping(path = "/doUpdateItem", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String doUpdateItem(@ModelAttribute ItemPart itemForm, Model model) {
        logger.info("En doUpdateItem....");
        logger.info("Form: " + itemForm); //To print info messages
        ItemPart saved = itemPartRepository.save(itemForm); //Save info from form
        logger.info("result:" + saved);
        model.addAttribute("saved", saved);
        return "addItemResult";
    }

}
