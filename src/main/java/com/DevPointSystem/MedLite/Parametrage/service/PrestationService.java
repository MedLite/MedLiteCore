/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.PriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.param;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.enumeration.EnumTypeUpdatePrice;
import com.DevPointSystem.MedLite.Parametrage.enumeration.ParametrageConstants;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.FamilleFacturationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.PrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.PriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.NatureAdmissionRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PrestationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PriceListRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Service
@Transactional
public class PrestationService {

    private final Logger log = LoggerFactory.getLogger(PrestationService.class);

    private final PrestationRepo prestationRepo;

    private final CompteurService compteurService;
    private final ParamService paramService;

    private final NatureAdmissionService natureAdmissionService;
    private final NatureAdmissionRepo natureAdmissionRepo;

    private final DetailsPrestationRepo detailsPrestationRepo;
    private final PriceListRepo priceListRepo;
    private final DetailsPriceListService detailsPriceListService;
    private final PriceListService priceListService;
    private final DetailsPriceListRepo detailsPriceListRepo;

    public PrestationService(PrestationRepo prestationRepo, CompteurService compteurService, ParamService paramService, NatureAdmissionService natureAdmissionService, NatureAdmissionRepo natureAdmissionRepo, DetailsPrestationRepo detailsPrestationRepo, PriceListRepo priceListRepo, DetailsPriceListService detailsPriceListService, PriceListService priceListService, DetailsPriceListRepo detailsPriceListRepo) {
        this.prestationRepo = prestationRepo;
        this.compteurService = compteurService;
        this.paramService = paramService;
        this.natureAdmissionService = natureAdmissionService;
        this.natureAdmissionRepo = natureAdmissionRepo;
        this.detailsPrestationRepo = detailsPrestationRepo;
        this.priceListRepo = priceListRepo;
        this.detailsPriceListService = detailsPriceListService;
        this.priceListService = priceListService;
        this.detailsPriceListRepo = detailsPriceListRepo;
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

        return PrestationFactory.prestationToPrestationDTOForUpdate(domaine);
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
        PriceList plCash = priceListRepo.findByCash(Boolean.TRUE);
        Preconditions.checkArgument(plCash != null, "error.PriceListCashNotFound");

        if (dto.getDetailsPriceLists() != null) {
            for (DetailsPriceListDTO detailsDto : dto.getDetailsPriceLists()) {
                DetailsPriceList detailsDomaine = DetailsPriceListFactory.DetailspriceListDTOToDetailsPriceList(detailsDto, new DetailsPriceList());
                detailsDomaine.setCodePrestation(domaine.getCode());
                if (domaine.getCode() != null) {
                    detailsDomaine.setPrestation(PrestationFactory.createPrestationByCode(domaine.getCode()));

                }
                detailsDomaine.setCodePriceList(plCash.getCode());
                if (plCash.getCode() != null) {
                    detailsDomaine.setPriceList(PriceListFactory.createPriceListByCode(plCash.getCode()));

                }
                detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                if (detailsDto.getCodeNatureAdmission() != null) {
                    detailsDomaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));

                }
                detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeTypeIntervenant());
                if (detailsDto.getCodeTypeIntervenant() != null) {
                    detailsDomaine.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(detailsDto.getCodeTypeIntervenant()));

                }
                detailsDomaine.setRemMaj("REM");
                detailsDomaine.setMontantPere(detailsDto.getMontantPere());
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setPrestation(domaine); // Set the relationship to the saved Prestation
                detailsPriceListRepo.save(detailsDomaine);
            }
        }

        return PrestationFactory.prestationToPrestationDTO(domaine);
    }

    public PrestationDTO updateNewWithFlush(PrestationDTO dto) {
        Prestation domaine = prestationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.PrestationNotFound");
        detailsPrestationRepo.deleteByCodePrestation(dto.getCode());
        domaine = PrestationFactory.prestationDTOToPrestation(dto, domaine);
 domaine = prestationRepo.save(domaine);
        detailsPriceListRepo.deleteByCodePrestation(domaine.getCode());
        PriceList plCash = priceListRepo.findByCash(Boolean.TRUE);
        Preconditions.checkArgument(plCash != null, "error.PriceListCashNotFound");
        if (dto.getDetailsPriceLists() != null) {
            for (DetailsPriceListDTO detailsDto : dto.getDetailsPriceLists()) {
                DetailsPriceList detailsDomaine = DetailsPriceListFactory.DetailspriceListDTOToDetailsPriceList(detailsDto, new DetailsPriceList());
                detailsDomaine.setCodePrestation(domaine.getCode());
                if (domaine.getCode() != null) {
                    detailsDomaine.setPrestation(PrestationFactory.createPrestationByCode(domaine.getCode()));
                }
                detailsDomaine.setCodePriceList(plCash.getCode());
                if (plCash.getCode() != null) {
                    detailsDomaine.setPriceList(PriceListFactory.createPriceListByCode(plCash.getCode()));
                }
                detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                if (detailsDto.getCodeNatureAdmission() != null) {
                    detailsDomaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));
                }
                detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeTypeIntervenant());
                if (detailsDto.getCodeTypeIntervenant() != null) {
                    detailsDomaine.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(detailsDto.getCodeTypeIntervenant()));
                }
                detailsDomaine.setRemMaj("REM");
                detailsDomaine.setMontantPere(detailsDto.getMontantPere());
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setPrestation(domaine); // Set the relationship to the saved Prestation
                detailsPriceListRepo.save(detailsDomaine);
            }
        }
       
        PrestationDTO resultDTO = PrestationFactory.prestationToPrestationDTO(domaine);
        return resultDTO;
    }

    public void deletePrestation(Integer code) {
        Preconditions.checkArgument(prestationRepo.existsById(code), "error.PrestationNotFound");
        detailsPriceListRepo.deleteByCodePrestation(code);
        prestationRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPrestationDTO> findOneWithDetails(Integer code) {
        Collection<DetailsPrestation> domaine = detailsPrestationRepo.findByDetailsPrestationPK_codePrestation(code);
        return DetailsPrestationFactory.detailsPrestationTodetailsPrestationDTOCollections(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPrestationDTO> findOneWithDetailsCodePrestationAndCodeNatureAdmission(Integer code, Integer codeNatureAdmission) {
        Collection<DetailsPrestation> domaine = detailsPrestationRepo.findByDetailsPrestationPK_codePrestationAndCodeNatureAdmission(code, codeNatureAdmission);
        return DetailsPrestationFactory.detailsPrestationTodetailsPrestationDTOCollections(domaine);
    }
}
