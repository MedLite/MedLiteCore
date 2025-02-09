/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.TypeIntervenant;
import com.FrameWork.MedLite.Parametrage.dto.TypeIntervenantDTO;
import com.FrameWork.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.FrameWork.MedLite.Parametrage.repository.TypeIntervenantRepo;
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
public class TypeIntervenantService {

    private final TypeIntervenantRepo typeIntervenantRepo;

    private final CompteurService compteurService;

    public TypeIntervenantService(TypeIntervenantRepo typeIntervenantRepo, CompteurService compteurService) {
        this.typeIntervenantRepo = typeIntervenantRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<TypeIntervenantDTO> findAllTypeIntervenant() {
        return TypeIntervenantFactory.listTypeIntervenantToTypeIntervenantDTOs(typeIntervenantRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<TypeIntervenantDTO> findAllTypeIntervenantByActif(Boolean actif) {
        return TypeIntervenantFactory.listTypeIntervenantToTypeIntervenantDTOs(typeIntervenantRepo.findByActif(actif));

    }
    
    @Transactional(readOnly = true)
    public List<TypeIntervenantDTO> findAllTypeIntervenantByActifAndVirtuel(Boolean actif ,Boolean virtuel ) {
        return TypeIntervenantFactory.listTypeIntervenantToTypeIntervenantDTOs(typeIntervenantRepo.findByActifAndVirtuel(actif,virtuel));

    }

    @Transactional(readOnly = true)
    public TypeIntervenantDTO findOne(Integer code) {
        TypeIntervenant domaine = typeIntervenantRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.TypeIntervenantNotFound");
        return TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<TypeIntervenantDTO> findIsNotActif(Boolean code) {
        List<TypeIntervenant> domaine = typeIntervenantRepo.findByIsClinique(code);
        Preconditions.checkArgument(domaine != null, "error.TypeIntervenantNotFound");
        return TypeIntervenantFactory.listTypeIntervenantToTypeIntervenantDTOs(domaine);
    }

    public TypeIntervenantDTO save(TypeIntervenantDTO dto) {
        TypeIntervenant domaine = TypeIntervenantFactory.typeIntervenantDTOToTypeIntervenant(dto, new TypeIntervenant());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieTypInterv");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = typeIntervenantRepo.save(domaine);

        return TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine);
    }

    public TypeIntervenant update(TypeIntervenantDTO dto) {
        TypeIntervenant domaine = typeIntervenantRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TypeIntervenantNotFound");
        dto.setCode(domaine.getCode());
        TypeIntervenantFactory.typeIntervenantDTOToTypeIntervenant(dto, domaine);
        return typeIntervenantRepo.save(domaine);
    }

    public void deleteTypeIntervenant(Integer code) {
        Preconditions.checkArgument(typeIntervenantRepo.existsById(code), "error.TypeIntervenantNotFound");
        TypeIntervenant domaine = typeIntervenantRepo.findByCode(code);
        Preconditions.checkArgument(domaine.isIsClinique() != Boolean.TRUE, "error.TypeIntervenantIsClinique");

        typeIntervenantRepo.deleteById(code);
    }
}
