/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.ListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
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
@Table(name = "Details_Admission", schema = "recept")
@Audited
@AuditTable("Details_Admission_AUD")
public class DetailsAdmission {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Admission admission;

    @Column(name = "Code_Admission", insertable = false, updatable = false)
    private Integer codeAdmission;

    @JoinColumn(name = "Code_Prestation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Prestation prestation;

    @Column(name = "Code_Prestation", insertable = false, updatable = false)
    private Integer codePrestation;

    @Column(name = "Montant", nullable = false, columnDefinition = ("decimal(18,3) "))
    private BigDecimal montant;

    @Column(name = "Montant_Patient", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPatient;

    @Column(name = "Montant_PEC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPEC;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    
        @Column(name = "Etat_Paiement", nullable = false)
    private boolean etatPaiement;

    public DetailsAdmission() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    public Integer getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(Integer codeAdmission) {
        this.codeAdmission = codeAdmission;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public boolean isEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(boolean etatPaiement) {
        this.etatPaiement = etatPaiement;
    }
    
    
    
    

}
