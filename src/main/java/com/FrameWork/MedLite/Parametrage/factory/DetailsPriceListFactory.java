/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.EditionPriceListParTypeIntervenant;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsPriceListFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static DetailsPriceListDTO DetailsPriceListToDetailsPriceListDTO(DetailsPriceList domaine) {
        if (domaine != null) {
            DetailsPriceListDTO dto = new DetailsPriceListDTO();
            dto.setCode(domaine.getCode());
            dto.setMontant(domaine.getMontant());
            dto.setMontantPere(domaine.getMontantPere());
            dto.setRemMaj(domaine.getRemMaj());
            dto.setRemMajValeur(domaine.getRemMaj());
            dto.setMntApresMaj(domaine.getMontant());

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

    ///// newww group deailspricelist par operation 
    public static DetailsPriceListDTO DetailsPriceListPrestationToDetailsPriceListPrestationDTONew(DetailsPriceList domaine) {
        if (domaine != null) {
            DetailsPriceListDTO dto = new DetailsPriceListDTO();
            dto.setCode(domaine.getCode());
            dto.setMontant(domaine.getMontant() == null ? BigDecimal.ZERO : domaine.getMontant()); // Handle null montant
            dto.setMntApresMaj(domaine.getMontant() == null ? BigDecimal.ZERO : domaine.getMontant()); // Handle null montant
            dto.setMontantPere(domaine.getMontantPere() == null ? BigDecimal.ZERO : domaine.getMontantPere()); // Handle null montantPere
            dto.setRemMaj(domaine.getRemMaj());
            dto.setRemMajValeur(domaine.getRemMaj());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));

            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

            dto.setCodePriceList(domaine.getCodePriceList());
            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));
            return dto;
        } else {
            return null;
        }
    }

//    public static List<DetailsPriceListDTO> groupByPrestationAndPriceList(List<DetailsPriceListDTO> dtos) {
//        if (dtos == null || dtos.isEmpty()) {
//            return List.of();
//        }
//
//        // Use a LinkedHashMap to preserve insertion order
//        Map<PrestationPriceListKey, DetailsPriceListDTO> grouped = dtos.stream()
//                .filter(Objects::nonNull) // Filter out null DTOs
//                .collect(Collectors.toMap(
//                        dto -> new PrestationPriceListKey(dto.getCodePrestation(), dto.getCodePriceList()), // Composite key
//                        dto -> dto, // Keep the first DTO for each key
//                        (dto1, dto2) -> {
//                            // Retain the first codeNatureAdmission and handle different codeTypeIntervenant
//                            if (!dto1.getCodeNatureAdmission().equals(dto2.getCodeNatureAdmission())) {
//                                // If codeNatureAdmission is different, keep the first one
//                                return dto1;
//                            }
//                            // If codeNatureAdmission is the same, sum montant and montantPere
//                            dto1.setMontant(dto1.getMontant().add(dto2.getMontant()));
//                            dto1.setMontantPere(dto1.getMontantPere().add(dto2.getMontantPere()));
//                            return dto1;
//                        },
//                        LinkedHashMap::new // Preserve insertion order
//                ));
//
//        return new ArrayList<>(grouped.values());
//    }

    public static List<DetailsPriceListDTO> createDTOs(List<DetailsPriceList> domainObjects) {
        List<DetailsPriceListDTO> dtos = domainObjects.stream()
                .map(DetailsPriceListFactory::DetailsPriceListPrestationToDetailsPriceListPrestationDTONew)
                .filter(Objects::nonNull) // Filter out null DTOs
                .collect(Collectors.toList());

        return groupByPrestationAndPriceListNew(dtos);
    }

//    private static class PrestationPriceListKey {
//
//        private final Integer codePrestation;
//        private final Integer codePriceList;
//
//        public PrestationPriceListKey(Integer codePrestation, Integer codePriceList) {
//            this.codePrestation = codePrestation;
//            this.codePriceList = codePriceList;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//            PrestationPriceListKey that = (PrestationPriceListKey) o;
//            return Objects.equals(codePrestation, that.codePrestation)
//                    && Objects.equals(codePriceList, that.codePriceList);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(codePrestation, codePriceList);
//        }
//    }

    
    
    
    
    ////// newwww 
    
    public static List<DetailsPriceListDTO> groupByPrestationAndPriceListNew(List<DetailsPriceListDTO> dtos) {
    if (dtos == null || dtos.isEmpty()) {
        return List.of();
    }

    Map<PrestationNatureAdmissionPriceListKey, DetailsPriceListDTO> grouped = dtos.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(
                    dto -> new PrestationNatureAdmissionPriceListKey(dto.getCodePrestation(), dto.getCodeNatureAdmission(), dto.getCodePriceList()),
                    dto -> dto,
                    (dto1, dto2) -> {
                        // Sum montant for same Prestation, NatureAdmission, and PriceList regardless of codeTypeIntervenant
                        dto1.setMontant(dto1.getMontant().add(dto2.getMontant()));   
                        dto1.setMntApresMaj(dto1.getMontant()); 
                        dto1.setMontantPere(dto1.getMontantPere().add(dto2.getMontantPere()));
                        return dto1;
                    },
                    LinkedHashMap::new
            ));

    return new ArrayList<>(grouped.values());
}

private static class PrestationNatureAdmissionPriceListKey {
    private final Integer codePrestation;
    private final Integer codeNatureAdmission;
    private final Integer codePriceList;

    public PrestationNatureAdmissionPriceListKey(Integer codePrestation, Integer codeNatureAdmission, Integer codePriceList) {
        this.codePrestation = codePrestation;
        this.codeNatureAdmission = codeNatureAdmission;
        this.codePriceList = codePriceList;
    }

    // equals and hashCode methods (similar to PrestationPriceListKey)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrestationNatureAdmissionPriceListKey that = (PrestationNatureAdmissionPriceListKey) o;
        return Objects.equals(codePrestation, that.codePrestation) &&
                Objects.equals(codeNatureAdmission, that.codeNatureAdmission) &&
                Objects.equals(codePriceList, that.codePriceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePrestation, codeNatureAdmission, codePriceList);
    }
}

    
    
    public static List<EditionPriceListParTypeIntervenant> listDetailsPriceListTOEditionPriceListDetailsParTypeIntervenant(List<DetailsPriceList> detailsPriceList) {
        if (detailsPriceList == null) {
            return null;
        }
        List<EditionPriceListParTypeIntervenant> editionPriceListDetailsParTypeIntervenant = new ArrayList<>();
        for (DetailsPriceList detail : detailsPriceList) {

            EditionPriceListParTypeIntervenant det = new EditionPriceListParTypeIntervenant();
            det.setCode_Saisie(detail.getPrestation().getCodeSaisie());
            det.setCout_Revient(detail.getPrestation().getPrixPrestation());
            det.setPrix(detail.getMontant().setScale(2, RoundingMode.HALF_UP));
            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                det.setDesignation_Ar(detail.getTypeIntervenant().getDesignationAr());
                det.setDesignation_famille_prestation(detail.getPrestation().getFamillePrestation().getDesignationAr());
                det.setDesignation_sous_famille_prestation(detail.getPrestation().getSousFamillePrestation().getDesignationAr());
                det.setDesignation_nature_admission(detail.getNatureAdmission().getDesignationAr());
                det.setDesignation_prestation(detail.getPrestation().getDesignationAr());
                det.setDesignationFamilleFacturation(detail.getPrestation().getFamilleFacturation().getDesignationAr());

//                det.setDesignation_type_prestation(detail.getPrestation().getFamillePrestation().getDesignationAr());
            } else {
                det.setDesignation_Ar(detail.getTypeIntervenant().getDesignationLt());
                det.setDesignation_famille_prestation(detail.getPrestation().getFamillePrestation().getDesignationLt());
                det.setDesignation_sous_famille_prestation(detail.getPrestation().getSousFamillePrestation().getDesignationLt());
                det.setDesignation_nature_admission(detail.getNatureAdmission().getDesignationLt());
                det.setDesignation_prestation(detail.getPrestation().getDesignationLt());
                det.setDesignationFamilleFacturation(detail.getPrestation().getFamilleFacturation().getDesignationLt());

            }

            editionPriceListDetailsParTypeIntervenant.add(det);
        }

        return editionPriceListDetailsParTypeIntervenant;
    }

}
