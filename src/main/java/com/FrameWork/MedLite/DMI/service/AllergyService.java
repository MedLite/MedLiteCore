/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.service;

import com.FrameWork.MedLite.DMI.domaine.Allergy;
import com.FrameWork.MedLite.DMI.dto.AllergyDTO;
import com.FrameWork.MedLite.DMI.factory.AllergyFactory;
import com.FrameWork.MedLite.DMI.repository.AllergyRepo;
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
public class AllergyService {

    private final AllergyRepo allergyRepo;

    public AllergyService(AllergyRepo allergyRepo) {
        this.allergyRepo = allergyRepo;
    }

    @Transactional(readOnly = true)
    public List<AllergyDTO> findAllAllergyByCodeAdmission(Integer CodeAdmission) {
        return AllergyFactory.listAllergyToAllergyDTOs(allergyRepo.findByCodeAdmission(CodeAdmission));
    }
    
      public AllergyDTO save(AllergyDTO dto) {
          Allergy domaine = AllergyFactory.allergyDTOToAllergy(dto, new Allergy());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated()); 
        domaine = allergyRepo.save(domaine);
        return AllergyFactory.allergyToAllergyDTO(domaine);
    }
      
      
        public Allergy update(AllergyDTO dto) {
        Allergy domaine = allergyRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AllergyNotFound");
        dto.setCode(domaine.getCode());
        AllergyFactory.allergyDTOToAllergy(dto, domaine);
        return allergyRepo.save(domaine);
    }

    public void deleteAllergy(Integer code) {
        Preconditions.checkArgument(allergyRepo.existsById(code), "error.AllergyNotFound");
        allergyRepo.deleteById(code);
    }
    
     public void deleteAllergyByCodeAdmission(Integer codeAdmission) {
//        Preconditions.checkArgument(allergyRepo.findByCodeAdmission(codeAdmission), "error.AllergyNotFound");
        allergyRepo.deleteByCodeAdmission(codeAdmission);
    }

}
