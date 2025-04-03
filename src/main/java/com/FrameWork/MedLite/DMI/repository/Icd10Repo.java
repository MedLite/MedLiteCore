/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.DMI.repository;

import com.FrameWork.MedLite.DMI.domaine.Icd10;
import com.FrameWork.MedLite.Reception.domaine.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface Icd10Repo extends JpaRepository<Icd10, Integer>{
    
      List<Icd10> findByCodeContainingIgnoreCaseOrShortdescriptionContainingIgnoreCaseOrLongdescriptionContainingIgnoreCase(String code, String shortdescription, String longdescription); 
}
