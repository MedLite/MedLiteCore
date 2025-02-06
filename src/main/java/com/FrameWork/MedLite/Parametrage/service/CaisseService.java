/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Caisse;
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.dto.CaisseDTO;
import com.FrameWork.MedLite.Parametrage.factory.CaisseFactory;
import com.FrameWork.MedLite.Parametrage.factory.DeviseFactory;
import com.FrameWork.MedLite.Parametrage.repository.CaisseRepo;
import com.FrameWork.MedLite.Recette.domaine.SoldeCaisse;
import com.FrameWork.MedLite.Recette.repository.MouvementCaisseRepo;
import com.FrameWork.MedLite.Recette.repository.SoldeCaisseRepo;
import com.FrameWork.MedLite.Recette.service.SoldeCaisseService;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class CaisseService {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

     private final CompteurService compteurService;
    private final CaisseRepo caisseRepo;
    private final SoldeCaisseRepo soldeCaisseRepo;
    private final MouvementCaisseRepo mouvementCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;
    private final static String caisseError = "error.CaisseNotFound";

    public CaisseService(CompteurService compteurService, CaisseRepo caisseRepo, SoldeCaisseRepo soldeCaisseRepo, MouvementCaisseRepo mouvementCaisseRepo, SoldeCaisseService soldeCaisseService) {
        this.compteurService = compteurService;
        this.caisseRepo = caisseRepo;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
    }
 
    @Transactional(readOnly = true)
    public List<CaisseDTO> findAllCaisse() {
        return CaisseFactory.listCaisseToCaisseDTOs(caisseRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public CaisseDTO findOne(Integer code) {
        Caisse domaine = caisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, caisseError);
        return CaisseFactory.caisseToCaisseDTO(domaine);
    }

//    @Transactional(readOnly = true)
//    public List<CaisseDTO> findByCodeNotIn(List<Integer> code,List<Integer> codeDevise) {
////        List<Caisse> domaine = caisseRepo.findByCodeNotIn(Helper.removeNullValueFromCollection(code));
//
//        return CaisseFactory.listCaisseToCaisseDTOs(caisseRepo.findByCodeNotInAndCodeDeviseIn(Helper.removeNullValueFromCollection(code,codeDevise)));
//    }
//    
    @Transactional(readOnly = true)
    public List<Caisse> findByCodeNotInAndCodeDevise(Integer code, Integer codeDevise) {
        return caisseRepo.findByCodeNotAndCodeDevise(code, codeDevise);
    }

    @Transactional(readOnly = true)
    public List<CaisseDTO> findByCodeTypeCaisse(Integer codeTypeCaisse) {
        List<Caisse> result = caisseRepo.findByCodeTypeCaisse(codeTypeCaisse);
        return CaisseFactory.listCaisseToCaisseDTOs(result);
    }

    @Transactional(readOnly = true)
    public List<CaisseDTO> findByCodeDevise(Integer codeDevise) {
        List<Caisse> result = caisseRepo.findByCodeDevise(codeDevise);
        return CaisseFactory.listCaisseToCaisseDTOs(result);
    }

//
    public CaisseDTO save(CaisseDTO dto) {
        Caisse domaine = CaisseFactory.caisseDTOToCaisse(dto, new Caisse());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieCaisse");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine = caisseRepo.save(domaine);

        SoldeCaisse sc = new SoldeCaisse();
        sc.setCodeCaisse(domaine.getCode());
        if (sc.getCodeCaisse() != null) {
            sc.setCaisse(CaisseFactory.createCaisseByCode(domaine.getCode()));
        }
        sc.setCodeDevise(domaine.getCodeDevise());
        if (sc.getCodeDevise() != null) {
            sc.setDevise(DeviseFactory.createDeviseByCode(domaine.getCodeDevise()));
        }
        sc.setCredit(BigDecimal.ZERO);
        sc.setDebit(BigDecimal.ZERO);
        sc.setDateUpdated(dto.getDateCreate());
        sc.setUserUpdated(dto.getUserCreate());
        sc = soldeCaisseRepo.save(sc);

        return CaisseFactory.caisseToCaisseDTO(domaine);
    }

    public Caisse update(CaisseDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.CaisseNotFound");
        Caisse domaine = caisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, caisseError);
        dto.setCode(domaine.getCode());
        CaisseFactory.caisseDTOToCaisse(dto, domaine);
        return caisseRepo.save(domaine);
    }

    public void deleteCaisse(Integer code) {
        Preconditions.checkArgument(caisseRepo.existsById(code), "error.CaisseNotFound");
        Preconditions.checkArgument(!mouvementCaisseRepo.existsByCodeCaisse(code), "error.CaisseMouvementer");

        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(code);
        soldeCaisseService.deleteSoldeCaisse(soldeCaisse.getCode());
        caisseRepo.deleteById(code);
    }
}
