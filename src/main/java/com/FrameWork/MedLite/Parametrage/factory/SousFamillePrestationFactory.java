/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.SousFamillePrestation;
import com.FrameWork.MedLite.Parametrage.dto.SousFamillePrestationDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SousFamillePrestationFactory {

    
    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }
    
    public static SousFamillePrestation createSousFamillePrestationByCode(int code) {
        SousFamillePrestation domaine = new SousFamillePrestation();
        domaine.setCode(code);
        return domaine;
    }

    public static SousFamillePrestation sousFamillePrestationDTOToSousFamillePrestation(SousFamillePrestationDTO dto, SousFamillePrestation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setCodeFamillePrestation(dto.getCodeFamillePrestation());
            if (domaine.getCodeFamillePrestation() != null) {
                domaine.setFamillePrestation(FamillePrestationFactory.createFamillePrestationByCode(dto.getCodeFamillePrestation()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static SousFamillePrestationDTO sousFamillePrestationToSousFamillePrestationDTO(SousFamillePrestation domaine) {

        if (domaine != null) {
            SousFamillePrestationDTO dto = new SousFamillePrestationDTO();
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

            dto.setCodeFamillePrestation(domaine.getCodeFamillePrestation());
            dto.setFamillePrestationDTO(FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine.getFamillePrestation()));

            return dto;
        } else {
            return null;
        }
    }

    public static List<SousFamillePrestationDTO> listSousFamillePrestationToSousFamillePrestationDTOs(List<SousFamillePrestation> sousFamillePrestations) {
        List<SousFamillePrestationDTO> list = new ArrayList<>();
        for (SousFamillePrestation sousFamillePrestation : sousFamillePrestations) {
            list.add(sousFamillePrestationToSousFamillePrestationDTO(sousFamillePrestation));
        }
        return list;
    }
}
