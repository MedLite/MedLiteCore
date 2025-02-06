/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsListCouvertureOperationDTO {
    private Integer code;

    private ListCouvertureDTO listCouvertureDTO;

    private Integer codeListCouverture;
 
    private OperationDTO operationDTO;

    private Integer codeOperation;
    
        private NatureAdmissionDTO natureAdmissionDTO;

    private Integer codeNatureAdmission; 
 

 
    private BigDecimal montantPatient;
     
    private BigDecimal montantPEC;

    private BigDecimal montantPere;
 
    
    
    private BigDecimal mntApresMaj;

    
    private String RemMajValeur;

  private BigDecimal tauxCouverPec;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public DetailsListCouvertureOperationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ListCouvertureDTO getListCouvertureDTO() {
        return listCouvertureDTO;
    }

    public void setListCouvertureDTO(ListCouvertureDTO listCouvertureDTO) {
        this.listCouvertureDTO = listCouvertureDTO;
    }

    public Integer getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(Integer codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
    }

    public OperationDTO getOperationDTO() {
        return operationDTO;
    }

    public void setOperationDTO(OperationDTO operationDTO) {
        this.operationDTO = operationDTO;
    }

    public Integer getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(Integer codeOperation) {
        this.codeOperation = codeOperation;
    }

    

   

    public BigDecimal getMontantPatient() {
        return montantPatient;
    }

    public void setMontantPatient(BigDecimal montantPatient) {
        this.montantPatient = montantPatient;
    }

    public BigDecimal getMontantPEC() {
        return montantPEC;
    }

    public void setMontantPEC(BigDecimal montantPEC) {
        this.montantPEC = montantPEC;
    }

   

    public BigDecimal getMontantPere() {
        return montantPere;
    }

    public void setMontantPere(BigDecimal montantPere) {
        this.montantPere = montantPere;
    }

    public BigDecimal getMntApresMaj() {
        return mntApresMaj;
    }

    public void setMntApresMaj(BigDecimal mntApresMaj) {
        this.mntApresMaj = mntApresMaj;
    }

    public String getRemMajValeur() {
        return RemMajValeur;
    }

    public void setRemMajValeur(String RemMajValeur) {
        this.RemMajValeur = RemMajValeur;
    }

    public BigDecimal getTauxCouverPec() {
        return tauxCouverPec;
    }

    public void setTauxCouverPec(BigDecimal tauxCouverPec) {
        this.tauxCouverPec = tauxCouverPec;
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

    
    
    
    

}
