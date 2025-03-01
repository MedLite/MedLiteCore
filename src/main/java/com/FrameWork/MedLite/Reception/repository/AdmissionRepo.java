/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Reception.repository;

import com.FrameWork.MedLite.Reception.domaine.Admission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Integer> {

    Admission findByCode(Integer code);

    List<Admission> findByEtatPaiement(Boolean etatPaiement);

    List<Admission> findByCodeSociete(Integer codeSociete);

    List<Admission> findByCodeConvention(Integer codeConvention);

}
