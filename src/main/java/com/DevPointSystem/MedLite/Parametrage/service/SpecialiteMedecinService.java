/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.SpecialiteMedecin;
import com.DevPointSystem.MedLite.Parametrage.dto.SpecialiteMedecinDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.SpecialiteMedecinFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.SpecialiteMedecinRepo;
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
public class SpecialiteMedecinService {
      private final SpecialiteMedecinRepo specialiteMedecinRepo;

    public SpecialiteMedecinService(SpecialiteMedecinRepo specialiteMedecinRepo) {
        this.specialiteMedecinRepo = specialiteMedecinRepo;
    }

    @Transactional(readOnly = true)
    public List<SpecialiteMedecinDTO> findAllSpecialiteMedecin() {
        return SpecialiteMedecinFactory.listSpecialiteMedecinToSpecialiteMedecinDTOs(specialiteMedecinRepo.findAll());

    }

    @Transactional(readOnly = true)
    public SpecialiteMedecinDTO findOne(Integer code) {
        SpecialiteMedecin domaine = specialiteMedecinRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SpecialiteMedecinNotFound");
        return SpecialiteMedecinFactory.specialiteMedecinToSpecialiteMedecinDTO(domaine);
    }

    public SpecialiteMedecinDTO save(SpecialiteMedecinDTO dto) {
        SpecialiteMedecin domaine = SpecialiteMedecinFactory.specialiteMedecinDTOToSpecialiteMedecin(dto, new SpecialiteMedecin());

        domaine = specialiteMedecinRepo.save(domaine);

        return SpecialiteMedecinFactory.specialiteMedecinToSpecialiteMedecinDTO(domaine);
    }

    public SpecialiteMedecin update(SpecialiteMedecinDTO dto) {
        SpecialiteMedecin domaine = specialiteMedecinRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SpecialiteMedecinNotFound");
        dto.setCode(domaine.getCode());
        SpecialiteMedecinFactory.specialiteMedecinDTOToSpecialiteMedecin(dto, domaine);
        return specialiteMedecinRepo.save(domaine);
    }

    public void deleteSpecialiteMedecin(Integer code) {
        Preconditions.checkArgument(specialiteMedecinRepo.existsById(code), "error.SpecialiteMedecinNotFound");
        specialiteMedecinRepo.deleteById(code);
    }
}
