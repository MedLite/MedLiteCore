/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.TypeIntervenant;
import com.FrameWork.MedLite.Parametrage.dto.TypeIntervenantDTO;
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
public class TypeIntervenantFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

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
            domaine.setAutoriseFrais(dto.isAutoriseFrais());
            domaine.setIsClinique(dto.isIsClinique());

            return domaine;
        } else {
            return null;
        }
    }

    public static TypeIntervenantDTO typeIntervenantToTypeIntervenantDTO(TypeIntervenant domaine) {

        if (domaine != null) {
            TypeIntervenantDTO dto = new TypeIntervenantDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeTypeIntervenant(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }

            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationArTypeIntervenant(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationArTypeIntervenant(domaine.getDesignationLt());
                dto.setDesignationLTTypeIntervenant(domaine.getDesignationAr());
            }
 
            dto.setActif(domaine.isActif());
            dto.setAutoriseFrais(domaine.isAutoriseFrais());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setIsClinique(domaine.isIsClinique());

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
