/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.Nationalite;
import com.DevPointSystem.MedLite.Parametrage.dto.NationaliteDTO;
import com.DevPointSystem.MedLite.Parametrage.service.NationaliteService;
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
public class NationaliteRessource {
     private final NationaliteService nationaliteService;

    public NationaliteRessource(NationaliteService nationaliteService) {
        this.nationaliteService = nationaliteService;
    }

    @GetMapping("nationalite/{code}")
    public ResponseEntity<NationaliteDTO> getNationaliteByCode(@PathVariable Integer code) {
        NationaliteDTO dto = nationaliteService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("nationalite/all")
    public ResponseEntity<List<NationaliteDTO>> getAllNationalite() { 
        return ResponseEntity.ok().body(nationaliteService.findAllNationalite());
    }

    @PostMapping("nationalite")
    public ResponseEntity<NationaliteDTO> postNationalite(@Valid @RequestBody NationaliteDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        NationaliteDTO result = nationaliteService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("nationalite/update")
    public ResponseEntity<Nationalite> updateNationalite(@RequestBody @Valid NationaliteDTO dto) throws URISyntaxException {
        Nationalite result = nationaliteService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("nationalite/delete/{code}")
    public ResponseEntity<Nationalite> deleteNationalite(@PathVariable("Code") Integer code) {
        nationaliteService.deleteNationalite(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
