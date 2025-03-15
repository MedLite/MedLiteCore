/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Reception.repository;

import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.AdmissionFacturation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AdmissionFacturationRepo  extends JpaRepository<AdmissionFacturation, Integer> {

    AdmissionFacturation findByCode(Integer code);  
    AdmissionFacturation findByCodeAdmission(Integer codeAdmission);


    List<AdmissionFacturation> findByCodeEtatPaiement(Integer codeEtatPaiement);

    List<AdmissionFacturation> findByCodeSociete(Integer codeSociete);

    List<AdmissionFacturation> findByCodeConvention(Integer codeConvention);

}
