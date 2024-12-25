/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Cabinet;
import com.DevPointSystem.MedLite.Parametrage.dto.CabinetDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CabinetFactory {

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
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());
            
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

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
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
