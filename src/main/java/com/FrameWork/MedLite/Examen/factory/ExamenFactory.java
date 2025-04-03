/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.factory;

import com.FrameWork.MedLite.Examen.domaine.DetailsExamen;
import com.FrameWork.MedLite.Examen.domaine.DetailsExamenPK;
import com.FrameWork.MedLite.Examen.domaine.Examen;
import com.FrameWork.MedLite.Examen.dto.DetailsExamenDTO;
import com.FrameWork.MedLite.Examen.dto.ExamenDTO;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestationPK;
import com.FrameWork.MedLite.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import com.FrameWork.MedLite.Reception.factory.PatientFactory;
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
public class ExamenFactory {

    public static Examen createExamenByCode(int code) {
        Examen domaine = new Examen();
        domaine.setCode(code);
        return domaine;
    }

    public static Examen examenDTOToExamen(ExamenDTO dto, Examen domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setPret(dto.isPret());    
            domaine.setCodeMedecinDemande(dto.getCodeMedecinDemande());


            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }
            domaine.setCodeEtatPaiement(dto.getCodeEtatPaiement());
            if (domaine.getCodeEtatPaiement() != null) {
                domaine.setEtatPaiement(EtatPaiementFactory.createEtatPaiementByCode(dto.getCodeEtatPaiement()));
            }

            domaine.setCodePatient(dto.getCodePatient());
            if (domaine.getCodePatient() != null) {
                domaine.setPatient(PatientFactory.createPatientByCode(dto.getCodePatient()));
            }

            domaine.setDateCreate(new Date());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setTypeExamen(dto.getTypeExamen());

            if (dto.getDetailsExamenDTOs() == null || dto.getDetailsExamenDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsExamenRequired");
            }

            Collection<DetailsExamen> detailsCollections = new ArrayList<>();
            dto.getDetailsExamenDTOs().forEach(x -> {
                DetailsExamen detailsExamen = new DetailsExamen();

//                System.out.println("    prestation" + x.getCodePrestation());
//                System.out.println(" examennn " + x.getCodeExamen());
//                System.out.println(" admission " + x.getCodeAdmission());

                DetailsExamenPK detailsPK = new DetailsExamenPK();

                Preconditions.checkBusinessLogique(x.getCodePrestation() != null, "error.PrestationRequired");
                detailsPK.setCodePrestation(x.getCodePrestation());
                detailsExamen.setDetailsExamenPK(detailsPK);
                Preconditions.checkBusinessLogique(x.getCodePatient() != null, "error.PatientRequired");
//                detailsExamen.setCodePatient(x.getCodePatient());

                
                detailsExamen.setCodePatient(x.getCodePatient());
                if (detailsExamen.getCodePatient() != null) {
                    detailsExamen.setPatient(PatientFactory.createPatientByCode(x.getCodePatient()));
                }
                  
                  
                Preconditions.checkBusinessLogique(x.getCodeNatureAdmission() != null, "error.NatureAdmissionRequired");

                detailsExamen.setCodeNatureAdmission(x.getCodeNatureAdmission());
                if (detailsExamen.getCodeNatureAdmission() != null) {
                    detailsExamen.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(x.getCodeNatureAdmission()));
                }

                Preconditions.checkBusinessLogique(x.getCodeAdmission() != null, "error.CodeAdmissionRequired");
                detailsExamen.setCodeAdmission(x.getCodeAdmission());
                if (detailsExamen.getCodeAdmission() != null) {
                    detailsExamen.setAdmission(AdmissionFactory.createAdmissionByCode(x.getCodeAdmission()));
                }

                detailsExamen.setDateCreate(new Date());
                detailsExamen.setUserCreate(Helper.getUserAuthenticated());
                detailsExamen.setExamen(domaine);
                detailsCollections.add(detailsExamen);

            });

            if (domaine.getDetailsExamens() != null) {
                domaine.getDetailsExamens().clear();
                domaine.getDetailsExamens().addAll(detailsCollections);
            } else {
                domaine.setDetailsExamens(detailsCollections);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static ExamenDTO examenToExamenDTO(Examen domaine) {

        if (domaine != null) {
            ExamenDTO dto = new ExamenDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setPret(domaine.isPret());
                        dto.setCodeMedecinDemande(domaine.getCodeMedecinDemande());


            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());
            dto.setEtatPaiementDTO(EtatPaiementFactory.etatPaiemenetToEtatPaiementDTO(domaine.getEtatPaiement()));
            dto.setCodeEtatPaiement(domaine.getCodeEtatPaiement());

            dto.setPatientDTO(PatientFactory.patientToPatientDTO(domaine.getPatient()));
            dto.setCodePatient(domaine.getCodePatient());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setTypeExamen(domaine.getTypeExamen());

            if (domaine.getDetailsExamens() != null) {
                Collection<DetailsExamenDTO> detailsExamenDTOs = new ArrayList<>();
                domaine.getDetailsExamens().forEach(x -> {
                    DetailsExamenDTO detailsDTO = new DetailsExamenDTO();
                    detailsDTO = DetailsExamenFactory.DetailsExamenToDetailsExamenDTOCollection(x);
                    detailsExamenDTOs.add(detailsDTO);
                });
                if (dto.getDetailsExamenDTOs() != null) {
                    dto.getDetailsExamenDTOs().clear();
                    dto.getDetailsExamenDTOs().addAll(detailsExamenDTOs);
                } else {
                    dto.setDetailsExamenDTOs(detailsExamenDTOs);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static ExamenDTO examenToExamenDTOWithDetails(Examen domaine) {

        if (domaine != null) {
            ExamenDTO dto = new ExamenDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setPret(domaine.isPret());  
            dto.setCodeMedecinDemande(domaine.getCodeMedecinDemande());


            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());
            dto.setEtatPaiementDTO(EtatPaiementFactory.etatPaiemenetToEtatPaiementDTO(domaine.getEtatPaiement()));
            dto.setCodeEtatPaiement(domaine.getCodeEtatPaiement());

            dto.setPatientDTO(PatientFactory.patientToPatientDTO(domaine.getPatient()));
            dto.setCodePatient(domaine.getCodePatient());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setTypeExamen(domaine.getTypeExamen());

            if (domaine.getDetailsExamens() != null) {
                Collection<DetailsExamenDTO> detailsExamenDTOs = new ArrayList<>();
                domaine.getDetailsExamens().forEach(x -> {
                    DetailsExamenDTO detailsDTO = new DetailsExamenDTO();
                    detailsDTO = DetailsExamenFactory.DetailsExamenToDetailsExamenDTOCollectionForUpdate(x);
                    detailsExamenDTOs.add(detailsDTO);
                });
                if (dto.getDetailsExamenDTOs() != null) {
                    dto.getDetailsExamenDTOs().clear();
                    dto.getDetailsExamenDTOs().addAll(detailsExamenDTOs);
                } else {
                    dto.setDetailsExamenDTOs(detailsExamenDTOs);
                }
            }

            return dto;
        } else {
            return null;
        }
    }

    public static List<ExamenDTO> listExamenToExamenDTOs(List<Examen> examens) {
        List<ExamenDTO> list = new ArrayList<>();
        for (Examen examen : examens) {
            list.add(examenToExamenDTO(examen));
        }
        return list;
    }

    public static List<ExamenDTO> listExamenToExamenDTOsWithDetails(List<Examen> examens) {
        List<ExamenDTO> list = new ArrayList<>();
        for (Examen examen : examens) {
            list.add(examenToExamenDTOWithDetails(examen));
        }
        return list;
    }

}
