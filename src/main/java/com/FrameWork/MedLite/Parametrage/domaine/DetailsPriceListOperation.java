/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Details_Price_List_Operation", schema = "param")
@Audited
@AuditTable("Details_Price_List_Operation_AUD")
public class DetailsPriceListOperation {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Price_List", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private PriceList priceList;

    @Column(name = "Code_Price_List", insertable = false, updatable = false)
    private Integer codePriceList;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @JoinColumn(name = "Code_Operation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Operation operation;

    @Column(name = "Code_Operation", insertable = false, updatable = false)
    private Integer codeOperation;

    @JoinColumn(name = "Code_Type_Intervenant", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeIntervenant typeIntervenant;

    @Column(name = "Code_Type_Intervenant", insertable = false, updatable = false)
    private Integer codeTypeIntervenant;
    
      @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", insertable = false, updatable = false)
    private Integer codeNatureAdmission;
 

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "Montant", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montant;

    @Column(name = "Montant_Pere", nullable = false, columnDefinition = ("decimal(18,3) "))
    private BigDecimal montantPere;

    @Column(name = "RemMaj", nullable = false, columnDefinition = ("varchar(3) "))
    private String remMaj;

    public DetailsPriceListOperation() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
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

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Integer getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(Integer codeOperation) {
        this.codeOperation = codeOperation;
    }

    public TypeIntervenant getTypeIntervenant() {
        return typeIntervenant;
    }

    public void setTypeIntervenant(TypeIntervenant typeIntervenant) {
        this.typeIntervenant = typeIntervenant;
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

    public NatureAdmission getNatureAdmission() {
        return natureAdmission;
    }

    public void setNatureAdmission(NatureAdmission natureAdmission) {
        this.natureAdmission = natureAdmission;
    }

    public Integer getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(Integer codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }
    
    

     
    

}
