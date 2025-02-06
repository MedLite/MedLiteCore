/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO; 
import static com.FrameWork.MedLite.Parametrage.factory.DetailsListCouvertureFactory.LANGUAGE_SEC;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
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

     
    ///// newww group deailspricelist par operation 
    public static DetailsListCouvertureDTO DetailsListCouvertureToDetailsListCouvertureDTONew(DetailsListCouverture domaine) {
        if (domaine != null) {
            DetailsListCouvertureDTO dto = new DetailsListCouvertureDTO();
            dto.setCode(domaine.getCode());
            dto.setMontantPatient(domaine.getMontantPatient()== null ? BigDecimal.ZERO : domaine.getMontantPatient()); // Handle null montant  
            dto.setMontantPEC(domaine.getMontantPEC()== null ? BigDecimal.ZERO : domaine.getMontantPEC()); // Handle null montant

            dto.setMntApresMaj(domaine.getMontantPEC().add(domaine.getMontantPatient())); // Handle null montant
            dto.setMontantPere(domaine.getMontantPere() == null ? BigDecimal.ZERO : domaine.getMontantPere()); // Handle null montantPere
            dto.setTauxCouverPec(domaine.getTauxCouverPec()); 
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));
            
              dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
            
            
            dto.setCodeListCouverture(domaine.getCodeListCouverture());
            dto.setListCouvertureDTO(ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine.getListCouverture()));
            return dto;
        } else {
            return null;
        }
    }

   public static List<DetailsListCouvertureDTO> groupByPrestationAndPriceList(List<DetailsListCouvertureDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        // Use a LinkedHashMap to preserve insertion order
        Map<PrestationListCouverturetKey, DetailsListCouvertureDTO> grouped = dtos.stream()
                .filter(Objects::nonNull) // Filter out null DTOs
                .collect(Collectors.toMap(
                        dto -> new PrestationListCouverturetKey(dto.getCodePrestation(), dto.getCodeListCouverture()), // Composite key
                        dto -> dto, // Keep the first DTO for each key
                        (dto1, dto2) -> {
                            // Retain the first codeNatureAdmission and handle different codeTypeIntervenant
                            if (!dto1.getCodeNatureAdmission().equals(dto2.getCodeNatureAdmission())) {
                                // If codeNatureAdmission is different, keep the first one
                                return dto1;
                            }
                            // If codeNatureAdmission is the same, sum montant and montantPere
                            dto1.setMontantPEC(dto1.getMontantPEC());   
                            dto1.setMontantPatient(dto1.getMontantPatient());

                            dto1.setMontantPere(dto1.getMontantPere().add(dto2.getMontantPere()));
                            return dto1;
                        },
                        LinkedHashMap::new // Preserve insertion order
                ));

        return new ArrayList<>(grouped.values());
    }
    
    
    public static List<DetailsListCouvertureDTO> createDTOs(List<DetailsListCouverture> domainObjects) {
        List<DetailsListCouvertureDTO> dtos = domainObjects.stream()
                .map(DetailsListCouvertureFactory::DetailsListCouvertureToDetailsListCouvertureDTONew)
                .filter(Objects::nonNull) // Filter out null DTOs
                .collect(Collectors.toList());

        return groupByPrestationAndPriceList(dtos);
    }

    private static class PrestationListCouverturetKey {

        private final Integer codePrestation;
        private final Integer codeListCouverture;

        public PrestationListCouverturetKey(Integer codePrestation, Integer codeListCouverture) {
            this.codePrestation = codePrestation;
            this.codeListCouverture = codeListCouverture;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            PrestationListCouverturetKey that = (PrestationListCouverturetKey) o;
            return Objects.equals(codePrestation, that.codePrestation)
                    && Objects.equals(codeListCouverture, that.codeListCouverture);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codePrestation, codeListCouverture);
        }
    }

    
}
