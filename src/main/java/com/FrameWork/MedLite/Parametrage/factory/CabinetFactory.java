/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.dto.CabinetDTO;
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
public class CabinetFactory {
      static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static Cabinet createCabinetByCode(int code) {
        Cabinet domaine = new Cabinet();
        domaine.setCode(code);
        return domaine;
    }

    public static Cabinet cabinetDTOToCabinet(CabinetDTO dto, Cabinet domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setActif(dto.isActif());
        
            
            domaine.setCodeSpecialiteCabinet(dto.getCodeSpecialiteCabinet());
            if (domaine.getCodeSpecialiteCabinet() != null) {
                domaine.setSpecialiteCabinet(SpecialiteCabinetFactory.createSpecialiteCabinetByCode(dto.getCodeSpecialiteCabinet()));
            }
            
            return domaine;
        } else {
            return null;
        }
    }

    public static CabinetDTO cabinetToCabinetDTO(Cabinet domaine) {

        if (domaine != null) {
            CabinetDTO dto = new CabinetDTO();
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
            dto.setSpecialiteCabinetDTO(SpecialiteCabinetFactory.specialiteCabinetToSpecialiteCabinetDTO(domaine.getSpecialiteCabinet()));
            dto.setCodeSpecialiteCabinet(domaine.getCodeSpecialiteCabinet());

            return dto;
        } else {
            return null;
        }
    }

    public static List<CabinetDTO> listCabinetToCabinetDTOs(List<Cabinet> cabinets) {
        List<CabinetDTO> list = new ArrayList<>();
        for (Cabinet cabinet : cabinets) {
            list.add(cabinetToCabinetDTO(cabinet));
        }
        return list;
    }
}
