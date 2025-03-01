/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Reception.repository;

import com.FrameWork.MedLite.Reception.domaine.PlanningCabinet;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PlanningCabinetRepo extends JpaRepository<PlanningCabinet, Integer> {

    PlanningCabinet findByCode(Integer code);

    List<PlanningCabinet> findByCodeMedecin(Integer codeMedecin);   
    List<PlanningCabinet> findByActif(Boolean actif);


    List<PlanningCabinet> findByCodeCabinet(Integer codeCabinet);

    List<PlanningCabinet> findByCodeCabinetAndCodeMedecin(Integer codeCabinet, Integer codeMedecin);

    
    
    @Query("SELECT ff FROM PlanningCabinet ff WHERE ff.actif =?1 AND  ff.dateExiste BETWEEN ?2  AND ?3")
    Collection<PlanningCabinet> findByActifAndDateExisteBetween(Boolean actif, LocalDate dateDebut, LocalDate dateFin);

       @Query("SELECT ff FROM PlanningCabinet ff WHERE   ff.dateExiste BETWEEN ?1  AND ?2")
    Collection<PlanningCabinet> findAllByDateExisteBetween( LocalDate dateDebut, LocalDate dateFin);
    
    
}
