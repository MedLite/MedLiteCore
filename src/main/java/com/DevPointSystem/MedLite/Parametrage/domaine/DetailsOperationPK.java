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
public class DetailsOperationPK {
     @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Operation", nullable = false)
    private int codeOperation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Type_Intervenant", nullable = false)
    private int codeTypeIntervenant;

    public DetailsOperationPK() {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.codeOperation;
        hash = 19 * hash + this.codeTypeIntervenant;
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
        final DetailsOperationPK other = (DetailsOperationPK) obj;
        if (this.codeOperation != other.codeOperation) {
            return false;
        }
        return this.codeTypeIntervenant == other.codeTypeIntervenant;
    }

    @Override
    public String toString() {
        return "DetailsOperationPK{" + "codeOperation=" + codeOperation + ", codeTypeIntervenant=" + codeTypeIntervenant + '}';
    }
    
    
}
