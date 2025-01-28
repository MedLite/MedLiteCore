/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
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
public class DetailsListCouvertureFactory {
       static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }
    
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

             if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationArPrestation(domaine.getPrestation().getDesignationAr());
                dto.setDesignationLtPrestation(domaine.getPrestation().getDesignationLt());
            } else {
                dto.setDesignationLtPrestation(domaine.getPrestation().getDesignationAr());
                dto.setDesignationArPrestation(domaine.getPrestation().getDesignationLt());
            }
              
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
