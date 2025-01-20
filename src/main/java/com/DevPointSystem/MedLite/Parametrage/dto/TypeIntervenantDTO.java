/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TypeIntervenantDTO {

    private Integer code;   
    
    
    private Integer codeTypeIntervenant;


    private String codeSaisie;

    private String designationAr;  
    
    private String designationArTypeIntervenant;  
    private String designationLTTypeIntervenant;



    private String designationLt;
    private boolean actif;

    private String userCreate;

    private Date dateCreate;
    private boolean autoriseFrais;
    private boolean isClinique;

    public TypeIntervenantDTO() {
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

    public boolean isAutoriseFrais() {
        return autoriseFrais;
    }

    public void setAutoriseFrais(boolean autoriseFrais) {
        this.autoriseFrais = autoriseFrais;
    }

    public boolean isIsClinique() {
        return isClinique;
    }

    public void setIsClinique(boolean isClinique) {
        this.isClinique = isClinique;
    }

    public String getDesignationArTypeIntervenant() {
        return designationArTypeIntervenant;
    }

    public void setDesignationArTypeIntervenant(String designationArTypeIntervenant) {
        this.designationArTypeIntervenant = designationArTypeIntervenant;
    }

    public String getDesignationLTTypeIntervenant() {
        return designationLTTypeIntervenant;
    }

    public void setDesignationLTTypeIntervenant(String designationLTTypeIntervenant) {
        this.designationLTTypeIntervenant = designationLTTypeIntervenant;
    }

    public Integer getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(Integer codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }
    
    
    

}
