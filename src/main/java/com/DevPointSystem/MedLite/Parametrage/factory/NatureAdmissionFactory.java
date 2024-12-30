/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import com.DevPointSystem.MedLite.Parametrage.dto.NatureAdmissionDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class NatureAdmissionFactory {
    public static NatureAdmission createNatureAdmissionByCode(int code) {
        NatureAdmission domaine = new NatureAdmission();
        domaine.setCode(code);
        return domaine;
    }

    public static NatureAdmission natureAdmissionDTOToNatureAdmission(NatureAdmissionDTO dto, NatureAdmission domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode()); 
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr()); 
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());

            return domaine;
        } else {
            return null;
        }
    }

    public static NatureAdmissionDTO natureAdmissionToNatureAdmissionDTO(NatureAdmission domaine) {

        if (domaine != null) {
            NatureAdmissionDTO dto = new NatureAdmissionDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());  
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<NatureAdmissionDTO> listNatureAdmissionToNatureAdmissionDTOs(List<NatureAdmission> natureAdmissions) {
        List<NatureAdmissionDTO> list = new ArrayList<>();
        for (NatureAdmission natureAdmission : natureAdmissions) {
            list.add(natureAdmissionToNatureAdmissionDTO(natureAdmission));
        }
        return list;
    }
}
