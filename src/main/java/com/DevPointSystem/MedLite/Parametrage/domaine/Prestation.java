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
@Table(name = "Prestation", schema = "param")
@Audited
@AuditTable("Prestation_AUD")
public class Prestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Size(max = 200)
    @Column(name = "Designation_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationAr;

    @Size(max = 200)
    @Column(name = "Designation_Lt", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationLt;

    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Famille_Facturation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FamilleFacturation familleFacturation;

    @Column(name = "Code_Famille_Facturation", updatable = false, insertable = false)
    private Integer codeFamilleFacturation;

    @JoinColumn(name = "Code_Famille_Prestation", referencedColumnName = "Code", nullable = false,insertable = true,updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FamillePrestation famillePrestation;

    @Column(name = "Code_Famille_Prestation", updatable = false, insertable = false)
    private Integer codeFamillePrestation;

    @Column(name = "Montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestation")
    private Collection<DetailsPrestation> detailsPrestations;

    public Prestation() {
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

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
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

    public FamilleFacturation getFamilleFacturation() {
        return familleFacturation;
    }

    public void setFamilleFacturation(FamilleFacturation familleFacturation) {
        this.familleFacturation = familleFacturation;
    }

    public Integer getCodeFamilleFacturation() {
        return codeFamilleFacturation;
    }

    public void setCodeFamilleFacturation(Integer codeFamilleFacturation) {
        this.codeFamilleFacturation = codeFamilleFacturation;
    }

    public FamillePrestation getFamillePrestation() {
        return famillePrestation;
    }

    public void setFamillePrestation(FamillePrestation famillePrestation) {
        this.famillePrestation = famillePrestation;
    }

    public Integer getCodeFamillePrestation() {
        return codeFamillePrestation;
    }

    public void setCodeFamillePrestation(Integer codeFamillePrestation) {
        this.codeFamillePrestation = codeFamillePrestation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Collection<DetailsPrestation> getDetailsPrestations() {
        return detailsPrestations;
    }

    public void setDetailsPrestations(Collection<DetailsPrestation> detailsPrestations) {
        this.detailsPrestations = detailsPrestations;
    }

}
