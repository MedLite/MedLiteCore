/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ResponsableRemiseDTO {

    private Integer code;
    private String codeSaisie;

    private String nomAr;

    private String nomLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private BigDecimal montantAutoriser;

    private BigDecimal montantConsommer;

    private Date exercice;

    public ResponsableRemiseDTO() {
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

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getNomLt() {
        return nomLt;
    }

    public void setNomLt(String nomLt) {
        this.nomLt = nomLt;
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

    public BigDecimal getMontantAutoriser() {
        return montantAutoriser;
    }

    public void setMontantAutoriser(BigDecimal montantAutoriser) {
        this.montantAutoriser = montantAutoriser;
    }

    public BigDecimal getMontantConsommer() {
        return montantConsommer;
    }

    public void setMontantConsommer(BigDecimal montantConsommer) {
        this.montantConsommer = montantConsommer;
    }

    public Date getExercice() {
        return exercice;
    }

    public void setExercice(Date exercice) {
        this.exercice = exercice;
    }
    
    

}
