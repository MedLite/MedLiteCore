/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
//import com.FrameWork.MedLite.Authentification.web.Response.ErrorResponse;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.domaine.PrestationMedecinConsultation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationMedecinConsultationDTO;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.service.DetailsPrestationService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PrestationMedecinConsultationService;
import com.FrameWork.MedLite.Parametrage.service.PrestationService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final Logger log = LoggerFactory.getLogger(PrestationRessource.class);

    private final PrestationService prestationService;
    private final ParamService paramService;
    private final SocService societeService;

//    private final AccessUserService accessUserService;
    private final DetailsPrestationService detailsPrestationService;
    private final PrestationMedecinConsultationService prestationMedecinConsultationService;

    public PrestationRessource(PrestationService prestationService, ParamService paramService, SocService societeService, DetailsPrestationService detailsPrestationService, PrestationMedecinConsultationService prestationMedecinConsultationService) {
        this.prestationService = prestationService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.detailsPrestationService = detailsPrestationService;
        this.prestationMedecinConsultationService = prestationMedecinConsultationService;
    }

 

    @GetMapping("prestation/{code}")
    public ResponseEntity<PrestationDTO> getPrestationByCode(@PathVariable Integer code) {
        PrestationDTO dTO = prestationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_prestation/{code}")
    public ResponseEntity<Collection<DetailsPrestationDTO>> getDetailsPrestationByCode(@PathVariable Integer code) {
        Collection<DetailsPrestationDTO> dTO = detailsPrestationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("prestation/FindByCodeIn")
    public ResponseEntity<List<PrestationDTO>> getAllPrestationByCodeIn(@RequestParam List<Integer> code) {
        return ResponseEntity.ok().body(prestationService.findAllPrestationByCodeIn(code));
    }

//   
    @GetMapping("prestation/findBy")
    public ResponseEntity<List<PrestationDTO>> getAllPrestationByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(prestationService.findAllPrestationByActif(actif));
    }

    @GetMapping("prestation/prestationConsultation")
    public ResponseEntity<List<PrestationDTO>> getPrestationConsultation(@RequestParam Integer CodeNatureAdmission) {
        return ResponseEntity.ok().body(prestationService.findByCodeFamilleFacturationConsultation(CodeNatureAdmission));
    }
    
    @GetMapping("prestation/findBySousFamille")
    public ResponseEntity<List<PrestationDTO>> getPrestationBySousFamille(@RequestParam Integer codeSousFamille) {
        return ResponseEntity.ok().body(prestationService.findAllPrestationBySousFamille(codeSousFamille));
    }

    
        
    @GetMapping("prestation/findByTypePrestation")
    public ResponseEntity<List<PrestationDTO>> getPrestationByTypePrestation(@RequestParam Integer codeTypePrestation) {
        return ResponseEntity.ok().body(prestationService.findAllPrestationByTypePrestation(codeTypePrestation));
    }
    
    
    @GetMapping("details_prestation/By")
    public ResponseEntity<Collection<DetailsPrestationDTO>> getDetailsPrestationByCodeAndCodeNaureAdmission(@RequestParam Integer codePrestation, @RequestParam Integer codeNatureAdmission) {
        Collection<DetailsPrestationDTO> dTO = prestationService.findOneWithDetailsCodePrestationAndCodeNatureAdmission(codePrestation, codeNatureAdmission);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("prestation_consultation/codeMedecin")
    public ResponseEntity<PrestationMedecinConsultationDTO> getPrestationConsultationByCodeMedecin(@RequestParam Integer codeMedecin) {
        PrestationMedecinConsultationDTO dTO = prestationMedecinConsultationService.findByCodeMedecin(codeMedecin);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("prestation_consultation/medecinAndNatureAdmission")
    public ResponseEntity<PrestationMedecinConsultationDTO> getPrestationConsultationByCodeMedecinAndNatureAdmission(@RequestParam Integer codeMedecin, @RequestParam List<Integer> codeNatureAdmission) {
        PrestationMedecinConsultationDTO dTO = prestationMedecinConsultationService.findByCodeMedecinAndcodeNatureAdmimssion(codeMedecin, codeNatureAdmission);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("prestation/all")
    public ResponseEntity<List<PrestationDTO>> getAllPrestation() {
        return ResponseEntity.ok().body(prestationService.findAllPrestation());
    }

    @PostMapping("prestation")
    public ResponseEntity<PrestationDTO> postPrestation(@Valid @RequestBody PrestationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PrestationDTO result = prestationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }
//    @PostMapping("prestation")
//    public ResponseEntity<PrestationDTO> postPrestation(@RequestBody PrestationDTO prestationDTO) {
//        try {
//            PrestationDTO savedPrestationDTO = prestationService.save(prestationDTO);
//            return ResponseEntity.ok(savedPrestationDTO);
//        } catch (IllegalArgumentException | IllegalStateException e) {
////            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage())); // Custom Error Response
//            throw new IllegalArgumentException("error.Erorr");
//        } catch (Exception e) {
//            log.error("Error saving prestation", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PutMapping("prestation/update")
    public ResponseEntity<PrestationDTO> updatePrestation(@Valid @RequestBody PrestationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PrestationDTO result = prestationService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("prestation/delete/{Code}")
    public ResponseEntity<Prestation> deletePrestation(@PathVariable("Code") Integer code) {
        prestationService.deletePrestation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("alimentation_caisse/edition/{Code}")
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
//    @GetMapping("alimentation_caisse/excel/{Code}")
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
