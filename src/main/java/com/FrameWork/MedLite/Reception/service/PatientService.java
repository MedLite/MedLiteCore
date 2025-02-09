/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.Reception.domaine.Patient;
import com.FrameWork.MedLite.Reception.dto.PatientDTO;
import com.FrameWork.MedLite.Reception.factory.PatientFactory;
import com.FrameWork.MedLite.Reception.repository.PatientRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class PatientService {

    private final PatientRepo patientRepo;

    private final CompteurService compteurService;

    public PatientService(PatientRepo patientRepo, CompteurService compteurService) {
        this.patientRepo = patientRepo;
        this.compteurService = compteurService;
    }
 
    @Transactional(readOnly = true)
    public List<PatientDTO> searchPatients(String searchTerm) {
        return PatientFactory.listPatientToPatientDTOs(patientRepo.findByNomCompltArContainingIgnoreCaseOrNomCompltLtContainingIgnoreCase(searchTerm, searchTerm));

    }

    @Transactional(readOnly = true)
    public List<PatientDTO> findAllPatient() {
        return PatientFactory.listPatientToPatientDTOs(patientRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public PatientDTO findOne(Integer code) {
        Patient domaine = patientRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.PatientNotFound");
        return PatientFactory.patientToPatientDTO(domaine);
    }

    public PatientDTO save(PatientDTO dto) {
        Patient domaine = PatientFactory.patientDTOToPatient(dto, new Patient());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CompteurPatient");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = patientRepo.save(domaine);
        return PatientFactory.patientToPatientDTO(domaine);
    }

    public Patient update(PatientDTO dto) {
        Patient domaine = patientRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.PatientNotFound");
        dto.setCode(domaine.getCode());
        PatientFactory.patientDTOToPatient(dto, domaine);
        return patientRepo.save(domaine);
    }

    public void deletePatient(Integer code) {
        Preconditions.checkArgument(patientRepo.existsById(code), "error.PatientNotFound");
        patientRepo.deleteById(code);
    }

}
