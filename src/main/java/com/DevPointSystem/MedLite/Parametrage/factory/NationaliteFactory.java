/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Nationalite;
import com.DevPointSystem.MedLite.Parametrage.dto.NationaliteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class NationaliteFactory {
     public static Nationalite createNationaliteByCode(int code) {
        Nationalite domaine = new Nationalite();
        domaine.setCode(code);
        return domaine;
    }

    public static Nationalite nationaliteDTOToNationalite(NationaliteDTO dto, Nationalite domaine) {
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

    public static NationaliteDTO nationaliteToNationaliteDTO(Nationalite domaine) {

        if (domaine != null) {
            NationaliteDTO dto = new NationaliteDTO();
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

    public static List<NationaliteDTO> listNationaliteToNationaliteDTOs(List<Nationalite> nationalites) {
        List<NationaliteDTO> list = new ArrayList<>();
        for (Nationalite nationalite : nationalites) {
            list.add(nationaliteToNationaliteDTO(nationalite));
        }
        return list;
    }
}
