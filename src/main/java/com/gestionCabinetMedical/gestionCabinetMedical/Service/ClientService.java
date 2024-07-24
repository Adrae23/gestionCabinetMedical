package com.gestionCabinetMedical.gestionCabinetMedical.Service;

import com.gestionCabinetMedical.gestionCabinetMedical.DTO.ClientDTO;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import com.gestionCabinetMedical.gestionCabinetMedical.Repositoty.ClientRepository;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public boolean isPresent(Long id) {
        List<Client> clients = getAllClients();
        for (Client client : clients) {
            if (Objects.equals(client.getId(), id)) {
                return true;
            }
        }
        return false;
    }
    public void addClient(ClientDTO clientDTO) {
        // Log des valeurs reçues
        System.out.println("ClientDTO reçu : " + clientDTO);

        // Vérifier si l'email existe déjà dans la base de données
        Optional<Client> emailExist = clientRepository.findByEmail(clientDTO.getEmail());
        if (emailExist != null) {
            throw new IllegalArgumentException("L'email existe déjà.");
        }

        Client client = new Client();
        client.setNom(clientDTO.getNom());
        client.setAdresse(clientDTO.getAdresse());
        client.setEmail(clientDTO.getEmail());
        client.setTelephone(clientDTO.getTelephone());
        client.setGenre(clientDTO.getGenre());
        client.setDateNaissance(clientDTO.getDateNaissance());

        // Vérifier que le mot de passe n'est pas null
        String motDePasse = clientDTO.getMdp();
        if (motDePasse == null) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être null.");
        }
        client.setMdp(motDePasse);

        clientRepository.save(client);
    }

    @Transactional
    public void updateClient(ClientDTO client) {
        Optional<Client> existingClient = clientRepository.findById(client.getId());
        if (existingClient.isPresent()) {
            Client c = existingClient.get();
            c.setAdresse(client.getAdresse());
            c.setEmail(client.getEmail());
            c.setTelephone(client.getTelephone());
            c.setDateNaissance(client.getDateNaissance());
            clientRepository.save(c);
        }
    }
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public boolean authenticate(String email, String mdp) {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isPresent()) {
            return clientRepository.existsByEmailAndMdp(email, mdp);
        }
        return false;
    }

    public Client getByClientCin(String cin) {
        return clientRepository.findByCin(cin);
    }
}

