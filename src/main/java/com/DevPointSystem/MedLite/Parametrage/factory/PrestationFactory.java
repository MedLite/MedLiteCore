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
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import java.math.BigDecimal;
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

            domaine.setPrixPrestation(dto.getPrixPrestation());
            domaine.setOpd(dto.isOpd());
            domaine.setEr(dto.isEr());
            domaine.setIp(dto.isIp());
//            
            if (dto.isOpd() == Boolean.TRUE) {
                if (!dto.getPrixPrestation().equals(dto.getMontantOPD())) {
                    throw new IllegalArgumentException("error.PriceNotEqualsOPD");
                } else {
                    domaine.setMontantOPD(dto.getMontantOPD());
                }
            }
            if (dto.isEr() == Boolean.TRUE) {
                System.out.println("prix getPrixPrestation   " + dto.getPrixPrestation() + "Prix ER " + dto.getMontantER());
                if (!dto.getPrixPrestation().equals(dto.getMontantER())) {
                    throw new IllegalArgumentException("error.PriceNotEqualsER");
                } else {
                    domaine.setMontantER(dto.getMontantER());
                }
            }
            if (dto.isIp() == Boolean.TRUE) {
                if (!dto.getPrixPrestation().equals(dto.getMontantIP())) {
                    throw new IllegalArgumentException("error.PriceNotEqualsIP");
                } else {
                    domaine.setMontantIP(dto.getMontantIP());
                }
            }
            domaine.setCodeFamilleFacturation(dto.getCodeFamilleFacturation());
            if (domaine.getCodeFamilleFacturation() != null) {
                domaine.setFamilleFacturation(FamilleFacturationFactory.createFamilleFacturationByCode(dto.getCodeFamilleFacturation()));
            }

            domaine.setCodeSousFamillePrestation(dto.getCodeSousFamillePrestation());
            if (domaine.getCodeSousFamillePrestation() != null) {
                domaine.setSousFamillePrestation(SousFamillePrestationFactory.createSousFamillePrestationByCode(dto.getCodeSousFamillePrestation()));
            }

            domaine.setCodeFamillePrestation(dto.getCodeFamillePrestation());
            if (domaine.getCodeFamillePrestation() != null) {
                domaine.setFamillePrestation(FamillePrestationFactory.createFamillePrestationByCode(dto.getCodeFamillePrestation()));
            }

            if (dto.getDetailsPrestationDTOs() == null || dto.getDetailsPrestationDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsPrestationRequired");
            }

            Collection<DetailsPrestation> detailsCollections = new ArrayList<>();
            dto.getDetailsPrestationDTOs().forEach(x -> {
                DetailsPrestation detailsPrestation = new DetailsPrestation();

                DetailsPrestationPK detailsPK = new DetailsPrestationPK();
                Preconditions.checkBusinessLogique(x.getCodeNatureAdmission() != null, "error.NatureAdmissionRequired");
                detailsPK.setCodeNatureAdmission(x.getCodeNatureAdmission());
                Preconditions.checkBusinessLogique(x.getCodeTypeIntervenant() != null, "error.TypeIntervenantRequired");

                detailsPK.setCodeTypeIntervenant(x.getCodeTypeIntervenant());
                detailsPrestation.setDetailsPrestationPK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsPrestation.setMontant(x.getMontant());

                detailsPrestation.setDateCreate(new Date());
                detailsPrestation.setUsercreate(Helper.getUserAuthenticated());
                detailsPrestation.setPrestation(domaine);
                detailsCollections.add(detailsPrestation);

            });

            if (domaine.getDetailsPrestations() != null) {
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

            dto.setMontantOPD(domaine.getMontantOPD());
            dto.setMontantER(domaine.getMontantER());
            dto.setMontantIP(domaine.getMontantIP());
            dto.setPrixPrestation(domaine.getPrixPrestation());

            dto.setOpd(domaine.isOpd());

            dto.setEr(domaine.isEr());
            dto.setIp(domaine.isIp());

            dto.setFamilleFacturationDTO(FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine.getFamilleFacturation()));
            dto.setCodeFamilleFacturation(domaine.getCodeFamilleFacturation());
            dto.setFamillePrestationDTO(FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine.getFamillePrestation()));
            dto.setCodeFamillePrestation(domaine.getCodeFamillePrestation());

            dto.setSousFamillePrestationDTO(SousFamillePrestationFactory.sousFamillePrestationToSousFamillePrestationDTO(domaine.getSousFamillePrestation()));
            dto.setCodeSousFamillePrestation(domaine.getCodeSousFamillePrestation());

            if (domaine.getDetailsPrestations() != null) {
                Collection<DetailsPrestationDTO> detailsPrestationDTOs = new ArrayList<>();
                domaine.getDetailsPrestations().forEach(x -> {
                    DetailsPrestationDTO detailsDTO = new DetailsPrestationDTO();
                    detailsDTO = DetailsPrestationFactory.DetailsPrestationToDetailsPrestationDTOCollection(x);
                    detailsPrestationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsPrestationDTOs() != null) {
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

    public static PrestationDTO prestationToPrestationDTOForUpdate(Prestation domaine) {

        if (domaine != null) {
            PrestationDTO dto = new PrestationDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setMontantOPD(domaine.getMontantOPD());
            dto.setMontantER(domaine.getMontantER());
            dto.setMontantIP(domaine.getMontantIP());
            dto.setPrixPrestation(domaine.getPrixPrestation());

            dto.setOpd(domaine.isOpd());
            dto.setEr(domaine.isEr());
            dto.setIp(domaine.isIp());

            dto.setFamilleFacturationDTO(FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine.getFamilleFacturation()));
            dto.setCodeFamilleFacturation(domaine.getCodeFamilleFacturation());
            dto.setFamillePrestationDTO(FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine.getFamillePrestation()));
            dto.setCodeFamillePrestation(domaine.getCodeFamillePrestation());
            dto.setSousFamillePrestationDTO(SousFamillePrestationFactory.sousFamillePrestationToSousFamillePrestationDTO(domaine.getSousFamillePrestation()));
            dto.setCodeSousFamillePrestation(domaine.getCodeSousFamillePrestation());
            if (domaine.getDetailsPrestations() != null) {
                Collection<DetailsPrestationDTO> detailsPrestationDTOs = new ArrayList<>();
                domaine.getDetailsPrestations().forEach(x -> {
                    DetailsPrestationDTO detailsDTO = new DetailsPrestationDTO();
                    detailsDTO = DetailsPrestationFactory.DetailsPrestationToDetailsPrestationDTOCollectionForUpdate(x);
                    detailsPrestationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsPrestationDTOs() != null) {
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
