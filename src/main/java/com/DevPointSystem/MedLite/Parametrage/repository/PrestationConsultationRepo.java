/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;
 
import com.DevPointSystem.MedLite.Parametrage.domaine.PrestationConsultation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PrestationConsultationRepo extends JpaRepository<PrestationConsultation, Integer> {

    PrestationConsultation findByCode(Integer code);   
    
    List<PrestationConsultation> findByCodeMedecin(Integer codeMedecin);

}
