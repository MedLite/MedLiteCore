/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.factory;

import com.FrameWork.MedLite.DMI.domaine.CheifComplaint;
import com.FrameWork.MedLite.DMI.dto.CheifComplaintDTO;
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
public class CheifComplaintFactory {

    public static CheifComplaint createCheifComplaintByCode(int code) {
        CheifComplaint domaine = new CheifComplaint();
        domaine.setCode(code);
        return domaine;
    }

    public static CheifComplaint cheifComplaintDTOToCheifComplaint(CheifComplaintDTO dto, CheifComplaint domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }

            domaine.setDateCreate(new Date());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setCheifComplaint(dto.getCheifComplaint());

            return domaine;
        } else {
            return null;
        }
    }

    public static CheifComplaintDTO cheifComplaintToCheifComplaintDTO(CheifComplaint domaine) {

        if (domaine != null) {
            CheifComplaintDTO dto = new CheifComplaintDTO();
            dto.setCode(domaine.getCode());

            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setCheifComplaint(domaine.getCheifComplaint());

            return dto;
        } else {
            return null;
        }
    }

    public static List<CheifComplaintDTO> listCheifComplaintToCheifComplaintDTOs(List<CheifComplaint> cheifComplaints) {
        List<CheifComplaintDTO> list = new ArrayList<>();
        for (CheifComplaint cheifComplaint : cheifComplaints) {
            list.add(cheifComplaintToCheifComplaintDTO(cheifComplaint));
        }
        return list;
    }

}
