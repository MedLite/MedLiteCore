/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;
 
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.VPriceList;
import com.DevPointSystem.MedLite.Parametrage.dto.VPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.VPriceListFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.PrestationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.VPriceListRepo;
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
@Transactional
public class VPriceListService {
    
    
      private final VPriceListRepo vPriceListRepo;

      private final PrestationRepo prestationRepo;

    public VPriceListService(VPriceListRepo vPriceListRepo, PrestationRepo prestationRepo) {
        this.vPriceListRepo = vPriceListRepo;
        this.prestationRepo = prestationRepo;
    }


    

    
    @Transactional(readOnly = true)
    public List<VPriceListDTO> findAllVPriceList() {
        return VPriceListFactory.listvPriceListTovPriceListDTOs(vPriceListRepo.findAll());

    }

    @Transactional(readOnly = true)
    public Collection<VPriceListDTO> findOne(Integer code) {
        Collection<VPriceList> domaine = vPriceListRepo.findByvPriceListPK_codePriceList(code);
        Preconditions.checkArgument(domaine  != null, "error.VPriceListNotFound");
        
        Prestation pres = prestationRepo.findByCode(domaine.iterator().next().getCodePrestation());
        
       
        
        
        
        VPriceListDTO vd = new VPriceListDTO();
        vd.setDesignationArPrestation(pres.getDesignationAr());
        return VPriceListFactory.vPriceListTovPriceListDTOCollections(domaine);
    }

 
  
   
    
}
