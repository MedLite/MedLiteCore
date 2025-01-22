/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.FamilleFacturation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamilleFacturationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.FamilleFacturationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.FamilleFacturationRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
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
public class FamilleFacturationService {

    private final FamilleFacturationRepo familleFacturationRepo;
    private final CompteurService compteurService;

    public FamilleFacturationService(FamilleFacturationRepo familleFacturationRepo, CompteurService compteurService) {
        this.familleFacturationRepo = familleFacturationRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<FamilleFacturationDTO> findAllFamilleFacturation() {
        return FamilleFacturationFactory.listFamilleFacturationToFamilleFacturationDTOs(familleFacturationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public FamilleFacturationDTO findOne(Integer code) {
        FamilleFacturation domaine = familleFacturationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FamilleFacturationNotFound");
        return FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine);
    }

    public FamilleFacturationDTO save(FamilleFacturationDTO dto) {
        FamilleFacturation domaine = FamilleFacturationFactory.familleFacturationDTOToFamilleFacturation(dto, new FamilleFacturation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFamilleFacturation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = familleFacturationRepo.save(domaine);

        return FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine);
    }

    public FamilleFacturation update(FamilleFacturationDTO dto) {
        FamilleFacturation domaine = familleFacturationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FamilleFacturationNotFound");
        dto.setCode(domaine.getCode());
        FamilleFacturationFactory.familleFacturationDTOToFamilleFacturation(dto, domaine);
        return familleFacturationRepo.save(domaine);
    }

    public void deleteFamilleFacturation(Integer code) {
        Preconditions.checkArgument(familleFacturationRepo.existsById(code), "error.FamilleFacturationNotFound");
        familleFacturationRepo.deleteById(code);
    }
}
