/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.web;

import com.FrameWork.MedLite.DMI.domaine.CheifComplaint;
import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.dto.CheifComplaintDTO;
import com.FrameWork.MedLite.DMI.factory.AllergyFactory;
import com.FrameWork.MedLite.DMI.factory.CheifComplaintFactory;
import com.FrameWork.MedLite.DMI.repository.FeuilleSoinOPDRepo;
import com.FrameWork.MedLite.DMI.service.CheifComplaintService;
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
public class CheifComplaintRessource {

    private final CheifComplaintService cheifComplaintService;
    private final FeuilleSoinOPDRepo feuilleSoinOPDRepo;

    public CheifComplaintRessource(CheifComplaintService cheifComplaintService, FeuilleSoinOPDRepo feuilleSoinOPDRepo) {
        this.cheifComplaintService = cheifComplaintService;
        this.feuilleSoinOPDRepo = feuilleSoinOPDRepo;
    }

//    @GetMapping("examen/all")
//    public ResponseEntity<List<CheifComplaintDTO>> getAllExamen() { 
//        return ResponseEntity.ok().body(cheifComplaintService.findAllExamen());
//    }
    @GetMapping("cheif_complaint/findByCodeAdmission")
    public ResponseEntity<List<CheifComplaintDTO>> getAllCheifComplaintByCodeAdmission(@RequestParam Integer codeAdmission) {
        return ResponseEntity.ok().body(cheifComplaintService.findAllCheifComplaintByCodeAdmission(codeAdmission));
    }

    @PostMapping("cheif_complaint")
    public ResponseEntity<CheifComplaintDTO> postCheifComplaint(@Valid @RequestBody CheifComplaintDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CheifComplaintDTO result = cheifComplaintService.save(dto);

        FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(dto.getCodeAdmission());
        if (feuilleSoinOPD != null) {

            feuilleSoinOPD.setCodeCheifComplaint(result.getCode());
            if (result.getCode() != null) {
                feuilleSoinOPD.setCheifComplaint(CheifComplaintFactory.createCheifComplaintByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(feuilleSoinOPD);
        } else {
            FeuilleSoinOPD newfeuilleSoinOPD = new FeuilleSoinOPD();

            newfeuilleSoinOPD.setCode(dto.getCode());

            newfeuilleSoinOPD.setCodeAdmission(result.getCodeAdmission());
            if (result.getCodeAdmission() != null) {
                newfeuilleSoinOPD.setAdmission(AdmissionFactory.createAdmissionByCode(result.getCodeAdmission()));
            }

            newfeuilleSoinOPD.setCodeCheifComplaint(result.getCode());
            if (result.getCode() != null) {
                newfeuilleSoinOPD.setCheifComplaint(CheifComplaintFactory.createCheifComplaintByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(newfeuilleSoinOPD);

        }
        return ResponseEntity.created(new URI("/api/dmi/" + result.getCode())).body(result);
    }

//    @PutMapping("cheif_complaint/update")
//    public ResponseEntity<CheifComplaint> updateCheifComplaint(@RequestBody @Valid CheifComplaintDTO dto) throws URISyntaxException {
//        CheifComplaint result = cheifComplaintService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }

    @DeleteMapping("cheif_complaint/delete")
    public ResponseEntity<CheifComplaint> deleteCheifComplaint(@RequestParam Integer code, @RequestParam Integer codeAdmission) {
        cheifComplaintService.deleteCheifComplaint(code);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("cheif_complaint/deleteByCodeAdmission/{Code}")
    public ResponseEntity<CheifComplaint> deleteCheifComplaintByCodeAdmission(@PathVariable("Code") Integer codeAdmission) {
        FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(codeAdmission);
        feuilleSoinOPD.setCodeCheifComplaint(null);

        feuilleSoinOPD.setCheifComplaint(null);
 
        feuilleSoinOPDRepo.save(feuilleSoinOPD);

        cheifComplaintService.deleteCheifComplaintByCodeAdmission(codeAdmission);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
