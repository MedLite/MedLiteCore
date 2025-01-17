/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceListPK;
import com.DevPointSystem.MedLite.Parametrage.domaine.PriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.PriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PriceListRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java.util.List;
import jakarta.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class PriceListService {

//    @Autowired
//    private EntityManager entityManager; // Inject the EntityManager
    private final PriceListRepo priceListRepo;

    private final DetailsPriceListRepo detailsPriceListRepo;

    private final CompteurService compteurService;

    public PriceListService(PriceListRepo priceListRepo, DetailsPriceListRepo detailsPriceListRepo, CompteurService compteurService) {
        this.priceListRepo = priceListRepo;
        this.detailsPriceListRepo = detailsPriceListRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<PriceListDTO> findAllPriceList() {
        return PriceListFactory.listPriceListToPriceListDTOs(priceListRepo.findAll());

    }

    @Transactional(readOnly = true)
    public PriceListDTO findOne(Integer code) {
        PriceList domaine = priceListRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PriceListNotFound");

        return PriceListFactory.priceListToPriceListDTO(domaine);
    }

    
      @Transactional(readOnly = true)
    public PriceListDTO findIsCash(Boolean isCash) {
        PriceList domaine = priceListRepo.findByCash(isCash);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PriceListNotFound"); 
        return PriceListFactory.priceListToPriceListDTO(domaine);
    }
    
    
    public PriceListDTO save(PriceListDTO dto) {
        PriceList domaine = PriceListFactory.priceListDTOToPriceList(dto, new PriceList());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisiePL");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = priceListRepo.save(domaine);
        return PriceListFactory.priceListToPriceListDTO(domaine);
    }

    public PriceListDTO updateNew(PriceListDTO dto) {
        PriceList inBase = priceListRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.PriceListNotFound"); 
        detailsPriceListRepo.deleteByCodePriceList(dto.getCode()); 
        inBase = PriceListFactory.priceListDTOToPriceList(dto, inBase);
        inBase = priceListRepo.save(inBase);
        PriceListDTO resultDTO = PriceListFactory.priceListToPriceListDTO(inBase);
        return resultDTO;
    }

    public void deletePriceList(Integer code) {
        Preconditions.checkArgument(priceListRepo.existsById(code), "error.PriceListNotFound");
        PriceList domaine= priceListRepo.findByCode(code);
        Preconditions.checkArgument(domaine.isCash() != Boolean.TRUE, "error.PriceListIsCashWeDontAccessToDelete");
        priceListRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPriceListDTO> findOneWithDetails(Integer code) {
        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceList(code);
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPriceListDTO> findOneWithDetailsWithCodePriceAndCodePrestation(Integer codePrice, Integer codePrestation) {
        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceListAndCodePrestation(codePrice, codePrestation);
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPriceListDTO> findOneWithDetailsWithCodePriceAndPrestationAndNatureAdmission(Integer codePrice, Integer codePrestation, Integer codeNatureAdmission) {
        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceListAndCodePrestationAndCodeNatureAdmission(codePrice, codePrestation, codeNatureAdmission);
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
    }
}
