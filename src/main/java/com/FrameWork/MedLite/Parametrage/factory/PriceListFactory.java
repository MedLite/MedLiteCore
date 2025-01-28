/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListPK;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import com.FrameWork.MedLite.web.Util.Helper;
import com.FrameWork.MedLite.web.Util.Preconditions;
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
            domaine.setCodeSaisie(dto.getCodeSaisie());            
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setActif(dto.isActif());            
            domaine.setCash(dto.isCash());
            domaine.setUserCreate(Helper.getUserAuthenticated());
            domaine.setDateCreate(new Date());
            
            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
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
            dto.setCodeSaisie(domaine.getCodeSaisie());
            
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());            
            dto.setCash(domaine.isCash());
            
            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));

//            if (domaine.getDetailsPriceLists() != null) {
//                Collection<DetailsPriceListDTO> detailsPriceListDTOs = new ArrayList<>();
//                domaine.getDetailsPriceLists().forEach(x -> {
//                    DetailsPriceListDTO detailsDTO = new DetailsPriceListDTO();
//                    detailsDTO = DetailsPriceListFactory.DetailsPriceListToDetailsPriceListDTOCollection(x);
//                    detailsPriceListDTOs.add(detailsDTO);
//                });
//                if (dto.getDetailsPriceListsListDTOs() != null) {
//                    dto.getDetailsPriceListsListDTOs().clear();
//                    dto.getDetailsPriceListsListDTOs().addAll(detailsPriceListDTOs);
//                } else {
//                    dto.setDetailsPriceListsListDTOs(detailsPriceListDTOs);
//                }
//            }
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
