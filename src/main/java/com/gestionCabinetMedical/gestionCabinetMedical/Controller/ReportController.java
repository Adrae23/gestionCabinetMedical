package com.gestionCabinetMedical.gestionCabinetMedical.Controller;

import com.gestionCabinetMedical.gestionCabinetMedical.Service.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {

    @Autowired
    private JasperReportService jasperReportService;

    @GetMapping("/reportclient/{clientId}")
    public ResponseEntity<byte[]> generateClientReport(@PathVariable Long clientId) {
        try {
            byte[] pdfReport = jasperReportService.generateReport(clientId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "rapport_client.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfReport);
        } catch (JRException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
