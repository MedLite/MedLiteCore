/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;
 
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationRepo;


/**
 *
 * @author Administrator
 */
public class DetailsPrestationService {
       private final DetailsPrestationRepo detailsPrestationRepo;

    public DetailsPrestationService(DetailsPrestationRepo detailsPrestationRepo) {
        this.detailsPrestationRepo = detailsPrestationRepo;
    }
    
    
    
      public Boolean deleteByCodePrestation(Integer codePrestation) {
        detailsPrestationRepo.deleteByCodePrestation(codePrestation);
        return true;
    }
}
