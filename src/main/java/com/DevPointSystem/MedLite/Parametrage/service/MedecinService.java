/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.Medecin;
import com.DevPointSystem.MedLite.Parametrage.domaine.PrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.dto.MedecinDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.MedecinFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.MedecinRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PrestationConsultationRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
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
    private final PrestationConsultationRepo prestationConsultationRepo;

    private final static String medecinError = "error.MedecinNotFound";

    public MedecinService(MedecinRepo medecinRepo, CompteurService compteurService, PrestationConsultationRepo prestationConsultationRepo) {
        this.medecinRepo = medecinRepo;
        this.compteurService = compteurService;
        this.prestationConsultationRepo = prestationConsultationRepo;
    }

    

    @Transactional(readOnly = true)
    public List<MedecinDTO> findAllMedecin() {
        return MedecinFactory.listMedecinToMedecinDTOs(medecinRepo.findAll());

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
        return MedecinFactory.medecinToMedecinDTO(domaine);
    }

    public Medecin update(MedecinDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), medecinError);
        Medecin domaine = medecinRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, medecinError);
        dto.setCode(domaine.getCode());
        MedecinFactory.medecinDTOToMedecin(dto, domaine);
        return medecinRepo.save(domaine);
    }

    public void deleteMedecin(Integer code) {
        Preconditions.checkArgument(medecinRepo.existsById(code), medecinError);
        
        List<PrestationConsultation> presCons = prestationConsultationRepo.findByCodeMedecin(code);
        Preconditions.checkArgument(presCons.isEmpty(), "error.MedecinUsedInPrestationCOnsultation");
        medecinRepo.deleteById(code);
    }
}
