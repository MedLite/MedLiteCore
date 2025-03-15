/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.service;

import com.FrameWork.MedLite.Examen.domaine.DetailsExamen;
import com.FrameWork.MedLite.Examen.dto.DetailsExamenDTO;
import com.FrameWork.MedLite.Examen.factory.DetailsExamenFactory;
import com.FrameWork.MedLite.Examen.repository.DetailsExamenRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
public class DetailsExamenService {
     private final DetailsExamenRepo detailsExamenRepo;

    public DetailsExamenService(DetailsExamenRepo detailsExamenRepo) {
        this.detailsExamenRepo = detailsExamenRepo;
    }

    public Boolean deleteByCodeExamen(Integer codeExamen) {
        detailsExamenRepo.deleteByCodeExamen(codeExamen);
        return true;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsExamenDTO> findOne(Integer code) {
        Collection<DetailsExamen> domaine = detailsExamenRepo.findByDetailsExamenPK_codeExamen(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsExamenNotFound");
        return DetailsExamenFactory.detailsExamenTodetailsExamenDTOCollections(domaine);
    }
  
}
