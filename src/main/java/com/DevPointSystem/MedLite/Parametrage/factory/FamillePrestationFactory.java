/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamillePrestation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamillePrestationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FamillePrestationFactory {
      public static FamillePrestation createFamillePrestationByCode(int code) {
        FamillePrestation domaine = new FamillePrestation();
        domaine.setCode(code);
        return domaine;
    }

    public static FamillePrestation famillePrestationDTOToFamillePrestation(FamillePrestationDTO dto, FamillePrestation domaine) {
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
    
    
 

    public static FamillePrestationDTO famillePrestationToFamillePrestationDTO(FamillePrestation domaine) {

        if (domaine != null) {
            FamillePrestationDTO dto = new FamillePrestationDTO();
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
    
    
    public static List<FamillePrestationDTO> listFamillePrestationToFamillePrestationDTOs(List<FamillePrestation> famillePrestations) {
        List<FamillePrestationDTO> list = new ArrayList<>();
        for (FamillePrestation famillePrestation : famillePrestations) {
            list.add(famillePrestationToFamillePrestationDTO(famillePrestation));
        }
        return list;
    }
}
