/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.ResponsableRemise;
import com.FrameWork.MedLite.Parametrage.dto.ResponsableRemiseDTO;
import com.FrameWork.MedLite.Parametrage.factory.ResponsableRemiseFactory;
import com.FrameWork.MedLite.Parametrage.repository.ResponsableRemiseRepo;
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
public class ResponsableRemiseService {

    private final ResponsableRemiseRepo responsableRemiseRepo;

    public ResponsableRemiseService(ResponsableRemiseRepo responsableRemiseRepo) {
        this.responsableRemiseRepo = responsableRemiseRepo;
    }

    @Transactional(readOnly = true)
    public List<ResponsableRemiseDTO> findAllResponsableRemise() {
        return ResponsableRemiseFactory.listResponsableRemiseToResponsableRemiseDTOs(responsableRemiseRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public ResponsableRemiseDTO findOne(Integer code) {
        ResponsableRemise domaine = responsableRemiseRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.ResponsableRemiseNotFound");
        return ResponsableRemiseFactory.responsableRemiseToResponsableRemiseDTO(domaine);
    }

//
    public ResponsableRemiseDTO save(ResponsableRemiseDTO dto) {
        ResponsableRemise domaine = ResponsableRemiseFactory.responsableRemiseDTOToResponsableRemise(dto, new ResponsableRemise());
            domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = responsableRemiseRepo.save(domaine);
        return ResponsableRemiseFactory.responsableRemiseToResponsableRemiseDTO(domaine);
    }

    public ResponsableRemise update(ResponsableRemiseDTO dto) {
        ResponsableRemise domaine = responsableRemiseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.ResponsableRemiseNotFound");
        dto.setCode(domaine.getCode());
        ResponsableRemiseFactory.responsableRemiseDTOToResponsableRemise(dto, domaine);
        return responsableRemiseRepo.save(domaine);
    }

    public void deleteResponsableRemise(Integer code) {
        Preconditions.checkArgument(responsableRemiseRepo.existsById(code), "error.ResponsableRemiseNotFound");
        responsableRemiseRepo.deleteById(code);
    }
}
