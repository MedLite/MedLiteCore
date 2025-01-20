/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class DetailsPriceListItemDTO {
     private int codeNatureAdmission;
    private int codePrestation;   
    private int codePriceList;

    private BigDecimal montant;

    public DetailsPriceListItemDTO() {
    }

    public int getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(int codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public int getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(int codePriceList) {
        this.codePriceList = codePriceList;
    }

    
    
    
}
