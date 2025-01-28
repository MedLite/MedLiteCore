/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;
 
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPriceListOperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListOperationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsPriceListOperationService {
      private final Logger log = LoggerFactory.getLogger(DetailsPriceListOperationService.class);

    private final DetailsPriceListOperationRepo detailsPriceListOperationRepo;

    public DetailsPriceListOperationService(DetailsPriceListOperationRepo detailsPriceListOperationRepo) {
        this.detailsPriceListOperationRepo = detailsPriceListOperationRepo;
    }

    
    @Transactional(readOnly = true)
    public List<DetailsPriceListOperationDTO> findOneWithCodePriceListOperationAndCodeOperation(Integer codePriceListOperation, Integer codeOperation) {
        List<DetailsPriceListOperation> domaine = detailsPriceListOperationRepo.findByCodePriceListAndCodeOperation(codePriceListOperation, codeOperation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
        return DetailsPriceListOperationFactory.detailsPriceListOperationTodetailsPriceListOperationDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsPriceListOperationDTO> findOneWithCodeOperation(Integer codeOperation) {
        List<DetailsPriceListOperation> domaine = detailsPriceListOperationRepo.findByCodeOperation(codeOperation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
        return DetailsPriceListOperationFactory.detailsPriceListOperationTodetailsPriceListOperationDTOs(domaine);
    }

   
 public DetailsPriceListOperationDTO save(DetailsPriceListOperationDTO dto) {
        DetailsPriceListOperation domaine = DetailsPriceListOperationFactory.DetailspriceListOperationDTOToDetailsPriceListOperation(dto, new DetailsPriceListOperation());
 
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsPriceListOperationRepo.save(domaine);

        return DetailsPriceListOperationFactory.DetailsPriceListOperationToDetailsPriceListOperationDTO(domaine);
    }
 
  public DetailsPriceListOperation update(DetailsPriceListOperationDTO dto) {
        DetailsPriceListOperation domaine = detailsPriceListOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
        dto.setCode(domaine.getCode());
        DetailsPriceListOperationFactory.DetailspriceListOperationDTOToDetailsPriceListOperation(dto, domaine);

        return detailsPriceListOperationRepo.save(domaine);
    }
  
    public void deleteDetailsPriceListOperation(Integer code) {
        Preconditions.checkArgument(detailsPriceListOperationRepo.existsById(code), "error.DetailsPriceListOperationNotFound");
        detailsPriceListOperationRepo.deleteById(code);
    }
    
      public void deleteDetailsPriceListOperationByCodeOperation(Integer code) { 
        detailsPriceListOperationRepo.deleteByCodeOperation(code);
    }
    
}
