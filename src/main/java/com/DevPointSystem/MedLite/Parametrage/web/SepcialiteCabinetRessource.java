/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.SpecialiteCabinet;
import com.DevPointSystem.MedLite.Parametrage.dto.SpecialiteCabinetDTO;
import com.DevPointSystem.MedLite.Parametrage.service.SpecialiteCabinetService;
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
public class SepcialiteCabinetRessource {
    private final SpecialiteCabinetService specialiteCabinetService;

    public SepcialiteCabinetRessource(SpecialiteCabinetService specialiteCabinetService) {
        this.specialiteCabinetService = specialiteCabinetService;
    }

   

    @GetMapping("specialite_cabinet/{code}")
    public ResponseEntity<SpecialiteCabinetDTO> getSpecialiteCabinetByCode(@PathVariable Integer code) {
        SpecialiteCabinetDTO dto = specialiteCabinetService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("specialite_cabinet/all")
    public ResponseEntity<List<SpecialiteCabinetDTO>> getAllSpecialiteCabinet() { 
        return ResponseEntity.ok().body(specialiteCabinetService.findAllSpecialiteCabinet());
    }

    @PostMapping("specialite_cabinet")
    public ResponseEntity<SpecialiteCabinetDTO> postSpecialiteCabinet(@Valid @RequestBody SpecialiteCabinetDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        SpecialiteCabinetDTO result = specialiteCabinetService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("specialite_cabinet/update")
    public ResponseEntity<SpecialiteCabinet> updateSpecialiteCabinet(@RequestBody @Valid SpecialiteCabinetDTO dto) throws URISyntaxException {
        SpecialiteCabinet result = specialiteCabinetService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("specialite_cabinet/delete/{code}")
    public ResponseEntity<SpecialiteCabinet> deleteSpecialiteCabinet(@PathVariable("Code") Integer code) {
        specialiteCabinetService.deleteSpecialiteCabinet(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
