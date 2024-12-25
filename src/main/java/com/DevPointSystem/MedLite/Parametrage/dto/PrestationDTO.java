/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PrestationDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private FamilleFacturationDTO familleFacturationDTO;
    private Integer codeFamilleFacturation;

    private FamillePrestationDTO famillePrestationDTO;

    private Integer codeFamillePrestation;

    private BigDecimal montant;

    private Collection<DetailsPrestationDTO> detailsPrestationDTOs;

    public PrestationDTO() {
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

    public FamilleFacturationDTO getFamilleFacturationDTO() {
        return familleFacturationDTO;
    }

    public void setFamilleFacturationDTO(FamilleFacturationDTO familleFacturationDTO) {
        this.familleFacturationDTO = familleFacturationDTO;
    }

    public Integer getCodeFamilleFacturation() {
        return codeFamilleFacturation;
    }

    public void setCodeFamilleFacturation(Integer codeFamilleFacturation) {
        this.codeFamilleFacturation = codeFamilleFacturation;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Collection<DetailsPrestationDTO> getDetailsPrestationDTOs() {
        return detailsPrestationDTOs;
    }

    public void setDetailsPrestationDTOs(Collection<DetailsPrestationDTO> detailsPrestationDTOs) {
        this.detailsPrestationDTOs = detailsPrestationDTOs;
    }

}
