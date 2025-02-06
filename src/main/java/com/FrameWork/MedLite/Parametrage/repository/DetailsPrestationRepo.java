/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestationPK;
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
public interface DetailsPrestationRepo extends JpaRepository<DetailsPrestation, DetailsPrestationPK> {

    Collection<DetailsPrestation> findByDetailsPrestationPK_codePrestation(Integer codePrestation);

    Collection<DetailsPrestation> findByDetailsPrestationPK_codePrestationAndCodeNatureAdmission(Integer codePrestation, Integer codeNatureAdmission);

    List<DetailsPrestation> findByDetailsPrestationPK_CodePrestation(int codePrestation);  
    List<DetailsPrestation> findByDetailsPrestationPK_CodePrestationIn(Collection<Integer> codePrestation);


    @Query(value = "SELECT * FROM param.Details_Prestation WHERE Code_Prestation IN (:codePrestations)", nativeQuery = true)
    List<DetailsPrestation> findByCodePrestationInNative(@Param("codePrestations") Collection<Integer> codePrestations);

    @Modifying
    @Query("delete from DetailsPrestation det where det.detailsPrestationPK.codePrestation=?1 ")
    public void deleteByCodePrestation(Integer codePrestation);

//     boolean existsByCodePrestationAndCodeNatureAdmissionAndCodeTypeIntervenant(int codePrestation, int codeNatureAdmission, int codeTypeIntervenant);
}
