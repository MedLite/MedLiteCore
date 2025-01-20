/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceListPK;
import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import com.DevPointSystem.MedLite.Parametrage.domaine.PriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.PriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PriceListRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.DevPointSystem.MedLite.web.errors.IllegalBusinessLogiqueException;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java.util.List;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.annotations.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(PriceListService.class);

//    @Autowired
//    private EntityManager entityManager; // Inject the EntityManager
    private final PriceListRepo priceListRepo;

    private final DetailsPriceListRepo detailsPriceListRepo;

    private final CompteurService compteurService;
    private final DetailsPriceListService detailsPriceListService;
    
    private final NatureAdmissionService natureAdmissionService;

    public PriceListService(PriceListRepo priceListRepo, DetailsPriceListRepo detailsPriceListRepo, CompteurService compteurService, DetailsPriceListService detailsPriceListService, NatureAdmissionService natureAdmissionService) {
        this.priceListRepo = priceListRepo;
        this.detailsPriceListRepo = detailsPriceListRepo;
        this.compteurService = compteurService;
        this.detailsPriceListService = detailsPriceListService;
        this.natureAdmissionService = natureAdmissionService;
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

    @Transactional
    public PriceList findPriceListByCode(Integer code) {
        System.out.println("code    " + code);
        PriceList domaine = priceListRepo.findByCode(code);

        if (domaine == null) {
            log.error("PriceList not found for code: {}", code); //Use slf4j or your logging framework
            throw new IllegalBusinessLogiqueException("error.PriceListNotFound");
        }
        return domaine;
    }

//    @Transactional(readOnly = true)
//    public PriceListDTO findIsCash(Boolean isCash) {
//        PriceList domaine = priceListRepo.findByCash(isCash);
//        Preconditions.checkArgument(domaine.getCode() != null, "error.PriceListNotFound");
//        return PriceListFactory.priceListToPriceListDTO(domaine);
//    }
    public PriceListDTO save(PriceListDTO dto) {
        PriceList domaine = PriceListFactory.priceListDTOToPriceList(dto, new PriceList());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisiePL");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

//           List<NatureAdmission> listNature = natureAdmissionService.findByActifIn(actif);
//        for (NatureAdmission nature : listNature) {
//            for (ListeCouverture couv : couvActif) {
//
//                DetailsListeCouverture detail = new DetailsListeCouverture();
//                DetailsListeCouverturePK detailPK = new DetailsListeCouverturePK();
//                detailPK.setCodeListeCouverture(couv.getCode());
//                detailPK.setCodeNatureAdmission(nature.getCode());
//                detail.setDetailsListeCouverturePK(detailPK);
//                detail.setIsException(false);
//                detail.setIsExceptionFamille(false);
//                detail.setDateCreate(new Date());
//                detail.setUserCreate(user);
//                detail.setPrestation(prestation);
//                detail.setListeCouverture(couv);
//                List<ListeCouvertureParSousFamille> couvParSouFam = listCouvParSouFam.stream()
//                        .filter(p -> p.getNatureAdmission().getCode().equals(nature.getCode()))
//                        .filter(p -> p.getListeCouverture().getCode().equals(couv.getCode()))
//                        .collect(Collectors.toList());
//                if (couvParSouFam.isEmpty() == false) {
//                    detail.setTauxCouverture(couvParSouFam.get(0).getTaux());
//                    detail.setMontantCouverture(couvParSouFam.get(0).getMontant());
//                } else {
//                    detail.setTauxCouverture(BigDecimal.ZERO);
//                    detail.setMontantCouverture(BigDecimal.ZERO);
//                }
//                listCouv.add(detail);
//            }
//        }

        domaine = priceListRepo.save(domaine);
        return PriceListFactory.priceListToPriceListDTO(domaine);
    }

    public PriceListDTO updateNew(PriceListDTO dto) {
        PriceList inBase = priceListRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.PriceListNotFound");
//        detailsPriceListRepo.deleteByCodePriceList(dto.getCode());
        inBase = PriceListFactory.priceListDTOToPriceList(dto, inBase);
        inBase = priceListRepo.save(inBase);
        PriceListDTO resultDTO = PriceListFactory.priceListToPriceListDTO(inBase);
        return resultDTO;
    }

    public void deletePriceList(Integer code) {
        Preconditions.checkArgument(priceListRepo.existsById(code), "error.PriceListNotFound");
        PriceList domaine = priceListRepo.findByCode(code);
        Preconditions.checkArgument(domaine.isCash() != Boolean.TRUE, "error.PriceListIsCashWeDontAccessToDelete");
        priceListRepo.deleteById(code);
    }

//    @Transactional(readOnly = true)
//    public Collection<DetailsPriceListDTO> findOneWithDetails(Integer code) {
//        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceList(code);
//        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<DetailsPriceListDTO> findOneWithDetailsWithCodePriceAndCodePrestation(Integer codePrice, Integer codePrestation) {
//        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceListAndCodePrestation(codePrice, codePrestation);
//        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<DetailsPriceListDTO> findOneWithDetailsWithCodePriceAndPrestationAndNatureAdmission(Integer codePrice, Integer codePrestation, Integer codeNatureAdmission) {
//        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceListAndCodePrestationAndCodeNatureAdmission(codePrice, codePrestation, codeNatureAdmission);
//        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
//    }
    public Collection<PriceList> findPriceListEncours(PriceList masterPriceList, Collection<PriceList> priceLists) {
        log.debug("findPriceListEncours codePriceList :{} ", masterPriceList.getCode());
//        if (masterPriceList.getPriceListList().isEmpty() == false) {
//            priceLists.addAll(masterPriceList.getPriceListList());
//            for (PriceList priceFils : masterPriceList.getPriceListList()) {
//                priceLists.addAll(findPriceListEncours(priceFils, priceLists));
//            }
//        }
        priceLists.add(masterPriceList);
        return priceLists.stream().distinct().collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public PriceList findByPriceListCash() {
        log.debug("Request to get PriceList mere : {}");
//        List<PriceList> listPriceList = priceListRepository.findByActifAndPriceListPereCodeIsNull(true);
        PriceList listPriceList = priceListRepo.findByCash(Boolean.TRUE);
        Preconditions.checkArgument(listPriceList != null, "error.PriceListInexistant");
        return listPriceList;
    }
}
