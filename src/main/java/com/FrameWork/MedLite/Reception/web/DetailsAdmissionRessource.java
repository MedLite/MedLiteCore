/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.web;

import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.DetailsAdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.PlanningCabinetDTO;
import com.FrameWork.MedLite.Reception.service.DetailsAdmissionService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/reception/")
public class DetailsAdmissionRessource {
    private final DetailsAdmissionService detailsAdmissionService;

    public DetailsAdmissionRessource(DetailsAdmissionService detailsAdmissionService) {
        this.detailsAdmissionService = detailsAdmissionService;
    }
    
     @GetMapping("details_admission/{code}")
    public ResponseEntity<DetailsAdmissionDTO> getDetailsAdmissionByCode(@PathVariable Integer code) {
        DetailsAdmissionDTO dTO = detailsAdmissionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_admission/all")
    public ResponseEntity<List<DetailsAdmissionDTO>> getAllDetailsAdmission() {
        return ResponseEntity.ok().body(detailsAdmissionService.findAllDetailsAdmission());
    }

  

   

    @PostMapping("details_admission")
    public ResponseEntity<DetailsAdmissionDTO> postDetailsAdmission(@Valid @RequestBody DetailsAdmissionDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        DetailsAdmissionDTO result = detailsAdmissionService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }
    
     @PostMapping("details_admission/List")
    public ResponseEntity<String> savePlanning(@RequestBody List<DetailsAdmissionDTO> detailsAdmissionDTOs) {
        List<DetailsAdmissionDTO> result = detailsAdmissionService.saveList(detailsAdmissionDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
 
    @PutMapping("details_admission/update")
    public ResponseEntity<DetailsAdmissionDTO> updateDetailsAdmission(@Valid @RequestBody DetailsAdmissionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsAdmissionDTO result = detailsAdmissionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

  
   

    @DeleteMapping("details_admission/delete/{code}")
    public ResponseEntity<DetailsAdmission> deleteDetailsAdmission(@PathVariable("Code") Integer code) {
        detailsAdmissionService.deleteDetailsAdmission(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
