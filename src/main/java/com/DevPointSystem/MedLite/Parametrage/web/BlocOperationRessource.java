/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.BlocOperation;
import com.DevPointSystem.MedLite.Parametrage.dto.BlocOperationDTO;
import com.DevPointSystem.MedLite.Parametrage.service.BlocOperationService;
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
public class BlocOperationRessource {
    
    private final BlocOperationService blocOperationService;

    public BlocOperationRessource(BlocOperationService blocOperationService) {
        this.blocOperationService = blocOperationService;
    }

    @GetMapping("bloc_operation/{code}")
    public ResponseEntity<BlocOperationDTO> getBlocOperationByCode(@PathVariable Integer code) {
        BlocOperationDTO dto = blocOperationService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("bloc_operation/all")
    public ResponseEntity<List<BlocOperationDTO>> getAllBlocOperation() {
        return ResponseEntity.ok().body(blocOperationService.findAllBlocOperation());
    }

    @PostMapping("bloc_operation")
    public ResponseEntity<BlocOperationDTO> postBlocOperation(@Valid @RequestBody BlocOperationDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        BlocOperationDTO result = blocOperationService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("bloc_operation/update")
    public ResponseEntity<BlocOperation> updateBlocOperation(@RequestBody @Valid BlocOperationDTO dto) throws URISyntaxException {
        BlocOperation result = blocOperationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("bloc_operation/delete/{code}")
    public ResponseEntity<BlocOperation> deleteBlocOperation(@PathVariable("Code") Integer code) {
        blocOperationService.deleteBlocOperation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
