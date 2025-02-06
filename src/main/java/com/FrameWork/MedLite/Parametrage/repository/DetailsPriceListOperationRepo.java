/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsPriceListOperationRepo extends JpaRepository<DetailsPriceListOperation, Integer> {
 
    DetailsPriceListOperation findByCode(Integer code);

    List<DetailsPriceListOperation> findByCodeOperation(Integer codeOperation);

    List<DetailsPriceListOperation> findByCodePriceList(Integer codePriceList);
 
    List<DetailsPriceListOperation> findByCodePriceListAndCodeOperation(Integer codePriceList, Integer codeOperation);
 

    
    public void deleteByCodeOperation(Integer codeOperation);   
    public void deleteByCodePriceList(Integer codePriceList);

}

