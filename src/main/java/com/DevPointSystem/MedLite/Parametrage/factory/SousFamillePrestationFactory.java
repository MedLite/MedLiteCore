/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.SousFamillePrestation;
import com.DevPointSystem.MedLite.Parametrage.dto.SousFamillePrestationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SousFamillePrestationFactory {

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
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
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
