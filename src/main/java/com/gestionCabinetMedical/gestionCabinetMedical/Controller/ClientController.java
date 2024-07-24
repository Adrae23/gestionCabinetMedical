package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.DTO.RendezVousDTO;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.gestionCabinetMedical.gestionCabinetMedical.DTO.ClientDTO;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.ClientService;
import com.gestionCabinetMedical.gestionCabinetMedical.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/s'inscrire")
    public String showClientForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "formulaire";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute ClientDTO clientDTO, Model model) {
        clientService.addClient(clientDTO);
        model.addAttribute("succes","Client ajouté avec succès!");
        return "authentification";
    }
    @PostMapping("/modifierClient")
    public String updateClient(@ModelAttribute ClientDTO clientDTO, Model model, HttpSession session) {
        // Récupérer l'email de la session
        String email = (String) session.getAttribute("email");

        // Trouver le client par email
        if (email != null) {
            Optional<Client> clientOptional = clientService.findByEmail(email);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                clientDTO.setId(client.getId()); // Assigner l'ID du client à DTO
                clientService.updateClient(clientDTO);
                model.addAttribute("succes", "Client modifié avec succès!");
                return "modificationClient";
            } else {
                model.addAttribute("errorMessage", "Client non trouvé.");
                return "error"; // Page d'erreur si le client n'est pas trouvé
            }
        } else {
            return "redirect:/login"; // Redirige vers la page de connexion si l'email n'est pas trouvé dans la session
        }
    }
    @PostMapping("/modifierClient2")
    public String updateClient2(@RequestParam("clientId") Long clientId, Model model) {
        if (clientId != null) {
            Optional<Client> clientOptional = clientService.findById(clientId);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                ClientDTO clientDTO = new ClientDTO(); // Créez un nouvel objet ClientDTO

                // Copier les propriétés du Client vers le ClientDTO
                clientDTO.setId(client.getId());
                clientDTO.setNom(client.getNom());
                clientDTO.setTelephone(client.getTelephone());
                clientDTO.setEmail(client.getEmail());
                clientDTO.setCin(client.getCin());
                clientDTO.setAdresse(client.getAdresse());
                clientDTO.setGenre(client.getGenre());
                clientDTO.setMdp(client.getMdp());
                clientDTO.setDateNaissance(client.getDateNaissance());
                // Ajoutez ici les autres propriétés si nécessaire

                clientService.updateClient(clientDTO);
                model.addAttribute("succes", "Client modifié avec succès!");
                return "modificationClient1";
            } else {
                model.addAttribute("errorMessage", "Client non trouvé.");
                return "error"; // Page d'erreur si le client n'est pas trouvé
            }
        } else {
            return "redirect:/login"; // Redirige vers la page de connexion si le clientId n'est pas fourni
        }
    }


    @GetMapping("/modificationClient1")
    public String getUpdatePage(){
        return "modificationClient";
    }
    @GetMapping("/modificationClient2")
    public String getUpdatePage1(){
        return "modificationClient1";
    }



    @PostMapping("/login")
    public String authenticate(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model,
                               HttpSession session) {
        // Logique d'authentification, vérifiez si le client existe
        boolean clientExists = clientService.authenticate(username, password);

        if (clientExists) {
            session.setAttribute("email", username);

            return "home"; // Assurez-vous que "/home" correspond à votre URL de redirection
        } else {
            // Ajouter un message d'erreur au modèle pour afficher l'alerte
            model.addAttribute("errorMessage", "Client introuvable. Veuillez vérifier vos informations de connexion.");
            return "redirect:/authentification"; // Retourne à la page de connexion pour afficher l'alerte

    }
}
    @GetMapping("/deconnexion")
    public String seDeconnecter(){
        return "redirect:/authentification";
    }

    @PostMapping("/PrendreRDV")
    public String prendreRDV(@ModelAttribute RendezVousDTO rendezVousDTO, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            model.addAttribute("errorMessage", "Vous devez être connecté pour prendre un rendez-vous.");
            return "error"; // Page d'erreur si l'email n'est pas trouvé dans la session
        }

        Optional<Client> clientOptional = clientService.findByEmail(email);
        if (!clientOptional.isPresent()) {
            model.addAttribute("errorMessage", "Client non trouvé.");
            return "error"; // Page d'erreur si le client n'est pas trouvé
        }

        Client client = clientOptional.get();
        rendezVousDTO.setClient(client.getId());

        // Logs pour debug
        System.out.println("DateTime: " + rendezVousDTO.getDateTime());
        System.out.println("MedecinId: " + rendezVousDTO.getMedecinId());
        System.out.println("ServiceId: " + rendezVousDTO.getServiceId());
        System.out.println("Status: " + rendezVousDTO.getStatus());
        System.out.println("ClientId: " + rendezVousDTO.getClient());

        try {
            // Log avant de créer le rendez-vous
            System.out.println("Création du rendez-vous pour le client ID: " + client.getId());
            rendezVousService.createRendezVous(rendezVousDTO);
            // Log après la création du rendez-vous
            System.out.println("Rendez-vous créé avec succès pour le client ID: " + client.getId());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de la création du rendez-vous : " + e.getMessage());
            return "error"; // Page d'erreur en cas de problème
        }

        model.addAttribute("successMessage", "Rendez-vous pris avec succès");
        return "prendreRDV"; // Vue de succès après la création du rendez-vous
    }
    // Endpoint pour afficher les rendez-vous d'un client

    @GetMapping("/VoirRDV")
    public String getRendezVousPage(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email == null) {
            model.addAttribute("errorMessage", "Vous devez être connecté pour voir vos rendez-vous.");
            return "error";
        }

        Optional<Client> clientOptional = clientService.findByEmail(email);
        if (!clientOptional.isPresent()) {
            model.addAttribute("errorMessage", "Client non trouvé.");
            return "error";
        }

        Client client = clientOptional.get();
        List<RendezVous> rendezVousList = rendezVousService.getRendezVousByClientId(client.getId());
        model.addAttribute("rendezVousList", rendezVousList);
        return "voirRDV";
    }


    @PostMapping("/SupprimerRDV")
    public String supprimerRendezVous(@RequestParam("rendezVousId") Long rendezVousId) {
        rendezVousService.supprimerRendezVous(rendezVousId);
        return "redirect:/voirRDV";
    }


}