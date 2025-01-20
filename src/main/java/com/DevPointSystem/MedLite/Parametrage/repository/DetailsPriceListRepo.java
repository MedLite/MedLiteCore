/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPriceListPK;
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

//    Collection<DetailsPriceList> findByDetailsPriceListPK_codePriceList(Integer codePriceList);  
//    
//    Collection<DetailsPriceList> findByDetailsPriceListPK_codePriceListAndCodePrestation(Integer codePriceList,Integer codePrestation);  
//    Collection<DetailsPriceList> findByDetailsPriceListPK_codePriceListAndCodePrestationAndCodeNatureAdmission(Integer codePriceList,Integer codePrestation,Integer codeNatureAdmission);
//    @Modifying
//    @Query("delete from DetailsPriceList det where det.detailsPriceListPK.codePriceList=?1 ")
//    public void deleteByCodePriceList(Integer codePriceList);
//    public void save(List<DetailsPriceList> listDetailsPriceList);

    DetailsPriceList findByCode(Integer code);

    DetailsPriceList findByCodePrestation(Integer codePrestation);

    DetailsPriceList findByCodePriceList(Integer codePriceList);

    DetailsPriceList findByCodeNatureAdmission(Integer codeNatureAdmission);

    List<DetailsPriceList> findByCodePriceListAndCodeNatureAdmission(Integer codePriceList, Integer codeNatureAdmission);

    List<DetailsPriceList> findByCodePriceListAndCodePrestation(Integer codePriceList, Integer codePrestation);

    List<DetailsPriceList> findByCodePriceListAndCodePrestationAndCodeNatureAdmission(Integer codePriceList, Integer codePrestation, Integer codeNatureAdmission);

    
    public void deleteByCodePrestation(Integer codePrestation);
}
