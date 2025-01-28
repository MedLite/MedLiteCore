/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Recette.service;

import com.FrameWork.MedLite.Recette.repository.DetailsAlimentationCaisseRepo;

/**
 *
 * @author Administrator
 */
public class DetailsAlimentationCaisseService {
    private final DetailsAlimentationCaisseRepo detailsAlimentationCaisseRepo;

    public DetailsAlimentationCaisseService(DetailsAlimentationCaisseRepo detailsAlimentationCaisseRepo) {
        this.detailsAlimentationCaisseRepo = detailsAlimentationCaisseRepo;
    }
    
    
    
      public Boolean deleteByCodeAlimentationCaisse(Integer codeAlimentationCaisse) {
        detailsAlimentationCaisseRepo.deleteByCodeAlimentationCaisse(codeAlimentationCaisse);
        return true;
    }
}
