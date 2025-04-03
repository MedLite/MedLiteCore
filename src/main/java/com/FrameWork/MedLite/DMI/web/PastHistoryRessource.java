/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.web;

import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.domaine.PastHistory;
import com.FrameWork.MedLite.DMI.dto.PastHistoryDTO;
import com.FrameWork.MedLite.DMI.factory.CheifComplaintFactory;
import com.FrameWork.MedLite.DMI.factory.PastHistoryFactory;
import com.FrameWork.MedLite.DMI.repository.FeuilleSoinOPDRepo;
import com.FrameWork.MedLite.DMI.service.PastHistoryService;
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
public class PastHistoryRessource {

    private final PastHistoryService pastHistoryService;

    private final FeuilleSoinOPDRepo feuilleSoinOPDRepo;

    public PastHistoryRessource(PastHistoryService pastHistoryService, FeuilleSoinOPDRepo feuilleSoinOPDRepo) {
        this.pastHistoryService = pastHistoryService;
        this.feuilleSoinOPDRepo = feuilleSoinOPDRepo;
    }

    @GetMapping("past_history/findByCodeAdmission")
    public ResponseEntity<List<PastHistoryDTO>> getAllPastHistoryByCodeAdmission(@RequestParam Integer codeAdmission) {
        return ResponseEntity.ok().body(pastHistoryService.findAllPastHistoryByCodeAdmission(codeAdmission));
    }

    @PostMapping("past_history")
    public ResponseEntity<PastHistoryDTO> postPastHistory(@Valid @RequestBody PastHistoryDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PastHistoryDTO result = pastHistoryService.save(dto);
        FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(dto.getCodeAdmission());
        if (feuilleSoinOPD != null) {

            feuilleSoinOPD.setCodePastHistory(result.getCode());
            if (result.getCode() != null) {
                feuilleSoinOPD.setPastHistory(PastHistoryFactory.createPastHistoryByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(feuilleSoinOPD);
        } else {
            FeuilleSoinOPD newfeuilleSoinOPD = new FeuilleSoinOPD();

            newfeuilleSoinOPD.setCode(dto.getCode());

            newfeuilleSoinOPD.setCodeAdmission(result.getCodeAdmission());
            if (result.getCodeAdmission() != null) {
                newfeuilleSoinOPD.setAdmission(AdmissionFactory.createAdmissionByCode(result.getCodeAdmission()));
            }

            newfeuilleSoinOPD.setCodePastHistory(result.getCode());
            if (result.getCode() != null) {
                newfeuilleSoinOPD.setPastHistory(PastHistoryFactory.createPastHistoryByCode(result.getCode()));
            }
            feuilleSoinOPDRepo.save(newfeuilleSoinOPD);

        }
        return ResponseEntity.created(new URI("/api/dmi/" + result.getCode())).body(result);
    }

//
//    @PutMapping("past_history/update")
//    public ResponseEntity<PastHistory> updatePastHistory(@RequestBody @Valid PastHistoryDTO dto) throws URISyntaxException {
//        PastHistory result = pastHistoryService.update(dto);
//        return ResponseEntity.ok().body(result);
//    }
    @DeleteMapping("past_history/delete/{Code}")
    public ResponseEntity<PastHistory> deletePastHistory(@PathVariable("Code") Integer code) {
        pastHistoryService.deletePastHistory(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("past_history/deleteByCodeAdmission/{Code}")
    public ResponseEntity<PastHistory> deletePastHistoryByCodeAdmission(@PathVariable("Code") Integer codeAdmission) {
        FeuilleSoinOPD feuilleSoinOPD = feuilleSoinOPDRepo.findByCodeAdmission(codeAdmission);
        feuilleSoinOPD.setCodePastHistory(null); 
        feuilleSoinOPD.setPastHistory(null); 
        feuilleSoinOPDRepo.save(feuilleSoinOPD);
        pastHistoryService.deletePastHistoryByCodeAdmission(codeAdmission);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
