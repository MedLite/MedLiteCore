/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.MotifAdmission;
import com.FrameWork.MedLite.Parametrage.dto.MotifAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.service.MotifAdmissionService;
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
public class MotifAdmissionRessource {

    private final MotifAdmissionService motifAdmissionService;

    public MotifAdmissionRessource(MotifAdmissionService motifAdmissionService) {
        this.motifAdmissionService = motifAdmissionService;
    }

    @GetMapping("motif_admission/{code}")
    public ResponseEntity<MotifAdmissionDTO> getMotifAdmissionByCode(@PathVariable Integer code) {
        MotifAdmissionDTO dto = motifAdmissionService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("motif_admission/findBy")
    public ResponseEntity<List<MotifAdmissionDTO>> getMotifAdmissionByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(motifAdmissionService.findAllPrestationByActif(actif));
    }

    @GetMapping("motif_admission/all")
    public ResponseEntity<List<MotifAdmissionDTO>> getAllMotifAdmission() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(motifAdmissionService.findAllMotifAdmission());
    }

    @PostMapping("motif_admission")
    public ResponseEntity<MotifAdmissionDTO> postMotifAdmission(@Valid @RequestBody MotifAdmissionDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        MotifAdmissionDTO result = motifAdmissionService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("motif_admission/update")
    public ResponseEntity<MotifAdmission> updateMotifAdmission(@RequestBody @Valid MotifAdmissionDTO dto) throws URISyntaxException {
        MotifAdmission result = motifAdmissionService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("motif_admission/delete/{Code}")
    public ResponseEntity<MotifAdmission> deleteMotifAdmission(@PathVariable("Code") Integer code) {
        motifAdmissionService.deleteMotifAdmission(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
