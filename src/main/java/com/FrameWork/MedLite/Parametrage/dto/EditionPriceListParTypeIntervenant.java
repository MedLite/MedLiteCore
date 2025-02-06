/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class EditionPriceListParTypeIntervenant {
     private String designation_type_prestation;
    private String designation_famille_prestation;
    private String designation_sous_famille_prestation;
    private String Code_Saisie;
    private String designation_prestation;
    private String designationFamilleFacturation;  

    private String designation_nature_admission;
    private String Designation_Ar; //Type_Intervenant
    private BigDecimal Cout_Revient;
    private BigDecimal Prix;

    public String getDesignation_type_prestation() {
        return designation_type_prestation;
    }

    public void setDesignation_type_prestation(String designation_type_prestation) {
        this.designation_type_prestation = designation_type_prestation;
    }

    public String getDesignation_famille_prestation() {
        return designation_famille_prestation;
    }

    public void setDesignation_famille_prestation(String designation_famille_prestation) {
        this.designation_famille_prestation = designation_famille_prestation;
    }

    public String getDesignation_sous_famille_prestation() {
        return designation_sous_famille_prestation;
    }

    public void setDesignation_sous_famille_prestation(String designation_sous_famille_prestation) {
        this.designation_sous_famille_prestation = designation_sous_famille_prestation;
    }

    public String getCode_Saisie() {
        return Code_Saisie;
    }

    public void setCode_Saisie(String Code_Saisie) {
        this.Code_Saisie = Code_Saisie;
    }

    public String getDesignation_prestation() {
        return designation_prestation;
    }

    public void setDesignation_prestation(String designation_prestation) {
        this.designation_prestation = designation_prestation;
    }

    public String getDesignationFamilleFacturation() {
        return designationFamilleFacturation;
    }

    public void setDesignationFamilleFacturation(String designationFamilleFacturation) {
        this.designationFamilleFacturation = designationFamilleFacturation;
    }

     
    public String getDesignation_nature_admission() {
        return designation_nature_admission;
    }

    public void setDesignation_nature_admission(String designation_nature_admission) {
        this.designation_nature_admission = designation_nature_admission;
    }

    public String getDesignation_Ar() {
        return Designation_Ar;
    }

    public void setDesignation_Ar(String Designation_Ar) {
        this.Designation_Ar = Designation_Ar;
    }

    public BigDecimal getCout_Revient() {
        return Cout_Revient;
    }

    public void setCout_Revient(BigDecimal Cout_Revient) {
        this.Cout_Revient = Cout_Revient;
    }

    public BigDecimal getPrix() {
        return Prix;
    }

    public void setPrix(BigDecimal Prix) {
        this.Prix = Prix;
    }
}
