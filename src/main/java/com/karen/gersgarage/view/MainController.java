package com.karen.gersgarage.view;

import com.karen.gersgarage.config.UserUserDetails;
import com.karen.gersgarage.model.*;
import com.karen.gersgarage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
    Authentication auth;

    UserUserDetails userDetails;

    @Autowired //Create object automatically
    ClientRepository clientRepository;

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

    private UserUserDetails  checkUser(){
        UserUserDetails user=null;
        auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("auth: " + auth);
        logger.info("auth::::-..: " + auth.getDetails().getClass().getName());
        if (auth.getPrincipal() instanceof UserUserDetails) {
            logger.info("UserUserDetails");
            logger.info("UserUserDetails: " + ((UserUserDetails) auth.getPrincipal()).getUsername());
            //model.addAttribute("user", ((UserUserDetails) auth.getPrincipal()));
            user = ((UserUserDetails) auth.getPrincipal());
        } else  {
            logger.info("No autenticado");

        }
        return user;
    }
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("user", checkUser());
        return "index";
    }//Link to index



    @GetMapping("/security/signup")
    @PreAuthorize("isAnonymous()")
    public String signup() {
        return "signup";
    } // Link to Sign Up Section

    @GetMapping("/contact")
    @PreAuthorize("isAnonymous()")
    public String contact() {
        return "contact";
    } // Link to Contact Section

    @GetMapping("/user/vehicleTeg")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String vehicleReg() {
        return "vehicleReg";
    } // Link to Register a Car Section


    @GetMapping("/admin/manageBooking")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageBooking() {
        // codigo java
        return "manageBooking";
    }

    @GetMapping("/test")
    public String test(Model model) {
        logger.info("Test" + clientRepository.findAll());

        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        model.addAttribute("client2Vehicles", hasVehicleRepository.findByClientsIdClients(2));
        return "test";
    }

    @GetMapping("/user/vehicleReg")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String vehicleReg(Model model) {
        UserUserDetails user = checkUser();
        model.addAttribute("makes", makeRepository.findAll());
        model.addAttribute("engineTypes", engineTypeRepository.findAll());
        logger.info("Vehicle reg user:" + user);
        return "vehicleRegistration";
    }

    @PostMapping(path = "/user/doVehicleReg", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String doVehicleReg(@ModelAttribute RegisterVehicle vehicleForm, @ModelAttribute Client client, Model model) {
        UserUserDetails user = checkUser();
        Vehicle saved = vehicleRepository.save(vehicleForm.getVehicle()); //Save info from form
        //logger.info("result:" + saved);
        logger.info("user: " + user.getUsername());
        hasVehicleRepository.save(new HasVehicle(saved.getRegistrationNumber(), user.getIdClients()));
        model.addAttribute("saved", saved);
        return "vehicleRegResult";
    }

    @GetMapping("/user/booking")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String startBooking(Model model) {
        UserUserDetails user = checkUser();
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

    @PostMapping(path = "/user/dobookservice", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ROLE_USER')")
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

    @GetMapping("/manage/manageItems")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageItems(Model model){
        model.addAttribute("items", itemPartRepository.findAll());
        return "manageItems";
    }

    @GetMapping("/manage/addItem")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addItem(Model model){

        return "addItem";
    }


    @PostMapping(path = "/manage/doAddItem", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String doAddItem(@ModelAttribute ItemPart itemForm, Model model) {
        logger.info("En doAddItem....");
        logger.info("Form: " + itemForm); //To print info messages
        ItemPart saved = itemPartRepository.save(itemForm); //Save info from form
        logger.info("result:" + saved);
        model.addAttribute("saved", saved);
        return "addItemResult";
    }

    @GetMapping("/manage/updateItem")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateItem(Model model){

        return "updateItem";
    }

    @PostMapping(path = "/manage/doUpdateItem", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String doUpdateItem(@ModelAttribute ItemPart itemForm, Model model) {
        logger.info("En doUpdateItem....");
        logger.info("Form: " + itemForm); //To print info messages
        ItemPart saved = itemPartRepository.save(itemForm); //Save info from form
        logger.info("result:" + saved);
        model.addAttribute("saved", saved);
        return "addItemResult";
    }

}
