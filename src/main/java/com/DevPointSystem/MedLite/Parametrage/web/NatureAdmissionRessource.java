/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import com.DevPointSystem.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.DevPointSystem.MedLite.Parametrage.service.NatureAdmissionService;
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
public class NatureAdmissionRessource {
     private final NatureAdmissionService natureAdmissionService;

    public NatureAdmissionRessource(NatureAdmissionService natureAdmissionService) {
        this.natureAdmissionService = natureAdmissionService;
    }

    @GetMapping("nature_admission/{code}")
    public ResponseEntity<NatureAdmissionDTO> getNatureAdmissionByCode(@PathVariable Integer code) {
        NatureAdmissionDTO dto = natureAdmissionService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("nature_admission/all")
    public ResponseEntity<List<NatureAdmissionDTO>> getAllNatureAdmission() { 
        return ResponseEntity.ok().body(natureAdmissionService.findAllNatureAdmission());
    }

//    @PostMapping("nature_admission")
//    public ResponseEntity<NatureAdmissionDTO> postNatureAdmission(@Valid @RequestBody NatureAdmissionDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
//        NatureAdmissionDTO result = natureAdmissionService.save(ddeTransfertDTO);
//        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
//    }
//
//    @PutMapping("nature_admission/update")
//    public ResponseEntity<NatureAdmission> updateNatureAdmission(@RequestBody @Valid NatureAdmissionDTO dto) throws URISyntaxException {
//        NatureAdmission result = natureAdmissionService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
//
//    @DeleteMapping("nature_admission/delete/{code}")
//    public ResponseEntity<NatureAdmission> deleteNatureAdmission(@PathVariable("Code") Integer code) {
//        natureAdmissionService.deleteNatureAdmission(code);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
