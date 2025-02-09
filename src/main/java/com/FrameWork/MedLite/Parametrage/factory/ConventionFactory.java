/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.dto.ConventionDTO;
import static com.FrameWork.MedLite.Parametrage.factory.PrestationFactory.LANGUAGE_SEC;
import com.FrameWork.MedLite.web.Util.Helper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ConventionFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static Convention createConventionByCode(int code) {
        Convention domaine = new Convention();
        domaine.setCode(code);
        return domaine;
    }

    public static Convention conventionDTOToConvention(ConventionDTO dto, Convention domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setActif(dto.isActif());

            domaine.setDateDeb(dto.getDateDeb());
            domaine.setDateFin(dto.getDateFin());

            domaine.setCodePriceList(dto.getCodePriceList());
            if (domaine.getCodePriceList() != null) {
                domaine.setPriceList(PriceListFactory.createPriceListByCode(dto.getCodePriceList()));

            }
            domaine.setCodeListCouverture(dto.getCodeListCouverture());
            if (domaine.getCodeListCouverture() != null) {
                domaine.setListCouverture(ListCouvertureFactory.createListCouvertureByCode(dto.getCodeListCouverture()));

            }

            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));

            }

            return domaine;
        } else {
            return null;
        }
    }

    public static ConventionDTO conventionToConventionDTO(Convention domaine) {

        if (domaine != null) {
            ConventionDTO dto = new ConventionDTO();
            dto.setCode(domaine.getCode());

            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateDeb(domaine.getDateDeb());
            dto.setDateFin(domaine.getDateFin());

            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));
            dto.setCodePriceList(domaine.getCodePriceList());

            dto.setListCouvertureDTO(ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine.getListCouverture()));
            dto.setCodeListCouverture(domaine.getCodeListCouverture());

            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));
            dto.setCodeSociete(domaine.getCodeSociete());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ConventionDTO> listConventionToConventionDTOs(List<Convention> conventions) {
        List<ConventionDTO> list = new ArrayList<>();
        for (Convention convention : conventions) {
            list.add(conventionToConventionDTO(convention));
        }
        return list;
    }
}
