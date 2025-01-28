/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.dto.CabinetDTO;
import com.FrameWork.MedLite.Parametrage.factory.CabinetFactory;
import com.FrameWork.MedLite.Parametrage.repository.CabinetRepo;
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
public class CabinetService {

    private final CabinetRepo cabinetRepo;
     private final CompteurService compteurService;

    public CabinetService(CabinetRepo cabinetRepo, CompteurService compteurService) {
        this.cabinetRepo = cabinetRepo;
        this.compteurService = compteurService;
    }


 

    @Transactional(readOnly = true)
    public List<CabinetDTO> findAllCabinet() {
        return CabinetFactory.listCabinetToCabinetDTOs(cabinetRepo.findAll());

    }

    @Transactional(readOnly = true)
    public CabinetDTO findOne(Integer code) {
        Cabinet domaine = cabinetRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.CabinetNotFound");
        return CabinetFactory.cabinetToCabinetDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<CabinetDTO> FindByCodeSpecialite(Integer codeSpecilaiteCabinet) {
        List<Cabinet> domaine = cabinetRepo.findByCodeSpecialiteCabinet(codeSpecilaiteCabinet);
        Preconditions.checkArgument(domaine != null, "error.SpecialiteCabinetNotFound");
        return CabinetFactory.listCabinetToCabinetDTOs(domaine);
    }

//
    public CabinetDTO save(CabinetDTO dto) {
        Cabinet domaine = CabinetFactory.cabinetDTOToCabinet(dto, new Cabinet());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieCabinet");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine = cabinetRepo.save(domaine);
        return CabinetFactory.cabinetToCabinetDTO(domaine);
    }

    public Cabinet update(CabinetDTO dto) {
        Cabinet domaine = cabinetRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.CabinetNotFound");
        dto.setCode(domaine.getCode());
        CabinetFactory.cabinetDTOToCabinet(dto, domaine);
        return cabinetRepo.save(domaine);
    }

    public void deleteCabinet(Integer code) {
        Preconditions.checkArgument(cabinetRepo.existsById(code), "error.CabinetNotFound");
        cabinetRepo.deleteById(code);
    }
}
