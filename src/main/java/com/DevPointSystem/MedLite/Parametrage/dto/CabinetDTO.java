/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import com.DevPointSystem.MedLite.Parametrage.domaine.SpecialiteCabinet;
import jakarta.persistence.Column;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class CabinetDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private SpecialiteCabinetDTO specialiteCabinetDTO;

    private Integer codeSpecialiteCabinet;

    public CabinetDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeSaisie() {
        return codeSaisie;
    }

    public void setCodeSaisie(String codeSaisie) {
        this.codeSaisie = codeSaisie;
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public SpecialiteCabinetDTO getSpecialiteCabinetDTO() {
        return specialiteCabinetDTO;
    }

    public void setSpecialiteCabinetDTO(SpecialiteCabinetDTO specialiteCabinetDTO) {
        this.specialiteCabinetDTO = specialiteCabinetDTO;
    }

    public Integer getCodeSpecialiteCabinet() {
        return codeSpecialiteCabinet;
    }

    public void setCodeSpecialiteCabinet(Integer codeSpecialiteCabinet) {
        this.codeSpecialiteCabinet = codeSpecialiteCabinet;
    }
    
    
}
