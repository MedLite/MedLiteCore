/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsAdmissionPK {

    @Column(name = "Code_Admission")
    private int codeAdmission;
    @Column(name = "Code_Prestation")
    private int codePrestation;

    public DetailsAdmissionPK() {
    }

    
    
    public DetailsAdmissionPK(int codeAdmission, int codePrestation) {
        this.codeAdmission = codeAdmission;
        this.codePrestation = codePrestation;
    }

    public int getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(int codeAdmission) {
        this.codeAdmission = codeAdmission;
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

}
