/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.EtatPatient;
import com.FrameWork.MedLite.Parametrage.dto.EtatPatientDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatPatientFactory {
    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static EtatPatient createEtatPatientByCode(int code) {
        EtatPatient domaine = new EtatPatient();
        domaine.setCode(code);
        return domaine;
    }

    public static EtatPatient etatPatientDTOToEtatPatient(EtatPatientDTO Dto, EtatPatient domaine) {
        if (Dto != null) {
            domaine.setCode(Dto.getCode());
         
            domaine.setDesignationAr(Dto.getDesignationAr());    
            domaine.setDesignationLt(Dto.getDesignationLt());

             

            return domaine;
        } else {
            return null;
        }
    }

    public static EtatPatientDTO etatPatientToEtatPatientDTO(EtatPatient domaine) {

        if (domaine != null) {
            EtatPatientDTO dTO = new EtatPatientDTO();
            dTO.setCode(domaine.getCode());
          
            dTO.setDesignationAr(domaine.getDesignationAr());           
            dTO.setDesignationLt(domaine.getDesignationLt()); 
            
 
            
            return dTO;
        } else {
            return null;
        }
    }

    public static List<EtatPatientDTO> listEtatPatientToEtatPatientDTOs(List<EtatPatient> ds) {
        List<EtatPatientDTO> list = new ArrayList<>();
        for (EtatPatient approuver : ds) {
            list.add(etatPatientToEtatPatientDTO(approuver));
        }
        return list;
    }

    public static Collection<EtatPatientDTO> listEtatPatientToEtatPatientDTOsCollection(Collection<EtatPatient> collection) {
        List<EtatPatientDTO> dTOs = new ArrayList<>();
        collection.forEach(x -> {
            dTOs.add(etatPatientToEtatPatientDTO(x));
        });
        return dTOs;

    }
}
