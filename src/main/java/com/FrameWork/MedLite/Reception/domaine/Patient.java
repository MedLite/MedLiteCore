/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Patient", schema = "recept")
@Audited
@AuditTable("Patient_AUD")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Size(max = 200)
    @Column(name = "Nom_Complet_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
    private String nomCompltAr;

    @Size(max = 200)
    @Column(name = "Nom_Complet_Lt", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
    private String nomCompltLt;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Date_Naissance", columnDefinition = "datetime")
//    private Date dateNaissance;
//    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Naissance", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    @Size(max = 200)
    @Column(name = "Num_Tel", length = 200, nullable = false, columnDefinition = "nvarchar(20)")
    private String numTel;

    @Column(name = "Code_Societe")
    private Integer codeSociete;

    @Column(name = "Code_Convention")
    private Integer codeConvention;

    @Column(name = "Actif", nullable = false, columnDefinition = "bit default(1)")
    private boolean actif;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;
 
    @JoinColumn(name = "Code_Price_List", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private PriceList priceList;

    @Column(name = "Code_Price_List", updatable = false, insertable = false, nullable = false)
    private Integer codePriceList;

    public Patient() {
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

    public String getNomCompltAr() {
        return nomCompltAr;
    }

    public void setNomCompltAr(String nomCompltAr) {
        this.nomCompltAr = nomCompltAr;
    }

    public String getNomCompltLt() {
        return nomCompltLt;
    }

    public void setNomCompltLt(String nomCompltLt) {
        this.nomCompltLt = nomCompltLt;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
    }

    public Integer getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(Integer codeConvention) {
        this.codeConvention = codeConvention;
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

//    public Nationalite getNationalite() {
//        return nationalite;
//    }
//
//    public void setNationalite(Nationalite nationalite) {
//        this.nationalite = nationalite;
//    }
//
//    public Integer getCodeNationalite() {
//        return codeNationalite;
//    }
//
//    public void setCodeNationalite(Integer codeNationalite) {
//        this.codeNationalite = codeNationalite;
//    }
//
//    public Convention getConvention() {
//        return convention;
//    }
//
//    public void setConvention(Convention convention) {
//        this.convention = convention;
//    }
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

}
