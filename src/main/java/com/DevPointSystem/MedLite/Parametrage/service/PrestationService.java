/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.PrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PrestationRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class PrestationService {
        private final Logger log = LoggerFactory.getLogger(PrestationService.class);

    private final PrestationRepo prestationRepo;

    private final CompteurService compteurService;

    private final DetailsPrestationRepo detailsPrestationRepo;

    public PrestationService(PrestationRepo prestationRepo, CompteurService compteurService, DetailsPrestationRepo detailsPrestationRepo) {
        this.prestationRepo = prestationRepo;
        this.compteurService = compteurService;
        this.detailsPrestationRepo = detailsPrestationRepo;
    }

    @Transactional(readOnly = true)
    public List<PrestationDTO> findAllPrestation() {
        return PrestationFactory.listPrestationToPrestationDTOs(prestationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public PrestationDTO findOne(Integer code) {
        Prestation domaine = prestationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PrestationNotFound");
// 

        return PrestationFactory.prestationToPrestationDTO(domaine);
    }

    public PrestationDTO save(PrestationDTO dto) { 
        Prestation domaine = PrestationFactory.prestationDTOToPrestation(dto, new Prestation());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisiePres");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = prestationRepo.save(domaine);
       
        return PrestationFactory.prestationToPrestationDTO(domaine);
    } 
    public PrestationDTO updateNewWithFlush(PrestationDTO dto) {
        Prestation domaine = prestationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.PrestationNotFound");
        detailsPrestationRepo.deleteByCodePrestation(dto.getCode());
        domaine = PrestationFactory.prestationDTOToPrestation(dto, domaine);
        domaine = prestationRepo.save(domaine);
        PrestationDTO resultDTO = PrestationFactory.prestationToPrestationDTO(domaine);
        return resultDTO;
    }

    public void deletePrestation(Integer code) {
        Preconditions.checkArgument(prestationRepo.existsById(code), "error.PrestationNotFound");
        prestationRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPrestationDTO> findOneWithDetails(Integer code) {
        Collection<DetailsPrestation> domaine = detailsPrestationRepo.findByDetailsPrestationPK_codePrestation(code);
        return DetailsPrestationFactory.detailsPrestationTodetailsPrestationDTOCollections(domaine);
    }
    
    
     @Transactional(readOnly = true)
    public Collection<DetailsPrestationDTO> findOneWithDetailsCodePrestationAndCodeNatureAdmission(Integer code,Integer codeNatureAdmission) {
        Collection<DetailsPrestation> domaine = detailsPrestationRepo.findByDetailsPrestationPK_codePrestationAndcodeNatureAdmission(code,codeNatureAdmission);
        return DetailsPrestationFactory.detailsPrestationTodetailsPrestationDTOCollections(domaine);
    }
}
