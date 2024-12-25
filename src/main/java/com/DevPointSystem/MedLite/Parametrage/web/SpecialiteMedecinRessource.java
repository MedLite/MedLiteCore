/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.SpecialiteMedecin;
import com.DevPointSystem.MedLite.Parametrage.dto.SpecialiteMedecinDTO;
import com.DevPointSystem.MedLite.Parametrage.service.SpecialiteMedecinService;
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
public class SpecialiteMedecinRessource {
    
    private final SpecialiteMedecinService specialiteMedecinService;

    public SpecialiteMedecinRessource(SpecialiteMedecinService specialiteMedecinService) {
        this.specialiteMedecinService = specialiteMedecinService;
    }

    @GetMapping("specialite_medecin/{code}")
    public ResponseEntity<SpecialiteMedecinDTO> getSpecialiteMedecinByCode(@PathVariable Integer code) {
        SpecialiteMedecinDTO dto = specialiteMedecinService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("specialite_medecin/all")
    public ResponseEntity<List<SpecialiteMedecinDTO>> getAllSpecialiteMedecin() {
        return ResponseEntity.ok().body(specialiteMedecinService.findAllSpecialiteMedecin());
    }

    @PostMapping("specialite_medecin")
    public ResponseEntity<SpecialiteMedecinDTO> postSpecialiteMedecin(@Valid @RequestBody SpecialiteMedecinDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        SpecialiteMedecinDTO result = specialiteMedecinService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("specialite_medecin/update")
    public ResponseEntity<SpecialiteMedecin> updateSpecialiteMedecin(@RequestBody @Valid SpecialiteMedecinDTO dto) throws URISyntaxException {
        SpecialiteMedecin result = specialiteMedecinService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("specialite_medecin/delete/{code}")
    public ResponseEntity<SpecialiteMedecin> deleteSpecialiteMedecin(@PathVariable("Code") Integer code) {
        specialiteMedecinService.deleteSpecialiteMedecin(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
