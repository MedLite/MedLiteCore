/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamillePrestation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamillePrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.service.FamillePrestationService;
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
@RequestMapping("/api/parametrage/")
public class FamillePrestationRessource {

    private final FamillePrestationService famillePrestationService;

    public FamillePrestationRessource(FamillePrestationService famillePrestationService) {
        this.famillePrestationService = famillePrestationService;
    }

    @GetMapping("fammille_prestation/{code}")
    public ResponseEntity<FamillePrestationDTO> getFamillePrestationByCode(@PathVariable Integer code) {
        FamillePrestationDTO dto = famillePrestationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("fammille_prestation/all")
    public ResponseEntity<List<FamillePrestationDTO>> getAllFamillePrestation() {
        return ResponseEntity.ok().body(famillePrestationService.findAllFamillePrestation());
    }

    @PostMapping("fammille_prestation")
    public ResponseEntity<FamillePrestationDTO> postFamillePrestation(@Valid @RequestBody FamillePrestationDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FamillePrestationDTO result = famillePrestationService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("fammille_prestation/update")
    public ResponseEntity<FamillePrestation> updateFamillePrestation(@RequestBody @Valid FamillePrestationDTO dto) throws URISyntaxException {
        FamillePrestation result = famillePrestationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("fammille_prestation/delete/{code}")
    public ResponseEntity<FamillePrestation> deleteFamillePrestation(@PathVariable("Code") Integer code) {
        famillePrestationService.deleteFamillePrestation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
