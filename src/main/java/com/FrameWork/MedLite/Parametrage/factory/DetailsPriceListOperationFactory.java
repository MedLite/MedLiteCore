/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsPriceListOperationFactory {

    public static DetailsPriceListOperationDTO DetailsPriceListOperationToDetailsPriceListOperationDTO(DetailsPriceListOperation domaine) {
        if (domaine != null) {
            DetailsPriceListOperationDTO dto = new DetailsPriceListOperationDTO();
            dto.setCode(domaine.getCode());
            dto.setMontant(domaine.getMontant());
            dto.setMntApresMaj(domaine.getMontant());

            dto.setMontantPere(domaine.getMontantPere());
            dto.setRemMaj(domaine.getRemMaj());
            dto.setRemMajValeur(domaine.getRemMaj());

            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setCodeOperation(domaine.getCodeOperation());
            dto.setOperationDTO(OperationFactory.operationToOperationDTO(domaine.getOperation()));

            dto.setCodePriceList(domaine.getCodePriceList());
            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));

//             dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
//            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
            return dto;
        } else {
            return null;
        }

    }

    public static List<DetailsPriceListOperationDTO> detailsPriceListOperationTodetailsPriceListOperationDTOs(List<DetailsPriceListOperation> detailsPriceListOperations) {
        List<DetailsPriceListOperationDTO> detailsPriceListDTOs = new ArrayList<>();
        for (DetailsPriceListOperation detailsPriceListOperation : detailsPriceListOperations) {
            detailsPriceListDTOs.add(DetailsPriceListOperationToDetailsPriceListOperationDTO(detailsPriceListOperation));
        }
        return detailsPriceListDTOs;
    }

    public static DetailsPriceListOperation DetailspriceListOperationDTOToDetailsPriceListOperation(DetailsPriceListOperationDTO dto, DetailsPriceListOperation domaine) {
        if (dto != null) {

            domaine.setCode(dto.getCode());
            domaine.setMontant(dto.getMontant());
            domaine.setMontantPere(dto.getMontantPere());
            domaine.setRemMaj(dto.getRemMaj());
            domaine.setUsercreate(dto.getUsercreate());
            domaine.setDateCreate(new Date());

            domaine.setCodeOperation(dto.getCodeOperation());
            if (domaine.getCodeOperation() != null) {
                domaine.setOperation(OperationFactory.createOperationByCode(dto.getCodeOperation()));
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

    public static DetailsPriceListOperation DetailspriceListOperationDTOToDetailsPriceOperationList(DetailsPriceListOperationDTO dto, DetailsPriceListOperation domaine) {
        if (dto != null) {

            domaine.setCode(dto.getCode());
            domaine.setMontant(dto.getMontant());
            domaine.setMontantPere(dto.getMontantPere());
            domaine.setRemMaj(dto.getRemMaj());
            domaine.setUsercreate(dto.getUsercreate());
            domaine.setDateCreate(new Date());

            domaine.setCodeOperation(dto.getCodeOperation());
            if (domaine.getCodeOperation() != null) {
                domaine.setOperation(OperationFactory.createOperationByCode(dto.getCodeOperation()));
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
    public static DetailsPriceListOperationDTO DetailsPriceListOperationToDetailsPriceListOperationDTONew(DetailsPriceListOperation domaine) {
        if (domaine != null) {
            DetailsPriceListOperationDTO dto = new DetailsPriceListOperationDTO();
            dto.setCode(domaine.getCode());
            dto.setMontant(domaine.getMontant() == null ? BigDecimal.ZERO : domaine.getMontant()); // Handle null montant
            dto.setMntApresMaj(domaine.getMontant() == null ? BigDecimal.ZERO : domaine.getMontant()); // Handle null montant
            dto.setMontantPere(domaine.getMontantPere() == null ? BigDecimal.ZERO : domaine.getMontantPere()); // Handle null montantPere
            dto.setRemMaj(domaine.getRemMaj());
            dto.setRemMajValeur(domaine.getRemMaj());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setCodeOperation(domaine.getCodeOperation());
            dto.setOperationDTO(OperationFactory.operationToOperationDTO(domaine.getOperation()));
            dto.setCodePriceList(domaine.getCodePriceList());
            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));
            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsPriceListOperationDTO> groupByOperationAndSum(List<DetailsPriceListOperationDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }

        Map<Integer, DetailsPriceListOperationDTO> grouped = dtos.stream()
                .filter(dto -> dto != null)
                .collect(Collectors.toMap(
                        DetailsPriceListOperationDTO::getCodeOperation,
                        dto -> dto,
                        (dto1, dto2) -> {
                            dto1.setMontant(dto1.getMontant().add(dto2.getMontant()));
                            dto1.setMontantPere(dto1.getMontantPere().add(dto2.getMontantPere()));
                            return dto1;
                        }
                ));

        return grouped.values().stream().collect(Collectors.toList());
    }

    public static List<DetailsPriceListOperationDTO> createDTOs(List<DetailsPriceListOperation> domainObjects) {
        List<DetailsPriceListOperationDTO> dtos = domainObjects.stream()
                .map(DetailsPriceListOperationFactory::DetailsPriceListOperationToDetailsPriceListOperationDTONew)
                .collect(Collectors.toList());

        return groupByOperationAndSum(dtos);
    }

}
