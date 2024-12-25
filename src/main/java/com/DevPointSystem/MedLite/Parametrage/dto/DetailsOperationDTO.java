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
public class DetailsOperationDTO {
    private int codeOperation;
    private String codeSaisieOperation;

    @NotNull
    private TypeIntervenantDTO typeIntervenantDTO;
    private String codeSaisieTypeIntervenant;
    private Integer codeTypeIntervenant;
    private String designationArTypeIntervenant;
    private String designationLtTypeIntervenant;

    private BigDecimal montant;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public DetailsOperationDTO() {
    }

    public int getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(int codeOperation) {
        this.codeOperation = codeOperation;
    }

    public String getCodeSaisieOperation() {
        return codeSaisieOperation;
    }

    public void setCodeSaisieOperation(String codeSaisieOperation) {
        this.codeSaisieOperation = codeSaisieOperation;
    }

    public TypeIntervenantDTO getTypeIntervenantDTO() {
        return typeIntervenantDTO;
    }

    public void setTypeIntervenantDTO(TypeIntervenantDTO typeIntervenantDTO) {
        this.typeIntervenantDTO = typeIntervenantDTO;
    }

    public String getCodeSaisieTypeIntervenant() {
        return codeSaisieTypeIntervenant;
    }

    public void setCodeSaisieTypeIntervenant(String codeSaisieTypeIntervenant) {
        this.codeSaisieTypeIntervenant = codeSaisieTypeIntervenant;
    }

    public Integer getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(Integer codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public String getDesignationArTypeIntervenant() {
        return designationArTypeIntervenant;
    }

    public void setDesignationArTypeIntervenant(String designationArTypeIntervenant) {
        this.designationArTypeIntervenant = designationArTypeIntervenant;
    }

    public String getDesignationLtTypeIntervenant() {
        return designationLtTypeIntervenant;
    }

    public void setDesignationLtTypeIntervenant(String designationLtTypeIntervenant) {
        this.designationLtTypeIntervenant = designationLtTypeIntervenant;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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
    
    
}
