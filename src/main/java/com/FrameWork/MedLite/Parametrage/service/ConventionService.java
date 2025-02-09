/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.dto.ConventionDTO;
import com.FrameWork.MedLite.Parametrage.factory.ConventionFactory;
import com.FrameWork.MedLite.Parametrage.repository.ConventionRepo;
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
public class ConventionService {
    
     private final ConventionRepo conventionRepo;
     private final CompteurService compteurService;

    public ConventionService(ConventionRepo conventionRepo, CompteurService compteurService) {
        this.conventionRepo = conventionRepo;
        this.compteurService = compteurService;
    }


 

    @Transactional(readOnly = true)
    public List<ConventionDTO> findAllConvention() {
        return ConventionFactory.listConventionToConventionDTOs(conventionRepo.findAll(Sort.by("code").descending()));

    }
    
    @Transactional(readOnly = true)
    public List<ConventionDTO> findAllConventionByActif(Boolean actif) {
        return ConventionFactory.listConventionToConventionDTOs(conventionRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public ConventionDTO findOne(Integer code) {
        Convention domaine = conventionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.ConventionNotFound");
        return ConventionFactory.conventionToConventionDTO(domaine);
    }

    
      @Transactional(readOnly = true)
    public List<ConventionDTO> findConventionByCodeSociete(Integer codeSociete) {
        return ConventionFactory.listConventionToConventionDTOs(conventionRepo.findByCodeSociete(codeSociete));

    }
    

//
    public ConventionDTO save(ConventionDTO dto) {
        Convention domaine = ConventionFactory.conventionDTOToConvention(dto, new Convention());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieConvention");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine = conventionRepo.save(domaine);
        return ConventionFactory.conventionToConventionDTO(domaine);
    }

    public Convention update(ConventionDTO dto) {
        Convention domaine = conventionRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.ConventionNotFound");
        dto.setCode(domaine.getCode());
        ConventionFactory.conventionDTOToConvention(dto, domaine);
        return conventionRepo.save(domaine);
    }

    public void deleteConvention(Integer code) {
        Preconditions.checkArgument(conventionRepo.existsById(code), "error.ConventionNotFound");
        conventionRepo.deleteById(code);
    }
}
