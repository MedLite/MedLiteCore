/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsListCouvertureRepo extends JpaRepository<DetailsListCouverture, Integer> {

    DetailsListCouverture findByCode(Integer code);

    DetailsListCouverture findByCodePrestation(Integer codePrestation);

    List<DetailsListCouverture> findByCodeListCouverture(Integer codeListCouverture);

    DetailsListCouverture findByCodeNatureAdmission(Integer codeNatureAdmission);

    List<DetailsListCouverture> findByCodeListCouvertureAndCodeNatureAdmission(Integer codeListCouverture, Integer codeNatureAdmission);

    List<DetailsListCouverture> findByCodeListCouvertureAndCodePrestation(Integer codeListCouverture, Integer codePrestation);

     DetailsListCouverture  findByCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(Integer codeListCouverture, Integer codePrestation, Integer codeNatureAdmission);

    
    public void deleteByCodePrestation(Integer codePrestation);   
    public void deleteByCodeListCouverture(Integer codeListCouverture);
    
     boolean existsByCodePrestationAndCodeNatureAdmission(int codePrestation, int codeNatureAdmission);


}
