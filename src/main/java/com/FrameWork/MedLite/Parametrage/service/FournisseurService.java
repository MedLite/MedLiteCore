/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.Fournisseur;
import com.FrameWork.MedLite.Parametrage.dto.FournisseurDTO;
import com.FrameWork.MedLite.Parametrage.factory.FournisseurFactory;
import com.FrameWork.MedLite.Parametrage.repository.FournisseurRepo;
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
public class FournisseurService {
     private final CompteurService compteurService;
    private final FournisseurRepo fournisseurRepo;

    public FournisseurService(CompteurService compteurService, FournisseurRepo fournisseurRepo) {
        this.compteurService = compteurService;
        this.fournisseurRepo = fournisseurRepo;
    }

   
    
    
    @Transactional(readOnly = true)
    public List<FournisseurDTO> findAllFournisseur() {
        return FournisseurFactory.listFournisseurToFournisseurDTOs(fournisseurRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public FournisseurDTO findOne(Integer code) {
        Fournisseur domaine = fournisseurRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FournisseurNotFound");
        return FournisseurFactory.fournisseurToFournisseurDTO(domaine);
    }

//
    public FournisseurDTO save(FournisseurDTO dto) {
        Fournisseur domaine = FournisseurFactory.fournisseurDTOToFournisseur(dto, new Fournisseur());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
          Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFrs");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        
        
        domaine = fournisseurRepo.save(domaine);
        return FournisseurFactory.fournisseurToFournisseurDTO(domaine);
    }

    public Fournisseur update(FournisseurDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.FournisseurNotFound");
        Fournisseur domaine = fournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FournisseurNotFound");
        dto.setCode(domaine.getCode());
        FournisseurFactory.fournisseurDTOToFournisseur(dto, domaine);
        return fournisseurRepo.save(domaine);
    }

    public void deleteFournisseur(Integer code) {
        Preconditions.checkArgument(fournisseurRepo.existsById(code), "error.FournisseurNotFound");
        fournisseurRepo.deleteById(code);
    }
}
