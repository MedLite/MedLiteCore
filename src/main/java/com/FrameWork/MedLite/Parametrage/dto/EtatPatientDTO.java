/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

/**
 *
 * @author Administrator
 */
public class EtatPatientDTO {
    private Integer code;
    private String designationAr;
 
    private String designationLt;

    public EtatPatientDTO() {
    }

   

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
    }

}
