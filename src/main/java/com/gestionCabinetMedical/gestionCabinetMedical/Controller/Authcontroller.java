package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.DTO.ClientDTO;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class Authcontroller {

    private ClientService clientService;

    @GetMapping("/authentification")
    public String showLoginPage() {

        return "authentification";
    }




}
