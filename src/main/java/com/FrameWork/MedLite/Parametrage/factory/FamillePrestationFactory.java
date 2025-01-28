/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.FamillePrestation;
import com.FrameWork.MedLite.Parametrage.dto.FamillePrestationDTO; 
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FamillePrestationFactory {
      static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }
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
              if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }
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
