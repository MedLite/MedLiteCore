/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.dto.CabinetDTO;
import com.FrameWork.MedLite.Parametrage.service.CabinetService;
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
public class CabinetRessource {
    private final CabinetService cabinetService;

    public CabinetRessource(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @GetMapping("cabinet/{code}")
    public ResponseEntity<CabinetDTO> getCabinetByCode(@PathVariable Integer code) {
        CabinetDTO dto = cabinetService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    
    @GetMapping("cabinet/BySpecialiteCabinet")
    public ResponseEntity<List<CabinetDTO>> getCabinetBySpecialiteCabinet(@RequestParam Integer code) {
        List<CabinetDTO> dto = cabinetService.FindByCodeSpecialite(code);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping("cabinet/all")
    public ResponseEntity<List<CabinetDTO>> getAllCabinet() { 
        return ResponseEntity.ok().body(cabinetService.findAllCabinet());
    }

    @PostMapping("cabinet")
    public ResponseEntity<CabinetDTO> postCabinet(@Valid @RequestBody CabinetDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CabinetDTO result = cabinetService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("cabinet/update")
    public ResponseEntity<Cabinet> updateCabinet(@RequestBody @Valid CabinetDTO dto) throws URISyntaxException {
        Cabinet result = cabinetService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("cabinet/delete/{Code}")
    public ResponseEntity<Cabinet> deleteCabinet(@PathVariable("Code") Integer code) {
        cabinetService.deleteCabinet(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
