/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.Caisse;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PrestationRepo  extends JpaRepository<Prestation, Integer>{
    
    Prestation findByCode(Integer code);
    List<Prestation> findByActif (Boolean actif);
}
