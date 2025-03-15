/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.EtatPaiement;
import com.FrameWork.MedLite.Parametrage.dto.EtatPaiementDTO;
import com.FrameWork.MedLite.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.MedLite.Parametrage.repository.EtatPaiementRepo;
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
public class EtatPaiementService {
     private final EtatPaiementRepo etatPaiementOrdreAchatRepo;

    public EtatPaiementService(EtatPaiementRepo etatPaiementOrdreAchatRepo) {
        this.etatPaiementOrdreAchatRepo = etatPaiementOrdreAchatRepo;
    }

    @Transactional(readOnly = true)
    public List<EtatPaiementDTO> findAllEtatPaiement() {
        return EtatPaiementFactory.listEtatPaiementToEtatPaiementDTOs(etatPaiementOrdreAchatRepo.findAll());

    }

    @Transactional(readOnly = true)
    public EtatPaiementDTO findOne(Integer code) {
        EtatPaiement domaine = etatPaiementOrdreAchatRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.EtatPaiementOrdreAchatNotFound");
        return EtatPaiementFactory.etatPaiemenetToEtatPaiementDTO(domaine);
    }

}
