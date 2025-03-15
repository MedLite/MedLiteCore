/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

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

    @JoinColumn(name = "Code_Sous_Famille_Prestation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private SousFamillePrestation sousFamillePrestation;

    @Column(name = "Code_Sous_Famille_Prestation", updatable = false, insertable = false)
    private Integer codeSousFamillePrestation;

    @JoinColumn(name = "Code_Famille_Prestation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FamillePrestation famillePrestation;

    @Column(name = "Code_Famille_Prestation", updatable = false, insertable = false)
    private Integer codeFamillePrestation;
    
    
    @JoinColumn(name = "Code_Type_Prestation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypePrestation  typePrestation;

    @Column(name = "Code_Type_Prestation", updatable = false, insertable = false)
    private Integer codeTypePrestation;
    
    
    

    @Column(name = "Montant_OPD", columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantOPD;

    @Column(name = "Montant_ER", columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantER;

    @Column(name = "Montant_IP", columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantIP;

    @Column(name = "Prix_Prestation", columnDefinition = ("decimal(18,3) "))
    private BigDecimal prixPrestation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestation")
    private Collection<DetailsPrestation> detailsPrestations;

    @Column(name = "OPD", nullable = false, columnDefinition = ("bit default(0)"))
    private boolean opd;
    @Column(name = "ER", nullable = false, columnDefinition = ("bit default(0)"))
    private boolean er;
    @Column(name = "IP", nullable = false, columnDefinition = ("bit default(0)"))
    private boolean ip;

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

    public BigDecimal getMontantOPD() {
        return montantOPD;
    }

    public void setMontantOPD(BigDecimal montantOPD) {
        this.montantOPD = montantOPD;
    }

    public BigDecimal getMontantER() {
        return montantER;
    }

    public void setMontantER(BigDecimal montantER) {
        this.montantER = montantER;
    }

    public BigDecimal getMontantIP() {
        return montantIP;
    }

    public void setMontantIP(BigDecimal montantIP) {
        this.montantIP = montantIP;
    }

    public Collection<DetailsPrestation> getDetailsPrestations() {
        return detailsPrestations;
    }

    public void setDetailsPrestations(Collection<DetailsPrestation> detailsPrestations) {
        this.detailsPrestations = detailsPrestations;
    }

    public boolean isOpd() {
        return opd;
    }

    public void setOpd(boolean opd) {
        this.opd = opd;
    }

    public boolean isEr() {
        return er;
    }

    public void setEr(boolean er) {
        this.er = er;
    }

    public boolean isIp() {
        return ip;
    }

    public void setIp(boolean ip) {
        this.ip = ip;
    }

    public BigDecimal getPrixPrestation() {
        return prixPrestation;
    }

    public void setPrixPrestation(BigDecimal prixPrestation) {
        this.prixPrestation = prixPrestation;
    }

    public SousFamillePrestation getSousFamillePrestation() {
        return sousFamillePrestation;
    }

    public void setSousFamillePrestation(SousFamillePrestation sousFamillePrestation) {
        this.sousFamillePrestation = sousFamillePrestation;
    }

    public Integer getCodeSousFamillePrestation() {
        return codeSousFamillePrestation;
    }

    public void setCodeSousFamillePrestation(Integer codeSousFamillePrestation) {
        this.codeSousFamillePrestation = codeSousFamillePrestation;
    }

    public TypePrestation getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(TypePrestation typePrestation) {
        this.typePrestation = typePrestation;
    }

    public Integer getCodeTypePrestation() {
        return codeTypePrestation;
    }

    public void setCodeTypePrestation(Integer codeTypePrestation) {
        this.codeTypePrestation = codeTypePrestation;
    }

    
    
}
