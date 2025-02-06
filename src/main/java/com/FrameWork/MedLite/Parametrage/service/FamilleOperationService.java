/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.FamilleOperation;
import com.FrameWork.MedLite.Parametrage.dto.FamilleOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.FamilleOperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.FamilleOperationRepo;
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
public class FamilleOperationService {

    private final FamilleOperationRepo familleOperationRepo;
    
    private final CompteurService compteurService;

    public FamilleOperationService(FamilleOperationRepo familleOperationRepo, CompteurService compteurService) {
        this.familleOperationRepo = familleOperationRepo;
        this.compteurService = compteurService;
    }

    
    @Transactional(readOnly = true)
    public List<FamilleOperationDTO> findAllFamilleOperation() {
        return FamilleOperationFactory.listFamilleOperationToFamilleOperationDTOs(familleOperationRepo.findAll(Sort.by("code").descending()));

    }
    
     @Transactional(readOnly = true)
    public List<FamilleOperationDTO> findAllFamilleOperationByActif(Boolean actif) {
        return FamilleOperationFactory.listFamilleOperationToFamilleOperationDTOs(familleOperationRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public FamilleOperationDTO findOne(Integer code) {
        FamilleOperation domaine = familleOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FamilleOperationNotFound");
        return FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine);
    }

    public FamilleOperationDTO save(FamilleOperationDTO dto) {
        FamilleOperation domaine = FamilleOperationFactory.familleOperationDTOToFamilleOperation(dto, new FamilleOperation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
             Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFamilleOperation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        
        domaine = familleOperationRepo.save(domaine);

        return FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine);
    }

    public FamilleOperation update(FamilleOperationDTO dto) {
        FamilleOperation domaine = familleOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FamilleOperationNotFound");
        dto.setCode(domaine.getCode());
        FamilleOperationFactory.familleOperationDTOToFamilleOperation(dto, domaine);
        return familleOperationRepo.save(domaine);
    }

    public void deleteFamilleOperation(Integer code) {
        Preconditions.checkArgument(familleOperationRepo.existsById(code), "error.FamilleOperationNotFound");
        familleOperationRepo.deleteById(code);
    }
}
