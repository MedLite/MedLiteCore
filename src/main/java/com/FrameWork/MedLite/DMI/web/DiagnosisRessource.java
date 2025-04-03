/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.web;

import com.FrameWork.MedLite.DMI.domaine.Diagnosis;
import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.dto.DiagnosisDTO;
import com.FrameWork.MedLite.DMI.factory.CheifComplaintFactory;
import com.FrameWork.MedLite.DMI.factory.DiagnosisFactory;
import com.FrameWork.MedLite.DMI.repository.FeuilleSoinOPDRepo;
import com.FrameWork.MedLite.DMI.service.DiagnosisService;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
@RequestMapping("/api/dmi/")
public class DiagnosisRessource {
        private final DiagnosisService diagnosisService;
   private final FeuilleSoinOPDRepo feuilleSoinOPDRepo;

    public DiagnosisRessource(DiagnosisService diagnosisService, FeuilleSoinOPDRepo feuilleSoinOPDRepo) {
        this.diagnosisService = diagnosisService;
        this.feuilleSoinOPDRepo = feuilleSoinOPDRepo;
    }
    

    
   
    
   
     @GetMapping("diagnosis/findByCodeAdmission")
    public ResponseEntity<List<DiagnosisDTO>> getAllDiagnosisByCodeAdmission(@RequestParam Integer codeAdmission ) { 
        return ResponseEntity.ok().body(diagnosisService.findAllDiagnosisByCodeAdmission( codeAdmission));
    }
 
    @PostMapping("diagnosis")
    public ResponseEntity<DiagnosisDTO> postDiagnosis(@Valid @RequestBody DiagnosisDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        DiagnosisDTO result = diagnosisService.save(dto);
           FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(dto.getCodeAdmission());
        if (feuilleSoinOPD != null) {

            feuilleSoinOPD.setCodeDiagnosis(result.getCode());
            if (result.getCode() != null) {
                feuilleSoinOPD.setDiagnosis(DiagnosisFactory.createDiagnosisByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(feuilleSoinOPD);
        } else {
            FeuilleSoinOPD newfeuilleSoinOPD = new FeuilleSoinOPD();

            newfeuilleSoinOPD.setCode(dto.getCode());

            newfeuilleSoinOPD.setCodeAdmission(result.getCodeAdmission());
            if (result.getCodeAdmission() != null) {
                newfeuilleSoinOPD.setAdmission(AdmissionFactory.createAdmissionByCode(result.getCodeAdmission()));
            }

            newfeuilleSoinOPD.setCodeDiagnosis(result.getCode());
            if (result.getCode() != null) {
                newfeuilleSoinOPD.setDiagnosis(DiagnosisFactory.createDiagnosisByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(newfeuilleSoinOPD);

        }
        return ResponseEntity.created(new URI("/api/dmi/" + result.getCode())).body(result);
    }
    
    
  

//    @PutMapping("diagnosis/update")
//    public ResponseEntity<Diagnosis> updateDiagnosis(@RequestBody @Valid DiagnosisDTO dto) throws URISyntaxException {
//        Diagnosis result = diagnosisService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }

    @DeleteMapping("diagnosis/delete/{Code}")
    public ResponseEntity<Diagnosis> deleteDiagnosis(@PathVariable("Code") Integer code) {
        diagnosisService.deleteDiagnosis(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("diagnosis/deleteByCodeAdmission/{Code}")
    public ResponseEntity<Diagnosis> deleteDiagnosisByCodeAdmission(@PathVariable("Code") Integer codeAdmission) {
            FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(codeAdmission);
        feuilleSoinOPD.setCodeDiagnosis(null);

        feuilleSoinOPD.setDiagnosis(null);
 
        feuilleSoinOPDRepo.save(feuilleSoinOPD);
        diagnosisService.deleteDiagnosisByCodeAdmission(codeAdmission);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
