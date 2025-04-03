/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.web;

import com.FrameWork.MedLite.DMI.dto.CheifComplaintDTO;
import com.FrameWork.MedLite.DMI.dto.Icd10DTO;
import com.FrameWork.MedLite.DMI.service.CheifComplaintService;
import com.FrameWork.MedLite.DMI.service.Icd10Service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/dmi/")
public class Icd10Ressource {

    private final Icd10Service icd10Service;

    public Icd10Ressource(Icd10Service icd10Service) {
        this.icd10Service = icd10Service;
    }

    @GetMapping("ICD10/findByCodeOrDesignation")
    public ResponseEntity<List<Icd10DTO>> getICD10ByCodeOrDesignation(@RequestParam String searchTerme) {
        return ResponseEntity.ok().body(icd10Service.findByCodeOrDesignation(searchTerme));
    }

    @GetMapping("ICD10/all")
    public ResponseEntity<List<Icd10DTO>> getICD10All() {
        return ResponseEntity.ok().body(icd10Service.findAll());
    }

}
