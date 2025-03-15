/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Reception.repository;

import com.FrameWork.MedLite.Reception.domaine.PlanningCabinet;
import com.FrameWork.MedLite.Reception.domaine.Reglement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ReglementRepo extends JpaRepository<Reglement, Integer> {

    Reglement findByCode(Integer code);

    List<Reglement> findByCodeAdmission(String codeAdmission);

    List<Reglement> findByCaissier(String caissier);

    @Query("SELECT ff FROM Reglement ff WHERE   ff.dateReglement BETWEEN ?1  AND ?2")
    Collection<Reglement> findAllByDateReglementBetween(LocalDate dateDebut, LocalDate dateFin);

}
