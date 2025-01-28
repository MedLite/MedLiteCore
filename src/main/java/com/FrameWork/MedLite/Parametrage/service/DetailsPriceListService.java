/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.FrameWork.MedLite.web.errors.IllegalBusinessLogiqueException;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsPriceListService {

    private final Logger log = LoggerFactory.getLogger(DetailsPriceListService.class);

    private final DetailsPriceListRepo detailsPriceListRepo;

    public DetailsPriceListService(DetailsPriceListRepo detailsPriceListRepo) {
        this.detailsPriceListRepo = detailsPriceListRepo;
    }

//    public Boolean deleteByCodePriceList(Integer codePriceList) {
//        detailsPriceListRepo.deleteByCodePriceList(codePriceList);
//        return true;
//    }
//
    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO> findOneWithCodePriceListAndCodePrestationAndCodeNatureAdmission(Integer codePriceList, Integer codePrestation, Integer codeNatureAdmission) {
        List<DetailsPriceList> domaine = detailsPriceListRepo.findByCodePriceListAndCodePrestationAndCodeNatureAdmission(codePriceList, codePrestation, codeNatureAdmission);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO> findOneWithCodePriceListAndCodePrestation(Integer codePriceList, Integer codePrestation) {
        List<DetailsPriceList> domaine = detailsPriceListRepo.findByCodePriceListAndCodePrestation(codePriceList, codePrestation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO> findOneWithCodePriceListAndCodeNatureAdmission(Integer codePriceList, Integer codeNatureAdmission) {
        List<DetailsPriceList> domaine = detailsPriceListRepo.findByCodePriceListAndCodeNatureAdmission(codePriceList, codeNatureAdmission);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOs(domaine);
    }

 public DetailsPriceListDTO save(DetailsPriceListDTO dto) {
        DetailsPriceList domaine = DetailsPriceListFactory.DetailspriceListDTOToDetailsPriceList(dto, new DetailsPriceList());
 
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsPriceListRepo.save(domaine);

        return DetailsPriceListFactory.DetailsPriceListToDetailsPriceListDTO(domaine);
    }
 
  public DetailsPriceList update(DetailsPriceListDTO dto) {
        DetailsPriceList domaine = detailsPriceListRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceList");
        dto.setCode(domaine.getCode());
        DetailsPriceListFactory.DetailspriceListDTOToDetailsPriceList(dto, domaine);

        return detailsPriceListRepo.save(domaine);
    }
  
    public void deleteDetailsPriceList(Integer code) {
        Preconditions.checkArgument(detailsPriceListRepo.existsById(code), "error.DetailsPriceList");
        detailsPriceListRepo.deleteById(code);
    }
    
    
  
}
