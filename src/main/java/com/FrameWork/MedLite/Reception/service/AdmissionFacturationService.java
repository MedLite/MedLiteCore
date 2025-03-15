/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.Reception.domaine.AdmissionFacturation;
import com.FrameWork.MedLite.Reception.dto.AdmissionFacturationDTO;
import com.FrameWork.MedLite.Reception.factory.AdmissionFacturationFactory;
import com.FrameWork.MedLite.Reception.repository.AdmissionFacturationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class AdmissionFacturationService {

    private final AdmissionFacturationRepo admissionFacturationRepo;

    public AdmissionFacturationService(AdmissionFacturationRepo admissionFacturationRepo) {
        this.admissionFacturationRepo = admissionFacturationRepo;
    }

    @Transactional(readOnly = true)
    public List<AdmissionFacturationDTO> findAllAdmissionFacturation() {
        return AdmissionFacturationFactory.listAdmissionFacturationToAdmissionFacturationDTOs(admissionFacturationRepo.findAll(Sort.by("code").descending()));
    }

      @Transactional(readOnly = true)
    public List<AdmissionFacturationDTO> findAllAdmissionFacturationByPaied(Integer codeEtatPaiement) {
        return AdmissionFacturationFactory.listAdmissionFacturationToAdmissionFacturationDTOs(admissionFacturationRepo.findByCodeEtatPaiement(codeEtatPaiement));
    }
    
    
    @Transactional(readOnly = true)
    public AdmissionFacturationDTO findOneByCodeAdmission(Integer codeAdmission) {
        AdmissionFacturation domaine = admissionFacturationRepo.findByCodeAdmission(codeAdmission);
        Preconditions.checkArgument(domaine != null, "error.AdmissionFacturationNotFound");
        return AdmissionFacturationFactory.admissionFacturationToAdmissionFacturationDTO(domaine);
    }

    @Transactional(readOnly = true)
    public AdmissionFacturationDTO findOne(Integer code) {
        AdmissionFacturation domaine = admissionFacturationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.AdmissionFacturationNotFound");
        return AdmissionFacturationFactory.admissionFacturationToAdmissionFacturationDTO(domaine);
    }

    public AdmissionFacturationDTO save(AdmissionFacturationDTO dto) {
        AdmissionFacturation domaine = AdmissionFacturationFactory.admissionFacturationDTOToAdmissionFacturation(dto, new AdmissionFacturation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = admissionFacturationRepo.save(domaine);
        return AdmissionFacturationFactory.admissionFacturationToAdmissionFacturationDTO(domaine);
    }

    public AdmissionFacturation update(AdmissionFacturationDTO dto) {
        AdmissionFacturation domaine = admissionFacturationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AdmissionFacturationNotFound");
        dto.setCode(domaine.getCode());
        AdmissionFacturationFactory.admissionFacturationDTOToAdmissionFacturation(dto, domaine);
        return admissionFacturationRepo.save(domaine);
    }

    public void deleteAdmissionFacturation(Integer code) {
        Preconditions.checkArgument(admissionFacturationRepo.existsById(code), "error.AdmissionFacturationNotFound");
        admissionFacturationRepo.deleteById(code);
    }
}
