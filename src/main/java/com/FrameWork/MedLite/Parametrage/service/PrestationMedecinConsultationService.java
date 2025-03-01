/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.PrestationMedecinConsultation;
import com.FrameWork.MedLite.Parametrage.dto.PrestationMedecinConsultationDTO;
import com.FrameWork.MedLite.Parametrage.factory.PrestationMedecinConsultationFactory;
import com.FrameWork.MedLite.Parametrage.repository.PrestationMedecinConsultationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
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
@Service
@Transactional
public class PrestationMedecinConsultationService {
    
    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    private final PrestationMedecinConsultationRepo prestationMedecinConsultationRepo;
 

    private final static String prestationMedecinConsultationError = "error.PrestationMedecinConsultationNotFound";

    public PrestationMedecinConsultationService(PrestationMedecinConsultationRepo prestationMedecinConsultationRepo) {
        this.prestationMedecinConsultationRepo = prestationMedecinConsultationRepo;
    }


   
    

    @Transactional(readOnly = true)
    public List<PrestationMedecinConsultationDTO> findAllPrestationMedecinConsultation() {
        return PrestationMedecinConsultationFactory.listPrestationMedecinConsultationToPrestationMedecinConsultationDTOs(prestationMedecinConsultationRepo.findAll(Sort.by("code").descending()));

    }
    
   
  
    
        @Transactional(readOnly = true)
    public  PrestationMedecinConsultationDTO findByCodeMedecinAndcodeNatureAdmimssion(Integer code,List<Integer> codeNatureAdmission) {
        PrestationMedecinConsultation domaine = prestationMedecinConsultationRepo.findByCodeMedecinAndCodeNatureAdmissionIn(code,codeNatureAdmission);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PrestationNotFound");
        return PrestationMedecinConsultationFactory.medecinToPrestationMedecinConsultationDTO(domaine);
    }
  
         @Transactional(readOnly = true)
    public  PrestationMedecinConsultationDTO findByCodeMedecin(Integer code) {
        PrestationMedecinConsultation domaine = prestationMedecinConsultationRepo.findByCodeMedecin(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PrestationNotFound");
        return PrestationMedecinConsultationFactory.medecinToPrestationMedecinConsultationDTO(domaine);
    }
  

    

    public PrestationMedecinConsultationDTO save(PrestationMedecinConsultationDTO dto) {
        PrestationMedecinConsultation domaine = PrestationMedecinConsultationFactory.medecinDTOToPrestationMedecinConsultation(dto, new PrestationMedecinConsultation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
 
        domaine = prestationMedecinConsultationRepo.save(domaine);
        return PrestationMedecinConsultationFactory.medecinToPrestationMedecinConsultationDTO(domaine);
    }

     public void deletePrestationConsultation(Integer codeMedecin) { 
        prestationMedecinConsultationRepo.deleteByCodeMedecin(codeMedecin);  
    }


}
