/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsOperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsOperationRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
public class DetailsOperationService {

    private final DetailsOperationRepo detailsOperationRepo;

    public DetailsOperationService(DetailsOperationRepo detailsOperationRepo) {
        this.detailsOperationRepo = detailsOperationRepo;
    }

    public Boolean deleteByCodeOperation(Integer codeOperation) {
        detailsOperationRepo.deleteByCodeOperation(codeOperation);
        return true;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsOperationDTO> findOne(Integer code) {
        Collection<DetailsOperation> domaine = detailsOperationRepo.findByDetailsOperationPK_codeOperation(code);
        Preconditions.checkArgument(domaine != null, "error.DeviseNotFound");
        return DetailsOperationFactory.detailsOperationTodetailsOperationDTOCollections(domaine);
    }
}
