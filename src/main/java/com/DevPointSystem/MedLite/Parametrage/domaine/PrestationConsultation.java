/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */

@Entity
@Table(name = "Prestation_Consultation", schema = "param")
@Audited
@AuditTable("Prestation_Consultation_AUD")
public class PrestationConsultation {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;
 
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;
 

    @Column(name = "Montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestationConsultation")
    private Collection<DetailsPrestationConsultation> detailsPrestationConsultations;

    public PrestationConsultation() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Collection<DetailsPrestationConsultation> getDetailsPrestationConsultations() {
        return detailsPrestationConsultations;
    }

    public void setDetailsPrestationConsultations(Collection<DetailsPrestationConsultation> detailsPrestationConsultations) {
        this.detailsPrestationConsultations = detailsPrestationConsultations;
    }

    
    
    
}
