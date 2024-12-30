/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Ville;
import com.DevPointSystem.MedLite.Parametrage.dto.VilleDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class VilleFactory {
     public static Ville createVilleByCode(int code) {
        Ville domaine = new Ville();
        domaine.setCode(code);
        return domaine;
    }

    public static Ville villeDTOToVille(VilleDTO dto, Ville domaine) {
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

    public static VilleDTO villeToVilleDTO(Ville domaine) {

        if (domaine != null) {
            VilleDTO dto = new VilleDTO();
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

    public static List<VilleDTO> listVilleToVilleDTOs(List<Ville> villes) {
        List<VilleDTO> list = new ArrayList<>();
        for (Ville ville : villes) {
            list.add(villeToVilleDTO(ville));
        }
        return list;
    }
}
