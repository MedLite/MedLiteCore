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
@Table(name = "Operation", schema = "param")
@Audited
@AuditTable("Operation_AUD")
public class Operation {

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

    @Column(name = "Code_Famille_Facturation", updatable = false, insertable = false, nullable = false)
    private Integer codeFamilleFacturation;

    @JoinColumn(name = "Code_Famille_Operation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FamilleOperation familleOperation;

    @Column(name = "Code_Famille_Operation", updatable = false, insertable = false, nullable = false)
    private Integer codeFamilleOperation;

    @JoinColumn(name = "Code_Bloc_Operation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private BlocOperation blocOperation;

    @Column(name = "Code_Bloc_Operation", updatable = false, insertable = false, nullable = false)
    private Integer codeBlocOperation;

    @Column(name = "Cout_Revient", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal coutRevient;

    @Column(name = "Prix_Moyene", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal prixMoyene;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operation")
    private Collection<DetailsOperation> detailsOperations;

    public Operation() {
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

    public FamilleOperation getFamilleOperation() {
        return familleOperation;
    }

    public void setFamilleOperation(FamilleOperation familleOperation) {
        this.familleOperation = familleOperation;
    }

    public Integer getCodeFamilleOperation() {
        return codeFamilleOperation;
    }

    public void setCodeFamilleOperation(Integer codeFamilleOperation) {
        this.codeFamilleOperation = codeFamilleOperation;
    }

  

    public Collection<DetailsOperation> getDetailsOperations() {
        return detailsOperations;
    }

    public void setDetailsOperations(Collection<DetailsOperation> detailsOperations) {
        this.detailsOperations = detailsOperations;
    }

    public BlocOperation getBlocOperation() {
        return blocOperation;
    }

    public void setBlocOperation(BlocOperation blocOperation) {
        this.blocOperation = blocOperation;
    }

    public Integer getCodeBlocOperation() {
        return codeBlocOperation;
    }

    public void setCodeBlocOperation(Integer codeBlocOperation) {
        this.codeBlocOperation = codeBlocOperation;
    }

    public BigDecimal getCoutRevient() {
        return coutRevient;
    }

    public void setCoutRevient(BigDecimal coutRevient) {
        this.coutRevient = coutRevient;
    }

    public BigDecimal getPrixMoyene() {
        return prixMoyene;
    }

    public void setPrixMoyene(BigDecimal prixMoyene) {
        this.prixMoyene = prixMoyene;
    }
    
    

}
