/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.paramDTO;
import com.FrameWork.MedLite.Parametrage.factory.CabinetFactory;
import com.FrameWork.MedLite.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.MedLite.Parametrage.factory.EtatPatientFactory;
import com.FrameWork.MedLite.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.repository.CabinetRepo;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.Parametrage.service.DetailsListCouvertureService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PrestationService;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.AdmissionFacturation;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.domaine.Reglement;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.AdmissionFacturationDTO;
import com.FrameWork.MedLite.Reception.dto.DetailsAdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.ReglementDTO;
import com.FrameWork.MedLite.Reception.enumeration.ReceptionConstants;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import com.FrameWork.MedLite.Reception.factory.AdmissionFacturationFactory;
import com.FrameWork.MedLite.Reception.factory.DetailsAdmissionFactory;
import com.FrameWork.MedLite.Reception.factory.ReglementFactory;
import com.FrameWork.MedLite.Reception.repository.AdmissionFacturationRepo;
import com.FrameWork.MedLite.Reception.repository.AdmissionRepo;
import com.FrameWork.MedLite.Reception.repository.DetailsAdmissionRepo;
import com.FrameWork.MedLite.Reception.repository.ReglementRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final ReglementRepo reglementRepo;
    private final AdmissionFacturationRepo admissionFacturationRepo;
    private final CabinetRepo cabinetRepo;

    private final CompteurService compteurService;
    private final ParamService paramService;
    private final DetailsListCouvertureService detailsListCouvertureService;
    private final PrestationService prestationService;

    public AdmissionService(AdmissionRepo admissionRepo, DetailsAdmissionRepo detailsAdmissionRepo, ReglementRepo reglementRepo, AdmissionFacturationRepo admissionFacturationRepo, CabinetRepo cabinetRepo, CompteurService compteurService, ParamService paramService, DetailsListCouvertureService detailsListCouvertureService, PrestationService prestationService) {
        this.admissionRepo = admissionRepo;
        this.detailsAdmissionRepo = detailsAdmissionRepo;
        this.reglementRepo = reglementRepo;
        this.admissionFacturationRepo = admissionFacturationRepo;
        this.cabinetRepo = cabinetRepo;
        this.compteurService = compteurService;
        this.paramService = paramService;
        this.detailsListCouvertureService = detailsListCouvertureService;
        this.prestationService = prestationService;
    }

 

    @Transactional(readOnly = true)
    public List<AdmissionDTO> findAllAdmission() {
        List<Admission> domaine = admissionRepo.findAll();

        Map<Integer, Cabinet> cabinetMap = new HashMap<>();
 
        List<Cabinet> allCabinets = cabinetRepo.findAll();  // Assuming cabinetRepo.findAll() is efficient.
        for (Cabinet cabinet : allCabinets) {
            cabinetMap.put(cabinet.getCode(), cabinet); // Populate the map
        }

        List<AdmissionDTO> admissionDTOs = new ArrayList<>();
        for (Admission admission : domaine) {
            AdmissionDTO admissionDTO = AdmissionFactory.admissionToAdmissionDTO(admission); //Your existing method

            if (admission.getCodeCabinet() != null) { //Check for null before accessing the property
                Cabinet cabinet = cabinetMap.get(admission.getCodeCabinet());
                if (cabinet != null) {
                    admissionDTO.setCabinetDTO(CabinetFactory.cabinetToCabinetDTO(cabinet)); //Assuming you have a CabinetFactory
                }
            }
            admissionDTOs.add(admissionDTO);
        }
        return admissionDTOs;

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
    public List<AdmissionDTO> findAllAdmissionByCodeNatureAdmission(Integer codeNatureAdmission) {
        
         List<Admission> domaine = admissionRepo.findByCodeNatureAdmission(codeNatureAdmission);

        Map<Integer, Cabinet> cabinetMap = new HashMap<>();
 
        List<Cabinet> allCabinets = cabinetRepo.findAll();  // Assuming cabinetRepo.findAll() is efficient.
        for (Cabinet cabinet : allCabinets) {
            cabinetMap.put(cabinet.getCode(), cabinet); // Populate the map
        }

        List<AdmissionDTO> admissionDTOs = new ArrayList<>();
        for (Admission admission : domaine) {
            AdmissionDTO admissionDTO = AdmissionFactory.admissionToAdmissionDTO(admission); //Your existing method

            if (admission.getCodeCabinet() != null) { //Check for null before accessing the property
                Cabinet cabinet = cabinetMap.get(admission.getCodeCabinet());
                if (cabinet != null) {
                    admissionDTO.setCabinetDTO(CabinetFactory.cabinetToCabinetDTO(cabinet)); //Assuming you have a CabinetFactory
                }
            }
            admissionDTOs.add(admissionDTO);
        }
        return admissionDTOs;
        
        
        
//        return AdmissionFactory.listAdmissionToAdmissionDTOs(admissionRepo.findByCodeNatureAdmission(codeNatureAdmission));

    }

    
    @Transactional(readOnly = true)
    public AdmissionDTO findOne(Integer code) {
        Admission domaine = admissionRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AdmissionNotFound");
        return AdmissionFactory.admissionToAdmissionDTO(domaine);
    }

    public AdmissionDTO save(AdmissionDTO dto) {

        System.out.println("com.FrameWork.MedLite.Reception.service.AdmissionService.save()" + dto.getCodeSaisie());

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
                List<DetailsAdmissionDTO> detailsAdmissionDTOs = dto.getDetailsAdmissionDTOs();
                for (DetailsAdmissionDTO detailsDto : detailsAdmissionDTOs) {
                    DetailsAdmission detailsDomaine = DetailsAdmissionFactory.detailsAdmissionDTOToDetailsAdmission(detailsDto, new DetailsAdmission()); // Assuming you have this factory method
                    detailsDomaine.setAdmission(domaine); // Associate with the saved Admission
                    detailsDomaine.setDateCreate(new Date());
                    detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
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
        }

        if (domaine.isRegDeferral() == false) {
            if (dto.getReglementDTOs() != null) {

                paramDTO codePriceListCash = paramService.findParamByCodeParamS("PriceListCash");

                /// Cash Admission
                if (dto.getCodePriceList().toString().equals(codePriceListCash.getValeur())) {
                    List<ReglementDTO> reglementDTOs = dto.getReglementDTOs();
                    for (ReglementDTO detailsDto : reglementDTOs) {
                        Reglement detailsDomaine = ReglementFactory.reglementDTOToReglement(detailsDto, new Reglement()); // Assuming you have this factory method
                        detailsDomaine.setCodeAdmission(domaine.getCodeSaisie()); // Associate with the saved Admission
                        detailsDomaine.setDateCreate(new Date());
                        detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                        PrestationDTO prestationDTO = prestationService.findOne(detailsDto.getCodePrestation());
                        Preconditions.checkArgument(prestationDTO != null, "error.DetailsPrestation");
                        detailsDomaine.setMontantBon(prestationDTO.getPrixPrestation());
                        detailsDomaine.setMontantReglement(detailsDto.getMontantReglement());
                        detailsDomaine.setMontantPEC(BigDecimal.ZERO);

                        Compteur CompteurCodeSaisieReglemnt = compteurService.findOne("CompteurReglement");
                        String codeSaisieRG = CompteurCodeSaisieReglemnt.getPrefixe() + CompteurCodeSaisieReglemnt.getSuffixe();
                        detailsDomaine.setCodeSaisie(codeSaisieRG);
                        compteurService.incrementeSuffixe(CompteurCodeSaisieReglemnt);

                        detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                        if (detailsDomaine.getCodeNatureAdmission() != null) {
                            detailsDomaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));
                        }

                        if (detailsDto.getCodeModeReglement() == null) {
                            throw new IllegalArgumentException("error.ModeReglementRequired");
                        }
                        detailsDomaine.setCodeModeReglement(detailsDto.getCodeModeReglement());
                        if (detailsDomaine.getCodeModeReglement() != null) {
                            detailsDomaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(detailsDto.getCodeModeReglement()));
                        }

                        detailsDomaine.setDateReglement(detailsDto.getDateReglement());
                        detailsDomaine.setTypeReglement(detailsDto.getTypeReglement());

                        reglementRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo
                    }
                } // PEC Admission
                else {
                    List<ReglementDTO> reglementDTOs = dto.getReglementDTOs();
                    for (ReglementDTO detailsDto : reglementDTOs) {
                        Reglement detailsDomaine = ReglementFactory.reglementDTOToReglement(detailsDto, new Reglement()); // Assuming you have this factory method
                        detailsDomaine.setCodeAdmission(domaine.getCodeSaisie());// Associate with the saved Admission
                        detailsDomaine.setDateCreate(new Date());
                        detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                        DetailsListCouvertureDTO detailsListCouvertureDTO = detailsListCouvertureService.findOneWithCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(domaine.getCodeListCouverture(), detailsDto.getCodePrestation(), dto.getCodeNatureAdmission());
                        com.FrameWork.MedLite.web.Util.Preconditions.checkFound(detailsListCouvertureDTO != null, "error.DetailsListCouvertureError");
                        detailsDomaine.setMontantBon(detailsListCouvertureDTO.getMontantPatient().add(detailsListCouvertureDTO.getMontantPEC()));
                        detailsDomaine.setMontantReglement(detailsDto.getMontantReglement());
                        detailsDomaine.setMontantPEC(detailsListCouvertureDTO.getMontantPEC());
                        detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                        if (detailsDomaine.getCodeNatureAdmission() != null) {
                            detailsDomaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));
                        }

                        detailsDomaine.setCodeModeReglement(detailsDto.getCodeModeReglement());
                        if (detailsDomaine.getCodeModeReglement() != null) {
                            detailsDomaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(detailsDto.getCodeModeReglement()));
                        }

                        Compteur CompteurCodeSaisieReglemnt = compteurService.findOne("CompteurReglement");
                        String codeSaisieRG = CompteurCodeSaisieReglemnt.getPrefixe() + CompteurCodeSaisieReglemnt.getSuffixe();
                        detailsDomaine.setCodeSaisie(codeSaisieRG);
                        compteurService.incrementeSuffixe(CompteurCodeSaisieReglemnt);
                        detailsDomaine.setDateReglement(detailsDto.getDateReglement());
                        detailsDomaine.setTypeReglement(detailsDto.getTypeReglement());
                        reglementRepo.save(detailsDomaine); // Assuming you have a detailsAdmissionRepo
                    }
                }
            }
        } else {

        }
        ///// fin reglement

        //// AdmissionFacturation
        if (dto.getAdmissionFacturationDTOs() != null) {

            List<AdmissionFacturationDTO> reglementDTOs = dto.getAdmissionFacturationDTOs();
            for (AdmissionFacturationDTO detailsDto : reglementDTOs) {
                AdmissionFacturation admissionFacturation = AdmissionFacturationFactory.admissionFacturationDTOToAdmissionFacturation(detailsDto, new AdmissionFacturation()); // Assuming you have this factory method
//                admissionFacturation.setCodeAdmission(domaine.getCode()); // Associate with the saved Admission
                admissionFacturation.setDateCreate(new Date());
                admissionFacturation.setUserCreate(Helper.getUserAuthenticated());

                admissionFacturation.setCodeAdmission(domaine.getCode());
                if (domaine.getCode() != null) {
                    admissionFacturation.setAdmission(AdmissionFactory.createAdmissionByCode(domaine.getCode()));
                }

                admissionFacturation.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                if (detailsDto.getCodeNatureAdmission() != null) {
                    admissionFacturation.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));
                }

                Integer CodeEtatPaiementPaied = Integer.parseInt((ReceptionConstants.CODE_ETAT_PATIEMENT_PAIED).toString());
                admissionFacturation.setCodeEtatPaiement(CodeEtatPaiementPaied);
                if (CodeEtatPaiementPaied != null) {
                    admissionFacturation.setEtatPaiement(EtatPaiementFactory.createEtatPaiementByCode(CodeEtatPaiementPaied));
                }

                admissionFacturation.setCodeEtatPatient(detailsDto.getCodeEtatPatient());
                if (detailsDto.getCodeEtatPatient() != null) {
                    admissionFacturation.setEtatPatient(EtatPatientFactory.createEtatPatientByCode(detailsDto.getCodeEtatPatient()));
                }

                admissionFacturation.setCodeConvention(detailsDto.getCodeConvention());
                admissionFacturation.setCodeSociete(detailsDto.getCodeSociete());

                admissionFacturationRepo.save(admissionFacturation); // Assuming you have a detailsAdmissionRepo
            }

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
