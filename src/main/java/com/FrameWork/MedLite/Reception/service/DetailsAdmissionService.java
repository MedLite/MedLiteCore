/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.paramDTO;
import com.FrameWork.MedLite.Parametrage.service.DetailsListCouvertureService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PrestationService;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.AdmissionFacturation;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.DetailsAdmissionDTO;
import com.FrameWork.MedLite.Reception.enumeration.ReceptionConstants;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import com.FrameWork.MedLite.Reception.factory.DetailsAdmissionFactory;
import com.FrameWork.MedLite.Reception.repository.AdmissionFacturationRepo;
import com.FrameWork.MedLite.Reception.repository.AdmissionRepo;
import com.FrameWork.MedLite.Reception.repository.DetailsAdmissionRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsAdmissionService {

    private final AdmissionRepo admissionRepo;
    private final DetailsAdmissionRepo detailsAdmissionRepo;
    private final AdmissionFacturationRepo admissionFacturationRepo;

    private final PrestationService prestationService;

    private final DetailsListCouvertureService detailsListCouvertureService;
    private final ParamService paramService;

    public DetailsAdmissionService(AdmissionRepo admissionRepo, DetailsAdmissionRepo detailsAdmissionRepo, AdmissionFacturationRepo admissionFacturationRepo, PrestationService prestationService, DetailsListCouvertureService detailsListCouvertureService, ParamService paramService) {
        this.admissionRepo = admissionRepo;
        this.detailsAdmissionRepo = detailsAdmissionRepo;
        this.admissionFacturationRepo = admissionFacturationRepo;
        this.prestationService = prestationService;
        this.detailsListCouvertureService = detailsListCouvertureService;
        this.paramService = paramService;
    }

    @Transactional(readOnly = true)
    public List<DetailsAdmissionDTO> findAllDetailsAdmission() {
        return DetailsAdmissionFactory.listDetailsAdmissionToDetailsADmissionDTOs(detailsAdmissionRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsAdmissionDTO findOne(Integer code) {
        DetailsAdmission domaine = detailsAdmissionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsAdmissionNotFound");
        return DetailsAdmissionFactory.DetailsAdmissionToDetailsAdmissionDTONew(domaine);
    }

//
    public DetailsAdmissionDTO save(DetailsAdmissionDTO dto) {
        DetailsAdmission domaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(dto, new DetailsAdmission());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsAdmissionRepo.save(domaine);
        return DetailsAdmissionFactory.DetailsAdmissionToDetailsAdmissionDTONew(domaine);
    }

    public List<DetailsAdmissionDTO> saveList(List<DetailsAdmissionDTO> dto) {
        List<DetailsAdmissionDTO> detailsAdmissionDTOs = dto;
        paramDTO codePriceListCash = paramService.findParamByCodeParamS("PriceListCash");

        Admission admission = admissionRepo.findByCode(dto.iterator().next().getCode());

        AdmissionFacturation admFact = admissionFacturationRepo.findByCodeAdmission(admission.getCode());

        if (admFact.getCodeEtatPatient() == Integer.parseInt((ReceptionConstants.CODE_ETAT_PATIENT_LEAVE).toString())) {
            throw new IllegalArgumentException("error.PatientLeave");
        } else {

            if (admission.getCodePriceList().toString().equals(codePriceListCash.getValeur())) {
                for (DetailsAdmissionDTO detailsDto : detailsAdmissionDTOs) {
                    DetailsAdmission detailsDomaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(detailsDto, new DetailsAdmission()); // Assuming you have this factory method

                    detailsDomaine.setCodeAdmission(detailsDto.getCodeAdmission());
                    if (detailsDto.getCodeAdmission() != null) {
                        detailsDomaine.setAdmission(AdmissionFactory.createAdmissionByCode(detailsDto.getCodeAdmission()));
                    }
                    detailsDomaine.setDateCreate(new Date());
                    detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                    PrestationDTO prestationDTO = prestationService.findOne(detailsDto.getCodePrestation());
                    Preconditions.checkArgument(prestationDTO != null, "error.DetailsPrestation");
                    detailsDomaine.setMontant(prestationDTO.getPrixPrestation());
                    detailsDomaine.setMontantPatient(prestationDTO.getPrixPrestation());
                    detailsDomaine.setMontantPEC(BigDecimal.ZERO);
                    detailsAdmissionRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo

                }
            } else {
//                List<DetailsAdmissionDTO> detailsAdmissionDTOsPEC = dto;
                for (DetailsAdmissionDTO detailsDtoPEC : detailsAdmissionDTOs) {
                    DetailsAdmission detailsDomaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(detailsDtoPEC, new DetailsAdmission()); // Assuming you have this factory method
                    detailsDomaine.setCodeAdmission(detailsDtoPEC.getCodeAdmission());
                    if (detailsDtoPEC.getCodeAdmission() != null) {
                        detailsDomaine.setAdmission(AdmissionFactory.createAdmissionByCode(detailsDtoPEC.getCodeAdmission()));
                    }
                    detailsDomaine.setDateCreate(new Date());
                    detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                    DetailsListCouvertureDTO detailsListCouvertureDTO = detailsListCouvertureService.findOneWithCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(admission.getCodeListCouverture(), detailsDtoPEC.getCodePrestation(), admission.getCodeNatureAdmission());
                    com.FrameWork.MedLite.web.Util.Preconditions.checkFound(detailsListCouvertureDTO != null, "error.DetailsListCouvertureError");
                    detailsDomaine.setMontant(detailsListCouvertureDTO.getMontantPatient().add(detailsListCouvertureDTO.getMontantPEC()));
                    detailsDomaine.setMontantPatient(detailsListCouvertureDTO.getMontantPatient());
                    detailsDomaine.setMontantPEC(detailsListCouvertureDTO.getMontantPEC());
                    detailsAdmissionRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo
                }
            }
 

        }

        return detailsAdmissionDTOs;
    }

//    public DetailsAdmission update(DetailsAdmissionDTO dto) {
//        Preconditions.checkArgument((dto.getCode() != null), "error.DetailsAdmissionNotFound");
//        DetailsAdmission domaine = detailsAdmissionRepo.findByCode(dto.getCode());
//        Preconditions.checkArgument(domaine != null, "error.DetailsAdmissionNotFound");
//        dto.setCode(domaine.getCode());
//        DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(dto, domaine);
//        return detailsAdmissionRepo.save(domaine);
//    }
    
       public DetailsAdmissionDTO update(DetailsAdmissionDTO dto) {
        DetailsAdmission domaine = detailsAdmissionRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(dto, domaine);
        domaine = detailsAdmissionRepo.save(domaine);
        DetailsAdmissionDTO resultDTO = DetailsAdmissionFactory.DetailsAdmissionToDetailsAdmissionDTONew(domaine);
        return resultDTO;
    }

    public void deleteDetailsAdmission(Integer code) {
        Preconditions.checkArgument(detailsAdmissionRepo.existsById(code), "error.DetailsAdmissionNotFound");
        detailsAdmissionRepo.deleteById(code);
    }

}
