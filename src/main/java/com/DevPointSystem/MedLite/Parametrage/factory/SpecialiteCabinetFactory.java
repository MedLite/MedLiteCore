/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.SpecialiteCabinet;
import com.DevPointSystem.MedLite.Parametrage.dto.SpecialiteCabinetDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SpecialiteCabinetFactory {

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
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());

            return domaine;
        } else {
            return null;
        }
    }

    public static SpecialiteCabinetDTO specialiteCabinetToSpecialiteCabinetDTO(SpecialiteCabinet domaine) {

        if (domaine != null) {
            SpecialiteCabinetDTO dto = new SpecialiteCabinetDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
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
