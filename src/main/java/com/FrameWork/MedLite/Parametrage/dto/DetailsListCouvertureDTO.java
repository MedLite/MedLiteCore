/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsListCouvertureDTO {
   private int codeListCouverture;
    private String codeSaisieListCouverture;

    @NotNull
    private PrestationDTO prestationDTO;
    private String codeSaisiePrestation;
    private Integer codePrestation;
    private String designationArPrestation;
    private String designationLtPrestation;

  private BigDecimal tauxCouverPec;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public DetailsListCouvertureDTO() {
    }

    public int getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(int codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
    }

    public String getCodeSaisieListCouverture() {
        return codeSaisieListCouverture;
    }

    public void setCodeSaisieListCouverture(String codeSaisieListCouverture) {
        this.codeSaisieListCouverture = codeSaisieListCouverture;
    }

    public PrestationDTO getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(PrestationDTO prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    public String getCodeSaisiePrestation() {
        return codeSaisiePrestation;
    }

    public void setCodeSaisiePrestation(String codeSaisiePrestation) {
        this.codeSaisiePrestation = codeSaisiePrestation;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

   
    
    public String getDesignationArPrestation() {
        return designationArPrestation;
    }

    public void setDesignationArPrestation(String designationArPrestation) {
        this.designationArPrestation = designationArPrestation;
    }

    public String getDesignationLtPrestation() {
        return designationLtPrestation;
    }

    public void setDesignationLtPrestation(String designationLtPrestation) {
        this.designationLtPrestation = designationLtPrestation;
    }

    public BigDecimal getTauxCouverPec() {
        return tauxCouverPec;
    }

    public void setTauxCouverPec(BigDecimal tauxCouverPec) {
        this.tauxCouverPec = tauxCouverPec;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    
    

}
