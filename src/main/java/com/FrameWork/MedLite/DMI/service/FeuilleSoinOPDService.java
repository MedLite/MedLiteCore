/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.service;

import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.dto.FeuilleSoinOPDDTO;
import com.FrameWork.MedLite.DMI.factory.FeuilleSoinOPDFactory;
import com.FrameWork.MedLite.DMI.repository.FeuilleSoinOPDRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
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
public class FeuilleSoinOPDService {

    private final FeuilleSoinOPDRepo feuilleSoinOPDRepo;

    public FeuilleSoinOPDService(FeuilleSoinOPDRepo feuilleSoinOPDRepo) {
        this.feuilleSoinOPDRepo = feuilleSoinOPDRepo;
    }

    @Transactional(readOnly = true)
    public FeuilleSoinOPDDTO findAllFeuilleSoinOPDByCodeAdmission(Integer CodeAdmission) {
        return FeuilleSoinOPDFactory.feuilleSoinOPDToFeuilleSoinOPDDTO(feuilleSoinOPDRepo.findByCodeAdmission(CodeAdmission));
    }
    
      public FeuilleSoinOPDDTO save(FeuilleSoinOPDDTO dto) {
          FeuilleSoinOPD domaine = FeuilleSoinOPDFactory.feuilleSoinOPDDTOToFeuilleSoinOPD(dto, new FeuilleSoinOPD());
       
        domaine = feuilleSoinOPDRepo.save(domaine);
        return FeuilleSoinOPDFactory.feuilleSoinOPDToFeuilleSoinOPDDTO(domaine);
    }
      
      
        public FeuilleSoinOPD update(FeuilleSoinOPDDTO dto) {
        FeuilleSoinOPD domaine = feuilleSoinOPDRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FeuilleSoinOPDNotFound");
        dto.setCode(domaine.getCode());
        FeuilleSoinOPDFactory.feuilleSoinOPDDTOToFeuilleSoinOPD(dto, domaine);
        return feuilleSoinOPDRepo.save(domaine);
    }

    public void deleteFeuilleSoinOPD(Integer code) {
        Preconditions.checkArgument(feuilleSoinOPDRepo.existsById(code), "error.FeuilleSoinOPDNotFound");
        feuilleSoinOPDRepo.deleteById(code);
    }
    
     public void deleteFeuilleSoinOPDByCodeAdmission(Integer codeAdmission) { 
        feuilleSoinOPDRepo.deleteByCodeAdmission(codeAdmission);
    }

}
