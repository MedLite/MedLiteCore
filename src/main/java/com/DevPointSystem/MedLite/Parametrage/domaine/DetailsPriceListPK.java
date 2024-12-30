/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.domaine;

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

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Price_List", nullable = false)
    private int codePriceList;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Prestation", nullable = false)
    private int codePrestation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Nature_Admission", nullable = false)
    private int codeNatureAdmission;

    public DetailsPriceListPK() {
    }

    public DetailsPriceListPK( int codeNatureAdmission ,int codePrestation) {
        this.codePrestation = codePrestation;
        this.codeNatureAdmission = codeNatureAdmission;
    }
    
    

    public int getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(int codePriceList) {
        this.codePriceList = codePriceList;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.codePriceList;
        hash = 97 * hash + this.codePrestation;
        hash = 97 * hash + this.codeNatureAdmission;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetailsPriceListPK other = (DetailsPriceListPK) obj;
        if (this.codePriceList != other.codePriceList) {
            return false;
        }
        if (this.codePrestation != other.codePrestation) {
            return false;
        }
        return this.codeNatureAdmission == other.codeNatureAdmission;
    }

    @Override
    public String toString() {
        return "DetailsPriceListPK{" + "codePriceList=" + codePriceList + ", codePrestation=" + codePrestation + ", codeNatureAdmission=" + codeNatureAdmission + '}';
    }
    
    
    
}
