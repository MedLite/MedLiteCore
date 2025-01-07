/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.VPriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.VPriceListDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class VPriceListFactory {

    public static VPriceListDTO VPriceListToVPriceListDTOCollectionForUpdate(VPriceList domaine) {
        if (domaine != null) {
            VPriceListDTO dto = new VPriceListDTO();

            dto.setCodePriceList(domaine.getvPriceListPK().getCodePriceList()); 
            dto.setMontant(domaine.getMontant());
            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());   
            dto.setCodePrestation(domaine.getCodePrestation()); 

            
            dto.setDesignationArPrestation(domaine.getDesignationLtPrestation());
 

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<VPriceListDTO> vPriceListTovPriceListDTOCollections(Collection<VPriceList> vPriceLists) {
        Collection<VPriceListDTO> vPriceListDTOs = new ArrayList<>();
        for (VPriceList vPriceList : vPriceLists) {
            vPriceListDTOs.add(VPriceListToVPriceListDTOCollectionForUpdate(vPriceList));
        }
        return vPriceListDTOs;
    }

    public static List<VPriceListDTO> listvPriceListTovPriceListDTOs(List<VPriceList> vPriceLists) {
        List<VPriceListDTO> list = new ArrayList<>();
        for (VPriceList vPriceList : vPriceLists) {
            list.add(VPriceListToVPriceListDTOCollectionForUpdate(vPriceList));
        }
        return list;
    }

//    public static VPriceListDTO VPriceListToVPriceListDTOCollection(VPriceList domaine) {
//        if (domaine != null) {
//            VPriceListDTO dto = new VPriceListDTO();
//
//            dto.setCodeOperation(domaine.getVPriceListPK().getCodeOperation());
//            dto.setCodeOperation(domaine.getOperation().getCode());
//            dto.setDateCreate(domaine.getDateCreate());
//            dto.setUsercreate(domaine.getUsercreate());
//            dto.setMontant(domaine.getMontant());
//            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
//            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
//
//            dto.setCodeSaisieOperation(domaine.getOperation().getCodeSaisie());
//
//            return dto;
//        } else {
//            return null;
//        }
//
//    }
}
