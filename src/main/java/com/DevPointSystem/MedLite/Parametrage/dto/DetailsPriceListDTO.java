/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsPriceListDTO {

    private int codePriceList;
    private String codeSaisiePriceList;

    @NotNull
    private PrestationDTO prestationDTO;
    private String codeSaisiePrestation;
    private Integer codePrestation;
    private String designationArPrestation;
    private String designationLtPrestation;

    @NotNull
    private NatureAdmissionDTO natureAdmissionDTO;
    private Integer codeNatureAdmission;
    private String designationArNatureAdmission;
    private String designationLtNatureAdmission;

     private BigDecimal montant;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

 

    public DetailsPriceListDTO() {
    }

    public int getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(int codePriceList) {
        this.codePriceList = codePriceList;
    }

    public String getCodeSaisiePriceList() {
        return codeSaisiePriceList;
    }

    public void setCodeSaisiePriceList(String codeSaisiePriceList) {
        this.codeSaisiePriceList = codeSaisiePriceList;
    }

    public PrestationDTO getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(PrestationDTO prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    public String getCodeSaisiePrestation() {
        return codeSaisiePrestation;
    }

    public void setCodeSaisiePrestation(String codeSaisiePrestation) {
        this.codeSaisiePrestation = codeSaisiePrestation;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public String getDesignationArPrestation() {
        return designationArPrestation;
    }

    public void setDesignationArPrestation(String designationArPrestation) {
        this.designationArPrestation = designationArPrestation;
    }

    public String getDesignationLtPrestation() {
        return designationLtPrestation;
    }

    public void setDesignationLtPrestation(String designationLtPrestation) {
        this.designationLtPrestation = designationLtPrestation;
    }

    public NatureAdmissionDTO getNatureAdmissionDTO() {
        return natureAdmissionDTO;
    }

    public void setNatureAdmissionDTO(NatureAdmissionDTO natureAdmissionDTO) {
        this.natureAdmissionDTO = natureAdmissionDTO;
    }

    public Integer getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(Integer codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public String getDesignationArNatureAdmission() {
        return designationArNatureAdmission;
    }

    public void setDesignationArNatureAdmission(String designationArNatureAdmission) {
        this.designationArNatureAdmission = designationArNatureAdmission;
    }

    public String getDesignationLtNatureAdmission() {
        return designationLtNatureAdmission;
    }

    public void setDesignationLtNatureAdmission(String designationLtNatureAdmission) {
        this.designationLtNatureAdmission = designationLtNatureAdmission;
    }
 

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

     
    
}
