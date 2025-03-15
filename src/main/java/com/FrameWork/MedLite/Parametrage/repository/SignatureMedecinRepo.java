/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.ResponsableRemise;
import com.FrameWork.MedLite.Parametrage.domaine.SignatureMedecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface SignatureMedecinRepo extends JpaRepository<SignatureMedecin, Integer> {

    SignatureMedecin findByCode(Integer code);  
    
    SignatureMedecin findByCodeMedecin(Integer codeMedecin);

}
