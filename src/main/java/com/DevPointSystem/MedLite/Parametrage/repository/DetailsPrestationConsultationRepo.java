/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationConsultation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestationConsultationPK; 
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
public interface DetailsPrestationConsultationRepo extends JpaRepository<DetailsPrestationConsultation, DetailsPrestationConsultationPK> {

    Collection<DetailsPrestationConsultation> findByDetailsPrestationConsultationPK_fkPrestationConsultation(Integer fkPrestationConsultation);

    @Modifying
    @Query("delete from DetailsPrestationConsultation det where det.detailsPrestationConsultationPK.fkPrestationConsultation=?1 ")
    public void deleteByFkPrestationConsultation(Integer fkPrestationConsultation);

}
