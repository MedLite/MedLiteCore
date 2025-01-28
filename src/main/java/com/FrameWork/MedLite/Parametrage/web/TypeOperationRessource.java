/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.TypeOperation;
import com.FrameWork.MedLite.Parametrage.dto.TypeOperationDTO;
import com.FrameWork.MedLite.Parametrage.service.TypeOperationService;
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
public class TypeOperationRessource {

    private final TypeOperationService typeOperationService;

    public TypeOperationRessource(TypeOperationService typeOperationService) {
        this.typeOperationService = typeOperationService;
    }

    @GetMapping("type_operation/{code}")
    public ResponseEntity<TypeOperationDTO> getTypeOperationByCode(@PathVariable Integer code) {
        TypeOperationDTO dto = typeOperationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("type_operation/all")
    public ResponseEntity<List<TypeOperationDTO>> getAllTypeOperation() {
        return ResponseEntity.ok().body(typeOperationService.findAllTypeOperation());
    }

    @GetMapping("type_operation/findBy")
    public ResponseEntity<List<TypeOperationDTO>> getAllTypeOperationByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(typeOperationService.findAllTypeOperationByActif(actif));
    }

    @PostMapping("type_operation")
    public ResponseEntity<TypeOperationDTO> postTypeOperation(@Valid @RequestBody TypeOperationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeOperationDTO result = typeOperationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_operation/update")
    public ResponseEntity<TypeOperation> updateTypeOperation(@RequestBody @Valid TypeOperationDTO dto) throws URISyntaxException {
        TypeOperation result = typeOperationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_operation/delete/{Code}")
    public ResponseEntity<TypeOperation> deleteTypeOperation(@PathVariable("Code") Integer code) {
        typeOperationService.deleteTypeOperation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
