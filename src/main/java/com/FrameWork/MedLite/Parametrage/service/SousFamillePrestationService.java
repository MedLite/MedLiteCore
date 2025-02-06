/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.SousFamillePrestation;
import com.FrameWork.MedLite.Parametrage.dto.SousFamillePrestationDTO;
import com.FrameWork.MedLite.Parametrage.factory.SousFamillePrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.SousFamillePrestationRepo;
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
public class SousFamillePrestationService {

    private final SousFamillePrestationRepo sousFamillePrestationRepo;
    private final CompteurService compteurService;
    
    public SousFamillePrestationService(SousFamillePrestationRepo sousFamillePrestationRepo, CompteurService compteurService) {
        this.sousFamillePrestationRepo = sousFamillePrestationRepo;
        this.compteurService = compteurService;
    }
    
    @Transactional(readOnly = true)
    public List<SousFamillePrestationDTO> findAllSousFamillePrestation() {
        return SousFamillePrestationFactory.listSousFamillePrestationToSousFamillePrestationDTOs(sousFamillePrestationRepo.findAll(Sort.by("code").descending()));
        
    }
    
    @Transactional(readOnly = true)
    public List<SousFamillePrestationDTO> findAllSousFamillePrestationByActif(Boolean actif) {
        return SousFamillePrestationFactory.listSousFamillePrestationToSousFamillePrestationDTOs(sousFamillePrestationRepo.findByActif(actif));

    }
    
    @Transactional(readOnly = true)
        public List<SousFamillePrestationDTO> findAllSousFamillePrestationByCodeFamille(Integer codeFamillePrestation) {
        return SousFamillePrestationFactory.listSousFamillePrestationToSousFamillePrestationDTOs(sousFamillePrestationRepo.findByCodeFamillePrestation(codeFamillePrestation));
        
    }
    
    @Transactional(readOnly = true)
    public SousFamillePrestationDTO findOne(Integer code) {
        SousFamillePrestation domaine = sousFamillePrestationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SousFamillePrestationNotFound");
        return SousFamillePrestationFactory.sousFamillePrestationToSousFamillePrestationDTO(domaine);
    }
    
    public SousFamillePrestationDTO save(SousFamillePrestationDTO dto) {
        SousFamillePrestation domaine = SousFamillePrestationFactory.sousFamillePrestationDTOToSousFamillePrestation(dto, new SousFamillePrestation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieSousFamillePrestation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine = sousFamillePrestationRepo.save(domaine);
        
        return SousFamillePrestationFactory.sousFamillePrestationToSousFamillePrestationDTO(domaine);
    }
    
    public SousFamillePrestation update(SousFamillePrestationDTO dto) {
        SousFamillePrestation domaine = sousFamillePrestationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SousFamillePrestationNotFound");
        dto.setCode(domaine.getCode());
        SousFamillePrestationFactory.sousFamillePrestationDTOToSousFamillePrestation(dto, domaine);
        return sousFamillePrestationRepo.save(domaine);
    }
    
    public void deleteSousFamillePrestation(Integer code) {
        Preconditions.checkArgument(sousFamillePrestationRepo.existsById(code), "error.SousFamillePrestationNotFound");
        sousFamillePrestationRepo.deleteById(code);
    }
}
