/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.BlocOperation;
import com.DevPointSystem.MedLite.Parametrage.dto.BlocOperationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.BlocOperationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.BlocOperationRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
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
public class BlocOperationService {

    private final BlocOperationRepo blocOperationRepo;

    public BlocOperationService(BlocOperationRepo blocOperationRepo) {
        this.blocOperationRepo = blocOperationRepo;
    }

    @Transactional(readOnly = true)
    public List<BlocOperationDTO> findAllBlocOperation() {
        return BlocOperationFactory.listBlocOperationToBlocOperationDTOs(blocOperationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public BlocOperationDTO findOne(Integer code) {
        BlocOperation domaine = blocOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.BlocOperationNotFound");
        return BlocOperationFactory.blocOperationToBlocOperationDTO(domaine);
    }

    public BlocOperationDTO save(BlocOperationDTO dto) {
        BlocOperation domaine = BlocOperationFactory.blocOperationDTOToBlocOperation(dto, new BlocOperation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = blocOperationRepo.save(domaine);

        return BlocOperationFactory.blocOperationToBlocOperationDTO(domaine);
    }

    public BlocOperation update(BlocOperationDTO dto) {
        BlocOperation domaine = blocOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.BlocOperationNotFound");
        dto.setCode(domaine.getCode());
        BlocOperationFactory.blocOperationDTOToBlocOperation(dto, domaine);
        return blocOperationRepo.save(domaine);
    }

    public void deleteBlocOperation(Integer code) {
        Preconditions.checkArgument(blocOperationRepo.existsById(code), "error.BlocOperationNotFound");
        blocOperationRepo.deleteById(code);
    }
}
