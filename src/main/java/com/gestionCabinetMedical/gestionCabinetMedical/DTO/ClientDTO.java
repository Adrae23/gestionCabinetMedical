package com.gestionCabinetMedical.gestionCabinetMedical.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClientDTO {
    @JsonProperty("id")
    @NotNull
    private Long id;

    @JsonProperty("nom")
    @NotNull
    private String nom;

    @JsonProperty("cin")
    @NotNull
    private String cin;

    @JsonProperty("adresse")
    @NotNull
    private String adresse;

    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("genre")
    @NotNull
    private String genre;

    @JsonProperty("dateNaissance")
    @NotNull
    private LocalDate dateNaissance;

    @JsonProperty("mdp")
    @NotNull
    private String mdp;
}
