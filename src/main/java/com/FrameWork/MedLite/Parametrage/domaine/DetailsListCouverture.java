/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

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
@Table(name = "Details_List_Couverture", schema = "param")
@Audited
@AuditTable("Details_List_Couverture_AUD")
public class DetailsListCouverture {
    
    @EmbeddedId
    protected DetailsListCouverturePK detailsListCouverturePK;

    @MapsId("codeListCouverture")
    @JoinColumn(name = "Code_List_Couverture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false)
    private ListCouverture listCouverture;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @JoinColumn(name = "Code_Prestation", referencedColumnName = "Code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Prestation prestation;

    @Column(name = "Code_Prestation", insertable = false, updatable = false)
    private Integer codePrestation;

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "Taux_Couver_PEC", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal tauxCouverPec;

    public DetailsListCouverture() {
    }

    public DetailsListCouverturePK getDetailsListCouverturePK() {
        return detailsListCouverturePK;
    }

    public void setDetailsListCouverturePK(DetailsListCouverturePK detailsListCouverturePK) {
        this.detailsListCouverturePK = detailsListCouverturePK;
    }

    public ListCouverture getListCouverture() {
        return listCouverture;
    }

    public void setListCouverture(ListCouverture listCouverture) {
        this.listCouverture = listCouverture;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getTauxCouverPec() {
        return tauxCouverPec;
    }

    public void setTauxCouverPec(BigDecimal tauxCouverPec) {
        this.tauxCouverPec = tauxCouverPec;
    }
    
    
}
