/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Authentification.service.AccessUserService;
import com.DevPointSystem.MedLite.Parametrage.domaine.Operation;
import com.DevPointSystem.MedLite.Parametrage.dto.OperationDTO;
import com.DevPointSystem.MedLite.Parametrage.service.ParamService;
import com.DevPointSystem.MedLite.Parametrage.service.OperationService;
import com.DevPointSystem.MedLite.Parametrage.service.SocService;
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
public class OperationRessource {
    
     private final OperationService operationService;
    private final ParamService paramService;
    private final SocService societeService;

    private final AccessUserService accessUserService;

    public OperationRessource(OperationService operationService, ParamService paramService, SocService societeService, AccessUserService accessUserService) {
        this.operationService = operationService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("operation/{code}")
    public ResponseEntity<OperationDTO> getOperationByCode(@PathVariable Integer code) {
        OperationDTO dTO = operationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("operation/all")
    public ResponseEntity<List<OperationDTO>> getAllOperation() {
        return ResponseEntity.ok().body(operationService.findAllOperation());
    }
 
    @PostMapping("operation")
    public ResponseEntity<OperationDTO> postOperation(@Valid @RequestBody OperationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        OperationDTO result = operationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("alimentation_caisse/update")
//    public ResponseEntity<OperationDTO> updateOperation(@Valid @RequestBody OperationDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        OperationDTO result = operationService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("operation/update")
    public ResponseEntity<OperationDTO> updateOperation(@Valid @RequestBody OperationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        OperationDTO result = operationService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }
 
    @DeleteMapping("operation/delete/{Code}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable("Code") Integer code) {
        operationService.deleteOperation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
