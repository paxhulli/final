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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    Logger logger = Logger.getLogger(MainController.class.getName()); //Creating logger (info) for actual class
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StaffRepository staffRepository;

    public MainController() {

    }

    //Check if user is logged in
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

    // Link to Login Section
    @GetMapping("/login")
    @PreAuthorize("isAnonymous()") //User must be anonymous to access this section (not logged in)
    public String login() {
        return "login";
    }

    // Link to Sign Up Section
    @GetMapping("/security/signup")
    @PreAuthorize("isAnonymous()")
    public String signup() {
        return "signup";
    }

    // Signing Up a new user
    @PostMapping(path = "/security/doSignup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("isAnonymous()")
    public String doSignup(@ModelAttribute Client client, Model model) {
        logger.info("/security/doSignup client: " + client);
        client.setProfile("ROLE_USER"); //Set profile to user
        client.setPassword(passwordEncoder.encode(client.getPassword())); //Encode password
        Client newUser = clientRepository.save(client); //Save new user
        if (newUser != null) {
            model.addAttribute("message", "Success!");
            model.addAttribute("newUser", newUser);
        } else {
            model.addAttribute("message", "Success!");
            return "signupResult";
        }

        return "signupResult";
    }

    // Link to 403 Section (Access Denied)
    @GetMapping("/security/403")
    @PreAuthorize("permitAll()")
    public String accessDenied() {
        logger.info( ":_:_:_:_:_:_:_:_:403" );
        return "403";
    }

    // Link to Contact Section
    @GetMapping("/contact")
    @PreAuthorize("isAnonymous()")
    public String contact() {
        return "contact";
    }

    // Link to Register a Car Section
    @GetMapping("/user/vehicleTeg")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String vehicleReg() {
        return "vehicleReg";
    }

    // Test
    @GetMapping("/test")
    @PreAuthorize("isAnonymous()")
    public String test(Model model) {
        logger.info("Test" + clientRepository.findAll());

        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        model.addAttribute("client2Vehicles", hasVehicleRepository.findByClientsIdClients(2));
        return "test";
    }

    // Register a Car
    @GetMapping("/user/vehicleReg")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String vehicleReg(Model model) {
        UserUserDetails user = checkUser();
        model.addAttribute("makes", makeRepository.findAll());
        model.addAttribute("engineTypes", engineTypeRepository.findAll());
        logger.info("Vehicle reg user:" + user);
        return "vehicleRegistration";
    }

    // Save a Car
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

    // Booking a Service
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

    // Create a Service and save it
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

    // Link to Manage Services Section
    @GetMapping("/manage/manageItems")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageItems(Model model){
        model.addAttribute("items", itemPartRepository.findAll());
        return "manageItems";
    }

    // Add Items to database
    @GetMapping("/manage/addItem")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addItem(Model model){

        return "addItem";
    }

    // Save Items to database
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

    //update items from database
    @PostMapping("/manage/updateItem")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateItem(@RequestParam Integer idItemsParts ,Model model){
        logger.info("En updateItem...."+ idItemsParts);
        model.addAttribute("item", itemPartRepository.findById(idItemsParts).get());
        return "updateItem";
    }

    //update items from database in form
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


    //delete items from database
    @PostMapping("/manage/deleteItem")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteItem(@RequestParam Integer idItemsParts ,Model model){
        logger.info("En deleteItem...."+ idItemsParts);
        itemPartRepository.deleteById(idItemsParts);
        return "redirect:/manage/manageItems";
    }

    //manage services
    @GetMapping("/manage/manageServices")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageServices(Model model){
        model.addAttribute("services", serviceRepository.findAll());
        return "manageServices";
    }

    //manage staff
    @GetMapping("/manage/manageStaff")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String manageStaff(Model model){
        model.addAttribute("staffs", staffRepository.findAll());
        return "manageStaff";
    }







}
