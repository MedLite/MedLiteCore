/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.SignatureMedecin;
import com.FrameWork.MedLite.Parametrage.domaine.SignatureMedecin;
import com.FrameWork.MedLite.Parametrage.dto.SignatureMedecinDTO;
import com.FrameWork.MedLite.Parametrage.dto.SignatureMedecinDTO;
import com.FrameWork.MedLite.Parametrage.service.SignatureMedecinService;
import com.FrameWork.MedLite.Parametrage.service.SignatureMedecinService;
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
public class SignatureMedecinRessource {

    private final SignatureMedecinService signatureMedecinService;

    public SignatureMedecinRessource(SignatureMedecinService signatureMedecinService) {
        this.signatureMedecinService = signatureMedecinService;
    }

    @GetMapping("signature_medecin/{code}")
    public ResponseEntity<SignatureMedecinDTO> getSignatureMedecinByCode(@PathVariable Integer code) {
        SignatureMedecinDTO dto = signatureMedecinService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("signature_medecin/findByMedecin")
    public ResponseEntity<SignatureMedecinDTO> getSignatureMedecinByCodeMedecin(@RequestParam Integer codeMedecin) {
        SignatureMedecinDTO dto = signatureMedecinService.findOneByCodeMedecin(codeMedecin);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("signature_medecin/all")
    public ResponseEntity<List<SignatureMedecinDTO>> getAllSignatureMedecin() {
        return ResponseEntity.ok().body(signatureMedecinService.findAllSignatureMedecin());
    }

    @PostMapping("signature_medecin")
    public ResponseEntity<SignatureMedecinDTO> postSignatureMedecin(@Valid @RequestBody SignatureMedecinDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        SignatureMedecinDTO result = signatureMedecinService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("signature_medecin/update")
    public ResponseEntity<SignatureMedecin> updateSignatureMedecin(@RequestBody @Valid SignatureMedecinDTO dto) throws URISyntaxException {
        SignatureMedecin result = signatureMedecinService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("signature_medecin/delete/{Code}")
    public ResponseEntity<SignatureMedecin> deleteSignatureMedecin(@PathVariable("Code") Integer code) {
        signatureMedecinService.deleteSignatureMedecin(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
