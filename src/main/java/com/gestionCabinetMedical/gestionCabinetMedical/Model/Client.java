package com.gestionCabinetMedical.gestionCabinetMedical.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;


@Data // Génère les getters, setters, toString, equals, et hashCode
@NoArgsConstructor // Génère un constructeur sans argument
@AllArgsConstructor // Génère un constructeur avec tous les arguments
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nom;
    private String cin;
    private String adresse;
    private String email;
    private String telephone;
    private String genre;
    private LocalDate dateNaissance;
    private String mdp;

    public Client(String nom, String s, String mail, String number,String genre,LocalDate naiss, String mdp) {
        this.nom=nom;
        this.adresse=s;
        this.email=mail;
        this.telephone=number;
        this.genre=genre;
        this.dateNaissance=naiss;
        this.mdp=mdp;
    }

    // Méthode pour valider l'email (simple validation)
    public boolean isEmailValid() {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Méthode pour valider le numéro de téléphone (simple validation)
    public boolean isTelephoneValid() {
        return telephone != null && telephone.matches("\\d+");
    }

}
