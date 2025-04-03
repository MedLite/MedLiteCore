/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Examen.repository;

import com.FrameWork.MedLite.Examen.domaine.DetailsExamen;
import com.FrameWork.MedLite.Examen.domaine.DetailsExamenPK; 
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsExamenRepo extends JpaRepository<DetailsExamen , DetailsExamenPK> {

    Collection<DetailsExamen> findByDetailsExamenPK_codeExamen(Integer codeExamen);

//    Collection<DetailsExamen> findByDetailsExamenPK_codePrestationAndCodeNatureAdmission(Integer codeExamen, Integer codeNatureAdmission);
    
        List<DetailsExamen> findByCodePatient(Integer codePatient);


    List<DetailsExamen> findByDetailsExamenPK_CodeExamen(int codeExamen);  
    List<DetailsExamen> findByDetailsExamenPK_CodeExamenIn(Collection<Integer> codeExamen);


    @Query(value = "SELECT * FROM param.Details_Examen WHERE Code_Examen IN (:codeExamens)", nativeQuery = true)
    List<DetailsExamen> findByCodeExamenInNative(@Param("codeExamens") Collection<Integer> codeExamens);

    @Modifying
    @Query("delete from DetailsExamen det where det.detailsExamenPK.codeExamen=?1 ")
    public void deleteByCodeExamen(Integer codeExamen);

 }
