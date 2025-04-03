/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.DMI.repository;

import com.FrameWork.MedLite.DMI.domaine.PastHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PastHistoryRepo extends JpaRepository<PastHistory, Integer>{
    
    List<PastHistory> findByCodeAdmission (Integer codeAdmission); 
    
      public void deleteByCodeAdmission(Integer codeAdmission);
    
}
