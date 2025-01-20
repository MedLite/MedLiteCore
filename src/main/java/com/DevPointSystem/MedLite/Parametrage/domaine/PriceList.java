/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Price_List", schema = "param")
@Audited
@AuditTable("Price_List_AUD")
public class PriceList {

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

    @Column(name = "Cash", nullable = false, columnDefinition = "bit default(0)")
    private boolean cash;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

//    @OneToMany(mappedBy = "priceList", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<DetailsPriceList> detailsPriceLists;
//        @JsonManagedReference
//    @OneToMany(mappedBy = "codePriceList", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)//
//    private List<DetailsPriceList> detailsPriceList;
    @OneToMany(mappedBy = "codePriceList", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("priceListDetails") // Unique name
    private List<DetailsPriceList> detailsPriceList;

    @JoinColumn(name = "Code_Societe", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Societe societe;

    @Column(name = "Code_Societe", insertable = false, updatable = false)
    private Integer codeSociete;

    public PriceList() {
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

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public List<DetailsPriceList> getDetailsPriceList() {
        return detailsPriceList;
    }

    public void setDetailsPriceList(List<DetailsPriceList> detailsPriceList) {
        this.detailsPriceList = detailsPriceList;
    }

}
