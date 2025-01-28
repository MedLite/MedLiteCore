/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.repository;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverturePK;
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
public interface DetailsListCouvertureRepo extends JpaRepository<DetailsListCouverture, DetailsListCouverturePK> {

    Collection<DetailsListCouverture> findByDetailsListCouverturePK_codeListCouverture(Integer codeListCouverture);

    @Modifying
    @Query("delete from DetailsListCouverture det where det.detailsListCouverturePK.codeListCouverture=?1 ")
    public void deleteByCodeListCouverture(Integer codeListCouverture);

}
