/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Details_List_Couverture_Operation", schema = "param")
@Audited
@AuditTable("Details_List_Couverture_Operation_AUD")
public class DetailsListCouvertureOperation {
     @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_List_Couverture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ListCouverture listCouverture;

    @Column(name = "Code_List_Couverture", insertable = false, updatable = false)
    private Integer codeListCouverture;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @JoinColumn(name = "Code_Operation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Operation operation;

    @Column(name = "Code_Operation", insertable = false, updatable = false)
    private Integer codeOperation;

     @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", insertable = false, updatable = false)
    private Integer codeNatureAdmission;

    @Column(name = "Montant_Patient", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPatient;
    
        @Column(name = "Montant_PEC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPEC;

    @Column(name = "Montant_Pere", nullable = false, columnDefinition = ("decimal(18,3) "))
    private BigDecimal montantPere;
 

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "Taux_Couver_PEC", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal tauxCouverPec;

    public DetailsListCouvertureOperation() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ListCouverture getListCouverture() {
        return listCouverture;
    }

    public void setListCouverture(ListCouverture listCouverture) {
        this.listCouverture = listCouverture;
    }

    public Integer getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(Integer codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getTauxCouverPec() {
        return tauxCouverPec;
    }

    public void setTauxCouverPec(BigDecimal tauxCouverPec) {
        this.tauxCouverPec = tauxCouverPec;
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
