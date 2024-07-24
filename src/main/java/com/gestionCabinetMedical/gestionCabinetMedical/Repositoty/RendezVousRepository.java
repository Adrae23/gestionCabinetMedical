package com.gestionCabinetMedical.gestionCabinetMedical.Repositoty;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    List<RendezVous> findByClientId(Long clientId);

    List<RendezVous> findByDateTimeAndMedecin_IdMedecin(LocalDate dateTime,Long id);
    List<RendezVous> findByDateTime(LocalDate dateTime);

    List<RendezVous> findAll();


}


