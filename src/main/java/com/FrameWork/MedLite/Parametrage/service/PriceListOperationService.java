/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.domaine.PriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListOperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.FrameWork.MedLite.Parametrage.repository.PriceListOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PriceListRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.FrameWork.MedLite.web.errors.IllegalBusinessLogiqueException;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class PriceListOperationService {
     private final Logger log = LoggerFactory.getLogger(PriceListOperationService.class);
 
    private final PriceListOperationRepo priceListOperationRepo;

    private final DetailsPriceListOperationRepo detailsPriceListOperationRepo;

    private final CompteurService compteurService;
    private final DetailsPriceListOperationService detailsPriceListOperationService;
     

    public PriceListOperationService(PriceListOperationRepo priceListOperationRepo, DetailsPriceListOperationRepo detailsPriceListOperationRepo, CompteurService compteurService, DetailsPriceListOperationService detailsPriceListOperationService) {
        this.priceListOperationRepo = priceListOperationRepo;
        this.detailsPriceListOperationRepo = detailsPriceListOperationRepo;
        this.compteurService = compteurService;
        this.detailsPriceListOperationService = detailsPriceListOperationService; 
    }

   

    @Transactional(readOnly = true)
    public List<PriceListOperationDTO> findAllPriceListOperation() {
        return PriceListOperationFactory.listPriceListOpeartionToPriceListOpeartionDTOs(priceListOperationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public PriceListOperationDTO findOne(Integer code) {
        PriceListOperation domaine = priceListOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PriceListOperationNotFound");

        return PriceListOperationFactory.priceListOperationToPriceListOperationDTO(domaine);
    }

    @Transactional
    public PriceListOperation findPriceListOperationByCode(Integer code) {
        System.out.println("code    " + code);
        PriceListOperation domaine = priceListOperationRepo.findByCode(code);

        if (domaine == null) {
            log.error("PriceList not found for code: {}", code); //Use slf4j or your logging framework
            throw new IllegalBusinessLogiqueException("error.PriceListNotFound");
        }
        return domaine;
    }
 
    public PriceListOperationDTO save(PriceListOperationDTO dto) {
        PriceListOperation domaine = PriceListOperationFactory.priceListOperationDTOToPriceListOperation(dto, new PriceListOperation());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisiePLOP");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
 
        domaine = priceListOperationRepo.save(domaine);
        return PriceListOperationFactory.priceListOperationToPriceListOperationDTO(domaine);
    }

    public PriceListOperationDTO updateNew(PriceListOperationDTO dto) {
        PriceListOperation inBase = priceListOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.PriceListNotFound"); 
        inBase = PriceListOperationFactory.priceListOperationDTOToPriceListOperation(dto, inBase);
        inBase = priceListOperationRepo.save(inBase);
        PriceListOperationDTO resultDTO = PriceListOperationFactory.priceListOperationToPriceListOperationDTO(inBase);
        return resultDTO;
    }

    public void deletePriceListOperation(Integer code) {
        Preconditions.checkArgument(priceListOperationRepo.existsById(code), "error.PriceListNotFound");
        PriceListOperation domaine = priceListOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.isCash() != Boolean.TRUE, "error.PriceListOperationIsCashWeDontAccessToDelete");
        priceListOperationRepo.deleteById(code);
    }
 
//    public Collection<PriceList> findPriceListEncours(PriceList masterPriceList, Collection<PriceList> priceLists) {
//        log.debug("findPriceListEncours codePriceList :{} ", masterPriceList.getCode()); 
//        priceLists.add(masterPriceList);
//        return priceLists.stream().distinct().collect(Collectors.toList());
//
//    }

    @Transactional(readOnly = true)
    public PriceListOperation findByPriceListOperationCash() {
        log.debug("Request to get PriceList mere : {}"); 
        PriceListOperation listPriceListOperation = priceListOperationRepo.findByCash(Boolean.TRUE);
        Preconditions.checkArgument(listPriceListOperation != null, "error.PriceListOperationInexistant");
        return listPriceListOperation;
    }
}
