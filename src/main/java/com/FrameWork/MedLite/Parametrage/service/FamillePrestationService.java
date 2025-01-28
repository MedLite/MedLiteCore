/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.FamillePrestation;
import com.FrameWork.MedLite.Parametrage.dto.FamillePrestationDTO;
import com.FrameWork.MedLite.Parametrage.factory.FamillePrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.FamillePrestationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class FamillePrestationService {

    private final FamillePrestationRepo famillePrestationRepo;
    private final CompteurService compteurService;

    public FamillePrestationService(FamillePrestationRepo famillePrestationRepo, CompteurService compteurService) {
        this.famillePrestationRepo = famillePrestationRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<FamillePrestationDTO> findAllFamillePrestation() {
        return FamillePrestationFactory.listFamillePrestationToFamillePrestationDTOs(famillePrestationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public List<FamillePrestationDTO> findAllFamillePrestationByActif(Boolean actif) {
        return FamillePrestationFactory.listFamillePrestationToFamillePrestationDTOs(famillePrestationRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public FamillePrestationDTO findOne(Integer code) {
        FamillePrestation domaine = famillePrestationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FamillePrestationNotFound");
        return FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine);
    }

    public FamillePrestationDTO save(FamillePrestationDTO dto) {
        FamillePrestation domaine = FamillePrestationFactory.famillePrestationDTOToFamillePrestation(dto, new FamillePrestation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFamillePrestation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = famillePrestationRepo.save(domaine);

        return FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine);
    }

    public FamillePrestation update(FamillePrestationDTO dto) {
        FamillePrestation domaine = famillePrestationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FamillePrestationNotFound");
        dto.setCode(domaine.getCode());
        FamillePrestationFactory.famillePrestationDTOToFamillePrestation(dto, domaine);
        return famillePrestationRepo.save(domaine);
    }

    public void deleteFamillePrestation(Integer code) {
        Preconditions.checkArgument(famillePrestationRepo.existsById(code), "error.FamillePrestationNotFound");
        famillePrestationRepo.deleteById(code);
    }
}
