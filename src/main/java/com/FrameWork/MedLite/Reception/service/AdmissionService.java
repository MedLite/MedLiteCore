/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouvertureOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.ListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.param;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.ListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.paramDTO;
import com.FrameWork.MedLite.Parametrage.factory.ListCouvertureFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.OperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.Parametrage.service.DetailsListCouvertureService;
import com.FrameWork.MedLite.Parametrage.service.DetailsPriceListService;
import com.FrameWork.MedLite.Parametrage.service.ListCouvertureService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PrestationService;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.DetailsAdmissionDTO;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import com.FrameWork.MedLite.Reception.factory.DetailsAdmissionFactory;
import com.FrameWork.MedLite.Reception.repository.AdmissionRepo;
import com.FrameWork.MedLite.Reception.repository.DetailsAdmissionRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class AdmissionService {

    private final AdmissionRepo admissionRepo;
    private final DetailsAdmissionRepo detailsAdmissionRepo;
    private final CompteurService compteurService;
    private final ParamService paramService;
    private final DetailsPriceListService detailsPriceListService;
    private final DetailsListCouvertureService detailsListCouvertureService;
    private final PrestationService prestationService;

    public AdmissionService(AdmissionRepo admissionRepo, DetailsAdmissionRepo detailsAdmissionRepo, CompteurService compteurService, ParamService paramService, DetailsPriceListService detailsPriceListService, DetailsListCouvertureService detailsListCouvertureService, PrestationService prestationService) {
        this.admissionRepo = admissionRepo;
        this.detailsAdmissionRepo = detailsAdmissionRepo;
        this.compteurService = compteurService;
        this.paramService = paramService;
        this.detailsPriceListService = detailsPriceListService;
        this.detailsListCouvertureService = detailsListCouvertureService;
        this.prestationService = prestationService;
    }

    @Transactional(readOnly = true)
    public List<AdmissionDTO> findAllAdmission() {
        return AdmissionFactory.listAdmissionToAdmissionDTOs(admissionRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<AdmissionDTO> findAllAdmissionByEtatPaiement(Boolean etatPaiement) {
        return AdmissionFactory.listAdmissionToAdmissionDTOs(admissionRepo.findByEtatPaiement(etatPaiement));

    }

    @Transactional(readOnly = true)
    public List<AdmissionDTO> findAllAdmissionByCodeSociete(Integer codeSociete) {
        return AdmissionFactory.listAdmissionToAdmissionDTOs(admissionRepo.findByCodeSociete(codeSociete));

    }

    @Transactional(readOnly = true)
    public AdmissionDTO findOne(Integer code) {
        Admission domaine = admissionRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AdmissionNotFound");
        return AdmissionFactory.admissionToAdmissionDTO(domaine);
    }

    public AdmissionDTO save(AdmissionDTO dto) {

        Admission domaine = AdmissionFactory.admissionDTOToAdmission(dto, new Admission());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieADMOPD");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = admissionRepo.save(domaine);

        if (dto.getDetailsAdmissionDTOs() != null) {

            paramDTO codePriceListCash = paramService.findParamByCodeParamS("PriceListCash");

            /// Cash Admission
            if (dto.getCodePriceList().toString().equals(codePriceListCash.getValeur())) {
                System.out.println("   codePriceListCash.getValeur()    " + codePriceListCash.getValeur());
                List<DetailsAdmissionDTO> detailsAdmissionDTOs = dto.getDetailsAdmissionDTOs();
                for (DetailsAdmissionDTO detailsDto : detailsAdmissionDTOs) {
                    DetailsAdmission detailsDomaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(detailsDto, new DetailsAdmission()); // Assuming you have this factory method
                    detailsDomaine.setAdmission(domaine); // Associate with the saved Admission
                    detailsDomaine.setDateCreate(new Date());
                    detailsDomaine.setUsercreate(Helper.getUserAuthenticated());

//                    DetailsPriceListDTO detailsPriceListCash = detailsPriceListService.findOneWithCodePriceListAndCodePrestationAndCodeNatureAdmission(Integer.parseInt(codePriceListCash.getValeur()), detailsDto.getCodePrestation(), dto.getCodeNatureAdmission());
                    PrestationDTO prestationDTO = prestationService.findOne(detailsDto.getCodePrestation());
                    Preconditions.checkArgument(prestationDTO != null, "error.DetailsPrestation");

                    detailsDomaine.setMontant(prestationDTO.getPrixPrestation());
                    detailsDomaine.setMontantPatient(prestationDTO.getPrixPrestation());
                    detailsDomaine.setMontantPEC(BigDecimal.ZERO);

                    detailsAdmissionRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo
                }

            } // PEC Admission
            else {

                List<DetailsAdmissionDTO> detailsAdmissionDTOs = dto.getDetailsAdmissionDTOs();
                for (DetailsAdmissionDTO detailsDto : detailsAdmissionDTOs) {
                    DetailsAdmission detailsDomaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(detailsDto, new DetailsAdmission()); // Assuming you have this factory method
                    detailsDomaine.setAdmission(domaine); // Associate with the saved Admission
                    detailsDomaine.setDateCreate(new Date());
                    detailsDomaine.setUsercreate(Helper.getUserAuthenticated());

                    DetailsListCouvertureDTO detailsListCouvertureDTO = detailsListCouvertureService.findOneWithCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(domaine.getCodeListCouverture(), detailsDto.getCodePrestation(), dto.getCodeNatureAdmission());

                    com.FrameWork.MedLite.web.Util.Preconditions.checkFound(detailsListCouvertureDTO != null, "error.DetailsListCouvertureError");

                    detailsDomaine.setMontant(detailsListCouvertureDTO.getMontantPatient().add(detailsListCouvertureDTO.getMontantPEC()));
                    detailsDomaine.setMontantPatient(detailsListCouvertureDTO.getMontantPatient());
                    detailsDomaine.setMontantPEC(detailsListCouvertureDTO.getMontantPEC());

                    detailsAdmissionRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo
                }

            }

//            List<DetailsAdmissionDTO> detailsAdmissionDTOs = dto.getDetailsAdmissionDTOs();
//            for (DetailsAdmissionDTO detailsDto : detailsAdmissionDTOs) {
//                DetailsAdmission detailsDomaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(detailsDto, new DetailsAdmission()); // Assuming you have this factory method
//                detailsDomaine.setAdmission(domaine); // Associate with the saved Admission
//                detailsDomaine.setDateCreate(new Date());
//                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
//                detailsAdmissionRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo
//            }
        }
        return AdmissionFactory.admissionToAdmissionDTO(domaine);
    }

    public void deleteAdmission(Integer code) {
        Preconditions.checkArgument(admissionRepo.existsById(code), "error.AdmissionNotFound");
        admissionRepo.deleteById(code);
    }

    public AdmissionDTO update(AdmissionDTO dto) {
        Admission domaine = admissionRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = AdmissionFactory.admissionDTOToAdmission(dto, domaine);
        domaine = admissionRepo.save(domaine);
        AdmissionDTO resultDTO = AdmissionFactory.admissionToAdmissionDTO(domaine);
        return resultDTO;
    }

}
