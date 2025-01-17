/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.Devise;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.DeviseDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
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
