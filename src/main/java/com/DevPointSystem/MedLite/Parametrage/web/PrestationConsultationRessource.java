/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Authentification.service.AccessUserService;
import com.DevPointSystem.MedLite.Parametrage.domaine.PrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationConsultationDTO;
import com.DevPointSystem.MedLite.Parametrage.service.ParamService;
import com.DevPointSystem.MedLite.Parametrage.service.PrestationConsultationService;
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
public class PrestationConsultationRessource {

    private final PrestationConsultationService prestationConsultationService;
    private final ParamService paramService;
    private final SocService societeService;
    private final AccessUserService accessUserService;

    public PrestationConsultationRessource(PrestationConsultationService prestationConsultationService, ParamService paramService, SocService societeService, AccessUserService accessUserService) {
        this.prestationConsultationService = prestationConsultationService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("prestation_consultation/{code}")
    public ResponseEntity<PrestationConsultationDTO> getPrestationConsultationByCode(@PathVariable Integer code) {
        PrestationConsultationDTO dTO = prestationConsultationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("prestation_consultation/all")
    public ResponseEntity<List<PrestationConsultationDTO>> getAllPrestationConsultation() {
        return ResponseEntity.ok().body(prestationConsultationService.findAllPrestationConsultation());
    }

    @PostMapping("prestation_consultation")
    public ResponseEntity<PrestationConsultationDTO> postPrestationConsultation(@Valid @RequestBody PrestationConsultationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PrestationConsultationDTO result = prestationConsultationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

//    @PutMapping("alimentation_caisse/update")
//    public ResponseEntity<PrestationConsultationDTO> updatePrestationConsultation(@Valid @RequestBody PrestationConsultationDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException {
//        PrestationConsultationDTO result = prestationConsultationService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @PutMapping("prestation_consultation/update")
    public ResponseEntity<PrestationConsultationDTO> updatePrestationConsultation(@Valid @RequestBody PrestationConsultationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PrestationConsultationDTO result = prestationConsultationService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("prestation_consultation/delete/{Code}")
    public ResponseEntity<PrestationConsultation> deletePrestationConsultation(@PathVariable("Code") Integer code) {
        prestationConsultationService.deletePrestationConsultation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
