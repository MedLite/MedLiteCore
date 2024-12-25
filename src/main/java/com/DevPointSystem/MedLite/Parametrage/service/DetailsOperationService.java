/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.repository.DetailsOperationRepo;

/**
 *
 * @author Administrator
 */
public class DetailsOperationService {
         private final DetailsOperationRepo detailsOperationRepo;

    public DetailsOperationService(DetailsOperationRepo detailsOperationRepo) {
        this.detailsOperationRepo = detailsOperationRepo;
    }
    
    
    
      public Boolean deleteByCodeOperation(Integer codeOperation) {
        detailsOperationRepo.deleteByCodeOperation(codeOperation);
        return true;
    }
}
