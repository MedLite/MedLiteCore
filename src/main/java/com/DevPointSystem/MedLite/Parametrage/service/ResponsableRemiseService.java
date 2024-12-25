/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.ResponsableRemise;
import com.DevPointSystem.MedLite.Parametrage.dto.ResponsableRemiseDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.ResponsableRemiseFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.ResponsableRemiseRepo;
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
public class ResponsableRemiseService {

    private final ResponsableRemiseRepo responsableRemiseRepo;

    public ResponsableRemiseService(ResponsableRemiseRepo responsableRemiseRepo) {
        this.responsableRemiseRepo = responsableRemiseRepo;
    }

    @Transactional(readOnly = true)
    public List<ResponsableRemiseDTO> findAllResponsableRemise() {
        return ResponsableRemiseFactory.listResponsableRemiseToResponsableRemiseDTOs(responsableRemiseRepo.findAll());

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
