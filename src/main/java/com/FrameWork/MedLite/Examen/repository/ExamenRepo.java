/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Examen.repository;

import com.FrameWork.MedLite.Examen.domaine.Examen;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ExamenRepo extends JpaRepository<Examen, Integer> {

    Examen findByCode(Integer code);

    List<Examen> findByCodeEtatPaiement(Integer codeEtatPaiement);

    List<Examen> findByTypeExamen(String typeExamen);

    List<Examen> findByTypeExamenAndCodeAdmission(String typeExamen, Integer codeAdmission);

    List<Examen> findByTypeExamenAndCodePatient(String typeExamen, Integer codePatient);

    List<Examen> findByCodeEtatPaiementAndCodeAdmission(Integer codeEtatPaiement, Integer codeAdmission);

    List<Examen> findByCodeEtatPaiementAndCodePatient(Integer codeEtatPaiement, Integer codePatient);

    List<Examen> findByCodePatient(Integer codePatient);

    List<Examen> findByCodeAdmission(Integer codeAdmission);

//    List<Examen> findByCodeNatureAdmission(Integer codeNatureAdmission);
}
