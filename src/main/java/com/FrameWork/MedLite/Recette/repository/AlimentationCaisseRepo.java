/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Recette.repository;

import com.FrameWork.MedLite.Recette.domaine.AlimentationCaisse;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AlimentationCaisseRepo extends JpaRepository<AlimentationCaisse, Integer> {

    List<AlimentationCaisse> findByCodeCaisseIn(Collection<Integer> codeCaisse);

    List<AlimentationCaisse> findByCodeDeviseIn(Collection<Integer> codeDevise);

    List<AlimentationCaisse> findAlimentationCaisseByCodeEtatApprouver(Integer codeEtatApprouver);

    List<AlimentationCaisse> findAllByOrderByCodeSaisieDesc();    
    
    AlimentationCaisse findByCode( Integer code);


}
