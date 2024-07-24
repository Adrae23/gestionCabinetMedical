package com.gestionCabinetMedical.gestionCabinetMedical.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Medecin;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.Client;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.ServiceDentaire;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RendezVousDTO {
    private Long id;

    @JsonProperty("dateTime")
    private LocalDate dateTime;

    @JsonProperty("idMedecin")
    private Long medecinId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("clientId")
    private Long client;

    @JsonProperty("serviceId")
    private Long serviceId;


}
