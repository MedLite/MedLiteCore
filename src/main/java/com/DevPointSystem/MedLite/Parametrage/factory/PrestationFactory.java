/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationPK;
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationDTO;
import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaissePK;
import com.DevPointSystem.MedLite.Recette.dto.DetailsAlimentationCaisseDTO;
import com.DevPointSystem.MedLite.Recette.factory.DetailsAlimentationCaisseFactory;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PrestationFactory {

    public static Prestation createPrestationByCode(int code) {
        Prestation domaine = new Prestation();
        domaine.setCode(code);
        return domaine;
    }

    public static Prestation prestationDTOToPrestation(PrestationDTO dto, Prestation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate()); 
            domaine.setMontant(dto.getMontant());

            domaine.setCodeFamilleFacturation(dto.getCodeFamilleFacturation());
            if (domaine.getCodeFamilleFacturation() != null) {
                domaine.setFamilleFacturation(FamilleFacturationFactory.createFamilleFacturationByCode(dto.getCodeFamilleFacturation()));
            } 
            domaine.setCodeFamillePrestation(dto.getCodeFamillePrestation());
            if (domaine.getCodeFamillePrestation() != null) {
                domaine.setFamillePrestation(FamillePrestationFactory.createFamillePrestationByCode(dto.getCodeFamillePrestation()));
            }
            
            if(dto.getDetailsPrestationDTOs().isEmpty()){
                 throw new IllegalArgumentException("error.DetailsRequired");
            }  
            Collection<DetailsPrestation> detailsCollections = new ArrayList<>();
            dto.getDetailsPrestationDTOs().forEach(x -> {
                DetailsPrestation detailsPrestation = new DetailsPrestation();

                DetailsPrestationPK detailsPK = new DetailsPrestationPK();
                Preconditions.checkBusinessLogique(x.getCodeTypeIntervenant()!= null, "error.TypeIntervenantRequired");
                detailsPK.setCodeTypeIntervenant(x.getCodeTypeIntervenant());

                detailsPrestation.setDetailsPrestationPK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsPrestation.setMontant(x.getMontant());    
                 

                detailsPrestation.setDateCreate(domaine.getDateCreate());
                detailsPrestation.setUsercreate(domaine.getUserCreate());
                detailsPrestation.setPrestation(domaine);
                detailsCollections.add(detailsPrestation);
            });

            if (domaine.getDetailsPrestations()!= null) {
                domaine.getDetailsPrestations().clear();
                domaine.getDetailsPrestations().addAll(detailsCollections);
            } else {
                domaine.setDetailsPrestations(detailsCollections);
            }
            

            return domaine;
        } else {
            return null;
        }
    }

    public static PrestationDTO prestationToPrestationDTO(Prestation domaine) {

        if (domaine != null) {
            PrestationDTO dto = new PrestationDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setMontant(domaine.getMontant());

            dto.setFamilleFacturationDTO(FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine.getFamilleFacturation()));
            dto.setCodeFamilleFacturation(domaine.getCodeFamilleFacturation());
            dto.setFamillePrestationDTO(FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine.getFamillePrestation()));
            dto.setCodeFamillePrestation(domaine.getCodeFamillePrestation());

            
            if (domaine.getDetailsPrestations()!= null) {
                Collection<DetailsPrestationDTO> detailsPrestationDTOs = new ArrayList<>();
                domaine.getDetailsPrestations().forEach(x -> {
                    DetailsPrestationDTO detailsDTO = new DetailsPrestationDTO();
                    detailsDTO = DetailsPrestationFactory.DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(x);
                    detailsPrestationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsPrestationDTOs()!= null) {
                    dto.getDetailsPrestationDTOs().clear();
                    dto.getDetailsPrestationDTOs().addAll(detailsPrestationDTOs);
                } else {
                    dto.setDetailsPrestationDTOs(detailsPrestationDTOs);
                }
            }
            
            return dto;
        } else {
            return null;
        }
    }

    public static List<PrestationDTO> listPrestationToPrestationDTOs(List<Prestation> prestations) {
        List<PrestationDTO> list = new ArrayList<>();
        for (Prestation prestation : prestations) {
            list.add(prestationToPrestationDTO(prestation));
        }
        return list;
    }
}
