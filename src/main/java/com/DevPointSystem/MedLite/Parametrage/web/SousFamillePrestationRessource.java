/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.SousFamillePrestation;
import com.DevPointSystem.MedLite.Parametrage.dto.SousFamillePrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.service.SousFamillePrestationService;
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
public class SousFamillePrestationRessource {
     private final SousFamillePrestationService sousFamillePrestationService;

    public SousFamillePrestationRessource(SousFamillePrestationService sousFamillePrestationService) {
        this.sousFamillePrestationService = sousFamillePrestationService;
    }

    @GetMapping("sous_famille_prestation/{code}")
    public ResponseEntity<SousFamillePrestationDTO> getSousFamillePrestationByCode(@PathVariable Integer code) {
        SousFamillePrestationDTO dto = sousFamillePrestationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("sous_famille_prestation/all")
    public ResponseEntity<List<SousFamillePrestationDTO>> getAllSousFamillePrestation() {
        return ResponseEntity.ok().body(sousFamillePrestationService.findAllSousFamillePrestation());
    }

    
     @GetMapping("sous_famille_prestation/FindBy")
    public ResponseEntity<List<SousFamillePrestationDTO>> getAllSousFamillePrestation(@RequestParam Integer codeFamillePrestation) {
        return ResponseEntity.ok().body(sousFamillePrestationService.findAllSousFamillePrestationByCodeFamille(codeFamillePrestation));
    }
    
    
    @PostMapping("sous_famille_prestation")
    public ResponseEntity<SousFamillePrestationDTO> postSousFamillePrestation(@Valid @RequestBody SousFamillePrestationDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        SousFamillePrestationDTO result = sousFamillePrestationService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("sous_famille_prestation/update")
    public ResponseEntity<SousFamillePrestation> updateSousFamillePrestation(@RequestBody @Valid SousFamillePrestationDTO dto) throws URISyntaxException {
        SousFamillePrestation result = sousFamillePrestationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("sous_famille_prestation/delete/{Code}")
    public ResponseEntity<SousFamillePrestation> deleteSousFamillePrestation(@PathVariable("Code") Integer code) {
        sousFamillePrestationService.deleteSousFamillePrestation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
