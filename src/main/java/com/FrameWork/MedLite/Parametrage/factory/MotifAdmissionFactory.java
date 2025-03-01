/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.MotifAdmission;
import com.FrameWork.MedLite.Parametrage.dto.MotifAdmissionDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class MotifAdmissionFactory {
    public static MotifAdmission createMotifAdmissionByCode(int code) {
        MotifAdmission domaine = new MotifAdmission();
        domaine.setCode(code);
        return domaine;
    }

    public static MotifAdmission motifAdmissionDTOToMotifAdmission(MotifAdmissionDTO dto, MotifAdmission domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode()); 
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());        
            domaine.setCodeSaisie(dto.getCodeSaisie());   
            domaine.setActif(dto.isActif());
       
            return domaine;
        } else {
            return null;
        }
    }

    public static MotifAdmissionDTO motifAdmissionToMotifAdmissionDTO(MotifAdmission domaine) {

        if (domaine != null) {
            MotifAdmissionDTO dto = new MotifAdmissionDTO();
            dto.setCode(domaine.getCode());  
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<MotifAdmissionDTO> listMotifAdmissionToMotifAdmissionDTOs(List<MotifAdmission> motifAdmissions) {
        List<MotifAdmissionDTO> list = new ArrayList<>();
        for (MotifAdmission motifAdmission : motifAdmissions) {
            list.add(motifAdmissionToMotifAdmissionDTO(motifAdmission));
        }
        return list;
    }
}
