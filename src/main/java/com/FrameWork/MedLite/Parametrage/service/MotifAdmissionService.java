/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.MotifAdmission;
import com.FrameWork.MedLite.Parametrage.dto.MotifAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.factory.MotifAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.repository.MotifAdmissionRepo;
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
public class MotifAdmissionService {
     private final MotifAdmissionRepo motifAdmissionRepo;

    public MotifAdmissionService(MotifAdmissionRepo motifAdmissionRepo) {
        this.motifAdmissionRepo = motifAdmissionRepo;
    }

    @Transactional(readOnly = true)
    public List<MotifAdmissionDTO> findAllMotifAdmission() {
        return MotifAdmissionFactory.listMotifAdmissionToMotifAdmissionDTOs(motifAdmissionRepo.findAll(Sort.by("code").descending()));

    }
    
       @Transactional(readOnly = true)
    public List<MotifAdmissionDTO> findAllPrestationByActif(Boolean actif) {
        return MotifAdmissionFactory.listMotifAdmissionToMotifAdmissionDTOs(motifAdmissionRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public MotifAdmissionDTO findOne(Integer code) {
        MotifAdmission domaine = motifAdmissionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.MotifAdmissionNotFound");
        return MotifAdmissionFactory.motifAdmissionToMotifAdmissionDTO(domaine);
    }

//
    public MotifAdmissionDTO save(MotifAdmissionDTO dto) {
        MotifAdmission domaine = MotifAdmissionFactory.motifAdmissionDTOToMotifAdmission(dto, new MotifAdmission());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = motifAdmissionRepo.save(domaine);
        return MotifAdmissionFactory.motifAdmissionToMotifAdmissionDTO(domaine);
    }

    public MotifAdmission update(MotifAdmissionDTO dto) {
        MotifAdmission domaine = motifAdmissionRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(true, "error.MotifAdmissionNotFound");
        dto.setCode(domaine.getCode());
        MotifAdmissionFactory.motifAdmissionDTOToMotifAdmission(dto, domaine);
        return motifAdmissionRepo.save(domaine);
    }

    public void deleteMotifAdmission(Integer code) {
        Preconditions.checkArgument(motifAdmissionRepo.existsById(code), "error.MotifAdmissionNotFound");
        motifAdmissionRepo.deleteById(code);
    }
}
