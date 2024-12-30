/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsListCouvertureFactory {
    public static DetailsListCouvertureDTO DetailsListCouvertureToDetailsListCouvertureDTOCollectionForUpdate(DetailsListCouverture domaine) {
        if (domaine != null) {
            DetailsListCouvertureDTO dto = new DetailsListCouvertureDTO();

            dto.setCodeListCouverture(domaine.getDetailsListCouverturePK().getCodeListCouverture());
            dto.setCodeListCouverture(domaine.getListCouverture().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setTauxCouverPec(domaine.getTauxCouverPec());
            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

            dto.setDesignationArPrestation(domaine.getPrestation().getDesignationAr());
            dto.setDesignationLtPrestation(domaine.getPrestation().getDesignationLt());
            dto.setCodeSaisiePrestation(domaine.getPrestation().getCodeSaisie());
            dto.setCodeSaisieListCouverture(domaine.getListCouverture().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsListCouvertureDTO> detailsListCouvertureTodetailsListCouvertureDTOCollections(Collection<DetailsListCouverture> detailsListCouvertures) {
        Collection<DetailsListCouvertureDTO> detailsListCouvertureDTOs = new ArrayList<>();
        for (DetailsListCouverture detailsListCouverture : detailsListCouvertures) {
            detailsListCouvertureDTOs.add(DetailsListCouvertureToDetailsListCouvertureDTOCollectionForUpdate(detailsListCouverture));
        }
        return detailsListCouvertureDTOs;
    }

    public static DetailsListCouvertureDTO DetailsListCouvertureToDetailsListCouvertureDTOCollection(DetailsListCouverture domaine) {
        if (domaine != null) {
            DetailsListCouvertureDTO dto = new DetailsListCouvertureDTO();

            dto.setCodeListCouverture(domaine.getDetailsListCouverturePK().getCodeListCouverture());
            dto.setCodeListCouverture(domaine.getListCouverture().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setTauxCouverPec(domaine.getTauxCouverPec()); 
            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

//            dto.setCodeSaisieListCouverture(domaine.getListCouverture().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }
}
