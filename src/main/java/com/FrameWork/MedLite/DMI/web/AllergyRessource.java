/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.web;

import com.FrameWork.MedLite.DMI.domaine.Allergy;
import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.dto.AllergyDTO;
import com.FrameWork.MedLite.DMI.factory.AllergyFactory;
import com.FrameWork.MedLite.DMI.repository.FeuilleSoinOPDRepo;
import com.FrameWork.MedLite.DMI.service.AllergyService;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
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
@RequestMapping("/api/dmi/")
public class AllergyRessource {

    private final AllergyService allergyService;
    private final FeuilleSoinOPDRepo feuilleSoinOPDRepo;

    public AllergyRessource(AllergyService allergyService, FeuilleSoinOPDRepo feuilleSoinOPDRepo) {
        this.allergyService = allergyService;
        this.feuilleSoinOPDRepo = feuilleSoinOPDRepo;
    }

    @GetMapping("allergy/findByCodeAdmission")
    public ResponseEntity<List<AllergyDTO>> getAllAllergyByCodeAdmission(@RequestParam Integer codeAdmission) {
        return ResponseEntity.ok().body(allergyService.findAllAllergyByCodeAdmission(codeAdmission));
    }

    @PostMapping("allergy")
    public ResponseEntity<AllergyDTO> postAllergy(@Valid @RequestBody AllergyDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        AllergyDTO result = allergyService.save(dto);

        FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(dto.getCodeAdmission());
        if (feuilleSoinOPD != null) {

            feuilleSoinOPD.setCodeAllergy(result.getCode());
            if (result.getCode() != null) {
                feuilleSoinOPD.setAllergy(AllergyFactory.createAllergyByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(feuilleSoinOPD);
        } else {
            FeuilleSoinOPD newfeuilleSoinOPD = new FeuilleSoinOPD();

            newfeuilleSoinOPD.setCode(dto.getCode());

            newfeuilleSoinOPD.setCodeAdmission(result.getCodeAdmission());
            if (result.getCodeAdmission() != null) {
                newfeuilleSoinOPD.setAdmission(AdmissionFactory.createAdmissionByCode(result.getCodeAdmission()));
            }

            newfeuilleSoinOPD.setCodeAllergy(result.getCode());
            if (result.getCode() != null) {
                newfeuilleSoinOPD.setAllergy(AllergyFactory.createAllergyByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(newfeuilleSoinOPD);

        }

        return ResponseEntity.created(new URI("/api/dmi/" + result.getCode())).body(result);
    }

//    @PutMapping("allergy/update")
//    public ResponseEntity<Allergy> updateAllergy(@RequestBody @Valid AllergyDTO dto) throws URISyntaxException {
//        Allergy result = allergyService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @DeleteMapping("allergy/delete")
    public ResponseEntity<Allergy> deleteAllergy(@RequestParam Integer code, @RequestParam Integer codeAdmission) {

        allergyService.deleteAllergy(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("allergy/deleteByCodeAdmission/{Code}")
    public ResponseEntity<Allergy> deleteAllergyByCodeAdmission(@PathVariable("Code") Integer codeAdmission) {
        FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(codeAdmission);
        feuilleSoinOPD.setCodeAllergy(null); 
        feuilleSoinOPD.setAllergy(null); 
        feuilleSoinOPDRepo.save(feuilleSoinOPD);
        allergyService.deleteAllergyByCodeAdmission(codeAdmission);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
