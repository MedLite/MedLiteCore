/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.repository.DetailsListCouvertureRepo;

/**
 *
 * @author Administrator
 */
public class DetailsListCouvertureService {

    private final DetailsListCouvertureRepo detailsListCouvertureRepo;

    public DetailsListCouvertureService(DetailsListCouvertureRepo detailsListCouvertureRepo) {
        this.detailsListCouvertureRepo = detailsListCouvertureRepo;
    }

    public Boolean deleteByCodeListCouverture(Integer codeListCouverture) {
        detailsListCouvertureRepo.deleteByCodeListCouverture(codeListCouverture);
        return true;
    }
}
