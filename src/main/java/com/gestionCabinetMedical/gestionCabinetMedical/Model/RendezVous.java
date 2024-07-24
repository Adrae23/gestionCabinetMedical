package com.gestionCabinetMedical.gestionCabinetMedical.Model;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Medecin;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.ServiceDentaire;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rendezvous")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time",nullable = false)
    private LocalDate dateTime;


    @OneToOne
    @JoinColumn(name = "medecin_id", nullable = false)
    private Medecin medecin;

    @Column(nullable = false)
    private String status;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_service", nullable = false)
    private ServiceDentaire service;
}
