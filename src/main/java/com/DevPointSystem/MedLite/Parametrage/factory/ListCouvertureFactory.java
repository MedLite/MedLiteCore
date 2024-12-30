/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsListCouverturePK;
import com.DevPointSystem.MedLite.Parametrage.domaine.ListCouverture;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.ListCouvertureDTO;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
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

 
            if(dto.getDetailsListCouvertureDTOs().isEmpty()){
                 throw new IllegalArgumentException("error.DetailsListCouvertureRequired");
            }  
            Collection<DetailsListCouverture> detailsCollections = new ArrayList<>();
            dto.getDetailsListCouvertureDTOs().forEach(x -> {
                DetailsListCouverture detailsListCouverture = new DetailsListCouverture();

                DetailsListCouverturePK detailsPK = new DetailsListCouverturePK();
                Preconditions.checkBusinessLogique(x.getCodePrestation()!= null, "error.PrestationRequired");
                detailsPK.setCodePrestation(x.getCodePrestation());

                detailsListCouverture.setDetailsListCouverturePK(detailsPK);

                Preconditions.checkBusinessLogique(x.getTauxCouverPec()!= null, "error.MontantRequired");
                detailsListCouverture.setTauxCouverPec(x.getTauxCouverPec());    
                 

                detailsListCouverture.setDateCreate(domaine.getDateCreate());
                detailsListCouverture.setUsercreate(domaine.getUserCreate());
                detailsListCouverture.setListCouverture(domaine);
                detailsCollections.add(detailsListCouverture);
            });

            if (domaine.getDetailsListCouvertures()!= null) {
                domaine.getDetailsListCouvertures().clear();
                domaine.getDetailsListCouvertures().addAll(detailsCollections);
            } else {
                domaine.setDetailsListCouvertures(detailsCollections);
            }
            

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
  
            
            if (domaine.getDetailsListCouvertures()!= null) {
                Collection<DetailsListCouvertureDTO> detailsListCouvertureDTOs = new ArrayList<>();
                domaine.getDetailsListCouvertures().forEach(x -> {
                    DetailsListCouvertureDTO detailsDTO = new DetailsListCouvertureDTO();
                    detailsDTO = DetailsListCouvertureFactory.DetailsListCouvertureToDetailsListCouvertureDTOCollectionForUpdate(x);
                    detailsListCouvertureDTOs.add(detailsDTO);
                });
                if (dto.getDetailsListCouvertureDTOs()!= null) {
                    dto.getDetailsListCouvertureDTOs().clear();
                    dto.getDetailsListCouvertureDTOs().addAll(detailsListCouvertureDTOs);
                } else {
                    dto.setDetailsListCouvertureDTOs(detailsListCouvertureDTOs);
                }
            }
            
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
