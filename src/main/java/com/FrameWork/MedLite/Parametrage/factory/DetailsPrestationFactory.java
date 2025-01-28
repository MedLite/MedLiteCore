/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperationPK;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestationPK;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.FrameWork.MedLite.web.Util.Helper;
import com.FrameWork.MedLite.web.Util.Preconditions;
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
public class DetailsPrestationFactory {

    public static DetailsPrestationDTO DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(DetailsPrestation domaine) {
        if (domaine != null) {
            DetailsPrestationDTO dto = new DetailsPrestationDTO();

            dto.setCodePrestation(domaine.getDetailsPrestationPK().getCodePrestation());
            dto.setCodePrestation(domaine.getPrestation().getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setPrixSelonTypeArriver(domaine.getMontant());

            dto.setCodeTypeIntervenant(domaine.getCodeTypeIntervenant());
            dto.setTypeIntervenantDTO(TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine.getTypeIntervenant()));
            dto.setDesignationArTypeIntervenant(domaine.getTypeIntervenant().getDesignationAr());
            dto.setDesignationLtTypeIntervenant(domaine.getTypeIntervenant().getDesignationLt());

            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));

            dto.setCodeSaisiePrestation(domaine.getPrestation().getCodeSaisie());

            return dto;
        } else {
            return null;
        }

    }

    public static Collection<DetailsPrestationDTO> detailsPrestationTodetailsPrestationDTOCollections(Collection<DetailsPrestation> detailsPrestations) {
        Collection<DetailsPrestationDTO> detailsPrestationDTOs = new ArrayList<>();
        for (DetailsPrestation detailsPrestation : detailsPrestations) {
            detailsPrestationDTOs.add(DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(detailsPrestation));
        }
        return detailsPrestationDTOs;
    }

    public static List<DetailsPrestationDTO> detailsPrestationTodetailsPrestationDTOLists(List<DetailsPrestation> detailsPrestations) {
        List<DetailsPrestationDTO> detailsPrestationDTOs = new ArrayList<>();
        for (DetailsPrestation detailsPrestation : detailsPrestations) {
            detailsPrestationDTOs.add(DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(detailsPrestation));
        }
        return detailsPrestationDTOs;
    }

    public static DetailsPrestationDTO DetailsPrestationToDetailsPrestationDTOCollection(DetailsPrestation domaine) {
        if (domaine != null) {
            DetailsPrestationDTO dto = new DetailsPrestationDTO();
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setMontant(domaine.getMontant());
            dto.setPrixSelonTypeArriver(domaine.getMontant());

            return dto;
        } else {
            return null;
        }

    }

    public static DetailsPrestation DetailsPrestationDTOToDetailsPrestation(DetailsPrestationDTO dto) {
        if (dto != null) {

            DetailsPrestation detailsPrestation = new DetailsPrestation();
//         detailsPrestation.setDetailsPrestationPK(new DetailsPrestationPK(dto.getCodePrestation(), dto.getCodeTypeIntervenant())); //Assuming a composite key
            detailsPrestation.setMontant(dto.getMontant());         // Map 'remMaj' field
            detailsPrestation.setDateCreate(dto.getDateCreate()); // Map 'dateCreate' field
            detailsPrestation.setUsercreate(dto.getUsercreate()); // Map 'usercreate' field

//        if (dto.getCodePrestation() != null) {
//            detailsPrestation.setPrestation(PrestationFactory.createPrestationByCode(dto.getCodePrestation())); // Assuming you have this factory method
//        }
//        if (dto.getCodeTypeIntervenant() != null) {
//            detailsPrestation.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(dto.getCodeTypeIntervenant())); // Assuming you have this factory method
//        }
            detailsPrestation.setCodeTypeIntervenant(dto.getCodeTypeIntervenant());
            if (detailsPrestation.getCodeTypeIntervenant() != null) {
                detailsPrestation.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(dto.getCodeTypeIntervenant()));
            }
            
             detailsPrestation.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (detailsPrestation.getCodeNatureAdmission() != null) {
                detailsPrestation.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            return detailsPrestation;
        } else {
            return null;
        }
    }
}
