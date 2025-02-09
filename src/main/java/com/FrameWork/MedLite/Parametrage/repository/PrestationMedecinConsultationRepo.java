/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.PrestationMedecinConsultation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PrestationMedecinConsultationRepo extends JpaRepository<PrestationMedecinConsultation, Integer> {

    PrestationMedecinConsultation findByCodeMedecin(Integer codeMedecin);

    List<PrestationMedecinConsultation> findByCodePrestation(Integer codePrestation);
    
    
    public void deleteByCodeMedecin(Integer codeMedecin);  
    
    

}
