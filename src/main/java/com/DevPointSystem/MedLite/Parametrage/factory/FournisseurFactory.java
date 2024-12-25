/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Fournisseur;
import com.DevPointSystem.MedLite.Parametrage.dto.FournisseurDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FournisseurFactory {
    public static Fournisseur createFournisseurByCode(int code) {
        Fournisseur domaine = new Fournisseur();
        domaine.setCode(code);
        return domaine;
    }

    public static Fournisseur fournisseurDTOToFournisseur(FournisseurDTO dto, Fournisseur domaine) {
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

    public static FournisseurDTO fournisseurToFournisseurDTO(Fournisseur domaine) {

        if (domaine != null) {
            FournisseurDTO dto = new FournisseurDTO();
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

    public static List<FournisseurDTO> listFournisseurToFournisseurDTOs(List<Fournisseur> fournisseurs) {
        List<FournisseurDTO> list = new ArrayList<>();
        for (Fournisseur fournisseur : fournisseurs) {
            list.add(fournisseurToFournisseurDTO(fournisseur));
        }
        return list;
    }
}
