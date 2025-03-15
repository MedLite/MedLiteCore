/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsExamenPK {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Examen", nullable = false)
    private int codeExamen;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Nature_Admission", nullable = false)
    private int codeNatureAdmission;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Patient", nullable = false)
    private int codePatient;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Prestation", nullable = false)
    private int codePrestation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Admission", nullable = false)
    private int codeAdmission;

    public DetailsExamenPK() {
    }

    public int getCodeExamen() {
        return codeExamen;
    }

    public void setCodeExamen(int codeExamen) {
        this.codeExamen = codeExamen;
    }

    public int getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(int codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public int getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(int codePatient) {
        this.codePatient = codePatient;
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

    public int getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(int codeAdmission) {
        this.codeAdmission = codeAdmission;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.codeExamen;
        hash = 23 * hash + this.codeNatureAdmission;
        hash = 23 * hash + this.codePatient;
        hash = 23 * hash + this.codePrestation;
        hash = 23 * hash + this.codeAdmission;
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
        final DetailsExamenPK other = (DetailsExamenPK) obj;
        if (this.codeExamen != other.codeExamen) {
            return false;
        }
        if (this.codeNatureAdmission != other.codeNatureAdmission) {
            return false;
        }
        if (this.codePatient != other.codePatient) {
            return false;
        }
        if (this.codePrestation != other.codePrestation) {
            return false;
        }
        return this.codeAdmission == other.codeAdmission;
    }

    @Override
    public String toString() {
        return "DetailsExamenPK{" + "codeExamen=" + codeExamen + ", codeNatureAdmission=" + codeNatureAdmission + ", codePatient=" + codePatient + ", codePrestation=" + codePrestation + ", codeAdmission=" + codeAdmission + '}';
    }

}
