/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsOperation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsOperationDTO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsOperationFactory {
    public static DetailsOperationDTO DetailsOperationToDetailsOperationDTOCollectionForUpdate(DetailsOperation domaine) {
        if (domaine != null) {
            DetailsOperationDTO dto = new DetailsOperationDTO();

            dto.setCodeOperation(domaine.getDetailsOperationPK().getCodeOperation());
            dto.setCodeOperation(domaine.getOperation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));

            dto.setDesignationArTypeIntervenant(domaine.getTypeIntervenant().getDesignationAr());
            dto.setDesignationLtTypeIntervenant(domaine.getTypeIntervenant().getDesignationLt());
            dto.setCodeSaisieTypeIntervenant(domaine.getTypeIntervenant().getCodeSaisie());
            dto.setCodeSaisieOperation(domaine.getOperation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsOperationDTO> detailsOperationTodetailsOperationDTOCollections(Collection<DetailsOperation> detailsOperations) {
        Collection<DetailsOperationDTO> detailsOperationDTOs = new ArrayList<>();
        for (DetailsOperation detailsOperation : detailsOperations) {
            detailsOperationDTOs.add(DetailsOperationToDetailsOperationDTOCollectionForUpdate(detailsOperation));
        }
        return detailsOperationDTOs;
    }

    public static DetailsOperationDTO DetailsOperationToDetailsOperationDTOCollection(DetailsOperation domaine) {
        if (domaine != null) {
            DetailsOperationDTO dto = new DetailsOperationDTO();

            dto.setCodeOperation(domaine.getDetailsOperationPK().getCodeOperation());
            dto.setCodeOperation(domaine.getOperation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant()); 
            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));

            dto.setCodeSaisieOperation(domaine.getOperation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }
}
