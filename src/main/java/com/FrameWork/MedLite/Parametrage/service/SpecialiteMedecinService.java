/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.domaine.SpecialiteMedecin;
import com.FrameWork.MedLite.Parametrage.dto.SpecialiteMedecinDTO;
import com.FrameWork.MedLite.Parametrage.factory.SpecialiteMedecinFactory;
import com.FrameWork.MedLite.Parametrage.repository.MedecinRepo;
import com.FrameWork.MedLite.Parametrage.repository.SpecialiteMedecinRepo;
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
public class SpecialiteMedecinService {

    private final SpecialiteMedecinRepo specialiteMedecinRepo;
    private final CompteurService compteurService;
    private final MedecinRepo medecinRepo;

    public SpecialiteMedecinService(SpecialiteMedecinRepo specialiteMedecinRepo, CompteurService compteurService, MedecinRepo medecinRepo) {
        this.specialiteMedecinRepo = specialiteMedecinRepo;
        this.compteurService = compteurService;
        this.medecinRepo = medecinRepo;
    }

    @Transactional(readOnly = true)
    public List<SpecialiteMedecinDTO> findAllSpecialiteMedecin() {
        return SpecialiteMedecinFactory.listSpecialiteMedecinToSpecialiteMedecinDTOs(specialiteMedecinRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public SpecialiteMedecinDTO findOne(Integer code) {
        SpecialiteMedecin domaine = specialiteMedecinRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SpecialiteMedecinNotFound");
        return SpecialiteMedecinFactory.specialiteMedecinToSpecialiteMedecinDTO(domaine);
    }

    public SpecialiteMedecinDTO save(SpecialiteMedecinDTO dto) {
        SpecialiteMedecin domaine = SpecialiteMedecinFactory.specialiteMedecinDTOToSpecialiteMedecin(dto, new SpecialiteMedecin());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieSpMdecin");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = specialiteMedecinRepo.save(domaine);

        return SpecialiteMedecinFactory.specialiteMedecinToSpecialiteMedecinDTO(domaine);
    }

    public SpecialiteMedecin update(SpecialiteMedecinDTO dto) {
        SpecialiteMedecin domaine = specialiteMedecinRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SpecialiteMedecinNotFound");
        dto.setCode(domaine.getCode());
        SpecialiteMedecinFactory.specialiteMedecinDTOToSpecialiteMedecin(dto, domaine);
        return specialiteMedecinRepo.save(domaine);
    }

    public void deleteSpecialiteMedecin(Integer code) {
        Preconditions.checkArgument(specialiteMedecinRepo.existsById(code), "error.SpecialiteMedecinNotFound");
        List<Medecin> md = medecinRepo.findByCodeSpecialiteMedecin(code);
        Preconditions.checkArgument(md.isEmpty(), "error.SpecialiteMedecinUsed");
        specialiteMedecinRepo.deleteById(code);
    }
}
