/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.enumeration;

/**
 *
 * @author Administrator
 */
public enum ReceptionConstants {
    CODE_ETAT_PATIEMENT_NOT_PAIED("2"),    
    CODE_ETAT_PATIEMENT_PAIED("1"),

    CODE_ETAT_PATIENT_RESIDANT("0"),     
    CODE_ETAT_PATIENT_LEAVE("1"), 

    CODE_TVA_ZERO("codeTVAZero"),
    POURCENTAGE_MEDICALE("pourcentageMedicale"),
    CODE_TYPE_ARRIVE_CLINIQUE("1"), 
    MODE_REGLEMENT_EMPLOYEE_ADVANCED("modeReglementEmployeeAdvances");
    private String name = "";

    //Constructeur
    ReceptionConstants(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
