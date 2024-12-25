/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamilleFacturation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamilleFacturationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FamilleFacturationFactory {
      public static FamilleFacturation createFamilleFacturationByCode(int code) {
        FamilleFacturation domaine = new FamilleFacturation();
        domaine.setCode(code);
        return domaine;
    }

    public static FamilleFacturation familleFacturationDTOToFamilleFacturation(FamilleFacturationDTO dto, FamilleFacturation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());    
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());    


            return domaine;
        } else {
            return null;
        }
    }
    
    
 

    public static FamilleFacturationDTO familleFacturationToFamilleFacturationDTO(FamilleFacturation domaine) {

        if (domaine != null) {
            FamilleFacturationDTO dto = new FamilleFacturationDTO();
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
    
    
    public static List<FamilleFacturationDTO> listFamilleFacturationToFamilleFacturationDTOs(List<FamilleFacturation> familleFacturations) {
        List<FamilleFacturationDTO> list = new ArrayList<>();
        for (FamilleFacturation familleFacturation : familleFacturations) {
            list.add(familleFacturationToFamilleFacturationDTO(familleFacturation));
        }
        return list;
    }
}