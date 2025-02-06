/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.factory;

import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.factory.ConventionFactory;
import com.FrameWork.MedLite.Parametrage.factory.FamilleFacturationFactory;
import com.FrameWork.MedLite.Parametrage.factory.NationaliteFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Reception.domaine.Patient;
import com.FrameWork.MedLite.Reception.dto.PatientDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PatientFactory {

    public static Patient createPatientByCode(int code) {
        Patient domaine = new Patient();
        domaine.setCode(code);
        return domaine;
    }

    public static Patient patientDTOToPatient(PatientDTO dto, Patient domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setNomCompltAr(dto.getNomCompltAr());
            domaine.setNomCompltLt(dto.getNomCompltLt());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeSociete(dto.getCodeSociete());
            domaine.setCodeConvention(dto.getCodeConvention());
            domaine.setNumTel(dto.getNumTel());
            domaine.setDateNaissance(dto.getDateNaissance());

            domaine.setCodePriceList(dto.getCodePriceList());
            if (domaine.getCodePriceList() != null) {
                domaine.setPriceList(PriceListFactory.createPriceListByCode(dto.getCodePriceList()));
            }

            domaine.setActif(dto.isActif());
            domaine.setDateCreate(new Date());
            domaine.setUserCreate(dto.getUserCreate());

            return domaine;
        } else {
            return null;
        }
    }

    public static PatientDTO patientToPatientDTO(Patient domaine) {

        if (domaine != null) {
            PatientDTO dto = new PatientDTO();
            dto.setCode(domaine.getCode());

            dto.setNomCompltAr(domaine.getNomCompltAr());
            dto.setNomCompltLt(domaine.getNomCompltLt());
            dto.setNumTel(domaine.getNumTel());
            dto.setDateNaissance(domaine.getDateNaissance());

//            dto.setNationaliteDTO(NationaliteFactory.nationaliteToNationaliteDTO(domaine.getNationalite()));
//            dto.setCodeNationalite(domaine.getCodeNationalite());
//            
//            if(domaine.getCodeConvention() != null){
//                Convention conv = 
//                dto.setConventionDTO(ConventionFactory.nationaliteToNationaliteDTO(domaine.getConvention()));
//            }
//            dto.setCodeSociete(domaine.getCodeSociete());

            dto.setPriceListDTO(PriceListFactory.priceListToPriceListDTO(domaine.getPriceList()));
            dto.setCodePriceList(domaine.getCodePriceList());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<PatientDTO> listPatientToPatientDTOs(List<Patient> patients) {
        List<PatientDTO> list = new ArrayList<>();
        for (Patient patient : patients) {
            list.add(patientToPatientDTO(patient));
        }
        return list;
    }
}
