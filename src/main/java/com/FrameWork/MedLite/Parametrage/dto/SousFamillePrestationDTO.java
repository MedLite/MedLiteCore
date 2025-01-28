/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import com.FrameWork.MedLite.Parametrage.domaine.FamillePrestation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class SousFamillePrestationDTO {

    private Integer code;
    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private FamillePrestationDTO famillePrestationDTO;

    private Integer codeFamillePrestation;

    public SousFamillePrestationDTO() {
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

    public FamillePrestationDTO getFamillePrestationDTO() {
        return famillePrestationDTO;
    }

    public void setFamillePrestationDTO(FamillePrestationDTO famillePrestationDTO) {
        this.famillePrestationDTO = famillePrestationDTO;
    }

    public Integer getCodeFamillePrestation() {
        return codeFamillePrestation;
    }

    public void setCodeFamillePrestation(Integer codeFamillePrestation) {
        this.codeFamillePrestation = codeFamillePrestation;
    }

}
