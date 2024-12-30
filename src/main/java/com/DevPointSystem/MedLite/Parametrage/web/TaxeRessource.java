/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.Taxe;
import com.DevPointSystem.MedLite.Parametrage.dto.TaxeDTO;
import com.DevPointSystem.MedLite.Parametrage.service.TaxeService;
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
public class TaxeRessource {

    private final TaxeService taxeService;

    public TaxeRessource(TaxeService taxeService) {
        this.taxeService = taxeService;
    }

    @GetMapping("taxe/{code}")
    public ResponseEntity<TaxeDTO> getTaxeByCode(@PathVariable Integer code) {
        TaxeDTO dto = taxeService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("taxe/all")
    public ResponseEntity<List<TaxeDTO>> getAllTaxe() {
        return ResponseEntity.ok().body(taxeService.findAllTaxe());
    }

    @PostMapping("taxe")
    public ResponseEntity<TaxeDTO> postTaxe(@Valid @RequestBody TaxeDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TaxeDTO result = taxeService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("taxe/update")
    public ResponseEntity<Taxe> updateTaxe(@RequestBody @Valid TaxeDTO dto) throws URISyntaxException {
        Taxe result = taxeService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("taxe/delete/{Code}")
    public ResponseEntity<Taxe> deleteTaxe(@PathVariable("Code") Integer code) {
        taxeService.deleteTaxe(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
