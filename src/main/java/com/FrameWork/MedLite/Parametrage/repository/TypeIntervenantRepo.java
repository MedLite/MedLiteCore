/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.TypeIntervenant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface TypeIntervenantRepo extends JpaRepository<TypeIntervenant, Integer> {

    TypeIntervenant findByCode(Integer code);

    List<TypeIntervenant> findByIsClinique(Boolean isClinique);

    List<TypeIntervenant> findByActif(Boolean actif);
    
        List<TypeIntervenant> findByActifAndVirtuel(Boolean actif,Boolean virtuel);


}
