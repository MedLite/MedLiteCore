/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.SpecialiteMedecin;
import com.FrameWork.MedLite.Parametrage.dto.SpecialiteMedecinDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SpecialiteMedecinFactory {
      public static SpecialiteMedecin createSpecialiteMedecinByCode(int code) {
        SpecialiteMedecin domaine = new SpecialiteMedecin();
        domaine.setCode(code);
        return domaine;
    }

    public static SpecialiteMedecin specialiteMedecinDTOToSpecialiteMedecin(SpecialiteMedecinDTO dto, SpecialiteMedecin domaine) {
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
    
    
 

    public static SpecialiteMedecinDTO specialiteMedecinToSpecialiteMedecinDTO(SpecialiteMedecin domaine) {

        if (domaine != null) {
            SpecialiteMedecinDTO dto = new SpecialiteMedecinDTO();
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
    
    
    public static List<SpecialiteMedecinDTO> listSpecialiteMedecinToSpecialiteMedecinDTOs(List<SpecialiteMedecin> specialiteMedecins) {
        List<SpecialiteMedecinDTO> list = new ArrayList<>();
        for (SpecialiteMedecin specialiteMedecin : specialiteMedecins) {
            list.add(specialiteMedecinToSpecialiteMedecinDTO(specialiteMedecin));
        }
        return list;
    }
}
