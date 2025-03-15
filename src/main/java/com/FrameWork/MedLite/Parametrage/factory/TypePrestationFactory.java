/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.TypePrestation;
import com.FrameWork.MedLite.Parametrage.dto.TypePrestationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypePrestationFactory {
     public static TypePrestation createTypePrestationByCode(int code) {
        TypePrestation domaine = new TypePrestation();
        domaine.setCode(code);
        return domaine;
    }

    public static TypePrestation typePrestationDTOToTypePrestation(TypePrestationDTO dto, TypePrestation domaine) {
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

    public static TypePrestationDTO typePrestationToTypePrestationDTO(TypePrestation domaine) {

        if (domaine != null) {
            TypePrestationDTO dto = new TypePrestationDTO();
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

    public static List<TypePrestationDTO> listTypePrestationToTypePrestationDTOs(List<TypePrestation> typePrestations) {
        List<TypePrestationDTO> list = new ArrayList<>();
        for (TypePrestation typePrestation : typePrestations) {
            list.add(typePrestationToTypePrestationDTO(typePrestation));
        }
        return list;
    }
}
