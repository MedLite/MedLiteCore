/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamilleFacturation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamilleFacturationDTO;
import com.DevPointSystem.MedLite.Parametrage.service.FamilleFacturationService;
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
public class FamilleFacturationRessource {
      private final FamilleFacturationService familleFacturationService;

    public FamilleFacturationRessource(FamilleFacturationService familleFacturationService) {
        this.familleFacturationService = familleFacturationService;
    }

    @GetMapping("famille_facturation/{code}")
    public ResponseEntity<FamilleFacturationDTO> getFamilleFacturationByCode(@PathVariable Integer code) {
        FamilleFacturationDTO dto = familleFacturationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("famille_facturation/all")
    public ResponseEntity<List<FamilleFacturationDTO>> getAllFamilleFacturation() {
        return ResponseEntity.ok().body(familleFacturationService.findAllFamilleFacturation());
    }

    @PostMapping("famille_facturation")
    public ResponseEntity<FamilleFacturationDTO> postFamilleFacturation(@Valid @RequestBody FamilleFacturationDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FamilleFacturationDTO result = familleFacturationService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("famille_facturation/update")
    public ResponseEntity<FamilleFacturation> updateFamilleFacturation(@RequestBody @Valid FamilleFacturationDTO dto) throws URISyntaxException {
        FamilleFacturation result = familleFacturationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("famille_facturation/delete/{Code}")
    public ResponseEntity<FamilleFacturation> deleteFamilleFacturation(@PathVariable("Code") Integer code) {
        familleFacturationService.deleteFamilleFacturation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
