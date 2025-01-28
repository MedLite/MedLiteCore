/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            dto.setMontantPere(domaine.getMontantPere());
            dto.setRemMaj(domaine.getRemMaj());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());

          
            dto.setCodeOperation(domaine.getCodeOperation());
            dto.setOperationDTO(OperationFactory.operationToOperationDTO(domaine.getOperation()));

            dto.setCodePriceList(domaine.getCodePriceList());
            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));

            
             dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
            
            
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
            if (domaine.getCodePriceList()!= null) {
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
            if (domaine.getCodePriceList()!= null) {
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

}
