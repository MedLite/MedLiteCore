/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListPK;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsPriceListRepo extends JpaRepository<DetailsPriceList, Integer> {
 
    DetailsPriceList findByCode(Integer code);

    DetailsPriceList findByCodePrestation(Integer codePrestation);

    List<DetailsPriceList> findByCodePriceList(Integer codePriceList);

    DetailsPriceList findByCodeNatureAdmission(Integer codeNatureAdmission);

    List<DetailsPriceList> findByCodePriceListAndCodeNatureAdmission(Integer codePriceList, Integer codeNatureAdmission);

    List<DetailsPriceList> findByCodePriceListAndCodePrestation(Integer codePriceList, Integer codePrestation);

    List<DetailsPriceList> findByCodePriceListAndCodePrestationAndCodeNatureAdmission(Integer codePriceList, Integer codePrestation, Integer codeNatureAdmission);

    
    public void deleteByCodePrestation(Integer codePrestation);   
    public void deleteByCodePriceList(Integer codePriceList);
    
     boolean existsByCodePrestationAndCodeNatureAdmissionAndCodeTypeIntervenant(int codePrestation, int codeNatureAdmission, int codeTypeIntervenant);

}
