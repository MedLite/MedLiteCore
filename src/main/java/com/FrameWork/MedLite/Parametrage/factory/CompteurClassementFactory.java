/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.CompteurClassement;
import com.FrameWork.MedLite.Parametrage.dto.CompteurClassementDTO;
import com.FrameWork.MedLite.Parametrage.dto.CompteurDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CompteurClassementFactory {
    

    public static CompteurClassement createCompteurByCode(int code) {
        CompteurClassement domaine = new CompteurClassement();
        domaine.setCode(code);
        return domaine;
    }

    public static CompteurClassement compteurDTOToCompteur(CompteurClassementDTO Dto, CompteurClassement domaine) {
        if (Dto != null) {
            domaine.setCode(Dto.getCode());
 
            domaine.setCompteur(Dto.getCompteur());
            domaine.setPrefixe(Dto.getPrefixe());
            domaine.setSuffixe(Dto.getSuffixe());      
            domaine.setNiveau(Dto.getNiveau()); 

 
 
            return domaine;
        } else {
            return null;
        }
    }

    public static CompteurClassementDTO compteurToCompteurDTO(CompteurClassement domaine) {

        if (domaine != null) {
            CompteurClassementDTO dTO = new CompteurClassementDTO();
            dTO.setCode(domaine.getCode());
            
            dTO.setCompteur(domaine.getCompteur());
            dTO.setPrefixe(domaine.getPrefixe());
            dTO.setSuffixe(domaine.getSuffixe());        

            return dTO;
        } else {
            return null;
        }
    }

    public static List<CompteurClassementDTO> listCompteurToCompteurDTOs(List<CompteurClassement> compteurs) {
        List<CompteurClassementDTO> list = new ArrayList<>();
        for (CompteurClassement compteur : compteurs) {
            list.add(compteurToCompteurDTO(compteur));
        }
        return list;
    }
}
