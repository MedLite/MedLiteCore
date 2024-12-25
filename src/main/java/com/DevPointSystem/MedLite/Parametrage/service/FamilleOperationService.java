/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.FamilleOperation;
import com.DevPointSystem.MedLite.Parametrage.dto.FamilleOperationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.FamilleOperationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.FamilleOperationRepo;
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
public class FamilleOperationService {

    private final FamilleOperationRepo familleOperationRepo;

    public FamilleOperationService(FamilleOperationRepo familleOperationRepo) {
        this.familleOperationRepo = familleOperationRepo;
    }

    @Transactional(readOnly = true)
    public List<FamilleOperationDTO> findAllFamilleOperation() {
        return FamilleOperationFactory.listFamilleOperationToFamilleOperationDTOs(familleOperationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public FamilleOperationDTO findOne(Integer code) {
        FamilleOperation domaine = familleOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FamilleOperationNotFound");
        return FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine);
    }

    public FamilleOperationDTO save(FamilleOperationDTO dto) {
        FamilleOperation domaine = FamilleOperationFactory.familleOperationDTOToFamilleOperation(dto, new FamilleOperation());

        domaine = familleOperationRepo.save(domaine);

        return FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine);
    }

    public FamilleOperation update(FamilleOperationDTO dto) {
        FamilleOperation domaine = familleOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FamilleOperationNotFound");
        dto.setCode(domaine.getCode());
        FamilleOperationFactory.familleOperationDTOToFamilleOperation(dto, domaine);
        return familleOperationRepo.save(domaine);
    }

    public void deleteFamilleOperation(Integer code) {
        Preconditions.checkArgument(familleOperationRepo.existsById(code), "error.FamilleOperationNotFound");
        familleOperationRepo.deleteById(code);
    }
}
