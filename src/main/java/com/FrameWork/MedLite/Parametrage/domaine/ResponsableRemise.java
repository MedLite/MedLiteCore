/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Responsable_Remise", schema = "param")
@Audited
@AuditTable("Responsable_Remise_AUD")
public class ResponsableRemise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Size(max = 200)
    @Column(name = "Nom_ar", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String nomAr;

    @Size(max = 200)
    @Column(name = "Nom_lt", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String nomLt;

    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @Column(name = "Montant_Autoriser", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montantAutoriser;

    @Column(name = "Montant_Consommer", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montantConsommer;

    @Column(name = "Exercie", nullable = false, columnDefinition = "smalldatetime")
    private Date exercice;

    public ResponsableRemise() {
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

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getNomLt() {
        return nomLt;
    }

    public void setNomLt(String nomLt) {
        this.nomLt = nomLt;
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

    public BigDecimal getMontantAutoriser() {
        return montantAutoriser;
    }

    public void setMontantAutoriser(BigDecimal montantAutoriser) {
        this.montantAutoriser = montantAutoriser;
    }

    public BigDecimal getMontantConsommer() {
        return montantConsommer;
    }

    public void setMontantConsommer(BigDecimal montantConsommer) {
        this.montantConsommer = montantConsommer;
    }

    public Date getExercice() {
        return exercice;
    }

    public void setExercice(Date exercice) {
        this.exercice = exercice;
    }

  


    
    

}
