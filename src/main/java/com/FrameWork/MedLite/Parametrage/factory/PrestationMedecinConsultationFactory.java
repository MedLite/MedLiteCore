/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.PrestationMedecinConsultation;
import com.FrameWork.MedLite.Parametrage.dto.PrestationMedecinConsultationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PrestationMedecinConsultationFactory {

    public static PrestationMedecinConsultation createPrestationMedecinConsultationByCode(int code) {
        PrestationMedecinConsultation domaine = new PrestationMedecinConsultation();
        domaine.setCode(code);
        return domaine;
    }

    public static PrestationMedecinConsultation medecinDTOToPrestationMedecinConsultation(PrestationMedecinConsultationDTO dto, PrestationMedecinConsultation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeMedecin(dto.getCodeMedecin());
            if (domaine.getCodeMedecin() != null) {
                domaine.setMedecin(MedecinFactory.createMedecinByCode(dto.getCodeMedecin()));
            }

            domaine.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (domaine.getCodeNatureAdmission() != null) {
                domaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            domaine.setCodePrestation(dto.getCodePrestation());
            if (domaine.getCodePrestation() != null) {
                domaine.setPrestation(PrestationFactory.createPrestationByCode(dto.getCodePrestation()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static PrestationMedecinConsultationDTO medecinToPrestationMedecinConsultationDTO(PrestationMedecinConsultation domaine) {

        if (domaine != null) {
            PrestationMedecinConsultationDTO dto = new PrestationMedecinConsultationDTO();
            dto.setCode(domaine.getCode());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setUserCreate(domaine.getUserCreate());

            dto.setMedecinDTO(MedecinFactory.medecinToMedecinDTO(domaine.getMedecin()));
            dto.setCodeMedecin(domaine.getCodeMedecin());

            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());

            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));
            dto.setCodePrestation(domaine.getCodePrestation());

            return dto;
        } else {
            return null;
        }
    }

    public static List<PrestationMedecinConsultationDTO> listPrestationMedecinConsultationToPrestationMedecinConsultationDTOs(List<PrestationMedecinConsultation> medecins) {
        List<PrestationMedecinConsultationDTO> list = new ArrayList<>();
        for (PrestationMedecinConsultation medecin : medecins) {
            list.add(medecinToPrestationMedecinConsultationDTO(medecin));
        }
        return list;
    }
}
