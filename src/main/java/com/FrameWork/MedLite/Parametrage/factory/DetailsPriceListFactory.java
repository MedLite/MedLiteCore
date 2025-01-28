/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
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
public class DetailsPriceListFactory {

    public static DetailsPriceListDTO DetailsPriceListToDetailsPriceListDTO(DetailsPriceList domaine) {
        if (domaine != null) {
            DetailsPriceListDTO dto = new DetailsPriceListDTO();
            dto.setCode(domaine.getCode());
            dto.setMontant(domaine.getMontant());
            dto.setMontantPere(domaine.getMontantPere());
            dto.setRemMaj(domaine.getRemMaj());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));

            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

            dto.setCodePriceList(domaine.getCodePriceList());
            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));

            
             dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
            
            
            return dto;
        } else {
            return null;
        }

    }

  
    
      public static List<DetailsPriceListDTO> detailsPriceListTodetailsPriceListDTOs(List<DetailsPriceList> detailsPriceLists) {
        List<DetailsPriceListDTO> detailsPriceListDTOs = new ArrayList<>();
        for (DetailsPriceList detailsPriceList : detailsPriceLists) {
            detailsPriceListDTOs.add(DetailsPriceListToDetailsPriceListDTO(detailsPriceList));
        }
        return detailsPriceListDTOs;
    }

    public static DetailsPriceList DetailspriceListDTOToDetailsPriceList(DetailsPriceListDTO dto, DetailsPriceList domaine) {
        if (dto != null) {

            domaine.setCode(dto.getCode());
            domaine.setMontant(dto.getMontant());
            domaine.setMontantPere(dto.getMontantPere());
            domaine.setRemMaj(dto.getRemMaj());
            domaine.setUsercreate(dto.getUsercreate());
            domaine.setDateCreate(new Date());

            domaine.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (domaine.getCodeNatureAdmission() != null) {
                domaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            domaine.setCodePrestation(dto.getCodePrestation());
            if (domaine.getCodePrestation() != null) {
                domaine.setPrestation(PrestationFactory.createPrestationByCode(dto.getCodePrestation()));
            }

            domaine.setCodePriceList(dto.getCodePriceList());
            if (domaine.getCodePriceList() != null) {
                domaine.setPriceList(PriceListFactory.createPriceListByCode(dto.getCodePriceList()));
            }
            
            domaine.setCodeTypeIntervenant(dto.getCodeTypeIntervenant());
            if (domaine.getCodeTypeIntervenant() != null) {
                domaine.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(dto.getCodeTypeIntervenant()));
            }

            return domaine;
        } else {
            return null;
        }
    }

}
