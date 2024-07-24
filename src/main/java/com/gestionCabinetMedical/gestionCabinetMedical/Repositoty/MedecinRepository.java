package com.gestionCabinetMedical.gestionCabinetMedical.Repositoty;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Medecin;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository <Medecin,Long> {

    Optional<Medecin> findById(Long aLong);

    Optional<Medecin> findByEmail(String email);


    boolean existsByEmailAndMdp(String email, String mdp);
}
