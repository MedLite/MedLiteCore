/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.Caisse;
import com.DevPointSystem.MedLite.Parametrage.dto.CaisseDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface CaisseRepo extends JpaRepository<Caisse, Integer> {

//      List<Caisse> findByCodeNotIn(Integer[] codes);
    List<Caisse> findByCodeNotAndCodeDevise(Integer code,Integer codeDevise);

    List<Caisse> findByCodeTypeCaisse(Integer codeTypeCaisse);  
    
    List<Caisse> findByCodeDevise(Integer codeDevise);


    Caisse findByCode(Integer code);

}
