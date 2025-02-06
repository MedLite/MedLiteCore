/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouvertureOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureOperationDTO;  
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsListCouvertureOperationFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

     
    ///// newww group deailspricelist par operation 
    public static DetailsListCouvertureOperationDTO DetailsListCouvertureOperationToDetailsListCouvertureOperationDTONew(DetailsListCouvertureOperation domaine) {
        if (domaine != null) {
            DetailsListCouvertureOperationDTO dto = new DetailsListCouvertureOperationDTO();
            dto.setCode(domaine.getCode());
            dto.setMontantPatient(domaine.getMontantPatient()== null ? BigDecimal.ZERO : domaine.getMontantPatient()); // Handle null montant  
            dto.setMontantPEC(domaine.getMontantPEC()== null ? BigDecimal.ZERO : domaine.getMontantPEC()); // Handle null montant

            dto.setMntApresMaj(domaine.getMontantPEC().add(domaine.getMontantPatient())); // Handle null montant
            dto.setMontantPere(domaine.getMontantPere() == null ? BigDecimal.ZERO : domaine.getMontantPere()); // Handle null montantPere
            dto.setTauxCouverPec(domaine.getTauxCouverPec()); 
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setCodeOperation(domaine.getCodeOperation());
            dto.setOperationDTO(OperationFactory.operationToOperationDTO(domaine.getOperation()));
       
            
            dto.setCodeListCouverture(domaine.getCodeListCouverture());
            dto.setListCouvertureDTO(ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine.getListCouverture()));
            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsListCouvertureOperationDTO> groupByAndSum(List<DetailsListCouvertureOperationDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        Map<Integer, DetailsListCouvertureOperationDTO> grouped = dtos.stream()
                .filter(dto -> dto != null)
                .collect(Collectors.toMap(
                        DetailsListCouvertureOperationDTO::getCode,
                        dto -> dto,
                        (dto1, dto2) -> {
                            dto1.setMontantPatient(dto1.getMontantPatient().add(dto2.getMontantPatient())); 
                            dto1.setMontantPEC(dto1.getMontantPEC().add(dto2.getMontantPEC())); 
                            dto1.setMontantPere(dto1.getMontantPere().add(dto2.getMontantPere()));
                            return dto1;
                        }
                ));

        return grouped.values().stream().collect(Collectors.toList());
    }

    public static List<DetailsListCouvertureOperationDTO> createDTOs(List<DetailsListCouvertureOperation> domainObjects) {
        List<DetailsListCouvertureOperationDTO> dtos = domainObjects.stream()
                .map(DetailsListCouvertureOperationFactory::DetailsListCouvertureOperationToDetailsListCouvertureOperationDTONew)
                .collect(Collectors.toList());

        return groupByAndSum(dtos);
    }

    
}
