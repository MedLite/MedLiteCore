/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.FamilleFacturation;
import com.FrameWork.MedLite.Parametrage.dto.FamilleFacturationDTO;
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
public class FamilleFacturationFactory {
       static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

   

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

    public static List<FamilleFacturationDTO> listFamilleFacturationToFamilleFacturationDTOs(List<FamilleFacturation> familleFacturations) {
        List<FamilleFacturationDTO> list = new ArrayList<>();
        for (FamilleFacturation familleFacturation : familleFacturations) {
            list.add(familleFacturationToFamilleFacturationDTO(familleFacturation));
        }
        return list;
    }
}
