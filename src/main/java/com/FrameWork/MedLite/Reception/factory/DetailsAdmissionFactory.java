/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.factory;

import com.FrameWork.MedLite.Parametrage.domaine.Fournisseur;
import com.FrameWork.MedLite.Parametrage.dto.FournisseurDTO;
import static com.FrameWork.MedLite.Parametrage.factory.FournisseurFactory.fournisseurToFournisseurDTO;
import com.FrameWork.MedLite.Parametrage.factory.MedecinFactory;
import com.FrameWork.MedLite.Parametrage.factory.MotifAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.DetailsAdmissionDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsAdmissionFactory {

    public static DetailsAdmissionDTO DetailsAdmissionToDetailsAdmissionDTONew(DetailsAdmission domaine) {
        if (domaine != null) {
            DetailsAdmissionDTO dto = new DetailsAdmissionDTO();
            dto.setCode(domaine.getCode());
            dto.setMontant(domaine.getMontant() == null ? BigDecimal.ZERO : domaine.getMontant()); // Handle null montant    
            dto.setMontantPEC(domaine.getMontantPEC() == null ? BigDecimal.ZERO : domaine.getMontantPEC()); // Handle null montant

            dto.setMontant(domaine.getMontant() == null ? BigDecimal.ZERO : domaine.getMontantPatient()); // Handle null montant

            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setEtatPaiement(domaine.isEtatPaiement());

            dto.setCodePrestation(domaine.getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));

            dto.setCodeAdmission(domaine.getCodeAdmission());
            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));

            return dto;
        } else {
            return null;
        }
    }

    public static DetailsAdmission detailsAdmissionDTOToDetailsAdmission(DetailsAdmissionDTO dto, DetailsAdmission domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setEtatPaiement(dto.isEtatPaiement());
            
            domaine.setMontant(dto.getMontant());
            
            
            domaine.setMontantPEC(dto.getMontantPEC());

            domaine.setMontantPatient(dto.getMontantPatient());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
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
    
     public static List<DetailsAdmissionDTO> listDetailsAdmissionToDetailsADmissionDTOs(List<DetailsAdmission> detailsAdmissions) {
        List<DetailsAdmissionDTO> list = new ArrayList<>();
        for (DetailsAdmission detailsAdmission : detailsAdmissions) {
            list.add(DetailsAdmissionToDetailsAdmissionDTONew(detailsAdmission));
        }
        return list;
    }
}
