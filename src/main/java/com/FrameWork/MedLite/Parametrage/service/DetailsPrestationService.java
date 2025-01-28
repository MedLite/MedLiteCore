/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.Devise;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.DeviseDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.DeviseFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
public class DetailsPrestationService {

    private final DetailsPrestationRepo detailsPrestationRepo;

    public DetailsPrestationService(DetailsPrestationRepo detailsPrestationRepo) {
        this.detailsPrestationRepo = detailsPrestationRepo;
    }

    public Boolean deleteByCodePrestation(Integer codePrestation) {
        detailsPrestationRepo.deleteByCodePrestation(codePrestation);
        return true;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsPrestationDTO> findOne(Integer code) {
        Collection<DetailsPrestation> domaine = detailsPrestationRepo.findByDetailsPrestationPK_codePrestation(code);
        Preconditions.checkArgument(domaine != null, "error.DeviseNotFound");
        return DetailsPrestationFactory.detailsPrestationTodetailsPrestationDTOCollections(domaine);
    }
  

}
