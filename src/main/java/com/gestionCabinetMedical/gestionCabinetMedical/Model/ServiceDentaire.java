package com.gestionCabinetMedical.gestionCabinetMedical.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "services")
public class ServiceDentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long idService;

    @Column(name = "nom_service")
    private String nomService;
    private String description;

    @Column(name = "prix")
    private BigDecimal prix;



}
