/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.ResponsableRemise;
import com.DevPointSystem.MedLite.Parametrage.dto.ResponsableRemiseDTO;
import com.DevPointSystem.MedLite.Parametrage.service.ResponsableRemiseService;
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
public class ResponsableRemiseRessource {

    private final ResponsableRemiseService responsableRemiseService;

    public ResponsableRemiseRessource(ResponsableRemiseService responsableRemiseService) {
        this.responsableRemiseService = responsableRemiseService;
    }

    @GetMapping("responsable_remise/{code}")
    public ResponseEntity<ResponsableRemiseDTO> getResponsableRemiseByCode(@PathVariable Integer code) {
        ResponsableRemiseDTO dto = responsableRemiseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("responsable_remise/all")
    public ResponseEntity<List<ResponsableRemiseDTO>> getAllResponsableRemise() {
        return ResponseEntity.ok().body(responsableRemiseService.findAllResponsableRemise());
    }

    @PostMapping("responsable_remise")
    public ResponseEntity<ResponsableRemiseDTO> postResponsableRemise(@Valid @RequestBody ResponsableRemiseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ResponsableRemiseDTO result = responsableRemiseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("responsable_remise/update")
    public ResponseEntity<ResponsableRemise> updateResponsableRemise(@RequestBody @Valid ResponsableRemiseDTO dto) throws URISyntaxException {
        ResponsableRemise result = responsableRemiseService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("responsable_remise/delete/{Code}")
    public ResponseEntity<ResponsableRemise> deleteResponsableRemise(@PathVariable("Code") Integer code) {
        responsableRemiseService.deleteResponsableRemise(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
