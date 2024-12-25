/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamilleOperation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamilleOperationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FamilleOperationFactory {
      public static FamilleOperation createFamilleOperationByCode(int code) {
        FamilleOperation domaine = new FamilleOperation();
        domaine.setCode(code);
        return domaine;
    }

    public static FamilleOperation familleOperationDTOToFamilleOperation(FamilleOperationDTO dto, FamilleOperation domaine) {
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
    
    
 

    public static FamilleOperationDTO familleOperationToFamilleOperationDTO(FamilleOperation domaine) {

        if (domaine != null) {
            FamilleOperationDTO dto = new FamilleOperationDTO();
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
    
    
    public static List<FamilleOperationDTO> listFamilleOperationToFamilleOperationDTOs(List<FamilleOperation> familleOperations) {
        List<FamilleOperationDTO> list = new ArrayList<>();
        for (FamilleOperation familleOperation : familleOperations) {
            list.add(familleOperationToFamilleOperationDTO(familleOperation));
        }
        return list;
    }
}
