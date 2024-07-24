package com.gestionCabinetMedical.gestionCabinetMedical.Repositoty;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);


    Optional<Client> findById(Long aLong);

    Client findByCin(String cin);

    boolean existsByEmailAndMdp(String email, String mdp);
}

