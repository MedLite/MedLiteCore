/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestationConsultation;
import com.FrameWork.MedLite.Parametrage.domaine.PrestationConsultation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationConsultationDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationConsultationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPrestationConsultationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationConsultationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPrestationConsultationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PrestationConsultationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Date;
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

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = prestationConsultationRepo.save(domaine);
        return PrestationConsultationFactory.prestationConsultationToPrestationConsultationDTO(domaine);
    }

    public PrestationConsultationDTO updateNewWithFlush(PrestationConsultationDTO dto) {
        PrestationConsultation domaine = prestationConsultationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.PrestationConsultationNotFound");
        detailsPrestationConsultationRepo.deleteByCodePrestConsult(dto.getCode());
        domaine = PrestationConsultationFactory.prestationConsultationDTOToPrestationConsultation(dto, domaine);
        domaine = prestationConsultationRepo.save(domaine);
        PrestationConsultationDTO resultDTO = PrestationConsultationFactory.prestationConsultationToPrestationConsultationDTO(domaine);
        return resultDTO;
    }

    public void deletePrestationConsultation(Integer code) {
        Preconditions.checkArgument(prestationConsultationRepo.existsById(code), "error.PrestationConsultationNotFound");
        prestationConsultationRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPrestationConsultationDTO> findOneWithDetails(Integer code) {
        Collection<DetailsPrestationConsultation> domaine = detailsPrestationConsultationRepo.findByDetailsPrestationConsultationPK_codePrestConsult(code);
        return DetailsPrestationConsultationFactory.detailsPrestationConsultationTodetailsPrestationConsultationDTOCollections(domaine);
    }
}
