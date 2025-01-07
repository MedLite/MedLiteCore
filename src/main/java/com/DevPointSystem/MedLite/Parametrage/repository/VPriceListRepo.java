/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.VPriceList;
import com.DevPointSystem.MedLite.Parametrage.domaine.VPriceListPK;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface VPriceListRepo extends JpaRepository<VPriceList, VPriceListPK>{
    
    Collection<VPriceList> findByvPriceListPK_codePriceList(Integer codePriceList);

}
