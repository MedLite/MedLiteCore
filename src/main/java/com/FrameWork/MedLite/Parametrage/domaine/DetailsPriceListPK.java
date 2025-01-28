/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsPriceListPK {
 
     @Column(name = "Code_PriceList")
    private int codePriceList;
    @Column(name = "Code_Prestation")
    private int codePrestation;
    @Column(name = "Code_Nature_Admission")
    private int codeNatureAdmission;
    @Column(name = "Code_Type_Intervenant")
    private int codeTypeIntervenant;

    public DetailsPriceListPK(int codePriceList, int codePrestation, int codeNatureAdmission, int codeTypeIntervenant) {
        this.codePriceList = codePriceList;
        this.codePrestation = codePrestation;
        this.codeNatureAdmission = codeNatureAdmission;
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public int getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(int codePriceList) {
        this.codePriceList = codePriceList;
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

    public int getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(int codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public int getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(int codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }
    
    
    
    
    
}
