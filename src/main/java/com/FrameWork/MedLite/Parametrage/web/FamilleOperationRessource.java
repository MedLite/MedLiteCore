/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.FamilleOperation;
import com.FrameWork.MedLite.Parametrage.dto.FamilleOperationDTO;
import com.FrameWork.MedLite.Parametrage.service.FamilleOperationService;
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
public class FamilleOperationRessource {

    private final FamilleOperationService familleOperationService;

    public FamilleOperationRessource(FamilleOperationService familleOperationService) {
        this.familleOperationService = familleOperationService;
    }

    @GetMapping("famille_operation/{code}")
    public ResponseEntity<FamilleOperationDTO> getFamilleOperationByCode(@PathVariable Integer code) {
        FamilleOperationDTO dto = familleOperationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("famille_operation/all")
    public ResponseEntity<List<FamilleOperationDTO>> getAllFamilleOperation() {
        return ResponseEntity.ok().body(familleOperationService.findAllFamilleOperation());
    }
    
   
    
    @GetMapping("famille_operation/findBy")
    public ResponseEntity<List<FamilleOperationDTO>> getAllFamilleOperationyActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(familleOperationService.findAllFamilleOperationByActif(actif));
    }


    @PostMapping("famille_operation")
    public ResponseEntity<FamilleOperationDTO> postFamilleOperation(@Valid @RequestBody FamilleOperationDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FamilleOperationDTO result = familleOperationService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("famille_operation/update")
    public ResponseEntity<FamilleOperation> updateFamilleOperation(@RequestBody @Valid FamilleOperationDTO dto) throws URISyntaxException {
        FamilleOperation result = familleOperationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("famille_operation/delete/{Code}")
    public ResponseEntity<FamilleOperation> deleteFamilleOperation(@PathVariable("Code") Integer code) {
        familleOperationService.deleteFamilleOperation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
