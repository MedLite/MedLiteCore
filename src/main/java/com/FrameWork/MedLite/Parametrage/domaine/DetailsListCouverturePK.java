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
public class DetailsListCouverturePK {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_List_Couverture", nullable = false)
    private int codeListCouverture;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_Prestation", nullable = false)
    private int codePrestation;

    public DetailsListCouverturePK() {
    }

    public int getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(int codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.codeListCouverture;
        hash = 53 * hash + this.codePrestation;
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
        final DetailsListCouverturePK other = (DetailsListCouverturePK) obj;
        if (this.codeListCouverture != other.codeListCouverture) {
            return false;
        }
        return this.codePrestation == other.codePrestation;
    }

    @Override
    public String toString() {
        return "DetailsListCouverturePK{" + "codeListCouverture=" + codeListCouverture + ", codePrestation=" + codePrestation + '}';
    }
    
    
}
