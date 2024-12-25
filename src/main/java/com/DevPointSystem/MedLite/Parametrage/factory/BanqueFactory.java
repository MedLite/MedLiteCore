/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Banque;
import com.DevPointSystem.MedLite.Parametrage.dto.BanqueDTO;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class BanqueFactory {

    public static Banque createBanqueByCode(int code) {
        Banque domaine = new Banque();
        domaine.setCode(code);
        return domaine;
    }

    public static Banque banqueDTOToBanque(BanqueDTO dto, Banque domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            
//            Preconditions.checkArgument(dto.getRib() != null , "RibbbObligatoir");
            domaine.setRib(dto.getRib());

            domaine.setActif(dto.isActif());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());

            return domaine;
        } else {
            return null;
        }
    }

    public static BanqueDTO banqueToBanqueDTO(Banque domaine) {

        if (domaine != null) {
            BanqueDTO dto = new BanqueDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setRib(domaine.getRib());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<BanqueDTO> listBanqueToBanqueDTOs(List<Banque> banques) {
        List<BanqueDTO> list = new ArrayList<>();
        for (Banque banque : banques) {
            list.add(banqueToBanqueDTO(banque));
        }
        return list;
    }
}
