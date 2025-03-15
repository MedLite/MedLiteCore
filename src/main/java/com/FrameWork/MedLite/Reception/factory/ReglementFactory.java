/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.factory;

import com.FrameWork.MedLite.Authentification.factory.AccessUserFactory;
import com.FrameWork.MedLite.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Reception.domaine.Reglement;
import com.FrameWork.MedLite.Reception.dto.ReglementDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ReglementFactory {

    public static Reglement createReglementByCode(int code) {
        Reglement domaine = new Reglement();
        domaine.setCode(code);
        return domaine;
    }

    public static Reglement reglementDTOToReglement(ReglementDTO dto, Reglement domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeAdmission(dto.getCodeAdmission());
            domaine.setCodeNatureAdmission(dto.getCodeNatureAdmission());
            if (domaine.getCodeNatureAdmission() != null) {
                domaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dto.getCodeNatureAdmission()));
            }

            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));
            }

            domaine.setCaissier(dto.getCaissier());

            domaine.setMontantBon(dto.getMontantBon());
            domaine.setMontantReglement(dto.getMontantReglement());
            domaine.setTypeReglement(dto.getTypeReglement());
            domaine.setCodeSession(dto.getCodeSession());
            domaine.setDateReglement(dto.getDateReglement());
            domaine.setMontantPEC(dto.getMontantPEC());

            domaine.setNumPiece(dto.getNumPiece());
            domaine.setCodeBanque(dto.getCodeBanque());

            return domaine;
        } else {
            return null;
        }
    }

    public static ReglementDTO reglementToReglementDTO(Reglement domaine) {

        if (domaine != null) {
            ReglementDTO dto = new ReglementDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());

            dto.setCodeAdmission(domaine.getCodeAdmission());

            dto.setNatureAdmissionDTO(NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine.getNatureAdmission()));
            dto.setCodeNatureAdmission(domaine.getCodeNatureAdmission());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setCaissier(domaine.getCaissier());

            dto.setMontantBon(domaine.getMontantBon());
            dto.setMontantReglement(domaine.getMontantReglement());

            dto.setTypeReglement(domaine.getTypeReglement());
            dto.setCodeSession(domaine.getCodeSession());
            dto.setDateReglement(domaine.getDateReglement());

            dto.setMontantPEC(domaine.getMontantPEC());

            dto.setNumPiece(domaine.getNumPiece()); 
            dto.setCodeBanque(domaine.getCodeBanque());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ReglementDTO> listReglementToReglementDTOs(List<Reglement> reglements) {
        List<ReglementDTO> list = new ArrayList<>();
        for (Reglement reglement : reglements) {
            list.add(reglementToReglementDTO(reglement));
        }
        return list;
    }

    public static Collection<ReglementDTO> collectionReglementToReglementDTOs(Collection<Reglement> reglements) {
        Collection<ReglementDTO> collection = new ArrayList<>();
        for (Reglement reglement : reglements) {
            collection.add(reglementToReglementDTO(reglement));
        }
        return collection;
    }
}
