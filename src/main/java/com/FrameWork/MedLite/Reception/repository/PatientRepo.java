/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Reception.repository;

import com.FrameWork.MedLite.Reception.domaine.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer>{
     List<Patient> findByCodeSociete(Integer codeSociete);    
     List<Patient> findByCodeConvention(Integer codeConvention);

    Patient findByCodeSaisie(String codeSaisie); 
    Patient findByCode(Integer code);
    
      List<Patient> findByNomCompltArContainingIgnoreCaseOrNomCompltLtContainingIgnoreCase(String nomCompltAr, String nomCompltLt); 
}
