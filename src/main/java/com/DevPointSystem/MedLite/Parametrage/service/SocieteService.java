/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Societe;
import com.DevPointSystem.MedLite.Parametrage.dto.SocieteDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.SocieteFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.SocieteRepo;
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
public class SocieteService {
       private final SocieteRepo societeRepo;

    public SocieteService(SocieteRepo societeRepo) {
        this.societeRepo = societeRepo;
    }

    @Transactional(readOnly = true)
    public List<SocieteDTO> findAllSociete() {
        return SocieteFactory.listSocieteToSocieteDTOs(societeRepo.findAll());

    }

    @Transactional(readOnly = true)
    public SocieteDTO findOne(Integer code) {
        Societe domaine = societeRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SocieteNotFound");
        return SocieteFactory.societeToSocieteDTO(domaine);
    }

    public SocieteDTO save(SocieteDTO dto) {
        Societe domaine = SocieteFactory.societeDTOToSociete(dto, new Societe());

        domaine = societeRepo.save(domaine);

        return SocieteFactory.societeToSocieteDTO(domaine);
    }

    public Societe update(SocieteDTO dto) {
        Societe domaine = societeRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SocieteNotFound");
        dto.setCode(domaine.getCode());
        SocieteFactory.societeDTOToSociete(dto, domaine);
        return societeRepo.save(domaine);
    }

    public void deleteSociete(Integer code) {
        Preconditions.checkArgument(societeRepo.existsById(code), "error.SocieteNotFound");
        societeRepo.deleteById(code);
    }
}
