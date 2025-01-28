/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsPriceListOperationPK {
     @Column(name = "Code_PriceList")
    private int codePriceList;
    @Column(name = "Code_Operation")
    private int codeOperation; 
    @Column(name = "Code_Type_Intervenant")
    private int codeTypeIntervenant;

    public DetailsPriceListOperationPK(int codePriceList, int codeOperation, int codeTypeIntervenant) {
        this.codePriceList = codePriceList;
        this.codeOperation = codeOperation;
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public int getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(int codePriceList) {
        this.codePriceList = codePriceList;
    }

    public int getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(int codeOperation) {
        this.codeOperation = codeOperation;
    }

    public int getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(int codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

   
 
    
}
