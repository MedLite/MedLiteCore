/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.TypePrestation;
import com.FrameWork.MedLite.Parametrage.dto.TypePrestationDTO;
import com.FrameWork.MedLite.Parametrage.service.TypePrestationService;
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
public class TypePrestationRessource {
    private final TypePrestationService typePrestationService;

    public TypePrestationRessource(TypePrestationService typePrestationService) {
        this.typePrestationService = typePrestationService;
    }

    @GetMapping("type_prestation/{code}")
    public ResponseEntity<TypePrestationDTO> getTypePrestationByCode(@PathVariable Integer code) {
        TypePrestationDTO dto = typePrestationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("type_prestation/all")
    public ResponseEntity<List<TypePrestationDTO>> getAllTypePrestation() {
        return ResponseEntity.ok().body(typePrestationService.findAllTypePrestation());
    }

    @GetMapping("type_prestation/findBy")
    public ResponseEntity<List<TypePrestationDTO>> getAllTypePrestationByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(typePrestationService.findAllTypePrestationByActif(actif));
    }

    @PostMapping("type_prestation")
    public ResponseEntity<TypePrestationDTO> postTypePrestation(@Valid @RequestBody TypePrestationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypePrestationDTO result = typePrestationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_prestation/update")
    public ResponseEntity<TypePrestation> updateTypePrestation(@RequestBody @Valid TypePrestationDTO dto) throws URISyntaxException {
        TypePrestation result = typePrestationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_prestation/delete/{Code}")
    public ResponseEntity<TypePrestation> deleteTypePrestation(@PathVariable("Code") Integer code) {
        typePrestationService.deleteTypePrestation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
