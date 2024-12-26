/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationConsultationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationRepo;

/**
 *
 * @author Administrator
 */
public class DetailsPrestationConsultationService {
       private final DetailsPrestationConsultationRepo detailsPrestationConsultationRepo;

    public DetailsPrestationConsultationService(DetailsPrestationConsultationRepo detailsPrestationConsultationRepo) {
        this.detailsPrestationConsultationRepo = detailsPrestationConsultationRepo;
    }

   
    
    
      public Boolean deleteByCodePrestationConsultation(Integer codePrestationConsultation) {
        detailsPrestationConsultationRepo.deleteByCodePrestConsult(codePrestationConsultation);
        return true;
    }
}
