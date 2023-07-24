package com.karen.gersgarage.view;

import com.karen.gersgarage.model.Client;
import com.karen.gersgarage.services.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class MainController {
    Logger logger = Logger.getLogger(MainController.class.getName()); //Creating logger for actual class

    @Autowired //Create object automatically
    ClientRepository clientRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/manageBooking")
    public String manageBooking() {
        // codigo java
        return "manageBooking";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute Client client, Model model){ //To call data from view
        logger.info("Client: " + client); //To print info messages
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model) {
        logger.info("Test"+clientRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        return "test";
    }
    
}
