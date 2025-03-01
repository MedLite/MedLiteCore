/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.dto.ConventionDTO;
import com.FrameWork.MedLite.Parametrage.service.ConventionService;
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
@RequestMapping("/api/parametrage/")
public class ConventionRessource {
     private final ConventionService conventionService;

    public ConventionRessource(ConventionService conventionService) {
        this.conventionService = conventionService;
    }

    @GetMapping("convention/{code}")
    public ResponseEntity<ConventionDTO> getConventionByCode(@PathVariable Integer code) {
        ConventionDTO dto = conventionService.findOne(code);
        return ResponseEntity.ok().body(dto);
    } 
   
    
     @GetMapping("convention/BySociete")
    public ResponseEntity<List<ConventionDTO>> getConventionByCodeSociete(@RequestParam Integer code) { 
        return ResponseEntity.ok().body(conventionService.findConventionByCodeSociete(code));
    } 
    
      
    @GetMapping("convention/findBy")
    public ResponseEntity<List<ConventionDTO>> getAllMedecingetAllConventionByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(conventionService.findAllConventionByActif(actif));
    }

    

    @GetMapping("convention/all")
    public ResponseEntity<List<ConventionDTO>> getAllConvention() { 
        return ResponseEntity.ok().body(conventionService.findAllConvention());
    }

    @PostMapping("convention")
    public ResponseEntity<ConventionDTO> postConvention(@Valid @RequestBody ConventionDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ConventionDTO result = conventionService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("convention/update")
    public ResponseEntity<Convention> updateConvention(@RequestBody @Valid ConventionDTO dto) throws URISyntaxException {
        Convention result = conventionService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("convention/delete/{Code}")
    public ResponseEntity<Convention> deleteConvention(@PathVariable("Code") Integer code) {
        conventionService.deleteConvention(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
