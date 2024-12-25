/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.repository;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsOperation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsOperationPK;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsOperationRepo extends JpaRepository<DetailsOperation, DetailsOperationPK> {

    Collection<DetailsOperation> findByDetailsOperationPK_codeOperation(Integer codeOperation);

    @Modifying
    @Query("delete from DetailsOperation det where det.detailsOperationPK.codeOperation=?1 ")
    public void deleteByCodeOperation(Integer codeOperation);

}
