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

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Prestation_Consultation", nullable = false)
    private int fkPrestationConsultation;

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

    public int getFkPrestationConsultation() {
        return fkPrestationConsultation;
    }

    public void setFkPrestationConsultation(int fkPrestationConsultation) {
        this.fkPrestationConsultation = fkPrestationConsultation;
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
    
    

}
