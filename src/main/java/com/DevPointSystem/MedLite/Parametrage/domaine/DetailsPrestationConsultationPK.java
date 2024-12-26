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
    private int codePrestConsult; // This field MUST match the @MapsId value


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
    
    

}
