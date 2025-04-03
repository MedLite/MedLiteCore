/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.domaine.PrestationMedecinConsultation;
import com.FrameWork.MedLite.Parametrage.dto.MedecinDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationMedecinConsultationDTO;
import com.FrameWork.MedLite.Parametrage.factory.MedecinFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationMedecinConsultationFactory;
import com.FrameWork.MedLite.Parametrage.repository.MedecinRepo;
import com.FrameWork.MedLite.Parametrage.repository.PrestationMedecinConsultationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class MedecinService {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    private final MedecinRepo medecinRepo;
    private final CompteurService compteurService;
    private final PrestationMedecinConsultationRepo prestationMedecinConsultationRepo;
    private final PrestationMedecinConsultationService prestationMedecinConsultationService;

    private final static String medecinError = "error.MedecinNotFound";

    public MedecinService(MedecinRepo medecinRepo, CompteurService compteurService, PrestationMedecinConsultationRepo prestationMedecinConsultationRepo, PrestationMedecinConsultationService prestationMedecinConsultationService) {
        this.medecinRepo = medecinRepo;
        this.compteurService = compteurService;
        this.prestationMedecinConsultationRepo = prestationMedecinConsultationRepo;
        this.prestationMedecinConsultationService = prestationMedecinConsultationService;
    }

    

    @Transactional(readOnly = true)
    public List<MedecinDTO> findAllMedecin() {
        return MedecinFactory.listMedecinToMedecinDTOs(medecinRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<MedecinDTO> findAllMedecinByActif(Boolean actif) {
        return MedecinFactory.listMedecinToMedecinDTOs(medecinRepo.findByActif(actif));

    }
    
     @Transactional(readOnly = true)
    public List<MedecinDTO> findAllMedecinBySignature(Boolean haveSig) {
        return MedecinFactory.listMedecinToMedecinDTOs(medecinRepo.findByHaveSig(haveSig));

    }

    @Transactional(readOnly = true)
    public MedecinDTO findOne(Integer code) {
        Medecin domaine = medecinRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, medecinError);
        return MedecinFactory.medecinToMedecinDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<MedecinDTO> findByCodeTypeIntervenantMedecin(Integer codeTypeIntervenant) {
        List<Medecin> result = medecinRepo.findByCodeTypeIntervenant(codeTypeIntervenant);
        return MedecinFactory.listMedecinToMedecinDTOs(result);
    }

    @Transactional(readOnly = true)
    public List<MedecinDTO> findByCodeSpecialite(Integer codeSpecialite) {
        List<Medecin> result = medecinRepo.findByCodeSpecialiteMedecin(codeSpecialite);
        return MedecinFactory.listMedecinToMedecinDTOs(result);
    }

    @Transactional(readOnly = true)
    public List<MedecinDTO> findMedecinHasConsultationOld(Boolean autorisConsultation, Boolean actif) {
        List<Medecin> result = medecinRepo.findByAutorisConsultationAndActif(autorisConsultation, actif);

        return MedecinFactory.listMedecinToMedecinDTOs(result);
    }

    @Transactional(readOnly = true)
    public List<MedecinDTO> findMedecinHasConsultation(Boolean autorisConsultation, Boolean actif, Boolean opd, Boolean er) {
        List<Medecin> medecins = medecinRepo.findByAutorisConsultationAndActifAndOpdAndEr(autorisConsultation, actif,opd,er);

        List<MedecinDTO> medecinDTOs = new ArrayList<>();
        for (Medecin medecin : medecins) {
            MedecinDTO medecinDTO = MedecinFactory.medecinToMedecinDTO(medecin);  //Get the basic DTO
            if (medecinDTO != null) { //handle null case.
                PrestationMedecinConsultationDTO prestationDTO = prestationMedecinConsultationService.findByCodeMedecin(medecin.getCode()); // Fetch related data
                if (prestationDTO != null) { //handle null case
                    medecinDTO.setPrestationConsultationDTO(prestationDTO); //Set the prestation data
                }
                medecinDTOs.add(medecinDTO);
            }
        }
        return medecinDTOs;
    }

    @Transactional(readOnly = true)
    public List<MedecinDTO> findByCodeSpecialiteAndTypeIntervenant(Integer codeSpecialite, Integer codeTypeIntervenant) {
        List<Medecin> result = medecinRepo.findByCodeSpecialiteMedecinAndCodeTypeIntervenant(codeSpecialite, codeTypeIntervenant);
        return MedecinFactory.listMedecinToMedecinDTOs(result);
    }

    public MedecinDTO save(MedecinDTO dto) {
        Medecin domaine = MedecinFactory.medecinDTOToMedecin(dto, new Medecin());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieMedecin");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = medecinRepo.save(domaine);

        if (dto.getPrestationMedecinConsultationDTOs() != null) {
            for (PrestationMedecinConsultationDTO detailsDto : dto.getPrestationMedecinConsultationDTOs()) {
                PrestationMedecinConsultation psMdCons = PrestationMedecinConsultationFactory.medecinDTOToPrestationMedecinConsultation(detailsDto, new PrestationMedecinConsultation());
                psMdCons.setCodeMedecin(domaine.getCode());
                if (psMdCons.getCodeMedecin() != null) {
                    psMdCons.setMedecin(MedecinFactory.createMedecinByCode(domaine.getCode()));
                }

                psMdCons.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                if (psMdCons.getCodeNatureAdmission() != null) {
                    psMdCons.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));
                }

                psMdCons.setCodePrestation(detailsDto.getCodePrestation());
                if (psMdCons.getCodePrestation() != null) {
                    psMdCons.setPrestation(PrestationFactory.createPrestationByCode(detailsDto.getCodePrestation()));
                }

                psMdCons.setDateCreate(new Date());
                psMdCons.setUserCreate(Helper.getUserAuthenticated());
                prestationMedecinConsultationRepo.save(psMdCons);
            }
        }

        return MedecinFactory.medecinToMedecinDTO(domaine);
    }

    public Medecin update(MedecinDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), medecinError);
        Medecin domaine = medecinRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, medecinError);
        dto.setCode(domaine.getCode());
        MedecinFactory.medecinDTOToMedecin(dto, domaine);

        prestationMedecinConsultationRepo.deleteByCodeMedecin(domaine.getCode());

        if (dto.getPrestationMedecinConsultationDTOs() != null) {
            for (PrestationMedecinConsultationDTO detailsDto : dto.getPrestationMedecinConsultationDTOs()) {
                PrestationMedecinConsultation psMdCons = PrestationMedecinConsultationFactory.medecinDTOToPrestationMedecinConsultation(detailsDto, new PrestationMedecinConsultation());
                psMdCons.setCodeMedecin(domaine.getCode());
                if (psMdCons.getCodeMedecin() != null) {
                    psMdCons.setMedecin(MedecinFactory.createMedecinByCode(domaine.getCode()));
                }

                psMdCons.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                if (psMdCons.getCodeNatureAdmission() != null) {
                    psMdCons.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));
                }

                psMdCons.setCodePrestation(detailsDto.getCodePrestation());
                if (psMdCons.getCodePrestation() != null) {
                    psMdCons.setPrestation(PrestationFactory.createPrestationByCode(detailsDto.getCodePrestation()));
                }

                psMdCons.setDateCreate(new Date());
                psMdCons.setUserCreate(Helper.getUserAuthenticated());
                prestationMedecinConsultationRepo.save(psMdCons);
            }
        }

        return medecinRepo.save(domaine);
    }

    public void deleteMedecin(Integer code) {
        Preconditions.checkArgument(medecinRepo.existsById(code), medecinError);

        PrestationMedecinConsultation presCons = prestationMedecinConsultationRepo.findByCodeMedecin(code);
        Preconditions.checkArgument(presCons.getCodeMedecin() == null, "error.MedecinUsedInPrestationCOnsultation");
        medecinRepo.deleteById(code);
    }

}
