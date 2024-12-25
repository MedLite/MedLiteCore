/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Recette.repository;

import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaissePK;
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
public interface DetailsAlimentationCaisseRepo extends JpaRepository<DetailsAlimentationCaisse, DetailsAlimentationCaissePK> {

    Collection<DetailsAlimentationCaisse> findByDetailsAlimentationCaissePK_codeAlimentationCaisse(Integer codeAlimentationCaisse);

    @Modifying
    @Query("delete from DetailsAlimentationCaisse det where det.detailsAlimentationCaissePK.codeAlimentationCaisse=?1 ")
    public void deleteByCodeAlimentationCaisse(Integer codeAlimentationCaisse);

}
