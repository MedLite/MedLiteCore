/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.web;

import com.FrameWork.MedLite.Reception.domaine.Patient;
import com.FrameWork.MedLite.Reception.dto.PatientDTO;
import com.FrameWork.MedLite.Reception.service.PatientService;
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
public class PatientRessource {
    private final PatientService patientService;

    public PatientRessource(PatientService patientService) {
        this.patientService = patientService;
    }
    
    @GetMapping("patient/{code}")
    public ResponseEntity<PatientDTO> getPatientByCode(@PathVariable Integer code) {
        PatientDTO dto = patientService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
       @GetMapping("patient/q")
    public ResponseEntity<List<PatientDTO>> getPatientByCode(@RequestParam String nomPatient) {
        List<PatientDTO> dto = patientService.searchPatients(nomPatient);
        return ResponseEntity.ok().body(dto);
    }
    
    

    @GetMapping("patient/all")
    public ResponseEntity<List<PatientDTO>> getAllPatient() { 
        return ResponseEntity.ok().body(patientService.findAllPatient());
    }

    @PostMapping("patient")
    public ResponseEntity<PatientDTO> postPatient(@Valid @RequestBody PatientDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PatientDTO result = patientService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("patient/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid PatientDTO dto) throws URISyntaxException {
        Patient result = patientService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("patient/delete/{Code}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("Code") Integer code) {
        patientService.deletePatient(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
