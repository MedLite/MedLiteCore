/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationConsultationPK;
import com.DevPointSystem.MedLite.Parametrage.domaine.PrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationConsultationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationConsultationDTO;
import com.DevPointSystem.MedLite.web.Util.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

 
/**
 *
 * @author Administrator
 */
@Component
public class PrestationConsultationFactory {

 

    public static PrestationConsultation createPrestationConsultationByCode(int code) {
        PrestationConsultation domaine = new PrestationConsultation();
        domaine.setCode(code);
        return domaine;
    }

    public static PrestationConsultation prestationConsultationDTOToPrestationConsultation(PrestationConsultationDTO dto, PrestationConsultation domaine) {
        if (dto != null) {
 
            domaine.setCode(dto.getCode());  
            domaine.setMontant(dto.getMontant());

            domaine.setCodeMedecin(dto.getCodeMedecin());
            if (domaine.getCodeMedecin() != null) {
                domaine.setMedecin(MedecinFactory.createMedecinByCode(dto.getCodeMedecin()));
            }

            domaine.setCodePrestationConsultation(dto.getCodePrestationConsultation());
            if (domaine.getCodePrestationConsultation() != null) {
                domaine.setPrestationConsultation(PrestationFactory.createPrestationByCode(dto.getCodePrestationConsultation()));
            }

            if (dto.getDetailsPrestationConsultationDTOs() == null || dto.getDetailsPrestationConsultationDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsRequired");
            }
            Collection<DetailsPrestationConsultation> detailsCollections = new ArrayList<>();
            dto.getDetailsPrestationConsultationDTOs().forEach(x -> {
                DetailsPrestationConsultation detailsPrestationConsultation = new DetailsPrestationConsultation();

                DetailsPrestationConsultationPK detailsPK = new DetailsPrestationConsultationPK();
                Preconditions.checkBusinessLogique(x.getCodeTypeIntervenant() != null, "error.TypeIntervenantRequired");
                detailsPK.setCodeTypeIntervenant(x.getCodeTypeIntervenant());
                Preconditions.checkBusinessLogique(x.getCodePrestation() != null, "error.PrestationRequired");
                detailsPK.setCodePrestationConsultation(x.getCodePrestation());
                detailsPrestationConsultation.setDetailsPrestationConsultationPK(detailsPK);
                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsPrestationConsultation.setMontant(x.getMontant());

                detailsPrestationConsultation.setDateCreate(domaine.getDateCreate());
                detailsPrestationConsultation.setUsercreate(domaine.getUserCreate());
                detailsPrestationConsultation.setCodePrestationConsultationFK(domaine);
                detailsCollections.add(detailsPrestationConsultation);
            });

            if (domaine.getDetailsPrestationConsultations() != null) {
                domaine.getDetailsPrestationConsultations().clear();
                domaine.getDetailsPrestationConsultations().addAll(detailsCollections);
            } else {
                domaine.setDetailsPrestationConsultations(detailsCollections);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static PrestationConsultationDTO prestationConsultationToPrestationConsultationDTO(PrestationConsultation domaine) {

        if (domaine != null) {
            PrestationConsultationDTO dto = new PrestationConsultationDTO();
            dto.setCode(domaine.getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCodeMedecin(domaine.getCodeMedecin());
            dto.setMedecinDTO(MedecinFactory.medecinToMedecinDTO(domaine.getMedecin()));

            dto.setCodePrestationConsultation(domaine.getCodePrestationConsultation());
            dto.setPrestationConsultationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestationConsultation()));
            dto.setMontant(domaine.getMontant());

            if (domaine.getDetailsPrestationConsultations() != null) {
                Collection<DetailsPrestationConsultationDTO> detailsPrestationConsultationDTOs = new ArrayList<>();
                domaine.getDetailsPrestationConsultations().forEach(x -> {
                    DetailsPrestationConsultationDTO detailsDTO = new DetailsPrestationConsultationDTO();
                    detailsDTO = DetailsPrestationConsultationFactory.DetailsPrestationConsultationToDetailsPrestationConsultationDTOCollectionForUpdate(x);
                    detailsPrestationConsultationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsPrestationConsultationDTOs() != null) {
                    dto.getDetailsPrestationConsultationDTOs().clear();
                    dto.getDetailsPrestationConsultationDTOs().addAll(detailsPrestationConsultationDTOs);
                } else {
                    dto.setDetailsPrestationConsultationDTOs(detailsPrestationConsultationDTOs);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static List<PrestationConsultationDTO> listPrestationConsultationToPrestationConsultationDTOs(List<PrestationConsultation> prestationConsultations) {
        List<PrestationConsultationDTO> list = new ArrayList<>();
        for (PrestationConsultation prestationConsultation : prestationConsultations) {
            list.add(prestationConsultationToPrestationConsultationDTO(prestationConsultation));
        }
        return list;
    }
}
