/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.domaine.PrestationMedecinConsultation;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.FrameWork.MedLite.Parametrage.repository.NatureAdmissionRepo;
import com.FrameWork.MedLite.Parametrage.repository.PrestationMedecinConsultationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PrestationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PriceListRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
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
    private final PrestationMedecinConsultationRepo prestationMedecinConsultationRepo;

    public PrestationService(PrestationRepo prestationRepo, CompteurService compteurService, ParamService paramService, NatureAdmissionService natureAdmissionService, NatureAdmissionRepo natureAdmissionRepo, DetailsPrestationRepo detailsPrestationRepo, PriceListRepo priceListRepo, DetailsPriceListService detailsPriceListService, PriceListService priceListService, DetailsPriceListRepo detailsPriceListRepo, PrestationMedecinConsultationRepo prestationMedecinConsultationRepo) {
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
        this.prestationMedecinConsultationRepo = prestationMedecinConsultationRepo;
    }

    @Transactional(readOnly = true)
    public List<PrestationDTO> findAllPrestation() {
        return PrestationFactory.listPrestationToPrestationDTOs(prestationRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<PrestationDTO> findAllPrestationByActif(Boolean actif) {
        return PrestationFactory.listPrestationToPrestationDTOs(prestationRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public List<PrestationDTO> findAllPrestationByCodeIn(List<Integer> code) {
        return PrestationFactory.listPrestationToPrestationDTOs(prestationRepo.findByCodeIn(code));

    }

    @Transactional(readOnly = true)
    public List<PrestationDTO> findByCodeFamilleFacturationConsultation(Integer CodeNatureAdmission) {
        String codeFamilleConsultation = paramService.findParamByCodeParamS("CodeFamilleFacturationConsultation").getValeur();  
        String codePLCash = paramService.findParamByCodeParamS("PriceListCash").getValeur();


        List<Prestation> pres = prestationRepo.findByCodeFamilleFacturation(Integer.parseInt(codeFamilleConsultation));
        List<Integer> prestationCodes = pres.stream().map(Prestation::getCode).collect(Collectors.toList());

        // Fetch DetailsPriceList entries only for those prestations
        List<DetailsPriceList> dpl = detailsPriceListRepo.findByCodePriceListAndCodeNatureAdmissionAndCodePrestationIn(Integer.parseInt(codePLCash),CodeNatureAdmission,prestationCodes);

        // Filter prestations to keep only those with entries in DetailsPriceList
        List<Prestation> filteredPres = pres.stream()
                .filter(p -> dpl.stream().anyMatch(d -> d.getCodePrestation().equals(p.getCode())))
                .collect(Collectors.toList());

        return PrestationFactory.listPrestationToPrestationDTOs(filteredPres);

    }

    @Transactional(readOnly = true)
    public PrestationDTO findOne(Integer code) {
        Prestation domaine = prestationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PrestationNotFound");
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
                detailsDomaine.setCodeTypeIntervenant(detailsDto.getCodeTypeIntervenant());
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
                detailsDomaine.setCodeTypeIntervenant(detailsDto.getCodeTypeIntervenant());
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

        List<PrestationMedecinConsultation> presConsMed = prestationMedecinConsultationRepo.findByCodePrestation(code);
        Preconditions.checkArgument(presConsMed.isEmpty(), "error.PrestationInConsultation");

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
