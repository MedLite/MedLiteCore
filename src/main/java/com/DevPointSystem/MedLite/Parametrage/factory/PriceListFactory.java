/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceListPK;
import com.DevPointSystem.MedLite.Parametrage.domaine.PriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PriceListDTO;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PriceListFactory {

    public static PriceList createPriceListByCode(int code) {
        PriceList domaine = new PriceList();
        domaine.setCode(code);
        return domaine;
    }

    public static PriceList priceListDTOToPriceList(PriceListDTO dto, PriceList domaine) {
        if (dto != null) {

       
            domaine.setCode(dto.getCode());
       
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setActif(dto.isActif());

            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
            }

            if (dto.getDetailsPriceListsListDTOs() == null || dto.getDetailsPriceListsListDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsPriceListRequired");
            }
            Collection<DetailsPriceList> detailsCollections = new ArrayList<>();
            dto.getDetailsPriceListsListDTOs().forEach(x -> {
                DetailsPriceList detailsPriceList = new DetailsPriceList();

                DetailsPriceListPK detailsPK = new DetailsPriceListPK();
                Preconditions.checkBusinessLogique(x.getCodeNatureAdmission() != null, "error.NatureAdmissionRequired");
                detailsPK.setCodeNatureAdmission(x.getCodeNatureAdmission());
                Preconditions.checkBusinessLogique(x.getCodePrestation() != null, "error.PrestationRequired");
                detailsPK.setCodePrestation(x.getCodePrestation());

                detailsPriceList.setDetailsPriceListPK(detailsPK);
                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsPriceList.setMontant(x.getMontant());
                detailsPriceList.setDateCreate(domaine.getDateCreate());
                detailsPriceList.setUsercreate(domaine.getUserCreate());
                detailsPriceList.setPriceList(domaine);
                detailsCollections.add(detailsPriceList); 
            });

            if (domaine.getDetailsPriceLists() != null) { 
                domaine.getDetailsPriceLists().clear();
                domaine.getDetailsPriceLists().addAll(detailsCollections);
            } else { 

                domaine.setDetailsPriceLists(detailsCollections);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static PriceListDTO priceListToPriceListDTO(PriceList domaine) {

        if (domaine != null) {
            PriceListDTO dto = new PriceListDTO();
            dto.setCode(domaine.getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());

            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));

            if (domaine.getDetailsPriceLists() != null) {
                Collection<DetailsPriceListDTO> detailsPriceListDTOs = new ArrayList<>();
                domaine.getDetailsPriceLists().forEach(x -> {
                    DetailsPriceListDTO detailsDTO = new DetailsPriceListDTO();
                    detailsDTO = DetailsPriceListFactory.DetailsPriceListToDetailsPriceListDTOCollection(x);
                    detailsPriceListDTOs.add(detailsDTO);
                });
                if (dto.getDetailsPriceListsListDTOs() != null) {
                    dto.getDetailsPriceListsListDTOs().clear();
                    dto.getDetailsPriceListsListDTOs().addAll(detailsPriceListDTOs);
                } else {
                    dto.setDetailsPriceListsListDTOs(detailsPriceListDTOs);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static List<PriceListDTO> listPriceListToPriceListDTOs(List<PriceList> priceLists) {
        List<PriceListDTO> list = new ArrayList<>();
        for (PriceList priceList : priceLists) {
            list.add(priceListToPriceListDTO(priceList));
        }
        return list;
    }

}
