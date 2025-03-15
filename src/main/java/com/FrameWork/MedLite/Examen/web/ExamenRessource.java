/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.web;

import com.FrameWork.MedLite.Examen.domaine.Examen;
import com.FrameWork.MedLite.Examen.dto.ExamenDTO;
import com.FrameWork.MedLite.Examen.service.ExamenService;
import com.FrameWork.MedLite.Reception.dto.ReglementDTO;
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
@RequestMapping("/api/examen/")
public class ExamenRessource {
     private final ExamenService examenService;

    public ExamenRessource(ExamenService examenService) {
        this.examenService = examenService;
    }
    
    @GetMapping("examen/{code}")
    public ResponseEntity<ExamenDTO> getExamenByCode(@PathVariable Integer code) {
        ExamenDTO dto = examenService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
  
    
    

    @GetMapping("examen/all")
    public ResponseEntity<List<ExamenDTO>> getAllExamen() { 
        return ResponseEntity.ok().body(examenService.findAllExamen());
    }
     @GetMapping("examen/findByTypeExamenAndCodeAdmission")
    public ResponseEntity<List<ExamenDTO>> getAllExamenByTypeExamenAndCodeAdmission(@RequestParam String typeExamen ,@RequestParam Integer codeAdmission ) { 
        return ResponseEntity.ok().body(examenService.findAllExamenByTypeExmaneAndCodeAdmission(typeExamen, codeAdmission));
    }


   
    
    
    @PostMapping("examen")
    public ResponseEntity<ExamenDTO> postExamen(@Valid @RequestBody ExamenDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ExamenDTO result = examenService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }
    
    
    @PostMapping("examen/List")
    public ResponseEntity<String> saveReglementList(@RequestBody List<ExamenDTO> examenDTOs) {
        List<ExamenDTO> result = examenService.saveList(examenDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("examen/update")
    public ResponseEntity<Examen> updateExamen(@RequestBody @Valid ExamenDTO dto) throws URISyntaxException {
        Examen result = examenService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("examen/delete/{Code}")
    public ResponseEntity<Examen> deleteExamen(@PathVariable("Code") Integer code) {
        examenService.deleteExamen(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
