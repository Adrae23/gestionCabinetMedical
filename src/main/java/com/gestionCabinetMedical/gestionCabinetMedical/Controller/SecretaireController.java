package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.ClientService;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class SecretaireController {

    @Autowired
    private RendezVousService rendezVousService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/secretaire/deconnexion")
    public String seDeconnecter(){
        return "redirect:/loginPersonnels";
    }
    @GetMapping("/VOirRDVSelonJour")
    public String getRDV(){
        return "VoirRDVSelonJ";
    }
    @PostMapping("/VoirRDVSelonJour1")
    public String getRDV1(@RequestParam("date") String date, Model model) {
        try {
            LocalDate dateTime = LocalDate.parse(date);
            List<RendezVous> rendezvousList = rendezVousService.getRendezVousByDate(dateTime);
            model.addAttribute("listerdv", rendezvousList);
            return "VoirRDVSelonJ";
        } catch (DateTimeParseException e) {
            model.addAttribute("errorMessage", "Format de date invalide. Veuillez entrer une date valide au format AAAA-MM-JJ.");
            return "VoirRDVSelonJ"; // ou une autre vue pour afficher l'erreur
        }
    }
    @PostMapping("/VoirDossierMedicale1")
    public String getDossierMedical(@RequestParam("clientId") Long clientId, Model model) {
        System.out.println("clients id :" + clientId);
        List<RendezVous> rendezVousList = rendezVousService.getRendezVousByClientId(clientId);
        Client client = rendezVousList.get(0).getClient();
        model.addAttribute("Client", client);
        model.addAttribute("rendezVousList", rendezVousList);
        return "VoirDossierMedical1";
    }
    @GetMapping("/VOirTousRDV")
    public String getAllRDV(Model model){
        List<RendezVous> listRdv = rendezVousService.getAllRendezVous();
        model.addAttribute("listerdv", listRdv);
        return "VoirTousRDV";
    }
    @PostMapping("/VoirDossierMedicale2")
    public String getDossierMedical2(@RequestParam("cin") String cin, Model model) {
        System.out.println("clients id :" + cin);
        Client client = clientService.getByClientCin(cin);
        List<RendezVous> rendezVousList = rendezVousService.getRendezVousByCin(cin);
        model.addAttribute("Client", client);
        model.addAttribute("rendezvous", rendezVousList);
        return "ChercherClient";
    }

    @GetMapping("/VoirDossierMedicale3")
    public String getdMed(){
        return "ChercherClient";
    }

    @GetMapping("/reportclient1")
    private String getReport(){
        return "genererRaportClient";
    }


}
