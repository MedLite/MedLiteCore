/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.service;

import com.FrameWork.MedLite.DMI.domaine.PastHistory;
import com.FrameWork.MedLite.DMI.dto.PastHistoryDTO;
import com.FrameWork.MedLite.DMI.factory.PastHistoryFactory;
import com.FrameWork.MedLite.DMI.repository.PastHistoryRepo;
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
public class PastHistoryService {

    private final PastHistoryRepo pastHistoryRepo;

    public PastHistoryService(PastHistoryRepo pastHistoryRepo) {
        this.pastHistoryRepo = pastHistoryRepo;
    }

    @Transactional(readOnly = true)
    public List<PastHistoryDTO> findAllPastHistoryByCodeAdmission(Integer CodeAdmission) {
        return PastHistoryFactory.listPastHistoryToPastHistoryDTOs(pastHistoryRepo.findByCodeAdmission(CodeAdmission));
    }
    
      public PastHistoryDTO save(PastHistoryDTO dto) {
          PastHistory domaine = PastHistoryFactory.pastHistoryDTOToPastHistory(dto, new PastHistory());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated()); 
        domaine = pastHistoryRepo.save(domaine);
        return PastHistoryFactory.pastHistoryToPastHistoryDTO(domaine);
    }
      
      
        public PastHistory update(PastHistoryDTO dto) {
        PastHistory domaine = pastHistoryRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.PastHistoryNotFound");
        dto.setCode(domaine.getCode());
        PastHistoryFactory.pastHistoryDTOToPastHistory(dto, domaine);
        return pastHistoryRepo.save(domaine);
    }

    public void deletePastHistory(Integer code) {
        Preconditions.checkArgument(pastHistoryRepo.existsById(code), "error.PastHistoryNotFound");
        pastHistoryRepo.deleteById(code);
    }
    
     public void deletePastHistoryByCodeAdmission(Integer codeAdmission) {
//        Preconditions.checkArgument(pastHistoryRepo.findByCodeAdmission(codeAdmission), "error.PastHistoryNotFound");
        pastHistoryRepo.deleteByCodeAdmission(codeAdmission);
    }

}
