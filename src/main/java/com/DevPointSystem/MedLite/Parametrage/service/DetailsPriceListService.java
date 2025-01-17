/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsPriceListService {

    private final DetailsPriceListRepo detailsPriceListRepo;

    public DetailsPriceListService(DetailsPriceListRepo detailsPriceListRepo) {
        this.detailsPriceListRepo = detailsPriceListRepo;
    }

    public Boolean deleteByCodePriceList(Integer codePriceList) {
        detailsPriceListRepo.deleteByCodePriceList(codePriceList);
        return true;
    }
    
    
       @Transactional(readOnly = true)
    public Collection<DetailsPriceListDTO> findOneWithCodePriceListAndCodePrestationAndCodeNatureAdmission(Integer codePriceList,Integer codePrestation,Integer codeNatureAdmission) {
        Collection<DetailsPriceList> domaine = detailsPriceListRepo.findByDetailsPriceListPK_codePriceListAndCodePrestationAndCodeNatureAdmission(codePriceList,codePrestation,codeNatureAdmission);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOCollections(domaine);
    }
}
