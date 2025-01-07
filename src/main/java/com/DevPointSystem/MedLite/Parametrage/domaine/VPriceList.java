/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.domaine;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "V_Price_List", schema = "param")
public class VPriceList {

    @EmbeddedId
    protected VPriceListPK vPriceListPK;

    @Column(name = "Prestation", insertable = false, updatable = false)
    private Integer codePrestation;

    @Column(name = "Nature_Admission", insertable = false, updatable = false)
    private int codeNatureAdmission;

    @Column(name = "Prix", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montant;

    @Size(max = 200)
    @Column(name = "Designation_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationLtPrestation;

    public VPriceList() {
    }

    public VPriceListPK getvPriceListPK() {
        return vPriceListPK;
    }

    public void setvPriceListPK(VPriceListPK vPriceListPK) {
        this.vPriceListPK = vPriceListPK;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public int getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(int codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getDesignationLtPrestation() {
        return designationLtPrestation;
    }

    public void setDesignationLtPrestation(String designationLtPrestation) {
        this.designationLtPrestation = designationLtPrestation;
    }

     
}
