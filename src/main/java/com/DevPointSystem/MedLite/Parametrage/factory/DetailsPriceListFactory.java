/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPriceListDTO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsPriceListFactory {
     public static DetailsPriceListDTO DetailsPriceListToDetailsPriceListDTOCollectionForUpdate(DetailsPriceList domaine) {
        if (domaine != null) {
            DetailsPriceListDTO dto = new DetailsPriceListDTO();

            dto.setCodePriceList(domaine.getDetailsPriceListPK().getCodePriceList());
            dto.setCodePriceList(domaine.getPriceList().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());   
               

            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));

            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

            dto.setDesignationArNatureAdmission(domaine.getNatureAdmission().getDesignationAr());
            dto.setDesignationLtNatureAdmission(domaine.getNatureAdmission().getDesignationLt());
            
            dto.setDesignationArPrestation(domaine.getPrestation().getDesignationAr());
            dto.setDesignationLtPrestation(domaine.getPrestation().getDesignationLt());           
            dto.setCodeSaisiePrestation(domaine.getPrestation().getCodeSaisie());

          
            
            
            dto.setCodeSaisiePriceList(domaine.getPriceList().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsPriceListDTO> detailsPriceListTodetailsPriceListDTOCollections(Collection<DetailsPriceList> detailsPriceLists) {
        Collection<DetailsPriceListDTO> detailsPriceListDTOs = new ArrayList<>();
        for (DetailsPriceList detailsPriceList : detailsPriceLists) {
            detailsPriceListDTOs.add(DetailsPriceListToDetailsPriceListDTOCollectionForUpdate(detailsPriceList));
        }
        return detailsPriceListDTOs;
    }

    public static DetailsPriceListDTO DetailsPriceListToDetailsPriceListDTOCollection(DetailsPriceList domaine) {
        if (domaine != null) {
             DetailsPriceListDTO dto = new DetailsPriceListDTO();

            dto.setCodePriceList(domaine.getDetailsPriceListPK().getCodePriceList());
            dto.setCodePriceList(domaine.getPriceList().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
      
       dto.setMontant(domaine.getMontant());      

            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));

            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

            dto.setCodeSaisiePriceList(domaine.getPriceList().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }
}
