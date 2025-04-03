/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.web;

import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.dto.FeuilleSoinOPDDTO;
import com.FrameWork.MedLite.DMI.service.FeuilleSoinOPDService;
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
public class FeuilleSoinOPDRessource {
        private final FeuilleSoinOPDService feuilleSoinOPDService;

    public FeuilleSoinOPDRessource(FeuilleSoinOPDService feuilleSoinOPDService) {
        this.feuilleSoinOPDService = feuilleSoinOPDService;
    }

    
   
    
   
     @GetMapping("feuilleSoinOPD/findByCodeAdmission")
    public ResponseEntity<FeuilleSoinOPDDTO> getAllFeuilleSoinOPDByCodeAdmission(@RequestParam Integer codeAdmission ) { 
        return ResponseEntity.ok().body(feuilleSoinOPDService.findAllFeuilleSoinOPDByCodeAdmission( codeAdmission));
    }
 
    @PostMapping("feuilleSoinOPD")
    public ResponseEntity<FeuilleSoinOPDDTO> postFeuilleSoinOPD(@Valid @RequestBody FeuilleSoinOPDDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FeuilleSoinOPDDTO result = feuilleSoinOPDService.save(dto);
        return ResponseEntity.created(new URI("/api/dmi/" + result.getCode())).body(result);
    }
    
    
  

    @PutMapping("feuilleSoinOPD/update")
    public ResponseEntity<FeuilleSoinOPD> updateFeuilleSoinOPD(@RequestBody @Valid FeuilleSoinOPDDTO dto) throws URISyntaxException {
        FeuilleSoinOPD result = feuilleSoinOPDService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("feuilleSoinOPD/delete/{Code}")
    public ResponseEntity<FeuilleSoinOPD> deleteFeuilleSoinOPD(@PathVariable("Code") Integer code) {
        feuilleSoinOPDService.deleteFeuilleSoinOPD(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("feuilleSoinOPD/deleteByCodeAdmission/{Code}")
    public ResponseEntity<FeuilleSoinOPD> deleteFeuilleSoinOPDByCodeAdmission(@PathVariable("Code") Integer codeAdmission) {
        feuilleSoinOPDService.deleteFeuilleSoinOPDByCodeAdmission(codeAdmission);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
