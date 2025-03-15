/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.TypePrestation;
import com.FrameWork.MedLite.Parametrage.dto.TypePrestationDTO;
import com.FrameWork.MedLite.Parametrage.factory.TypePrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.TypePrestationRepo;
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
public class TypePrestationService {
    
    private final TypePrestationRepo typePrestationRepo;
    private final CompteurService compteurService;

    public TypePrestationService(TypePrestationRepo typePrestationRepo, CompteurService compteurService) {
        this.typePrestationRepo = typePrestationRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<TypePrestationDTO> findAllTypePrestation() {
        return TypePrestationFactory.listTypePrestationToTypePrestationDTOs(typePrestationRepo.findAll(Sort.by("code").descending()));

    }
    
     @Transactional(readOnly = true)
    public List<TypePrestationDTO> findAllTypePrestationByActif(Boolean actif) {
        return TypePrestationFactory.listTypePrestationToTypePrestationDTOs(typePrestationRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public TypePrestationDTO findOne(Integer code) {
        TypePrestation domaine = typePrestationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.TypePrestationNotFound");
        return TypePrestationFactory.typePrestationToTypePrestationDTO(domaine);
    }

    public TypePrestationDTO save(TypePrestationDTO dto) {
        TypePrestation domaine = TypePrestationFactory.typePrestationDTOToTypePrestation(dto, new TypePrestation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieTypePrestation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = typePrestationRepo.save(domaine);
        return TypePrestationFactory.typePrestationToTypePrestationDTO(domaine);
    }

    public TypePrestation update(TypePrestationDTO dto) {
        TypePrestation domaine = typePrestationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TypePrestationNotFound");
        dto.setCode(domaine.getCode());
        TypePrestationFactory.typePrestationDTOToTypePrestation(dto, domaine);
        return typePrestationRepo.save(domaine);
    }

    public void deleteTypePrestation(Integer code) {
        Preconditions.checkArgument(typePrestationRepo.existsById(code), "error.TypePrestationNotFound");
        typePrestationRepo.deleteById(code);
    }
}
