/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.BlocOperation;
import com.DevPointSystem.MedLite.Parametrage.dto.BlocOperationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class BlocOperationFactory {
       public static BlocOperation createBlocOperationByCode(int code) {
        BlocOperation domaine = new BlocOperation();
        domaine.setCode(code);
        return domaine;
    }

    public static BlocOperation blocOperationDTOToBlocOperation(BlocOperationDTO dto, BlocOperation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());    
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());    


            return domaine;
        } else {
            return null;
        }
    }
    
    
 

    public static BlocOperationDTO blocOperationToBlocOperationDTO(BlocOperation domaine) {

        if (domaine != null) {
            BlocOperationDTO dto = new BlocOperationDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());    


            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<BlocOperationDTO> listBlocOperationToBlocOperationDTOs(List<BlocOperation> blocOperations) {
        List<BlocOperationDTO> list = new ArrayList<>();
        for (BlocOperation blocOperation : blocOperations) {
            list.add(blocOperationToBlocOperationDTO(blocOperation));
        }
        return list;
    }
}
