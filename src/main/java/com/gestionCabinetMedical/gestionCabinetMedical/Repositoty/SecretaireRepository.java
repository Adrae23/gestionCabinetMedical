package com.gestionCabinetMedical.gestionCabinetMedical.Repositoty;


import com.gestionCabinetMedical.gestionCabinetMedical.Model.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecretaireRepository extends JpaRepository<Secretaire,Long> {
    Optional<Secretaire> findByEmail(String email);


    //Optional<Secretaire> findByIdSecraitaire(Long aLong);

    boolean existsByEmailAndMdp(String email, String mdp);
}
