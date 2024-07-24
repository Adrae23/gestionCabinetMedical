package com.gestionCabinetMedical.gestionCabinetMedical.Service;


import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Medecin;

import com.gestionCabinetMedical.gestionCabinetMedical.Repositoty.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MedecinService {
    @Autowired
    private MedecinRepository medecinrepository;

    public boolean authenticate(String email, String mdp) {
        Optional<Medecin> medOptional = medecinrepository.findByEmail(email);
        if (medOptional.isPresent()) {
            return medecinrepository.existsByEmailAndMdp(email, mdp);
        }
        return false;
    }

    public String createMedecin(Medecin medecin) {
        medecinrepository.save(medecin);
        return "le medecin est creer avec succes";
    }

    public Optional<Medecin> getMedecin(Long id) {
        return medecinrepository.findById(id);
    }

    public List<Medecin> getAllMedecins() {
        return medecinrepository.findAll();

    }

    public boolean isPresent(Long id) {
        List<Medecin> Medecins = getAllMedecins();
        for (Medecin medecin : Medecins) {
            if (Objects.equals(medecin.getIdMedecin(), id)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Medecin> findByEmail(String email) {
        return medecinrepository.findByEmail(email);
    }
}