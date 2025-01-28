/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsOperationDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsOperationFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static DetailsOperationDTO DetailsOperationToDetailsOperationDTOCollectionForUpdate(DetailsOperation domaine) {
        if (domaine != null) {
            DetailsOperationDTO dto = new DetailsOperationDTO();

            dto.setCodeOperation(domaine.getDetailsOperationPK().getCodeOperation());
            dto.setCodeOperation(domaine.getOperation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setPrixSelonTypeArriver(domaine.getMontant());

            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));

            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationArTypeIntervenant(domaine.getTypeIntervenant().getDesignationAr());
                dto.setDesignationLtTypeIntervenant(domaine.getTypeIntervenant().getDesignationLt());
            } else {
                dto.setDesignationLtTypeIntervenant(domaine.getTypeIntervenant().getDesignationAr());
                dto.setDesignationArTypeIntervenant(domaine.getTypeIntervenant().getDesignationLt());
            }

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

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());

            return dto;
        } else {
            return null;
        }

    }
}
