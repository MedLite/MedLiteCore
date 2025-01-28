/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.Societe;
import com.FrameWork.MedLite.Parametrage.dto.SocieteDTO;
import com.FrameWork.MedLite.Parametrage.factory.SocieteFactory;
import com.FrameWork.MedLite.Parametrage.repository.SocieteRepo;
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
public class SocieteService {

    private final CompteurService compteurService;
    private final SocieteRepo societeRepo;

    public SocieteService(CompteurService compteurService, SocieteRepo societeRepo) {
        this.compteurService = compteurService;
        this.societeRepo = societeRepo;
    }

    @Transactional(readOnly = true)
    public List<SocieteDTO> findAllSociete() {
        return SocieteFactory.listSocieteToSocieteDTOs(societeRepo.findAll());

    }

    @Transactional(readOnly = true)
    public SocieteDTO findOne(Integer code) {
        Societe domaine = societeRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SocieteNotFound");
        return SocieteFactory.societeToSocieteDTO(domaine);
    }

    public SocieteDTO save(SocieteDTO dto) {
        Societe domaine = SocieteFactory.societeDTOToSociete(dto, new Societe());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieSociete");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = societeRepo.save(domaine);

        return SocieteFactory.societeToSocieteDTO(domaine);
    }

    public Societe update(SocieteDTO dto) {
        Societe domaine = societeRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SocieteNotFound");
        dto.setCode(domaine.getCode());
        SocieteFactory.societeDTOToSociete(dto, domaine);
        return societeRepo.save(domaine);
    }

    public void deleteSociete(Integer code) {
        Preconditions.checkArgument(societeRepo.existsById(code), "error.SocieteNotFound");
        societeRepo.deleteById(code);
    }
}
