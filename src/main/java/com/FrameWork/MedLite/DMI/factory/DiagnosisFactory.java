/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.factory;

import com.FrameWork.MedLite.DMI.domaine.Diagnosis;
import com.FrameWork.MedLite.DMI.dto.DiagnosisDTO;
import com.FrameWork.MedLite.Reception.factory.AdmissionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DiagnosisFactory {

    public static Diagnosis createDiagnosisByCode(int code) {
        Diagnosis domaine = new Diagnosis();
        domaine.setCode(code);
        return domaine;
    }

    public static Diagnosis diagnosisDTOToDiagnosis(DiagnosisDTO dto, Diagnosis domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }

            domaine.setDateCreate(new Date());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setDiagnosis(dto.getDiagnosis());

            return domaine;
        } else {
            return null;
        }
    }

    public static DiagnosisDTO diagnosisToDiagnosisDTO(Diagnosis domaine) {

        if (domaine != null) {
            DiagnosisDTO dto = new DiagnosisDTO();
            dto.setCode(domaine.getCode());

            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDiagnosis(domaine.getDiagnosis());

            return dto;
        } else {
            return null;
        }
    }

    public static List<DiagnosisDTO> listDiagnosisToDiagnosisDTOs(List<Diagnosis> diagnosiss) {
        List<DiagnosisDTO> list = new ArrayList<>();
        for (Diagnosis diagnosis : diagnosiss) {
            list.add(diagnosisToDiagnosisDTO(diagnosis));
        }
        return list;
    }

}
