/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.TypeIntervenant;
import com.DevPointSystem.MedLite.Parametrage.dto.TypeIntervenantDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypeIntervenantFactory {
        public static TypeIntervenant createTypeIntervenantByCode(int code) {
        TypeIntervenant domaine = new TypeIntervenant();
        domaine.setCode(code);
        return domaine;
    }

    public static TypeIntervenant typeIntervenantDTOToTypeIntervenant(TypeIntervenantDTO dto, TypeIntervenant domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());    
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
      

            return domaine;
        } else {
            return null;
        }
    }
    
    
 

    public static TypeIntervenantDTO typeIntervenantToTypeIntervenantDTO(TypeIntervenant domaine) {

        if (domaine != null) {
            TypeIntervenantDTO dto = new TypeIntervenantDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());    


            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<TypeIntervenantDTO> listTypeIntervenantToTypeIntervenantDTOs(List<TypeIntervenant> typeIntervenants) {
        List<TypeIntervenantDTO> list = new ArrayList<>();
        for (TypeIntervenant typeIntervenant : typeIntervenants) {
            list.add(typeIntervenantToTypeIntervenantDTO(typeIntervenant));
        }
        return list;
    }
}
