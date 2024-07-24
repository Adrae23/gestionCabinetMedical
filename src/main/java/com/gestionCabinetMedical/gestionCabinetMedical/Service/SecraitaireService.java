package com.gestionCabinetMedical.gestionCabinetMedical.Service;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Secretaire;
import com.gestionCabinetMedical.gestionCabinetMedical.Repositoty.SecretaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecraitaireService {
    @Autowired
    private SecretaireRepository secretaireRepository;
    public boolean authenticate(String email, String mdp) {
        Optional<Secretaire> secOptional = secretaireRepository.findByEmail(email);
        if (secOptional.isPresent()) {
            return secretaireRepository.existsByEmailAndMdp(email, mdp);
        }
        return false;
    }

}
