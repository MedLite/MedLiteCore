/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.factory;

import com.FrameWork.MedLite.DMI.domaine.PastHistory;
import com.FrameWork.MedLite.DMI.dto.PastHistoryDTO;
import static com.FrameWork.MedLite.Examen.factory.ExamenFactory.examenToExamenDTO;
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
public class PastHistoryFactory {

    public static PastHistory createPastHistoryByCode(int code) {
        PastHistory domaine = new PastHistory();
        domaine.setCode(code);
        return domaine;
    }

    public static PastHistory pastHistoryDTOToPastHistory(PastHistoryDTO dto, PastHistory domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }

            domaine.setDateCreate(new Date());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setPastHistory(dto.getPastHistory());

            return domaine;
        } else {
            return null;
        }
    }

    public static PastHistoryDTO pastHistoryToPastHistoryDTO(PastHistory domaine) {

        if (domaine != null) {
            PastHistoryDTO dto = new PastHistoryDTO();
            dto.setCode(domaine.getCode());

            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setPastHistory(domaine.getPastHistory());

            return dto;
        } else {
            return null;
        }
    }

    public static List<PastHistoryDTO> listPastHistoryToPastHistoryDTOs(List<PastHistory> pastHistorys) {
        List<PastHistoryDTO> list = new ArrayList<>();
        for (PastHistory pastHistory : pastHistorys) {
            list.add(pastHistoryToPastHistoryDTO(pastHistory));
        }
        return list;
    }

}
