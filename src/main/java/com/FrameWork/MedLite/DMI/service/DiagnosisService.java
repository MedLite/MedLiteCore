/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.service;

import com.FrameWork.MedLite.DMI.domaine.Diagnosis;
import com.FrameWork.MedLite.DMI.dto.DiagnosisDTO;
import com.FrameWork.MedLite.DMI.factory.DiagnosisFactory;
import com.FrameWork.MedLite.DMI.repository.DiagnosisRepo;
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
public class DiagnosisService {

    private final DiagnosisRepo diagnosisRepo;

    public DiagnosisService(DiagnosisRepo diagnosisRepo) {
        this.diagnosisRepo = diagnosisRepo;
    }

    @Transactional(readOnly = true)
    public List<DiagnosisDTO> findAllDiagnosisByCodeAdmission(Integer CodeAdmission) {
        return DiagnosisFactory.listDiagnosisToDiagnosisDTOs(diagnosisRepo.findByCodeAdmission(CodeAdmission));
    }
    
      public DiagnosisDTO save(DiagnosisDTO dto) {
          Diagnosis domaine = DiagnosisFactory.diagnosisDTOToDiagnosis(dto, new Diagnosis());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated()); 
        domaine = diagnosisRepo.save(domaine);
        return DiagnosisFactory.diagnosisToDiagnosisDTO(domaine);
    }
      
      
        public Diagnosis update(DiagnosisDTO dto) {
        Diagnosis domaine = diagnosisRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DiagnosisNotFound");
        dto.setCode(domaine.getCode());
        DiagnosisFactory.diagnosisDTOToDiagnosis(dto, domaine);
        return diagnosisRepo.save(domaine);
    }

    public void deleteDiagnosis(Integer code) {
        Preconditions.checkArgument(diagnosisRepo.existsById(code), "error.DiagnosisNotFound");
        diagnosisRepo.deleteById(code);
    }
    
     public void deleteDiagnosisByCodeAdmission(Integer codeAdmission) {
//        Preconditions.checkArgument(diagnosisRepo.findByCodeAdmission(codeAdmission), "error.DiagnosisNotFound");
        diagnosisRepo.deleteByCodeAdmission(codeAdmission);
    }

}
