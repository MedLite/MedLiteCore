/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.DMI.repository;
 
import com.FrameWork.MedLite.DMI.domaine.FeuilleSoinOPD;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface FeuilleSoinOPDRepo extends JpaRepository<FeuilleSoinOPD, Integer>{
    
    FeuilleSoinOPD  findByCodeAdmission (Integer codeAdmission); 
    
      public void deleteByCodeAdmission(Integer codeAdmission);
    
}
