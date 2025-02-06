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

     @Column(name = "Code_ListCouverture")
    private int codeListCouverture;
    @Column(name = "Code_Prestation")
    private int codePrestation;  
        @Column(name = "Code_Nature_Admission")
    private int codeNatureAdmission;  

    public DetailsListCouverturePK(int codeListCouverture, int codePrestation, int codeNatureAdmission) {
        this.codeListCouverture = codeListCouverture;
        this.codePrestation = codePrestation;
        this.codeNatureAdmission = codeNatureAdmission;
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

    public int getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(int codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

  
   
    
    
}
