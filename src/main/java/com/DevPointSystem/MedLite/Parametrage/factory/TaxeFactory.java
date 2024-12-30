/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.Taxe;
import com.DevPointSystem.MedLite.Parametrage.dto.TaxeDTO;
import com.DevPointSystem.MedLite.web.Util.Helper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TaxeFactory {
    
    public static Taxe createTaxeByCode(int code) {
        Taxe domaine = new Taxe();
        domaine.setCode(code);
        return domaine;
    }

    public static Taxe taxeDTOToTaxe(TaxeDTO dto, Taxe domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());    
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setDesignationAr(dto.getDesignationAr()); 
            domaine.setDesignationLt(dto.getDesignationLt());     
            return domaine;
        } else {
            return null;
        }
    }

    public static TaxeDTO taxeToTaxeDTO(Taxe domaine) {

        if (domaine != null) {
            TaxeDTO dto = new TaxeDTO();
            dto.setCode(domaine.getCode());            
            dto.setCodeSaisie(domaine.getCodeSaisie());    
            dto.setDateCreate(domaine.getDateCreate());   


            dto.setDesignationAr(domaine.getDesignationAr());  
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setUserCreate(domaine.getUserCreate());  
 

            return dto;
        } else {
            return null;
        }
    }

    public static List<TaxeDTO> listTaxeToTaxeDTOs(List<Taxe> taxes) {
        List<TaxeDTO> list = new ArrayList<>();
        for (Taxe taxe : taxes) {
            list.add(taxeToTaxeDTO(taxe));
        }
        return list;
    }
}
