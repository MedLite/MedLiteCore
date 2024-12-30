/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.ResponsableRemise;
import com.DevPointSystem.MedLite.Parametrage.dto.ResponsableRemiseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ResponsableRemiseFactory {

    public static ResponsableRemise createResponsableRemiseByCode(int code) {
        ResponsableRemise domaine = new ResponsableRemise();
        domaine.setCode(code);
        return domaine;
    }

    public static ResponsableRemise responsableRemiseDTOToResponsableRemise(ResponsableRemiseDTO dto, ResponsableRemise domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setNomAr(dto.getNomAr());
            domaine.setNomLt(dto.getNomLt());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setActif(dto.isActif()); 
            domaine.setExercice(dto.getExercice());
            domaine.setMontantAutoriser(dto.getMontantAutoriser());
            domaine.setMontantConsommer(dto.getMontantConsommer());

            return domaine;
        } else {
            return null;
        }
    }

    public static ResponsableRemiseDTO responsableRemiseToResponsableRemiseDTO(ResponsableRemise domaine) {

        if (domaine != null) {
            ResponsableRemiseDTO dto = new ResponsableRemiseDTO();
            dto.setCode(domaine.getCode());

            dto.setNomAr(domaine.getNomAr());
            dto.setNomLt(domaine.getNomLt());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setExercice(domaine.getExercice());
            dto.setMontantAutoriser(domaine.getMontantAutoriser());
            dto.setMontantConsommer(domaine.getMontantConsommer());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ResponsableRemiseDTO> listResponsableRemiseToResponsableRemiseDTOs(List<ResponsableRemise> responsableRemises) {
        List<ResponsableRemiseDTO> list = new ArrayList<>();
        for (ResponsableRemise responsableRemise : responsableRemises) {
            list.add(responsableRemiseToResponsableRemiseDTO(responsableRemise));
        }
        return list;
    }
}
