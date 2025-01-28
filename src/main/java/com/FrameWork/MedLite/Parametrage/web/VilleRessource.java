/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.Ville;
import com.FrameWork.MedLite.Parametrage.dto.VilleDTO;
import com.FrameWork.MedLite.Parametrage.service.VilleService;
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
public class VilleRessource {
    private final VilleService villeService;

    public VilleRessource(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping("ville/{code}")
    public ResponseEntity<VilleDTO> getVilleByCode(@PathVariable Integer code) {
        VilleDTO dto = villeService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("ville/all")
    public ResponseEntity<List<VilleDTO>> getAllVille() { 
        return ResponseEntity.ok().body(villeService.findAllVille());
    }

    @PostMapping("ville")
    public ResponseEntity<VilleDTO> postVille(@Valid @RequestBody VilleDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        VilleDTO result = villeService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("ville/update")
    public ResponseEntity<Ville> updateVille(@RequestBody @Valid VilleDTO dto) throws URISyntaxException {
        Ville result = villeService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("ville/delete/{Code}")
    public ResponseEntity<Ville> deleteVille(@PathVariable("Code") Integer code) {
        villeService.deleteVille(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
