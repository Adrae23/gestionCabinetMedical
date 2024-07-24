package com.gestionCabinetMedical.gestionCabinetMedical.Service;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.ServiceDentaire;
import com.gestionCabinetMedical.gestionCabinetMedical.Repositoty.ServiceDentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ServicesService {
    @Autowired
    private ServiceDentaireRepository serviceDentaireRepository;

    public Optional<ServiceDentaire> getServiceById(Long id){

        return serviceDentaireRepository.findById(id);


    }

}
