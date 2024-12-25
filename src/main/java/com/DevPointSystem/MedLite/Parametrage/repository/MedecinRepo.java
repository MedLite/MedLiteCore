/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.Medecin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface MedecinRepo extends JpaRepository<Medecin, Integer> {

    Medecin findByCode(Integer code);

    List<Medecin> findByCodeTypeIntervenant(Integer codeTypeIntervenant);

    List<Medecin> findByCodeSpecialiteMedecin(Integer codeSpecialiteMedecin);  
    
    List<Medecin> findByCodeSpecialiteMedecinAndCodeTypeIntervenant(Integer codeSpecialiteMedecin,Integer codeTypeIntervenant);


}
