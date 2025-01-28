/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Devise;
import com.FrameWork.MedLite.Parametrage.domaine.TypeCaisse;
import jakarta.persistence.Column;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class CaisseDTO {

    private Integer code;

    private String codeSaisie;
    private String designationAr;

    private String designationLt;
    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private TypeCaisseDTO typeCaisseDTO;

    private Integer codeTypeCaisse;

    public CaisseDTO() {
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

    public DeviseDTO getDeviseDTO() {
        return deviseDTO;
    }

    public void setDeviseDTO(DeviseDTO deviseDTO) {
        this.deviseDTO = deviseDTO;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
    }

    public TypeCaisseDTO getTypeCaisseDTO() {
        return typeCaisseDTO;
    }

    public void setTypeCaisseDTO(TypeCaisseDTO typeCaisseDTO) {
        this.typeCaisseDTO = typeCaisseDTO;
    }

    public Integer getCodeTypeCaisse() {
        return codeTypeCaisse;
    }

    public void setCodeTypeCaisse(Integer codeTypeCaisse) {
        this.codeTypeCaisse = codeTypeCaisse;
    }

}
