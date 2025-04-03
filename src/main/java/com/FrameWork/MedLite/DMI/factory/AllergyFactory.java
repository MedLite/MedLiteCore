/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.factory;

import com.FrameWork.MedLite.DMI.domaine.Allergy;
import com.FrameWork.MedLite.DMI.dto.AllergyDTO;
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
public class AllergyFactory {

    public static Allergy createAllergyByCode(int code) {
        Allergy domaine = new Allergy();
        domaine.setCode(code);
        return domaine;
    }

    public static Allergy allergyDTOToAllergy(AllergyDTO dto, Allergy domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            if (domaine.getCodeAdmission() != null) {
                domaine.setAdmission(AdmissionFactory.createAdmissionByCode(dto.getCodeAdmission()));
            }

            domaine.setDateCreate(new Date());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setAllergy(dto.getAllergy());

            return domaine;
        } else {
            return null;
        }
    }

    public static AllergyDTO allergyToAllergyDTO(Allergy domaine) {

        if (domaine != null) {
            AllergyDTO dto = new AllergyDTO();
            dto.setCode(domaine.getCode());

            dto.setAdmissionDTO(AdmissionFactory.admissionToAdmissionDTO(domaine.getAdmission()));
            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setAllergy(domaine.getAllergy());

            return dto;
        } else {
            return null;
        }
    }

    public static List<AllergyDTO> listAllergyToAllergyDTOs(List<Allergy> allergys) {
        List<AllergyDTO> list = new ArrayList<>();
        for (Allergy allergy : allergys) {
            list.add(allergyToAllergyDTO(allergy));
        }
        return list;
    }

}
