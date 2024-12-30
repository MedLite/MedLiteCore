/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsPrestationPK {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Prestation", nullable = false)
    private int codePrestation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Type_Intervenant", nullable = false)
    private int codeTypeIntervenant;

    public DetailsPrestationPK() {
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

    public int getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(int codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codePrestation;
        hash = 97 * hash + this.codeTypeIntervenant;
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
        final DetailsPrestationPK other = (DetailsPrestationPK) obj;
        if (this.codePrestation != other.codePrestation) {
            return false;
        }
        return this.codeTypeIntervenant == other.codeTypeIntervenant;
    }

    @Override
    public String toString() {
        return "DetailsPrestationPK{" + "codePrestation=" + codePrestation + ", codeTypeIntervenant=" + codeTypeIntervenant + '}';
    }
    
    
    

}
