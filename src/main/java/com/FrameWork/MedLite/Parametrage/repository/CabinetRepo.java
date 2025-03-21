/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.domaine.Ville;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface CabinetRepo extends JpaRepository<Cabinet, Integer> {

    Cabinet findByCode(Integer code);

    List<Cabinet> findByCodeSpecialiteCabinet(Integer codeSpecialiteCabinet);
 List<Cabinet> findByActif(Boolean actif);

}
