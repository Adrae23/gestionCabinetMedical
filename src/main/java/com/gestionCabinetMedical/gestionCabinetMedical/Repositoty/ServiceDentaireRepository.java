package com.gestionCabinetMedical.gestionCabinetMedical.Repositoty;


import com.gestionCabinetMedical.gestionCabinetMedical.Model.ServiceDentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ServiceDentaireRepository extends JpaRepository<ServiceDentaire, Long> {

    Optional<ServiceDentaire> findById(Long aLong);


}
