/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Recette.factory;

import com.DevPointSystem.MedLite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.EtatApprouverFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.ModeReglementFactory;
import com.DevPointSystem.MedLite.Recette.domaine.AlimentationCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaissePK;
import com.DevPointSystem.MedLite.Recette.dto.AlimentationCaisseDTO;
import com.DevPointSystem.MedLite.Recette.dto.DetailsAlimentationCaisseDTO;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class AlimentationCaisseFactory {

    public static AlimentationCaisse createAlimentationCaisseByCode(int code) {
        AlimentationCaisse domaine = new AlimentationCaisse();
        domaine.setCode(code);
        return domaine;
    }

    public static AlimentationCaisse alimentationCaisseDTOToAlimentationCaisse( AlimentationCaisse domaine ,AlimentationCaisseDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setObservation(dto.getObservation());
            domaine.setCodeSaisie(dto.getCodeSaisie()); 
            domaine.setMontant(dto.getMontant());
            domaine.setMontantEnDevise(dto.getMontantEnDevise());
            domaine.setTauxChange(dto.getTauxChange()); 

            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseRequired");
            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }

            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));

            }

            domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
            if (domaine.getCodeEtatApprouver() != null) {
                domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
            }
            
            if(dto.getDetailsAlimentationCaisseDTOs().isEmpty()){
                 throw new IllegalArgumentException("error.DetailsRequired");
            }  
            Collection<DetailsAlimentationCaisse> detailsCollections = new ArrayList<>();
            dto.getDetailsAlimentationCaisseDTOs().forEach(x -> {
                DetailsAlimentationCaisse detailsAlimentationCaisse = new DetailsAlimentationCaisse();

                DetailsAlimentationCaissePK detailsPK = new DetailsAlimentationCaissePK();
                Preconditions.checkBusinessLogique(x.getCodeTypeRecette() != null, "error.TypeRecetteRequired");
                detailsPK.setCodeTypeRecette(x.getCodeTypeRecette());

                detailsAlimentationCaisse.setDetailsAlimentationCaissePK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsAlimentationCaisse.setMontant(x.getMontant());    
                
                detailsAlimentationCaisse.setMontantDevise(x.getMontantDevise());

                detailsAlimentationCaisse.setDateCreate(domaine.getDateCreate());
                detailsAlimentationCaisse.setUsercreate(domaine.getUserCreate());
                detailsAlimentationCaisse.setAlimentationCaisse(domaine);
                detailsCollections.add(detailsAlimentationCaisse);
            });

            if (domaine.getDetailsAlimentationCaisses() != null) {
                domaine.getDetailsAlimentationCaisses().clear();
                domaine.getDetailsAlimentationCaisses().addAll(detailsCollections);
            } else {
                domaine.setDetailsAlimentationCaisses(detailsCollections);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static AlimentationCaisseDTO alimentationCaisseToAlimentationCaisseDTOUpdate(AlimentationCaisse domaine) {

        if (domaine != null) {
            AlimentationCaisseDTO dto = new AlimentationCaisseDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setMontantEnDevise(domaine.getMontantEnDevise());       
            dto.setTauxChange(domaine.getTauxChange());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());

            

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            if (domaine.getDetailsAlimentationCaisses() != null) {
                Collection<DetailsAlimentationCaisseDTO> detailsAlimentationCaisseDTOsCollection = new ArrayList<>();
                domaine.getDetailsAlimentationCaisses().forEach(x -> {
                    DetailsAlimentationCaisseDTO detailsDTO = new DetailsAlimentationCaisseDTO();
                    detailsDTO = DetailsAlimentationCaisseFactory.DetailsAlimentationCaisseToDetailsAlimentationCaisseDTOCollectionForUpdate(x);
                    detailsAlimentationCaisseDTOsCollection.add(detailsDTO);
                });
                if (dto.getDetailsAlimentationCaisseDTOs() != null) {
                    dto.getDetailsAlimentationCaisseDTOs().clear();
                    dto.getDetailsAlimentationCaisseDTOs().addAll(detailsAlimentationCaisseDTOsCollection);
                } else {
                    dto.setDetailsAlimentationCaisseDTOs(detailsAlimentationCaisseDTOsCollection);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    
    public static AlimentationCaisseDTO alimentationCaisseToAlimentationCaisseDTO(AlimentationCaisse domaine) {

        if (domaine != null) {
            AlimentationCaisseDTO dto = new AlimentationCaisseDTO();
            dto.setCode(domaine.getCode());
            dto.setObservation(domaine.getObservation());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMontant(domaine.getMontant());
            dto.setMontantEnDevise(domaine.getMontantEnDevise());       
            dto.setTauxChange(domaine.getTauxChange());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());

            

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            if (domaine.getDetailsAlimentationCaisses() != null) {
                Collection<DetailsAlimentationCaisseDTO> detailsAlimentationCaisseDTOsCollection = new ArrayList<>();
                domaine.getDetailsAlimentationCaisses().forEach(x -> {
                    DetailsAlimentationCaisseDTO detailsDTO = new DetailsAlimentationCaisseDTO();
                    detailsDTO = DetailsAlimentationCaisseFactory.DetailsAlimentationCaisseToDetailsAlimentationCaisseDTOCollection(x);
                    detailsAlimentationCaisseDTOsCollection.add(detailsDTO);
                });
                if (dto.getDetailsAlimentationCaisseDTOs() != null) {
                    dto.getDetailsAlimentationCaisseDTOs().clear();
                    dto.getDetailsAlimentationCaisseDTOs().addAll(detailsAlimentationCaisseDTOsCollection);
                } else {
                    dto.setDetailsAlimentationCaisseDTOs(detailsAlimentationCaisseDTOsCollection);
                }
            }

            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<AlimentationCaisseDTO> listAlimentationCaisseToAlimentationCaisseDTOs(List<AlimentationCaisse> alimentationCaisses) {
        List<AlimentationCaisseDTO> list = new ArrayList<>();
        for (AlimentationCaisse alimentationCaisse : alimentationCaisses) {
            list.add(alimentationCaisseToAlimentationCaisseDTO(alimentationCaisse));
        }
        return list;
    }

    public static Collection<AlimentationCaisseDTO> CollectionalimentationCaissesToalimentationCaissesDTOsCollection(Collection<AlimentationCaisse> alimentationCaisses) {
        List<AlimentationCaisseDTO> dtos = new ArrayList<>();
        alimentationCaisses.forEach(x -> {
            dtos.add(alimentationCaisseToAlimentationCaisseDTO(x));
        });
        return dtos;

    }

    public static AlimentationCaisse ApprouveAlimentationCaisseDTOToAlimentationCaisse(AlimentationCaisse domaine, AlimentationCaisseDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
        domaine.setDateApprouve(new Date());

        return domaine;
    }

    public static AlimentationCaisse CancelAlimentationCaisseDTOToAlimentationCaisse(AlimentationCaisse domaine, AlimentationCaisseDTO dto) {
        domaine.setCode(dto.getCode());  
        domaine.setCodeSaisie(dto.getCodeSaisie());

        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(null);
        domaine.setDateApprouve(null);

        return domaine;
    }
}
