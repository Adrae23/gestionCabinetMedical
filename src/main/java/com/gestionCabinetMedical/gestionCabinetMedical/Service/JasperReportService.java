package com.gestionCabinetMedical.gestionCabinetMedical.Service;

import com.gestionCabinetMedical.gestionCabinetMedical.Model.RendezVous;
import com.gestionCabinetMedical.gestionCabinetMedical.Model.ServiceDentaire;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JasperReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RendezVousService rendezVousService;
    @Autowired
    private ServicesService servicesService;

    public byte[] generateReport(Long clientId) throws JRException {
        String jrxmlPath = "C:/Users/lenovo/JaspersoftWorkspace/MyReports/rapport_client.jrxml";

        // Compiler le rapport
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

        // Passer les paramètres au rapport
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("clientId", clientId);

        List<RendezVous> list = rendezVousService.getRendezVousByClientId(clientId);

        BigDecimal prixTot = BigDecimal.ZERO;

        for (RendezVous rdv : list) {
            Optional<ServiceDentaire> servicePrix = servicesService.getServiceById(rdv.getService().getIdService());
            prixTot = prixTot.add(servicePrix.get().getPrix());
        }

        parameters.put("prixTot", prixTot);

        // Obtenir la connexion
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            // Remplir le rapport avec des données
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Erreur lors de la génération du rapport", e);
        }
    }
}
