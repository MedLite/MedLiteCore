/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.TypeOperation;
import com.FrameWork.MedLite.Parametrage.dto.TypeOperationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypeOperationFactory {

    public static TypeOperation createTypeOperationByCode(int code) {
        TypeOperation domaine = new TypeOperation();
        domaine.setCode(code);
        return domaine;
    }

    public static TypeOperation typeOperationDTOToTypeOperation(TypeOperationDTO dto, TypeOperation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setActif(dto.isActif());
        
            
           
            return domaine;
        } else {
            return null;
        }
    }

    public static TypeOperationDTO typeOperationToTypeOperationDTO(TypeOperation domaine) {

        if (domaine != null) {
            TypeOperationDTO dto = new TypeOperationDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
         
            return dto;
        } else {
            return null;
        }
    }

    public static List<TypeOperationDTO> listTypeOperationToTypeOperationDTOs(List<TypeOperation> typeOperations) {
        List<TypeOperationDTO> list = new ArrayList<>();
        for (TypeOperation typeOperation : typeOperations) {
            list.add(typeOperationToTypeOperationDTO(typeOperation));
        }
        return list;
    }
}
