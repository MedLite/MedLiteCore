/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.BlocOperation;
import com.FrameWork.MedLite.Parametrage.dto.BlocOperationDTO;
import java.util.ArrayList;
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
public class BlocOperationFactory {
      static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }
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
           if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }
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
