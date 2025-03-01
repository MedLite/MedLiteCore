/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.factory;

import com.FrameWork.MedLite.Parametrage.factory.CabinetFactory;
import com.FrameWork.MedLite.Parametrage.factory.MedecinFactory;
import com.FrameWork.MedLite.Reception.domaine.PlanningCabinet;
import com.FrameWork.MedLite.Reception.dto.PlanningCabinetDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PlanningCabinetFactory {

    public static PlanningCabinet createPlanningCabinetByCode(int code) {
        PlanningCabinet domaine = new PlanningCabinet();
        domaine.setCode(code);
        return domaine;
    }

    public static PlanningCabinet planningCabinetDTOToPlanningCabinet(PlanningCabinetDTO dto, PlanningCabinet domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDateExiste(dto.getDateExiste());
            domaine.setNbrePlaceDispo(dto.getNbrePlaceDispo());
            domaine.setNbrePlaceUtiliser(dto.getNbrePlaceUtiliser());

            domaine.setCodeCabinet(dto.getCodeCabinet());
            if (domaine.getCodeCabinet() != null) {
                domaine.setCabinet(CabinetFactory.createCabinetByCode(dto.getCodeCabinet()));
            }

            domaine.setCodeMedecin(dto.getCodeMedecin());
            if (domaine.getCodeMedecin() != null) {
                domaine.setMedecin(MedecinFactory.createMedecinByCode(dto.getCodeMedecin()));
            }

            domaine.setActif(dto.isActif());

            return domaine;
        } else {
            return null;
        }
    }

    public static PlanningCabinetDTO planningCabinetToPlanningCabinetDTO(PlanningCabinet domaine) {

        if (domaine != null) {
            PlanningCabinetDTO dto = new PlanningCabinetDTO();
            dto.setCode(domaine.getCode());

            dto.setDateExiste(domaine.getDateExiste());

            dto.setMedecinDTO(MedecinFactory.medecinToMedecinDTO(domaine.getMedecin()));
            dto.setCodeMedecin(domaine.getCodeMedecin());

            dto.setCabinetDTO(CabinetFactory.cabinetToCabinetDTO(domaine.getCabinet()));
            dto.setCodeCabinet(domaine.getCodeCabinet());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setActif(domaine.isActif());
            dto.setNbrePlaceDispo(domaine.getNbrePlaceDispo());
            dto.setNbrePlaceUtiliser(domaine.getNbrePlaceUtiliser());

            return dto;
        } else {
            return null;
        }
    }

    public static List<PlanningCabinetDTO> listPlanningCabinetToPlanningCabinetDTOs(List<PlanningCabinet> planningCabinets) {
        List<PlanningCabinetDTO> list = new ArrayList<>();
        for (PlanningCabinet planningCabinet : planningCabinets) {
            list.add(planningCabinetToPlanningCabinetDTO(planningCabinet));
        }
        return list;
    }
    
     public static Collection<PlanningCabinetDTO> collectionPlanningCabinetToPlanningCabinetDTOs(Collection<PlanningCabinet> planningCabinets) {
        Collection<PlanningCabinetDTO> collection = new ArrayList<>();
        for (PlanningCabinet planningCabinet : planningCabinets) {
            collection.add(planningCabinetToPlanningCabinetDTO(planningCabinet));
        }
        return collection;
    }
}
