/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.SpecialiteCabinet;
import com.FrameWork.MedLite.Parametrage.dto.SpecialiteCabinetDTO;
import com.FrameWork.MedLite.Parametrage.factory.SpecialiteCabinetFactory;
import com.FrameWork.MedLite.Parametrage.repository.SpecialiteCabinetRepo;
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
public class SpecialiteCabinetService {

    private final SpecialiteCabinetRepo specialiteSpecialiteCabinetRepo;
    private final CompteurService compteurService;

    public SpecialiteCabinetService(SpecialiteCabinetRepo specialiteSpecialiteCabinetRepo, CompteurService compteurService) {
        this.specialiteSpecialiteCabinetRepo = specialiteSpecialiteCabinetRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<SpecialiteCabinetDTO> findAllSpecialiteCabinet() {
        return SpecialiteCabinetFactory.listSpecialiteCabinetToSpecialiteCabinetDTOs(specialiteSpecialiteCabinetRepo.findAll());

    }

    @Transactional(readOnly = true)
    public SpecialiteCabinetDTO findOne(Integer code) {
        SpecialiteCabinet domaine = specialiteSpecialiteCabinetRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SpecialiteCabinetNotFound");
        return SpecialiteCabinetFactory.specialiteCabinetToSpecialiteCabinetDTO(domaine);
    }

//
    public SpecialiteCabinetDTO save(SpecialiteCabinetDTO dto) {
        SpecialiteCabinet domaine = SpecialiteCabinetFactory.specialiteCabinetDTOToSpecialiteCabinet(dto, new SpecialiteCabinet());
        domaine.setDateCreate(new Date());
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieSpCabinet");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = specialiteSpecialiteCabinetRepo.save(domaine);
        return SpecialiteCabinetFactory.specialiteCabinetToSpecialiteCabinetDTO(domaine);
    }

    public SpecialiteCabinet update(SpecialiteCabinetDTO dto) {
        SpecialiteCabinet domaine = specialiteSpecialiteCabinetRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SpecialiteCabinetNotFound");
        dto.setCode(domaine.getCode());
        SpecialiteCabinetFactory.specialiteCabinetDTOToSpecialiteCabinet(dto, domaine);
        return specialiteSpecialiteCabinetRepo.save(domaine);
    }

    public void deleteSpecialiteCabinet(Integer code) {
        Preconditions.checkArgument(specialiteSpecialiteCabinetRepo.existsById(code), "error.SpecialiteCabinetNotFound");
        specialiteSpecialiteCabinetRepo.deleteById(code);
    }
}
