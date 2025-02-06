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
public class DetailsOperationPK {
     @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Operation", nullable = false)
    private int codeOperation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Type_Intervenant", nullable = false)
    private int codeTypeIntervenant;
    
        @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Nature_Admission", nullable = false)
    private int codeNatureAdmission;
        
        

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

    public int getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(int codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.codeOperation;
        hash = 73 * hash + this.codeTypeIntervenant;
        hash = 73 * hash + this.codeNatureAdmission;
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
        if (this.codeTypeIntervenant != other.codeTypeIntervenant) {
            return false;
        }
        return this.codeNatureAdmission == other.codeNatureAdmission;
    }

    @Override
    public String toString() {
        return "DetailsOperationPK{" + "codeOperation=" + codeOperation + ", codeTypeIntervenant=" + codeTypeIntervenant + ", codeNatureAdmission=" + codeNatureAdmission + '}';
    }

  
    
}
