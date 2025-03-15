/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.factory;

import com.FrameWork.MedLite.Parametrage.factory.ConventionFactory;
import com.FrameWork.MedLite.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.MedLite.Parametrage.factory.EtatPatientFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.SocieteFactory;
import com.FrameWork.MedLite.Reception.domaine.AdmissionFacturation;
import com.FrameWork.MedLite.Reception.dto.AdmissionFacturationDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class AdmissionFacturationFactory {

    public static AdmissionFacturation createAdmissionFacturationByCode(int code) {
        AdmissionFacturation domaine = new AdmissionFacturation();
        domaine.setCode(code);
        return domaine;
    }

    public static AdmissionFacturation admissionFacturationDTOToAdmissionFacturation(AdmissionFacturationDTO dto, AdmissionFacturation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeFactureAdmission(dto.getCodeFactureAdmission());

            domaine.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (domaine.getCodeNatureAdmission() != null) {
                domaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            domaine.setCodeSociete(dto.getCodeSociete());
           

            domaine.setCodeConvention(dto.getCodeConvention());
           

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }

            domaine.setCodeEtatPaiement(dto.getCodeEtatPaiement());
            if (domaine.getCodeEtatPaiement() != null) {
                domaine.setEtatPaiement(EtatPaiementFactory.createEtatPaiementByCode(dto.getCodeEtatPaiement()));
            }

            domaine.setCodeEtatPatient(dto.getCodeEtatPatient());
            if (domaine.getCodeEtatPatient() != null) {
                domaine.setEtatPatient(EtatPatientFactory.createEtatPatientByCode(dto.getCodeEtatPatient()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static AdmissionFacturationDTO admissionFacturationToAdmissionFacturationDTO(AdmissionFacturation domaine) {

        if (domaine != null) {
            AdmissionFacturationDTO dto = new AdmissionFacturationDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeFactureAdmission(domaine.getCodeFactureAdmission());

            dto.setUserCreate(domaine.getUserCreate());

            dto.setDateCreate(domaine.getDateCreate());

            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setEtatPaiementDTO(EtatPaiementFactory.etatPaiemenetToEtatPaiementDTO(domaine.getEtatPaiement()));
            dto.setCodeEtatPaiement(domaine.getCodeEtatPaiement());

            dto.setEtatPatientDTO(EtatPatientFactory.etatPatientToEtatPatientDTO(domaine.getEtatPatient()));
            dto.setCodeEtatPatient(domaine.getCodeEtatPatient());

            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());
 
            dto.setCodeSociete(domaine.getCodeSociete());
 
            dto.setCodeConvention(domaine.getCodeConvention());

            return dto;
        } else {
            return null;
        }
    }

    public static List<AdmissionFacturationDTO> listAdmissionFacturationToAdmissionFacturationDTOs(List<AdmissionFacturation> admissionFacturations) {
        List<AdmissionFacturationDTO> list = new ArrayList<>();
        for (AdmissionFacturation admissionFacturation : admissionFacturations) {
            list.add(admissionFacturationToAdmissionFacturationDTO(admissionFacturation));
        }
        return list;
    }
}
