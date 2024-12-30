/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Societe;
import com.DevPointSystem.MedLite.Parametrage.dto.SocieteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SocieteFactory {
      public static Societe createSocieteByCode(int code) {
        Societe domaine = new Societe();
        domaine.setCode(code);
        return domaine;
    }

    public static Societe societeDTOToSociete(SocieteDTO dto, Societe domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());    
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
//            domaine.setDateCreate(dto.getDateCreate());
//            domaine.setUserCreate(dto.getUserCreate());    


            return domaine;
        } else {
            return null;
        }
    }
    
    
 

    public static SocieteDTO societeToSocieteDTO(Societe domaine) {

        if (domaine != null) {
            SocieteDTO dto = new SocieteDTO();
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
    
    
    public static List<SocieteDTO> listSocieteToSocieteDTOs(List<Societe> societes) {
        List<SocieteDTO> list = new ArrayList<>();
        for (Societe societe : societes) {
            list.add(societeToSocieteDTO(societe));
        }
        return list;
    }
}
