/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.web;

import com.FrameWork.MedLite.Authentification.dto.AccessUserDTO;
import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.MedLite.Examen.domaine.Examen;
import com.FrameWork.MedLite.Examen.dto.DetailsExamenDTO;
import com.FrameWork.MedLite.Examen.dto.ExamenDTO;
import com.FrameWork.MedLite.Examen.service.ExamenService;
import com.FrameWork.MedLite.Parametrage.dto.SignatureMedecinDTO;
import com.FrameWork.MedLite.Parametrage.dto.SocDTO;
import com.FrameWork.MedLite.Parametrage.dto.paramDTO;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.SignatureMedecinService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
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
@RequestMapping("/api/examen/")
public class ExamenRessource {

    private final ExamenService examenService;
    private final ParamService paramService;
    private final SignatureMedecinService signatureMedecinService;
    private final SocService socService;

    public ExamenRessource(ExamenService examenService, ParamService paramService, SignatureMedecinService signatureMedecinService, SocService socService) {
        this.examenService = examenService;
        this.paramService = paramService;
        this.signatureMedecinService = signatureMedecinService;
        this.socService = socService;
    }

    @GetMapping("examen/{code}")
    public ResponseEntity<ExamenDTO> getExamenByCode(@PathVariable Integer code) {
        ExamenDTO dto = examenService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("examen/findWithDetails/{code}")
    public ResponseEntity<Collection<DetailsExamenDTO>> getExamenByCodeWithDetails(@PathVariable Integer code) {
        Collection<DetailsExamenDTO> dto = examenService.findOneWithDetails(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("examen/all")
    public ResponseEntity<List<ExamenDTO>> getAllExamen() {
        return ResponseEntity.ok().body(examenService.findAllExamen());
    }

    @GetMapping("examen/findByTypeExamenAndCodeAdmission")
    public ResponseEntity<List<ExamenDTO>> getAllExamenByTypeExamenAndCodeAdmission(@RequestParam String typeExamen, @RequestParam Integer codeAdmission) {
        return ResponseEntity.ok().body(examenService.findAllExamenByTypeExmaneAndCodeAdmission(typeExamen, codeAdmission));
    }

    @PostMapping("examen")
    public ResponseEntity<ExamenDTO> postExamen(@Valid @RequestBody ExamenDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ExamenDTO result = examenService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PostMapping("examen/List")
    public ResponseEntity<String> saveReglementList(@RequestBody List<ExamenDTO> examenDTOs) {
        List<ExamenDTO> result = examenService.saveList(examenDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("examen/update")
    public ResponseEntity<Examen> updateExamen(@RequestBody @Valid ExamenDTO dto) throws URISyntaxException {
        Examen result = examenService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("examen/delete/{Code}")
    public ResponseEntity<Examen> deleteExamen(@PathVariable("Code") Integer code) {
        examenService.deleteExamen(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("examen/edition/{code}")
    public ResponseEntity<byte[]> getReport(@PathVariable Integer code) throws Exception {

        Collection<DetailsExamenDTO> dto = examenService.findOneWithDetails(code);

        String fileNameJrxml = "src/main/resources/Reports/Examen.jrxml";
        paramDTO dTOs = paramService.findParamByCodeParamS("NomSociete");
        ExamenDTO rslt = examenService.findOne(code);
         long codeMedecinLong = rslt.getCodeMedecinDemande();
  
        Preconditions.checkArgument(rslt.getCodeMedecinDemande() != null, "error.UserNotFound");

        int codeMedecinInt = (int) codeMedecinLong;
        SignatureMedecinDTO getsignature = signatureMedecinService.findOneByCodeMedecin(codeMedecinInt);

        SocDTO societeDTO = socService.findOne(1);
        JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

      
        Map<String, Object> params = new HashMap<>();
        params.put("ItemDataSource", new JRBeanCollectionDataSource(dto));
        params.put("UserCreate", auth.getName());
        params.put("CodeSaisie", rslt.getCodeSaisie());
        params.put("societe", dTOs.getValeur());
        params.put("designationArPrestation", dto.iterator().next().getDesignationArPrestation());
        params.put("codeSaisiePrestation", dto.iterator().next().getCodeSaisiePrestation());

        params.put("logo", societeDTO.getLogo());
        params.put("signature", getsignature.getSignature());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
        exporter.exportReport();
        var res = pdfOutputStream.toByteArray();
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename= filename.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(res);
    }

}
