/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.ModeReglement;
import com.FrameWork.MedLite.Parametrage.dto.ModeReglementDTO;
import com.FrameWork.MedLite.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.MedLite.Parametrage.repository.ModeReglementRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ModeReglementService {

    private final ModeReglementRepo modeReglementRepo;

    public ModeReglementService(ModeReglementRepo modeReglementRepo) {
        this.modeReglementRepo = modeReglementRepo;
    }

    @Transactional(readOnly = true)
    public List<ModeReglementDTO> findAllModeReglement() {
        return ModeReglementFactory.listModeReglementToModeReglementDTOs(modeReglementRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public ModeReglementDTO findOne(Integer code) {
        ModeReglement domaine = modeReglementRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.ModeReglementNotFound");
        return ModeReglementFactory.modeReglementToModeReglementDTO(domaine);
    }

//
    public ModeReglementDTO save(ModeReglementDTO dto) {
        ModeReglement domaine = ModeReglementFactory.modeReglementDTOToModeReglement(dto, new ModeReglement());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = modeReglementRepo.save(domaine);
        return ModeReglementFactory.modeReglementToModeReglementDTO(domaine);
    }

    public ModeReglement update(ModeReglementDTO dto) {
        ModeReglement domaine = modeReglementRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(true, "error.ModeReglementNotFound");
        dto.setCode(domaine.getCode());
        ModeReglementFactory.modeReglementDTOToModeReglement(dto, domaine);
        return modeReglementRepo.save(domaine);
    }

    public void deleteModeReglement(Integer code) {
        Preconditions.checkArgument(modeReglementRepo.existsById(code), "error.ModeReglementNotFound");
        modeReglementRepo.deleteById(code);
    }
}
