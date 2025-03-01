/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.ListCouverture;
import com.FrameWork.MedLite.Parametrage.dto.ListCouvertureDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ListCouvertureFactory {
     public static ListCouverture createListCouvertureByCode(int code) {
        ListCouverture domaine = new ListCouverture();
        domaine.setCode(code);
        return domaine;
    }

    public static ListCouverture listCouvertureDTOToListCouverture(ListCouvertureDTO dto, ListCouverture domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());   


 
           

            return domaine;
        } else {
            return null;
        }
    }

    public static ListCouvertureDTO listCouvertureToListCouvertureDTO(ListCouverture domaine) {

        if (domaine != null) {
            ListCouvertureDTO dto = new ListCouvertureDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
  
 
            return dto;
        } else {
            return null;
        }
    }

    public static List<ListCouvertureDTO> listListCouvertureToListCouvertureDTOs(List<ListCouverture> listCouvertures) {
        List<ListCouvertureDTO> list = new ArrayList<>();
        for (ListCouverture listCouverture : listCouvertures) {
            list.add(listCouvertureToListCouvertureDTO(listCouverture));
        }
        return list;
    }
}
