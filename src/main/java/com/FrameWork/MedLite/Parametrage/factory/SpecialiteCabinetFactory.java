/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.SpecialiteCabinet;
import com.FrameWork.MedLite.Parametrage.dto.SpecialiteCabinetDTO;
import static com.FrameWork.MedLite.Parametrage.factory.CabinetFactory.LANGUAGE_SEC;
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
public class SpecialiteCabinetFactory {
      static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static SpecialiteCabinet createSpecialiteCabinetByCode(int code) {
        SpecialiteCabinet domaine = new SpecialiteCabinet();
        domaine.setCode(code);
        return domaine;
    }

    public static SpecialiteCabinet specialiteCabinetDTOToSpecialiteCabinet(SpecialiteCabinetDTO dto, SpecialiteCabinet domaine) {
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

    public static SpecialiteCabinetDTO specialiteCabinetToSpecialiteCabinetDTO(SpecialiteCabinet domaine) {

        if (domaine != null) {
            SpecialiteCabinetDTO dto = new SpecialiteCabinetDTO();
            dto.setCode(domaine.getCode());

           if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<SpecialiteCabinetDTO> listSpecialiteCabinetToSpecialiteCabinetDTOs(List<SpecialiteCabinet> specialiteCabinets) {
        List<SpecialiteCabinetDTO> list = new ArrayList<>();
        for (SpecialiteCabinet specialiteCabinet : specialiteCabinets) {
            list.add(specialiteCabinetToSpecialiteCabinetDTO(specialiteCabinet));
        }
        return list;
    }
}
