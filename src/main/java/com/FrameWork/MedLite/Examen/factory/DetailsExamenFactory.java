/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.factory;

import com.FrameWork.MedLite.Examen.domaine.DetailsExamen;
import com.FrameWork.MedLite.Examen.dto.DetailsExamenDTO;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import com.FrameWork.MedLite.Reception.factory.PatientFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsExamenFactory {
     public static DetailsExamenDTO DetailsExamenToDetailsExamenDTOCollectionForUpdate(DetailsExamen domaine) {
        if (domaine != null) {
            DetailsExamenDTO dto = new DetailsExamenDTO();

            dto.setCodeExamen(domaine.getDetailsExamenPK().getCodeExamen());
            dto.setCodePrestation(domaine.getPrestation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUserCreate()); 
            
            dto.setCodePrestation(domaine.getDetailsExamenPK().getCodePrestation());
            dto.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(domaine.getPrestation()));
            dto.setDesignationArPrestation(domaine.getPrestation().getDesignationAr());
            dto.setDesignationLtPrestation(domaine.getPrestation().getDesignationLt()); 
            dto.setCodeSaisiePrestation(domaine.getPrestation().getCodeSaisie());


            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));

          

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsExamenDTO> detailsExamenTodetailsExamenDTOCollections(Collection<DetailsExamen> detailsExamens) {
        Collection<DetailsExamenDTO> detailsExamenDTOs = new ArrayList<>();
        for (DetailsExamen detailsExamen : detailsExamens) {
            detailsExamenDTOs.add(DetailsExamenToDetailsExamenDTOCollectionForUpdate(detailsExamen));
        }
        return detailsExamenDTOs;
    }

    public static List<DetailsExamenDTO> detailsExamenTodetailsExamenDTOLists(List<DetailsExamen> detailsExamens) {
        List<DetailsExamenDTO> detailsExamenDTOs = new ArrayList<>();
        for (DetailsExamen detailsExamen : detailsExamens) {
            detailsExamenDTOs.add(DetailsExamenToDetailsExamenDTOCollectionForUpdate(detailsExamen));
        }
        return detailsExamenDTOs;
    }

    public static DetailsExamenDTO DetailsExamenToDetailsExamenDTOCollection(DetailsExamen domaine) {
        if (domaine != null) {
            DetailsExamenDTO dto = new DetailsExamenDTO();
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUserCreate()); 
      

            return dto;
        } else {
            return null;
        }

    }

    public static DetailsExamen DetailsExamenDTOToDetailsExamen(DetailsExamenDTO dto) {
        if (dto != null) {

            DetailsExamen detailsExamen = new DetailsExamen(); 
            detailsExamen.setDateCreate(dto.getDateCreate()); // Map 'dateCreate' field
            detailsExamen.setUserCreate(dto.getUsercreate()); // Map 'usercreate' field
 
            detailsExamen.setCodePatient(dto.getCodePatient());
            if (detailsExamen.getCodePatient() != null) {
                detailsExamen.setPatient(PatientFactory.createPatientByCode(dto.getCodePatient()));
            }
            
             detailsExamen.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (detailsExamen.getCodeNatureAdmission() != null) {
                detailsExamen.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            
            
             detailsExamen.setCodePrestation(dto.getCodePrestation());
            if (detailsExamen.getCodePrestation() != null) {
                detailsExamen.setPrestation(PrestationFactory.createPrestationByCode(dto.getCodePrestation()));
            }
            
              detailsExamen.setCodeAdmission(dto.getCodeAdmission());
            if (detailsExamen.getCodeAdmission() != null) {
                detailsExamen.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }       
 
            
            return detailsExamen;
        } else {
            return null;
        }
    }
}
