/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.service.AdmissionService;
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
@RequestMapping("/api/reception/")
public class AdmissionRessource {

    private final AdmissionService admissionService;

    public AdmissionRessource(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @GetMapping("admission/{code}")
    public ResponseEntity<AdmissionDTO> getAdmissionByCode(@PathVariable Integer code) {
        AdmissionDTO dTO = admissionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("admission/all")
    public ResponseEntity<List<AdmissionDTO>> getAllAdmission() {
        return ResponseEntity.ok().body(admissionService.findAllAdmission());
    }

    @GetMapping("admission/findByCodeNatureAdmission")
    public ResponseEntity<List<AdmissionDTO>> getAllAdmissionByCodeNatureAdmission(@RequestParam Integer codeNatureAdmission) {
        return ResponseEntity.ok().body(admissionService.findAllAdmissionByCodeNatureAdmission(codeNatureAdmission));
    }
    
    
     @GetMapping("admission/findForOPD")
    public ResponseEntity<List<AdmissionDTO>> getAllAdmissionForOPD( ) {
        return ResponseEntity.ok().body(admissionService.findAllAdmissionForOPD());
    }
     @GetMapping("admission/findByCodePatient")
    public ResponseEntity<List<AdmissionDTO>> getAllAdmissionByCodePatient(@RequestParam Integer codePatient) {
        return ResponseEntity.ok().body(admissionService.findAllAdmissionByCodePatient(codePatient));
    }

    @GetMapping("admission/findByCodeNatureAdmissionAndCodeMedecin")
    public ResponseEntity<List<AdmissionDTO>> getAllAdmissionByCodeNatureAdmissionAndCodeMedecin(@RequestParam Integer codeNatureAdmission, @RequestParam Integer codeMedecin) {
        return ResponseEntity.ok().body(admissionService.findAllAdmissionByCodeNatureAdmissionAndCodeMedecin(codeNatureAdmission, codeMedecin));
    }

    @PostMapping("admission")
    public ResponseEntity<AdmissionDTO> postAdmission(@Valid @RequestBody AdmissionDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        AdmissionDTO result = admissionService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("admission/update")
    public ResponseEntity<AdmissionDTO> updateAdmission(@Valid @RequestBody AdmissionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AdmissionDTO result = admissionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("admission/delete/{code}")
    public ResponseEntity<Admission> deleteAdmission(@PathVariable("Code") Integer code) {
        admissionService.deleteAdmission(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
