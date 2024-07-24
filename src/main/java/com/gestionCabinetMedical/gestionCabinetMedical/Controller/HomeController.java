package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.Service.MedecinService;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.SecraitaireService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class HomeController {

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private SecraitaireService secretaireService;

    @GetMapping("/")
    public String getHomePage() {
        String homePage = "home";
        homePage = "index";
        return homePage;
    }

    @GetMapping("/index")
    public String Home() {
        return "index";
    }

    @GetMapping("/loginPersonnels")
    public String login() {
        return "loginPersonnels";
    }

    @PostMapping("/loginPerso")
    public String getHome(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("role") String role,
                          Model model,
                          HttpSession session) {

        if ("MEDECIN".equals(role)) {
            boolean medecinExists = medecinService.authenticate(username, password);
            if (medecinExists) {
                session.setAttribute("email", username);
                return "medHome"; // Redirige vers la page medHome si l'authentification réussit
            } else {
                model.addAttribute("errorMessage", "Médecin introuvable. Veuillez vérifier vos informations de connexion.");
            }
        } else if ("SECRETAIRE".equals(role)) {
            boolean secretaireExists = secretaireService.authenticate(username, password);
            if (secretaireExists) {
                session.setAttribute("email", username);
                return "secHome"; // Redirige vers la page secHome si l'authentification réussit
            } else {
                model.addAttribute("errorMessage", "Secrétaire introuvable. Veuillez vérifier vos informations de connexion.");
            }
        } else {
            model.addAttribute("errorMessage", "Rôle invalide. Veuillez sélectionner un rôle valide.");
        }

        return "loginPersonnels"; // Retourne à la page de connexion pour afficher l'alerte d'erreur
    }


}