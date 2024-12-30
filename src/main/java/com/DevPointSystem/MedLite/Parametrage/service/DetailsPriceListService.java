/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.repository.DetailsPriceListRepo;

/**
 *
 * @author Administrator
 */
public class DetailsPriceListService {

    private final DetailsPriceListRepo detailsPriceListRepo;

    public DetailsPriceListService(DetailsPriceListRepo detailsPriceListRepo) {
        this.detailsPriceListRepo = detailsPriceListRepo;
    }

    public Boolean deleteByCodePriceList(Integer codePriceList) {
        detailsPriceListRepo.deleteByCodePriceList(codePriceList);
        return true;
    }
}
