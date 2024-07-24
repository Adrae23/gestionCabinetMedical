package com.gestionCabinetMedical.gestionCabinetMedical.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "secretaire")
public class Secretaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_secretaire")
    private Long idSecretaire;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String Login;
    private String mdp;

}
