/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.Soc;
import com.FrameWork.MedLite.Parametrage.dto.SocDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SocFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static SocDTO societeToSocieteDTO(Soc societe) {
        SocDTO societeDTO = new SocDTO();
        societeDTO.setCode(societe.getCode());
       
            societeDTO.setLogo(societe.getLogo());
       
        societeDTO.setNomSociete(societe.getNomSociete());

        return societeDTO;
    }

    public static Soc societeDTOToSociete(SocDTO societeDTO) {
        Soc societe = new Soc();
        societe.setCode(societeDTO.getCode());
        societe.setLogo(societeDTO.getLogo());
        societe.setNomSociete(societeDTO.getNomSociete());

        return societe;
    }

    public static Collection<SocDTO> societeToSocieteDTOs(Collection<Soc> societes) {
        List<SocDTO> societesDTO = new ArrayList<>();
        societes.forEach(x -> {
            societesDTO.add(societeToSocieteDTO(x));
        });
        return societesDTO;
    }
}
