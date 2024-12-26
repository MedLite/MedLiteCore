/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "Details_Prestation_Consultation", schema = "param")
@Audited
@AuditTable("Details_Prestation_Consultation_AUD")
public class DetailsPrestationConsultation {

    @EmbeddedId
    protected DetailsPrestationConsultationPK detailsPrestationConsultationPK;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Fk_Prestation_Consultation", referencedColumnName = "Code", nullable = false) 
    private PrestationConsultation codePrestationConsultationFK;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @JoinColumn(name = "Code_Type_Intervenant", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeIntervenant typeIntervenant;

    @Column(name = "Code_Type_Intervenant", insertable = false, updatable = false)
    private Integer codeTypeIntervenant;

    @JoinColumn(name = "Code_Prestation", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Prestation prestationConsultation;

    @Column(name = "Code_Prestation", insertable = false, updatable = false)
    private Integer codePrestationConsultation;

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "Montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    public DetailsPrestationConsultation() {
    }

    public DetailsPrestationConsultationPK getDetailsPrestationConsultationPK() {
        return detailsPrestationConsultationPK;
    }

    public void setDetailsPrestationConsultationPK(DetailsPrestationConsultationPK detailsPrestationConsultationPK) {
        this.detailsPrestationConsultationPK = detailsPrestationConsultationPK;
    }

    public PrestationConsultation getCodePrestationConsultationFK() {
        return codePrestationConsultationFK;
    }

    public void setCodePrestationConsultationFK(PrestationConsultation codePrestationConsultationFK) {
        this.codePrestationConsultationFK = codePrestationConsultationFK;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
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

    public Prestation getPrestationConsultation() {
        return prestationConsultation;
    }

    public void setPrestationConsultation(Prestation prestationConsultation) {
        this.prestationConsultation = prestationConsultation;
    }

    public Integer getCodePrestationConsultation() {
        return codePrestationConsultation;
    }

    public void setCodePrestationConsultation(Integer codePrestationConsultation) {
        this.codePrestationConsultation = codePrestationConsultation;
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
