package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Medecin;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.MedecinService;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.RendezVousService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MedecinController {

    @Autowired
    private MedecinService medecinservice;
    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/VoirRDVJour")
    public String getRendezVousPage(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email == null) {
            model.addAttribute("errorMessage", "Vous devez être connecté pour voir vos rendez-vous.");
            return "error";
        }

        Optional<Medecin> medOptional = medecinservice.findByEmail(email);
        if (!medOptional.isPresent()) {
            model.addAttribute("errorMessage", "Medecin non trouvé.");
            return "error";
        }

        Medecin medecin = medOptional.get();
        System.out.println(medecin.getIdMedecin());
        List<RendezVous> rendezVousList = rendezVousService.getRendezVousByIDAndDate(medecin.getIdMedecin());

        model.addAttribute("rendezVousList", rendezVousList);
        return "VoirRDVJour";
    }
    @PostMapping("/VoirDossierMedicale")
    public String getDossierMedical(@RequestParam("clientId") Long clientId, Model model) {
        System.out.println("clients id :" + clientId);
        List<RendezVous> rendezVousList = rendezVousService.getRendezVousByClientId(clientId);
        Client client = rendezVousList.get(0).getClient();
        model.addAttribute("Client", client);
        model.addAttribute("rendezVousList", rendezVousList);
        return "VoirDossierMedical";
    }
    @GetMapping("/deconnexion")
    public String seDeconnecter(){
        return "redirect:/loginPersonnels";
    }
}

