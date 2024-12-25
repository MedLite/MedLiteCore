/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamillePrestation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamillePrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.FamillePrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.FamillePrestationRepo;
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
public class FamillePrestationService {
    private final FamillePrestationRepo famillePrestationRepo;

    public FamillePrestationService(FamillePrestationRepo famillePrestationRepo) {
        this.famillePrestationRepo = famillePrestationRepo;
    }

    @Transactional(readOnly = true)
    public List<FamillePrestationDTO> findAllFamillePrestation() {
        return FamillePrestationFactory.listFamillePrestationToFamillePrestationDTOs(famillePrestationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public FamillePrestationDTO findOne(Integer code) {
        FamillePrestation domaine = famillePrestationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FamillePrestationNotFound");
        return FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine);
    }

    public FamillePrestationDTO save(FamillePrestationDTO dto) {
        FamillePrestation domaine = FamillePrestationFactory.famillePrestationDTOToFamillePrestation(dto, new FamillePrestation());

        domaine = famillePrestationRepo.save(domaine);

        return FamillePrestationFactory.famillePrestationToFamillePrestationDTO(domaine);
    }

    public FamillePrestation update(FamillePrestationDTO dto) {
        FamillePrestation domaine = famillePrestationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FamillePrestationNotFound");
        dto.setCode(domaine.getCode());
        FamillePrestationFactory.famillePrestationDTOToFamillePrestation(dto, domaine);
        return famillePrestationRepo.save(domaine);
    }

    public void deleteFamillePrestation(Integer code) {
        Preconditions.checkArgument(famillePrestationRepo.existsById(code), "error.FamillePrestationNotFound");
        famillePrestationRepo.deleteById(code);
    }
}