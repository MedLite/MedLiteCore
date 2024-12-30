/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Reception.domaine;

import com.DevPointSystem.MedLite.Parametrage.domaine.Convention;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsPrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.FamilleFacturation;
import com.DevPointSystem.MedLite.Parametrage.domaine.FamillePrestation;
import com.DevPointSystem.MedLite.Parametrage.domaine.Nationalite;
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
//@Entity
//@Table(name = "Patient", schema = "recept")
//@Audited
//@AuditTable("Patient_AUD")
public class Patient {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Code")
//    private Integer code;
//
//    @Size(max = 200)
//    @NotNull
//    @Column(name = "Code_Saisie", length = 200)
//    private String codeSaisie;
//
//    @Size(max = 200)
//    @Column(name = "Nom_Complet_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
//    private String NomCompltAr;
//
//    @Size(max = 200)
//    @Column(name = "Nom_Complet_Lt", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
//    private String NomCompltLt;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Date_Naissance", columnDefinition = "datetime")
//    private Date dateNaissance;
//
//    @Size(max = 200)
//    @Column(name = "Num_Tel", length = 200, nullable = false, columnDefinition = "nvarchar(20)")
//    private String numTel;
//
//    @Column(name = "Code_Societe")
//    private Integer codeSociete;
//
//    @JoinColumn(name = "Code_Convention", referencedColumnName = "Code")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JsonBackReference
//    private Convention convention;
//
//    @Column(name = "Code_Convention", updatable = false, insertable = false)
//    private Integer codeConvention;
//
//    @Column(name = "Actif", nullable = false, columnDefinition = "bit default(True)")
//    private boolean actif;
//
//    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
//    private String userCreate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
//    private Date dateCreate;
//
//    @JoinColumn(name = "Code_Nationalite", referencedColumnName = "Code", nullable = false)
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JsonBackReference
//    private Nationalite nationalite;
//
//    @Column(name = "Code_Nationalite", updatable = false, insertable = false, nullable = false)
//    private Integer codeNationalite;
//
//    public Patient() {
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getCodeSaisie() {
//        return codeSaisie;
//    }
//
//    public void setCodeSaisie(String codeSaisie) {
//        this.codeSaisie = codeSaisie;
//    }
//
//    public String getNomCompltAr() {
//        return NomCompltAr;
//    }
//
//    public void setNomCompltAr(String NomCompltAr) {
//        this.NomCompltAr = NomCompltAr;
//    }
//
//    public String getNomCompltLt() {
//        return NomCompltLt;
//    }
//
//    public void setNomCompltLt(String NomCompltLt) {
//        this.NomCompltLt = NomCompltLt;
//    }
//
//    public Date getDateNaissance() {
//        return dateNaissance;
//    }
//
//    public void setDateNaissance(Date dateNaissance) {
//        this.dateNaissance = dateNaissance;
//    }
//
//    public String getNumTel() {
//        return numTel;
//    }
//
//    public void setNumTel(String numTel) {
//        this.numTel = numTel;
//    }
//
//    public Integer getCodeSociete() {
//        return codeSociete;
//    }
//
//    public void setCodeSociete(Integer codeSociete) {
//        this.codeSociete = codeSociete;
//    }
//
//    public Integer getCodeConvention() {
//        return codeConvention;
//    }
//
//    public void setCodeConvention(Integer codeConvention) {
//        this.codeConvention = codeConvention;
//    }
//
//    public boolean isActif() {
//        return actif;
//    }
//
//    public void setActif(boolean actif) {
//        this.actif = actif;
//    }
//
//    public String getUserCreate() {
//        return userCreate;
//    }
//
//    public void setUserCreate(String userCreate) {
//        this.userCreate = userCreate;
//    }
//
//    public Date getDateCreate() {
//        return dateCreate;
//    }
//
//    public void setDateCreate(Date dateCreate) {
//        this.dateCreate = dateCreate;
//    }
//
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
//    

}
