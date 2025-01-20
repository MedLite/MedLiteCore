/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsPrestationFactory {

    public static DetailsPrestationDTO DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(DetailsPrestation domaine) {
        if (domaine != null) {
            DetailsPrestationDTO dto = new DetailsPrestationDTO();

            dto.setCodePrestation(domaine.getDetailsPrestationPK().getCodePrestation());
            dto.setCodePrestation(domaine.getPrestation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());    
            dto.setPrixSelonTypeArriver(domaine.getMontant());

            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
            dto.setDesignationArTypeIntervenant(domaine.getTypeIntervenant().getDesignationAr());     
            dto.setDesignationLtTypeIntervenant(domaine.getTypeIntervenant().getDesignationLt());

            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
 
            dto.setCodeSaisiePrestation(domaine.getPrestation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsPrestationDTO> detailsPrestationTodetailsPrestationDTOCollections(Collection<DetailsPrestation> detailsPrestations) {
        Collection<DetailsPrestationDTO> detailsPrestationDTOs = new ArrayList<>();
        for (DetailsPrestation detailsPrestation : detailsPrestations) {
            detailsPrestationDTOs.add(DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(detailsPrestation));
        }
        return detailsPrestationDTOs;
    }

    public static DetailsPrestationDTO DetailsPrestationToDetailsPrestationDTOCollection(DetailsPrestation domaine) {
        if (domaine != null) {
            DetailsPrestationDTO dto = new DetailsPrestationDTO();

//            dto.setCodePrestation(domaine.getDetailsPrestationPK().getCodePrestation());
//            dto.setCodePrestation(domaine.getPrestation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());    
            dto.setPrixSelonTypeArriver(domaine.getMontant());

//            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
//            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
//            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
//            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
//            dto.setCodeSaisiePrestation(domaine.getPrestation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }
}
