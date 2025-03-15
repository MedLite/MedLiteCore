/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.EtatPaiement;
import com.FrameWork.MedLite.Parametrage.domaine.EtatPaiement;
import com.FrameWork.MedLite.Parametrage.dto.EtatPaiementDTO;
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
public class EtatPaiementFactory {
    
 static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static EtatPaiement createEtatPaiementByCode(int code) {
        EtatPaiement domaine = new EtatPaiement();
        domaine.setCode(code);
        return domaine;
    }

    public static EtatPaiement etatPaiemenetDTOToEtatPaiement(EtatPaiementDTO Dto, EtatPaiement domaine) {
        if (Dto != null) {
            domaine.setCode(Dto.getCode());
         
            domaine.setDesignationAr(Dto.getDesignationAr());    
            domaine.setDesignationLt(Dto.getDesignationLt());

             

            return domaine;
        } else {
            return null;
        }
    }

    public static EtatPaiementDTO etatPaiemenetToEtatPaiementDTO(EtatPaiement domaine) {

        if (domaine != null) {
            EtatPaiementDTO dTO = new EtatPaiementDTO();
            dTO.setCode(domaine.getCode());
          
            dTO.setDesignationAr(domaine.getDesignationAr());           
            dTO.setDesignationLt(domaine.getDesignationLt()); 
            
 
            
            return dTO;
        } else {
            return null;
        }
    }

    public static List<EtatPaiementDTO> listEtatPaiementToEtatPaiementDTOs(List<EtatPaiement> ds) {
        List<EtatPaiementDTO> list = new ArrayList<>();
        for (EtatPaiement approuver : ds) {
            list.add(etatPaiemenetToEtatPaiementDTO(approuver));
        }
        return list;
    }

    public static Collection<EtatPaiementDTO> listEtatPaiementToEtatPaiementDTOsCollection(Collection<EtatPaiement> collection) {
        List<EtatPaiementDTO> dTOs = new ArrayList<>();
        collection.forEach(x -> {
            dTOs.add(etatPaiemenetToEtatPaiementDTO(x));
        });
        return dTOs;

    }
}
