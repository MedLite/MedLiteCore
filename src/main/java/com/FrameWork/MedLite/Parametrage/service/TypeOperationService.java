/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.TypeOperation;
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.dto.TypeOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.TypeOperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.TypeOperationRepo;
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
public class TypeOperationService {

    private final TypeOperationRepo typeOperationRepo;
    private final CompteurService compteurService;

    public TypeOperationService(TypeOperationRepo typeOperationRepo, CompteurService compteurService) {
        this.typeOperationRepo = typeOperationRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<TypeOperationDTO> findAllTypeOperation() {
        return TypeOperationFactory.listTypeOperationToTypeOperationDTOs(typeOperationRepo.findAll(Sort.by("code").descending()));

    }
    
     @Transactional(readOnly = true)
    public List<TypeOperationDTO> findAllTypeOperationByActif(Boolean actif) {
        return TypeOperationFactory.listTypeOperationToTypeOperationDTOs(typeOperationRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public TypeOperationDTO findOne(Integer code) {
        TypeOperation domaine = typeOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.TypeOperationNotFound");
        return TypeOperationFactory.typeOperationToTypeOperationDTO(domaine);
    }

    public TypeOperationDTO save(TypeOperationDTO dto) {
        TypeOperation domaine = TypeOperationFactory.typeOperationDTOToTypeOperation(dto, new TypeOperation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieTypeOperation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = typeOperationRepo.save(domaine);
        return TypeOperationFactory.typeOperationToTypeOperationDTO(domaine);
    }

    public TypeOperation update(TypeOperationDTO dto) {
        TypeOperation domaine = typeOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TypeOperationNotFound");
        dto.setCode(domaine.getCode());
        TypeOperationFactory.typeOperationDTOToTypeOperation(dto, domaine);
        return typeOperationRepo.save(domaine);
    }

    public void deleteTypeOperation(Integer code) {
        Preconditions.checkArgument(typeOperationRepo.existsById(code), "error.TypeOperationNotFound");
        typeOperationRepo.deleteById(code);
    }
}
