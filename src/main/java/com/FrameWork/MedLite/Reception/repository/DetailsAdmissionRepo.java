/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Reception.repository;

import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsAdmissionRepo extends JpaRepository<DetailsAdmission, Integer>{
    DetailsAdmission findByCode (Integer code);
    
    DetailsAdmission findByCodeAdmission (Integer codeAdmission);
    
    DetailsAdmission findByCodeAdmissionAndEtatPaiement (Integer codeAdmission, Boolean etatPaiement);
    
}
