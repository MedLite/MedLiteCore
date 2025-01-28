/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsPriceListOperationDTO {
    private Integer code;

    private PriceListDTO priceListDTO;

    private Integer codePriceList;

    private String usercreate;
    private OperationDTO operationDTO;

    private Integer codeOperation;
 
    private TypeIntervenantDTO typeIntervenantDTO;

    private Integer codeTypeIntervenant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    private BigDecimal montant;
    private BigDecimal montantPere;

    private String remMaj;

    public DetailsPriceListOperationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PriceListDTO getPriceListDTO() {
        return priceListDTO;
    }

    public void setPriceListDTO(PriceListDTO priceListDTO) {
        this.priceListDTO = priceListDTO;
    }

    public Integer getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(Integer codePriceList) {
        this.codePriceList = codePriceList;
    }
 
     

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
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

    public TypeIntervenantDTO getTypeIntervenantDTO() {
        return typeIntervenantDTO;
    }

    public void setTypeIntervenantDTO(TypeIntervenantDTO typeIntervenantDTO) {
        this.typeIntervenantDTO = typeIntervenantDTO;
    }

    public Integer getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(Integer codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
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

    public BigDecimal getMontantPere() {
        return montantPere;
    }

    public void setMontantPere(BigDecimal montantPere) {
        this.montantPere = montantPere;
    }

    public String getRemMaj() {
        return remMaj;
    }

    public void setRemMaj(String remMaj) {
        this.remMaj = remMaj;
    }
    
    
}
