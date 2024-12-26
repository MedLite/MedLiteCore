/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationConsultationDTO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsPrestationConsultationFactory {
     public static DetailsPrestationConsultationDTO DetailsPrestationConsultationToDetailsPrestationConsultationDTOCollectionForUpdate(DetailsPrestationConsultation domaine) {
        if (domaine != null) {
            DetailsPrestationConsultationDTO dto = new DetailsPrestationConsultationDTO();

            dto.setCodePrestConsult(domaine.getDetailsPrestationConsultationPK().getCodePrestConsult());
            dto.setCodePrestConsult(domaine.getPrestationConsultation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));

            dto.setDesignationArTypeIntervenant(domaine.getTypeIntervenant().getDesignationAr());
            dto.setDesignationLtTypeIntervenant(domaine.getTypeIntervenant().getDesignationLt());
            dto.setCodeSaisieTypeIntervenant(domaine.getTypeIntervenant().getCodeSaisie());
            dto.setCodeSaisieFKPrestationConsultation(domaine.getPrestationConsultation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsPrestationConsultationDTO> detailsPrestationConsultationTodetailsPrestationConsultationDTOCollections(Collection<DetailsPrestationConsultation> detailsPrestationConsultations) {
        Collection<DetailsPrestationConsultationDTO> detailsPrestationConsultationDTOs = new ArrayList<>();
        for (DetailsPrestationConsultation detailsPrestationConsultation : detailsPrestationConsultations) {
            detailsPrestationConsultationDTOs.add(DetailsPrestationConsultationToDetailsPrestationConsultationDTOCollectionForUpdate(detailsPrestationConsultation));
        }
        return detailsPrestationConsultationDTOs;
    }

    public static DetailsPrestationConsultationDTO DetailsPrestationConsultationToDetailsPrestationConsultationDTOCollection(DetailsPrestationConsultation domaine) {
        if (domaine != null) {
            DetailsPrestationConsultationDTO dto = new DetailsPrestationConsultationDTO();

            dto.setCodePrestConsult(domaine.getDetailsPrestationConsultationPK().getCodePrestationConsultation());
            dto.setCodePrestConsult(domaine.getPrestationConsultation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant()); 
            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));

            dto.setCodeSaisieFKPrestationConsultation(domaine.getPrestationConsultation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }
}
