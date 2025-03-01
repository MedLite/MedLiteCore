/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.factory;

import com.FrameWork.MedLite.Parametrage.factory.MedecinFactory;
import com.FrameWork.MedLite.Parametrage.factory.MotifAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmissionPK;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Recette.domaine.DetailsAlimentationCaisse;
import com.FrameWork.MedLite.Recette.domaine.DetailsAlimentationCaissePK;
import com.FrameWork.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class AdmissionFactory {

    public static Admission createAdmissionByCode(int code) {
        Admission domaine = new Admission();
        domaine.setCode(code);
        return domaine;
    }

    public static Admission admissionDTOToAdmission(AdmissionDTO dto, Admission domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie()); 

            domaine.setEtatPaiement(dto.isEtatPaiement());
            domaine.setCodeListCouverture(dto.getCodeListCouverture());
            domaine.setCodeSociete(dto.getCodeSociete());
            domaine.setCodeCabinet(dto.getCodeCabinet());
            domaine.setCodeConvention(dto.getCodeConvention());

            domaine.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (domaine.getCodeNatureAdmission() != null) {
                domaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            domaine.setCodePatient(dto.getCodePatient());
            if (domaine.getCodePatient() != null) {
                domaine.setPatient(PatientFactory.createPatientByCode(dto.getCodePatient()));
            }

            domaine.setCodeMotifAdmission(dto.getCodeMotifAdmission());
            if (domaine.getCodeMotifAdmission() != null) {
                domaine.setMotifAdmission(MotifAdmissionFactory.createMotifAdmissionByCode(dto.getCodeMotifAdmission()));
            }

            domaine.setCodeMedecin(dto.getCodeMedecin());
            if (domaine.getCodeMedecin() != null) {
                domaine.setMedecin(MedecinFactory.createMedecinByCode(dto.getCodeMedecin()));
            }
            
              domaine.setCodePriceList(dto.getCodePriceList());
            if (domaine.getCodePriceList() != null) {
                domaine.setPriceList(PriceListFactory.createPriceListByCode(dto.getCodePriceList()));
            }

 
//            List<DetailsAdmission> detailsCollections = new ArrayList<>();
//            dto.getDetailsAdmissionDTOs().forEach(x -> {
//                DetailsAdmission detailsAdmission = new DetailsAdmission();
// 
//                detailsAdmission.setCodePrestation(x.getCodePrestation());
//                if (x.getCodePrestation() != null) {
//                    detailsAdmission.setPrestation(PrestationFactory.createPrestationByCode(x.getCodePrestation()));
//                }
//                
//                  detailsAdmission.setCodeAdmission(x.getCodeAdmission());
//                if (x.getCodeAdmission() != null) {
//                    detailsAdmission.setAdmission(AdmissionFactory.createAdmissionByCode(x.getCodeAdmission()));
//                }
//
//                detailsAdmission.setDateCreate(dto.getDateCreate());
//                detailsAdmission.setUsercreate(dto.getUserCreate());
//                detailsAdmission.setAdmission(domaine);
//                detailsCollections.add(detailsAdmission);
//            });
//
//            if (domaine.getDetailsAdmissions() != null) {
//                domaine.getDetailsAdmissions().clear();
//                domaine.getDetailsAdmissions().addAll(detailsCollections);
//            } else {
//                domaine.setDetailsAdmissions(detailsCollections);
//            }

            return domaine;
        } else {
            return null;
        }
    }

    public static AdmissionDTO admissionToAdmissionDTO(Admission domaine) {

        if (domaine != null) {
            AdmissionDTO dto = new AdmissionDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setCodeCabinet(domaine.getCodeCabinet());
            dto.setCodeConvention(domaine.getCodeConvention());
            dto.setCodeMedecin(domaine.getCodeMedecin());
            dto.setCodeListCouverture(domaine.getCodeListCouverture());
            dto.setEtatPaiement(domaine.isEtatPaiement());

            dto.setPatientDTO(PatientFactory.patientToPatientDTO(domaine.getPatient()));
            dto.setCodePatient(domaine.getCodePatient());

            dto.setMedecinDTO(MedecinFactory.medecinToMedecinDTO(domaine.getMedecin()));
            dto.setCodeMedecin(domaine.getCodeMedecin());

            dto.setMotifAdmissionDTO(MotifAdmissionFactory.motifAdmissionToMotifAdmissionDTO(domaine.getMotifAdmission()));
            dto.setCodeMotifAdmission(domaine.getCodeMotifAdmission());

            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
            
            
            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));
            dto.setCodePriceList(domaine.getCodePriceList());
            
            

            return dto;
        } else {
            return null;
        }
    }

    public static List<AdmissionDTO> listAdmissionToAdmissionDTOs(List<Admission> admissions) {
        List<AdmissionDTO> list = new ArrayList<>();
        for (Admission admission : admissions) {
            list.add(admissionToAdmissionDTO(admission));
        }
        return list;
    }
}
