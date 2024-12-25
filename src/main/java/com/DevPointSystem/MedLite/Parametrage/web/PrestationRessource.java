/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Authentification.dto.AccessUserDTO;
import com.DevPointSystem.MedLite.Authentification.service.AccessUserService;
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.SocDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.paramDTO;
import com.DevPointSystem.MedLite.Parametrage.service.ParamService;
import com.DevPointSystem.MedLite.Parametrage.service.PrestationService;
import com.DevPointSystem.MedLite.Parametrage.service.SocService; 
import com.google.common.base.Preconditions;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class PrestationRessource {
    
     private final PrestationService prestationService;
    private final ParamService paramService;
    private final SocService societeService;

    private final AccessUserService accessUserService;

    public PrestationRessource(PrestationService prestationService, ParamService paramService, SocService societeService, AccessUserService accessUserService) {
        this.prestationService = prestationService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("alimentation_caisse/{code}")
    public ResponseEntity<PrestationDTO> getPrestationByCode(@PathVariable Integer code) {
        PrestationDTO dTO = prestationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("alimentation_caisse/all")
    public ResponseEntity<List<PrestationDTO>> getAllPrestation() {
        return ResponseEntity.ok().body(prestationService.findAllPrestation());
    }
 
    @PostMapping("alimentation_caisse")
    public ResponseEntity<PrestationDTO> postPrestation(@Valid @RequestBody PrestationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PrestationDTO result = prestationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("alimentation_caisse/update")
//    public ResponseEntity<PrestationDTO> updatePrestation(@Valid @RequestBody PrestationDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        PrestationDTO result = prestationService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("alimentation_caisse/update")
    public ResponseEntity<PrestationDTO> updatePrestation(@Valid @RequestBody PrestationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PrestationDTO result = prestationService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }
 
    @DeleteMapping("alimentation_caisse/delete/{code}")
    public ResponseEntity<Prestation> deletePrestation(@PathVariable("Code") Integer code) {
        prestationService.deletePrestation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("alimentation_caisse/edition/{code}")
//    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {
//
//        Collection<DetailsPrestationDTO> dto = prestationService.findOneWithDetails(code);
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        PrestationDTO rslt = prestationService.findOne(code);
//
//        Preconditions.checkArgument(rslt.getCodeUserApprouver() != null, "error.User.Approuve.Found");
//        AccessUserDTO getsignature = accessUserService.findOneByCode(rslt.getCodeUserApprouver());
//
//        SocDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("ItemDataSource", new JRBeanCollectionDataSource(dto));
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr());
//        params.put("typeRecette", rslt.getDetailsPrestationDTOs().iterator().next().getDesignationArTypeRecette());
//        params.put("caisse", rslt.getCaisseDTO().getDesignationAr());
//        params.put("modeReglement", rslt.getModeReglementDTO().getDesignationAr());
//        params.put("montant", rslt.getMontant());
//        params.put("montantEnDevise", rslt.getMontantEnDevise());
//        params.put("tauxChange", rslt.getTauxChange());
//        params.put("Observation", rslt.getObservation());
//        params.put("dateCreate", rslt.getDateCreate());
//        params.put("designationEtatApprouve", rslt.getEtatApprouverDTO().getDesignation());
//
//        params.put("logo", societeDTO.getLogo());
//        params.put("signature", getsignature.getSignature());
//
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//        JRPdfExporter exporter = new JRPdfExporter();
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
//        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
//        reportConfig.setSizePageToContent(true);
//        reportConfig.setForceLineBreakPolicy(false);
//        exporter.exportReport();
//        var res = pdfOutputStream.toByteArray();
//        var headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename= filename.pdf");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(res);
//    }
//
//    @GetMapping("alimentation_caisse/excel/{code}")
//    public ResponseEntity<byte[]> getReportExcel(@PathVariable Integer code) throws Exception {
//
//        Collection<DetailsPrestationDTO> dto = prestationService.findOneWithDetails(code);
//
//        String fileNameJrxml = "src/main/resources/Reports/AlimentCaisse.jrxml";
//        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
//        PrestationDTO rslt = prestationService.findOne(code);
//
//        Preconditions.checkArgument(rslt.getCodeUserApprouver() != null, "error.User.Approuve.Found");
//        AccessUserDTO getsignature = accessUserService.findOneByCode(rslt.getCodeUserApprouver());
//
//        SocDTO societeDTO = societeService.findOne(1);
//        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("ItemDataSource", new JRBeanCollectionDataSource(dto));
//        params.put("UserCreate", auth.getName());
//        params.put("CodeSaisie", rslt.getCodeSaisie());
//        params.put("societe", dTOs.getValeur());
//        params.put("devise", rslt.getDeviseDTO().getDesignationAr());
//        params.put("typeRecette", rslt.getDetailsPrestationDTOs().iterator().next().getDesignationArTypeRecette());
//        params.put("caisse", rslt.getCaisseDTO().getDesignationAr());
//        params.put("modeReglement", rslt.getModeReglementDTO().getDesignationAr());
//        params.put("montant", rslt.getMontant());
//        params.put("montantEnDevise", rslt.getMontantEnDevise());
//        params.put("tauxChange", rslt.getTauxChange());
//        params.put("Observation", rslt.getObservation());
//        params.put("dateCreate", rslt.getDateCreate());
//        params.put("designationEtatApprouve", rslt.getEtatApprouverDTO().getDesignation());
//
//        params.put("logo", societeDTO.getLogo());
//        params.put("signature", getsignature.getSignature());
//
//        final Map<String, Object> parameters = new HashMap<>();
//        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
//
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//        configuration.setOnePagePerSheet(true);
//        configuration.setDetectCellType(true); // Detect cell types (date and etc.)
//        configuration.setWhitePageBackground(false); // No white background!
// 
//
//        // No spaces between rows and columns 
//        configuration.setRemoveEmptySpaceBetweenRows(true);
//        configuration.setRemoveEmptySpaceBetweenColumns(true);
//        configuration.setFontSizeFixEnabled(true);
//
//        JRXlsxExporter exporter = new JRXlsxExporter();
//        exporter.setConfiguration(configuration);
//        ByteArrayOutputStream excelOutputStream = new ByteArrayOutputStream();
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(excelOutputStream));
//
//        exporter.exportReport();
//
//        byte[] excelData = excelOutputStream.toByteArray();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
//        headers.add("Content-Disposition", "attachment; filename=report.xls");
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(excelMediaType)
//                .body(excelData);
//    }

}
