/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.EtatPatient;
import com.FrameWork.MedLite.Parametrage.dto.EtatPatientDTO;
import com.FrameWork.MedLite.Parametrage.factory.EtatPatientFactory;
import com.FrameWork.MedLite.Parametrage.repository.EtatPatientRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class EtatPatientService {
     private final EtatPatientRepo etatPaiementOrdreAchatRepo;

    public EtatPatientService(EtatPatientRepo etatPaiementOrdreAchatRepo) {
        this.etatPaiementOrdreAchatRepo = etatPaiementOrdreAchatRepo;
    }

    @Transactional(readOnly = true)
    public List<EtatPatientDTO> findAllEtatPatient() {
        return EtatPatientFactory.listEtatPatientToEtatPatientDTOs(etatPaiementOrdreAchatRepo.findAll());

    }

    @Transactional(readOnly = true)
    public EtatPatientDTO findOne(Integer code) {
        EtatPatient domaine = etatPaiementOrdreAchatRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.EtatPatientNotFound");
        return EtatPatientFactory.etatPatientToEtatPatientDTO(domaine);
    }

}
