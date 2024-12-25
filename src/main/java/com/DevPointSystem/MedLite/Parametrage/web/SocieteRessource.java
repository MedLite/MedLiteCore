/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.Societe;
import com.DevPointSystem.MedLite.Parametrage.dto.SocieteDTO;
import com.DevPointSystem.MedLite.Parametrage.service.SocieteService;
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
public class SocieteRessource {
    
    private final SocieteService societeService;

    public SocieteRessource(SocieteService societeService) {
        this.societeService = societeService;
    }

    @GetMapping("societe/{code}")
    public ResponseEntity<SocieteDTO> getSocieteByCode(@PathVariable Integer code) {
        SocieteDTO dto = societeService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("societe/all")
    public ResponseEntity<List<SocieteDTO>> getAllSociete() {
        return ResponseEntity.ok().body(societeService.findAllSociete());
    }

    @PostMapping("societe")
    public ResponseEntity<SocieteDTO> postSociete(@Valid @RequestBody SocieteDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        SocieteDTO result = societeService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("societe/update")
    public ResponseEntity<Societe> updateSociete(@RequestBody @Valid SocieteDTO dto) throws URISyntaxException {
        Societe result = societeService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("societe/delete/{code}")
    public ResponseEntity<Societe> deleteSociete(@PathVariable("Code") Integer code) {
        societeService.deleteSociete(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
