/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperationPK;
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
public interface DetailsOperationRepo extends JpaRepository<DetailsOperation, DetailsOperationPK> {

    Collection<DetailsOperation> findByDetailsOperationPK_codeOperation(Integer codeOperation);

    List<DetailsOperation> findByDetailsOperationPK_CodeOperation(int codeOperation);
    List<DetailsOperation> findByDetailsOperationPK_CodeOperationIn(Collection<Integer> codeOperation);

//    @Query(value = "SELECT * FROM param.Details_Operation WHERE Code_Operation IN (:codeOperations)", nativeQuery = true)
//    List<DetailsOperation> findByCodeOperationInNative(@Param("codeOperations") Collection<Integer> codeOperations);

    @Modifying
    @Query("delete from DetailsOperation det where det.detailsOperationPK.codeOperation=?1 ")
    public void deleteByCodeOperation(Integer codeOperation);

}
