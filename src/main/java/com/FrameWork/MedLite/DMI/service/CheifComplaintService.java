/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.service;

import com.FrameWork.MedLite.DMI.domaine.CheifComplaint;
import com.FrameWork.MedLite.DMI.dto.CheifComplaintDTO;
import com.FrameWork.MedLite.DMI.factory.CheifComplaintFactory;
import com.FrameWork.MedLite.DMI.repository.CheifComplaintRepo;
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
public class CheifComplaintService {

    private final CheifComplaintRepo cheifComplaintRepo;

    public CheifComplaintService(CheifComplaintRepo cheifComplaintRepo) {
        this.cheifComplaintRepo = cheifComplaintRepo;
    }

    @Transactional(readOnly = true)
    public List<CheifComplaintDTO> findAllCheifComplaintByCodeAdmission(Integer CodeAdmission) {
        return CheifComplaintFactory.listCheifComplaintToCheifComplaintDTOs(cheifComplaintRepo.findByCodeAdmission(CodeAdmission));
    }
    
      public CheifComplaintDTO save(CheifComplaintDTO dto) {
          CheifComplaint domaine = CheifComplaintFactory.cheifComplaintDTOToCheifComplaint(dto, new CheifComplaint());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated()); 
        domaine = cheifComplaintRepo.save(domaine);
        return CheifComplaintFactory.cheifComplaintToCheifComplaintDTO(domaine);
    }
      
      
        public CheifComplaint update(CheifComplaintDTO dto) {
        CheifComplaint domaine = cheifComplaintRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.CheifComplaintNotFound");
        dto.setCode(domaine.getCode());
        CheifComplaintFactory.cheifComplaintDTOToCheifComplaint(dto, domaine);
        return cheifComplaintRepo.save(domaine);
    }

    public void deleteCheifComplaint(Integer code) {
        Preconditions.checkArgument(cheifComplaintRepo.existsById(code), "error.CheifComplaintNotFound");
        cheifComplaintRepo.deleteById(code);
    }
    
     public void deleteCheifComplaintByCodeAdmission(Integer codeAdmission) {
//        Preconditions.checkArgument(cheifComplaintRepo.findByCodeAdmission(codeAdmission), "error.CheifComplaintNotFound");
        cheifComplaintRepo.deleteByCodeAdmission(codeAdmission);
    }

}
