/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.factory;

import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
import com.FrameWork.MedLite.DMI.dto.FeuilleSoinOPDDTO;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FeuilleSoinOPDFactory {

    public static FeuilleSoinOPD createFeuilleSoinOPDByCode(int code) {
        FeuilleSoinOPD domaine = new FeuilleSoinOPD();
        domaine.setCode(code);
        return domaine;
    }

    public static FeuilleSoinOPD feuilleSoinOPDDTOToFeuilleSoinOPD(FeuilleSoinOPDDTO dto, FeuilleSoinOPD domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }

            domaine.setCodeAllergy(dto.getCodeAllergy());
            if (domaine.getCodeAllergy() != null) {
                domaine.setAllergy(AllergyFactory.createAllergyByCode(dto.getCodeAllergy()));
            }

            domaine.setCodeCheifComplaint(dto.getCodeCheifComplaint());
            if (domaine.getCodeCheifComplaint() != null) {
                domaine.setCheifComplaint(CheifComplaintFactory.createCheifComplaintByCode(dto.getCodeCheifComplaint()));
            }

            domaine.setCodeDiagnosis(dto.getCodeDiagnosis());
            if (domaine.getCodeDiagnosis() != null) {
                domaine.setDiagnosis(DiagnosisFactory.createDiagnosisByCode(dto.getCodeDiagnosis()));
            }

            domaine.setCodePastHistory(dto.getCodePastHistory());
            if (domaine.getCodePastHistory() != null) {
                domaine.setPastHistory(PastHistoryFactory.createPastHistoryByCode(dto.getCodePastHistory()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static FeuilleSoinOPDDTO feuilleSoinOPDToFeuilleSoinOPDDTO(FeuilleSoinOPD domaine) {

        if (domaine != null) {
            FeuilleSoinOPDDTO dto = new FeuilleSoinOPDDTO();
            dto.setCode(domaine.getCode());

            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setAllergyDTO(AllergyFactory.allergyToAllergyDTO(domaine.getAllergy()));
            dto.setCodeAllergy(domaine.getCodeAllergy());

            dto.setCheifComplaintDTO(CheifComplaintFactory.cheifComplaintToCheifComplaintDTO(domaine.getCheifComplaint()));
            dto.setCodeCheifComplaint(domaine.getCodeCheifComplaint());

            dto.setDiagnosisDTO(DiagnosisFactory.diagnosisToDiagnosisDTO(domaine.getDiagnosis()));
            dto.setCodeDiagnosis(domaine.getCodeDiagnosis());

            dto.setPastHistoryDTO(PastHistoryFactory.pastHistoryToPastHistoryDTO(domaine.getPastHistory()));
            dto.setCodePastHistory(domaine.getCodePastHistory());

            return dto;
        } else {
            return null;
        }
    }

    public static List<FeuilleSoinOPDDTO> listFeuilleSoinOPDToFeuilleSoinOPDDTOs(List<FeuilleSoinOPD> feuilleSoinOPDs) {
        List<FeuilleSoinOPDDTO> list = new ArrayList<>();
        for (FeuilleSoinOPD feuilleSoinOPD : feuilleSoinOPDs) {
            list.add(feuilleSoinOPDToFeuilleSoinOPDDTO(feuilleSoinOPD));
        }
        return list;
    }

}
