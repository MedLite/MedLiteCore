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
public class DetailsPrestationConsultationPK {

    @Column(name = "Fk_Prestation_Consultation", nullable = false) 
    private int codePrestConsult;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Type_Intervenant", nullable = false)
    private int codeTypeIntervenant;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Prestation", nullable = false)
    private int codePrestationConsultation;

    public DetailsPrestationConsultationPK() {
    }

    public int getCodePrestConsult() {
        return codePrestConsult;
    }

    public void setCodePrestConsult(int codePrestConsult) {
        this.codePrestConsult = codePrestConsult;
    }

    public int getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(int codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public int getCodePrestationConsultation() {
        return codePrestationConsultation;
    }

    public void setCodePrestationConsultation(int codePrestationConsultation) {
        this.codePrestationConsultation = codePrestationConsultation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.codePrestConsult;
        hash = 79 * hash + this.codeTypeIntervenant;
        hash = 79 * hash + this.codePrestationConsultation;
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
        final DetailsPrestationConsultationPK other = (DetailsPrestationConsultationPK) obj;
        if (this.codePrestConsult != other.codePrestConsult) {
            return false;
        }
        if (this.codeTypeIntervenant != other.codeTypeIntervenant) {
            return false;
        }
        return this.codePrestationConsultation == other.codePrestationConsultation;
    }

    @Override
    public String toString() {
        return "DetailsPrestationConsultationPK{" + "codePrestConsult=" + codePrestConsult + ", codeTypeIntervenant=" + codeTypeIntervenant + ", codePrestationConsultation=" + codePrestationConsultation + '}';
    }

}
