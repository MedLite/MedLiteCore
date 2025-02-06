/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TemporalType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import jakarta.persistence.Temporal;
import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Convention", schema = "param")
@Audited
@AuditTable("Convention_AUD")
public class Convention {

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

    @JoinColumn(name = "Code_Societe", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Societe societe;

    @Column(name = "Code_Societe", updatable = false, insertable = false, nullable = false)
    private Integer codeSociete;

    @JoinColumn(name = "Code_Price_List", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private PriceList priceList;

    @Column(name = "Code_Price_List", updatable = false, insertable = false, nullable = false)
    private Integer codePriceList;

    @JoinColumn(name = "Code_List_Couverture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ListCouverture listCouverture;

    @Column(name = "Code_List_Couverture", updatable = false, insertable = false, nullable = false)
    private Integer codeListCouverture;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Deb", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDeb;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Fin", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFin;

    public Convention() {
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

    public ListCouverture getListCouverture() {
        return listCouverture;
    }

    public void setListCouverture(ListCouverture listCouverture) {
        this.listCouverture = listCouverture;
    }

    public Integer getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(Integer codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
    }

    public LocalDate getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(LocalDate dateDeb) {
        this.dateDeb = dateDeb;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    

}
