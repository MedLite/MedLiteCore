/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationPK;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsPrestationRepo extends JpaRepository<DetailsPrestation, DetailsPrestationPK> {

    Collection<DetailsPrestation> findByDetailsPrestationPK_codePrestation(Integer codePrestation);  
    
    Collection<DetailsPrestation> findByDetailsPrestationPK_codePrestationAndCodeNatureAdmission(Integer codePrestation,Integer codeNatureAdmission);


    @Modifying
    @Query("delete from DetailsPrestation det where det.detailsPrestationPK.codePrestation=?1 ")
    public void deleteByCodePrestation(Integer codePrestation);

}