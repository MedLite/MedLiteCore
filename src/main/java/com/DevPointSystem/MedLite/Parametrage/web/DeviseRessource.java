/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.Devise;
import com.DevPointSystem.MedLite.Parametrage.dto.DeviseDTO;
import com.DevPointSystem.MedLite.Parametrage.service.DeviseService;
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
public class DeviseRessource {

    private final DeviseService deviseService;
    
    public DeviseRessource(DeviseService deviseService) {
        this.deviseService = deviseService;
    }
    
    @GetMapping("devise/{code}")
    public ResponseEntity<DeviseDTO> getDeviseByCode(@PathVariable Integer code) {
        DeviseDTO dto = deviseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("devise/all")
    public ResponseEntity<List<DeviseDTO>> getAllDevise() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(deviseService.findAllDevise());
    }
    
    @GetMapping("devise/hasTaux")
    public ResponseEntity<List<DeviseDTO>> getAllDeviseByTaux() {        
        return ResponseEntity.ok().body(deviseService.findByHasTaux(false));
    }
    
    @PostMapping("devise")
    public ResponseEntity<DeviseDTO> postDevise(@Valid @RequestBody DeviseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        DeviseDTO result = deviseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }
    
    @PutMapping("devise/update")
    public ResponseEntity<Devise> updateDevise(@RequestBody @Valid DeviseDTO dto) throws URISyntaxException {
        Devise result = deviseService.update(dto);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("devise/delete/{Code}")
    public ResponseEntity<Devise> deleteDevise(@PathVariable("Code") Integer code) {
        deviseService.deleteDevise(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
