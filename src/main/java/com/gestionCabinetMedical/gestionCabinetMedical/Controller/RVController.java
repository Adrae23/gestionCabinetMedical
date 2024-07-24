package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.ClientService;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.RendezVousService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.List;

@Controller
public class RVController {

    @Autowired
    private RendezVousService rendezVousService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/PrendreRDVPage")
    public String getRdvPage() {
        return "prendreRDV";
    }



}
