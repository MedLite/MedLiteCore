/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.dto.ConventionDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ConventionFactory {
//     public static Convention createConventionByCode(int code) {
//        Convention domaine = new Convention();
//        domaine.setCode(code);
//        return domaine;
//    }
//
//    public static Convention conventionDTOToConvention(ConventionDTO dto, Convention domaine) {
//        if (dto != null) {
//            domaine.setCode(dto.getCode()); 
//            domaine.setDesignationLt(dto.getDesignationLt());
//            domaine.setDesignationAr(dto.getDesignationAr());
//            domaine.setCodeSaisie(dto.getCodeSaisie()); 
//            domaine.setActif(dto.isActif());
//            domaine.setDateCreate(dto.getDateCreate());
//            domaine.setUserCreate(dto.getUserCreate());
//
//            return domaine;
//        } else {
//            return null;
//        }
//    }
//
//    public static ConventionDTO conventionToConventionDTO(Convention domaine) {
//
//        if (domaine != null) {
//            ConventionDTO dto = new ConventionDTO();
//            dto.setCode(domaine.getCode());
//
//            dto.setDesignationAr(domaine.getDesignationAr());
//            dto.setDesignationLt(domaine.getDesignationLt()); 
//            dto.setCodeSaisie(domaine.getCodeSaisie());
//            dto.setActif(domaine.isActif());
//            dto.setDateCreate(domaine.getDateCreate());
//            dto.setUserCreate(domaine.getUserCreate());
//
//            return dto;
//        } else {
//            return null;
//        }
//    }
//
//    public static List<ConventionDTO> listConventionToConventionDTOs(List<Convention> conventions) {
//        List<ConventionDTO> list = new ArrayList<>();
//        for (Convention convention : conventions) {
//            list.add(conventionToConventionDTO(convention));
//        }
//        return list;
//    }
}
