/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.dto.MedecinDTO; 
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class MedecinFactory {
    
 
    

    public static Medecin createMedecinByCode(int code) {
        Medecin domaine = new Medecin();
        domaine.setCode(code);
        return domaine;
    }

    public static Medecin medecinDTOToMedecin(MedecinDTO dto, Medecin domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setNomIntervAr(dto.getNomIntervAr());
            domaine.setNomIntervLt(dto.getNomIntervLt());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setActif(dto.isActif());  
            domaine.setAutoriseFrais(dto.isAutoriseFrais());  
            domaine.setAutorisConsultation(dto.isAutorisConsultation()); 
            domaine.setOpd(dto.isOpd());
            domaine.setEr(dto.isEr());



          
            domaine.setCodeSpecialiteMedecin(dto.getCodeSpecialiteMedecin());
            if (domaine.getCodeSpecialiteMedecin() != null) {
                domaine.setSpecialiteMedecin(SpecialiteMedecinFactory.createSpecialiteMedecinByCode(dto.getCodeSpecialiteMedecin()));
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

    public static MedecinDTO medecinToMedecinDTO(Medecin domaine) {

        if (domaine != null) {
            MedecinDTO dto = new MedecinDTO();
            dto.setCode(domaine.getCode());    
            dto.setAutoriseFrais(domaine.isAutoriseFrais());  
            dto.setAutorisConsultation(domaine.isAutorisConsultation());  
            dto.setOpd(domaine.isOpd());    
            dto.setEr(domaine.isEr());

            




            dto.setNomIntervAr(domaine.getNomIntervAr());
            dto.setNomIntervLt(domaine.getNomIntervLt());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
 

            dto.setSpecialiteMedecinDTO(SpecialiteMedecinFactory.specialiteMedecinToSpecialiteMedecinDTO(domaine.getSpecialiteMedecin()));
            dto.setCodeSpecialiteMedecin(domaine.getCodeSpecialiteMedecin());
            
            
         
       

            return dto;
        } else {
            return null;
        }
    }

    public static List<MedecinDTO> listMedecinToMedecinDTOs(List<Medecin> medecins) {
        List<MedecinDTO> list = new ArrayList<>();
        for (Medecin medecin : medecins) {
            list.add(medecinToMedecinDTO(medecin));
        }
        return list;
    }
}
