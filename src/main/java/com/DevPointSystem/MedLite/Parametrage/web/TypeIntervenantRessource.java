/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.domaine.TypeIntervenant;
import com.DevPointSystem.MedLite.Parametrage.dto.TypeIntervenantDTO;
import com.DevPointSystem.MedLite.Parametrage.service.TypeIntervenantService;
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
public class TypeIntervenantRessource {
      private final TypeIntervenantService typeIntervenantService;

    public TypeIntervenantRessource(TypeIntervenantService typeIntervenantService) {
        this.typeIntervenantService = typeIntervenantService;
    }

    @GetMapping("type_intervenant/{code}")
    public ResponseEntity<TypeIntervenantDTO> getTypeIntervenantByCode(@PathVariable Integer code) {
        TypeIntervenantDTO dto = typeIntervenantService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("type_intervenant/all")
    public ResponseEntity<List<TypeIntervenantDTO>> getAllTypeIntervenant() {
        return ResponseEntity.ok().body(typeIntervenantService.findAllTypeIntervenant());
    }

    @PostMapping("type_intervenant")
    public ResponseEntity<TypeIntervenantDTO> postTypeIntervenant(@Valid @RequestBody TypeIntervenantDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeIntervenantDTO result = typeIntervenantService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_intervenant/update")
    public ResponseEntity<TypeIntervenant> updateTypeIntervenant(@RequestBody @Valid TypeIntervenantDTO dto) throws URISyntaxException {
        TypeIntervenant result = typeIntervenantService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_intervenant/delete/{Code}")
    public ResponseEntity<TypeIntervenant> deleteTypeIntervenant(@PathVariable("Code") Integer code) {
        typeIntervenantService.deleteTypeIntervenant(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
