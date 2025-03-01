/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.dto.MedecinDTO;
import com.FrameWork.MedLite.Parametrage.service.MedecinService;
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
public class MedecinRessource {

    private final MedecinService medecinService;

    public MedecinRessource(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @GetMapping("medecin/{code}")
    public ResponseEntity<MedecinDTO> getMedecinByCode(@PathVariable Integer code) {
        MedecinDTO dto = medecinService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("medecin/BySpecialiteMedecin")
    public ResponseEntity<List<MedecinDTO>> getMedecinBySpecialiteMedecin(@RequestParam Integer codeSpecialite) {
        List<MedecinDTO> dto = medecinService.findByCodeSpecialite(codeSpecialite);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("medecin/ByTypeIntervenantMedecin")
    public ResponseEntity<List<MedecinDTO>> getMedecinByTypeIntervenantMedecin(@RequestParam Integer codeTypeIntervenant) {
        List<MedecinDTO> dto = medecinService.findByCodeSpecialite(codeTypeIntervenant);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("medecin/ByMixed")
    public ResponseEntity<List<MedecinDTO>> getMedecinBySpecialiteAndTypeIntervenantMedecin(@RequestParam Integer codeSpecialite, @RequestParam Integer codeTypeIntervenant) {
        List<MedecinDTO> dto = medecinService.findByCodeSpecialiteAndTypeIntervenant(codeSpecialite, codeTypeIntervenant);
        return ResponseEntity.ok().body(dto);
    }
    
    
    @GetMapping("medecin/findBy")
    public ResponseEntity<List<MedecinDTO>> getAllMedecingetAllOperationByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(medecinService.findAllMedecinByActif(actif));
    }
    
    
      @GetMapping("medecin/have_consultation")
    public ResponseEntity<List<MedecinDTO>> getAllMedecingetAllOperationByActifAndHaveConsultation(@RequestParam Boolean autorisConsultation,@RequestParam Boolean actif,@RequestParam Boolean opd,@RequestParam Boolean er) {
        return ResponseEntity.ok().body(medecinService.findMedecinHasConsultation(autorisConsultation,actif,opd,er));
    }

    @GetMapping("medecin/all")
    public ResponseEntity<List<MedecinDTO>> getAllMedecin() {
        return ResponseEntity.ok().body(medecinService.findAllMedecin());
    }

    @PostMapping("medecin")
    public ResponseEntity<MedecinDTO> postMedecin(@Valid @RequestBody MedecinDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        MedecinDTO result = medecinService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("medecin/update")
    public ResponseEntity<Medecin> updateMedecin(@RequestBody @Valid MedecinDTO dto) throws URISyntaxException {
        Medecin result = medecinService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("medecin/delete/{Code}")
    public ResponseEntity<Medecin> deleteMedecin(@PathVariable("Code") Integer code) {
        medecinService.deleteMedecin(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
