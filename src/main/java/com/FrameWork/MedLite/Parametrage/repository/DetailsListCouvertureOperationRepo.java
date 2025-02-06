/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouvertureOperation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsListCouvertureOperationRepo extends JpaRepository<DetailsListCouvertureOperation, Integer> {

    DetailsListCouvertureOperation findByCode(Integer code);

    DetailsListCouvertureOperation findByCodeOperation(Integer codeOperation);

    List<DetailsListCouvertureOperation> findByCodeListCouverture(Integer codeListCouverture);

    DetailsListCouvertureOperation findByCodeNatureAdmission(Integer codeNatureAdmission);

    List<DetailsListCouvertureOperation> findByCodeListCouvertureAndCodeNatureAdmission(Integer codeListCouverture, Integer codeNatureAdmission);

    List<DetailsListCouvertureOperation> findByCodeListCouvertureAndCodeOperation(Integer codeListCouverture, Integer codeOperation);

    List<DetailsListCouvertureOperation> findByCodeListCouvertureAndCodeOperationAndCodeNatureAdmission(Integer codeListCouverture, Integer codeOperation, Integer codeNatureAdmission);

    
    public void deleteByCodeOperation(Integer codeOperation);   
    public void deleteByCodeListCouverture(Integer codeListCouverture);
    
     boolean existsByCodeOperationAndCodeNatureAdmission(int codeOperation, int codeNatureAdmission);


}
