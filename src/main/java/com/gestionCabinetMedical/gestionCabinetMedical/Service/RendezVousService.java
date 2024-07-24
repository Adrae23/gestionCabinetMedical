package com.gestionCabinetMedical.gestionCabinetMedical.Service;

import com.gestionCabinetMedical.gestionCabinetMedical.DTO.RendezVousDTO;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.*;
import com.gestionCabinetMedical.gestionCabinetMedical.Repositoty.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ClientService clientService;

    @Transactional
    public void createRendezVous(RendezVousDTO rendezVousDTO) {
        System.out.println("Début de la méthode createRendezVous");
        try {
            // Vérification des identifiants non nuls
            if (rendezVousDTO.getMedecinId() == null) {
                throw new IllegalArgumentException("L'identifiant du médecin ne doit pas être nul");
            }
            if (rendezVousDTO.getClient() == null) {
                throw new IllegalArgumentException("L'identifiant du client ne doit pas être nul");
            }
            if (rendezVousDTO.getServiceId() == null) {
                throw new IllegalArgumentException("L'identifiant du service ne doit pas être nul");
            }
            
            // Création d'un nouvel objet RendezVous
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDateTime(rendezVousDTO.getDateTime());
            rendezVous.setStatus(rendezVousDTO.getStatus());

            // Récupération du service via le service ServicesService
            Optional<ServiceDentaire> serviceOpt = servicesService.getServiceById(rendezVousDTO.getServiceId());
            if (serviceOpt.isPresent()) {
                rendezVous.setService(serviceOpt.get());
                System.out.println("Service trouvé: " + serviceOpt.get());
            } else {
                System.out.println("Service introuvable, ID: " + rendezVousDTO.getServiceId());
                throw new RuntimeException("Service introuvable");
            }

            // Récupération du client via le service ClientService
            Optional<Client> clientOpt = clientService.findById(rendezVousDTO.getClient());
            if (clientOpt.isPresent()) {
                rendezVous.setClient(clientOpt.get());
                System.out.println("Client trouvé: " + clientOpt.get());
            } else {
                System.out.println("Client introuvable, ID: " + rendezVousDTO.getClient());
                throw new RuntimeException("Client introuvable");
            }

            // Récupération du médecin via le service MedecinService
            Optional<Medecin> medecinOpt = medecinService.getMedecin(rendezVousDTO.getMedecinId());
            if (medecinOpt.isPresent()) {
                rendezVous.setMedecin(medecinOpt.get());
                System.out.println("Médecin trouvé: " + medecinOpt.get());
            } else {
                System.out.println("Médecin introuvable, ID: " + rendezVousDTO.getMedecinId());
                throw new RuntimeException("Médecin introuvable");
            }

            // Recherche des absences du médecin


            // Log avant de sauvegarder le rendez-vous
            System.out.println("Sauvegarde du rendez-vous: " + rendezVous);

            // Sauvegarde du rendez-vous dans le repository
            rendezVousRepository.save(rendezVous);

            // Log après la sauvegarde
            System.out.println("Rendez-vous sauvegardé: " + rendezVous);

        } catch (Exception e) {
            System.out.println("Erreur lors de la création du rendez-vous: " + e.getMessage());
            e.printStackTrace();
            // Vous pouvez lancer une exception spécifique ou traiter l'erreur selon votre besoin
            throw new RuntimeException("Erreur lors de la création du rendez-vous", e);
        }
    }
    public List<RendezVous> getRendezVousByClientId(Long clientId) {
        List<RendezVous> rendezVousList = rendezVousRepository.findByClientId(clientId);
        for (RendezVous rendezVous : rendezVousList) {
            System.out.println("Rendez-vous trouvé pour le client " + clientId + ": " + rendezVous);
        }
        return rendezVousList;
    }



    public void supprimerRendezVous(Long rendezVousId) {
        rendezVousRepository.deleteById(rendezVousId);
    }

    public List<RendezVous> getRendezVousByIDAndDate(Long idMedecin) {
        return rendezVousRepository.findByDateTimeAndMedecin_IdMedecin(LocalDate.now(),idMedecin) ;
    }

    public List<RendezVous> getRendezVousByDate(LocalDate date) {
        return rendezVousRepository.findByDateTime(date) ;
    }
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll() ;
    }


    public List<RendezVous> getRendezVousByCin(String cin) {
        Client client = clientService.getByClientCin(cin);
        return rendezVousRepository.findByClientId(client.getId());
    }
}
