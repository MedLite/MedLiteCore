/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.PriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.PriceListOperationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PriceListOperationFactory {
    public static PriceListOperation createPriceListOperationByCode(int code) {
        PriceListOperation domaine = new PriceListOperation();
        domaine.setCode(code);
        return domaine;
    }

    public static PriceListOperation priceListOperationDTOToPriceListOperation(PriceListOperationDTO dto, PriceListOperation domaine) {
        if (dto != null) {

       
            domaine.setCode(dto.getCode());  
            domaine.setCodeSaisie(dto.getCodeSaisie()); 
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setActif(dto.isActif()); 
            domaine.setCash(dto.isCash());


            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
            }
 
            return domaine;
        } else {
            return null;
        }
    }

    public static PriceListOperationDTO priceListOperationToPriceListOperationDTO(PriceListOperation domaine) {

        if (domaine != null) {
            PriceListOperationDTO dto = new PriceListOperationDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());  
            dto.setCash(domaine.isCash());


            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));
 
            return dto;
        } else {
            return null;
        }
    }

    public static List<PriceListOperationDTO> listPriceListOpeartionToPriceListOpeartionDTOs(List<PriceListOperation> priceListOperations) {
        List<PriceListOperationDTO> list = new ArrayList<>();
        for (PriceListOperation priceListOperation : priceListOperations) {
            list.add(priceListOperationToPriceListOperationDTO(priceListOperation));
        }
        return list;
    }
}
