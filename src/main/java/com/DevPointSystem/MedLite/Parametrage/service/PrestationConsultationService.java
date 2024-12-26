/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.domaine.PrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationConsultationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.PrestationConsultationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPrestationConsultationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.PrestationConsultationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationConsultationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.PrestationConsultationRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class PrestationConsultationService {

    private final PrestationConsultationRepo prestationConsultationRepo;

    private final DetailsPrestationConsultationRepo detailsPrestationConsultationRepo;

    public PrestationConsultationService(PrestationConsultationRepo prestationConsultationRepo, DetailsPrestationConsultationRepo detailsPrestationConsultationRepo) {
        this.prestationConsultationRepo = prestationConsultationRepo;
        this.detailsPrestationConsultationRepo = detailsPrestationConsultationRepo;
    }

    @Transactional(readOnly = true)
    public List<PrestationConsultationDTO> findAllPrestationConsultation() {
        return PrestationConsultationFactory.listPrestationConsultationToPrestationConsultationDTOs(prestationConsultationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public PrestationConsultationDTO findOne(Integer code) {
        PrestationConsultation domaine = prestationConsultationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PrestationConsultationNotFound");

        return PrestationConsultationFactory.prestationConsultationToPrestationConsultationDTO(domaine);
    }

    public PrestationConsultationDTO save(PrestationConsultationDTO dto) {
        PrestationConsultation domaine = PrestationConsultationFactory.prestationConsultationDTOToPrestationConsultation(dto, new PrestationConsultation());
        domaine = prestationConsultationRepo.save(domaine);
        return PrestationConsultationFactory.prestationConsultationToPrestationConsultationDTO(domaine);
    }

    public PrestationConsultationDTO updateNewWithFlush(PrestationConsultationDTO dto) {
        PrestationConsultation inBase = prestationConsultationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.PrestationConsultationNotFound");
        detailsPrestationConsultationRepo.deleteByFkPrestationConsultation(dto.getCode());
        inBase = PrestationConsultationFactory.prestationConsultationDTOToPrestationConsultation(dto, inBase);
        inBase = prestationConsultationRepo.save(inBase);
        PrestationConsultationDTO resultDTO = PrestationConsultationFactory.prestationConsultationToPrestationConsultationDTO(inBase);
        return resultDTO;
    }

    public void deletePrestationConsultation(Integer code) {
        Preconditions.checkArgument(prestationConsultationRepo.existsById(code), "error.PrestationConsultationNotFound");
        prestationConsultationRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPrestationConsultationDTO> findOneWithDetails(Integer code) {
        Collection<DetailsPrestationConsultation> domaine = detailsPrestationConsultationRepo.findByDetailsPrestationConsultationPK_fkPrestationConsultation(code);
        return DetailsPrestationConsultationFactory.detailsPrestationConsultationTodetailsPrestationConsultationDTOCollections(domaine);
    }
}
