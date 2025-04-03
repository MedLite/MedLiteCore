/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.factory;

import com.FrameWork.MedLite.DMI.domaine.Icd10;
import com.FrameWork.MedLite.DMI.dto.Icd10DTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Icd10Factory {
    
    public static Icd10DTO icd10Toicd10DTO(Icd10 domaine) {

        if (domaine != null) {
            Icd10DTO dto = new Icd10DTO();
            dto.setId(domaine.getId()); 
            dto.setCode(domaine.getCode());
            dto.setShortdescription(domaine.getShortdescription());       
            dto.setLongdescription(domaine.getLongdescription()); 


            return dto;
        } else {
            return null;
        }
    }
    
     public static List<Icd10DTO> listICD10ToICD10DTOs(List<Icd10> icd10s) {
        List<Icd10DTO> list = new ArrayList<>();
        for (Icd10 icd10 : icd10s) {
            list.add(icd10Toicd10DTO(icd10));
        }
        return list;
    }
    
}
