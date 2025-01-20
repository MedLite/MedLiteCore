/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsPrestationDTO {

    private int codePrestation;
    private String codeSaisiePrestation;

    @NotNull
    private TypeIntervenantDTO typeIntervenantDTO;
    private String codeSaisieTypeIntervenant;
    private Integer codeTypeIntervenant;
    private String designationArTypeIntervenant;
    private String designationLtTypeIntervenant;

    @NotNull
    private NatureAdmissionDTO natureAdmissionDTO;
    private Integer codeNatureAdmission;

    private BigDecimal montant;  
    private BigDecimal PrixSelonTypeArriver;


    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public DetailsPrestationDTO() {
    }

    public int getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(int codePrestation) {
        this.codePrestation = codePrestation;
    }

    public String getCodeSaisiePrestation() {
        return codeSaisiePrestation;
    }

    public void setCodeSaisiePrestation(String codeSaisiePrestation) {
        this.codeSaisiePrestation = codeSaisiePrestation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
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

    public TypeIntervenantDTO getTypeIntervenantDTO() {
        return typeIntervenantDTO;
    }

    public void setTypeIntervenantDTO(TypeIntervenantDTO typeIntervenantDTO) {
        this.typeIntervenantDTO = typeIntervenantDTO;
    }

    public String getCodeSaisieTypeIntervenant() {
        return codeSaisieTypeIntervenant;
    }

    public void setCodeSaisieTypeIntervenant(String codeSaisieTypeIntervenant) {
        this.codeSaisieTypeIntervenant = codeSaisieTypeIntervenant;
    }

    public Integer getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(Integer codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public String getDesignationArTypeIntervenant() {
        return designationArTypeIntervenant;
    }

    public void setDesignationArTypeIntervenant(String designationArTypeIntervenant) {
        this.designationArTypeIntervenant = designationArTypeIntervenant;
    }

    public String getDesignationLtTypeIntervenant() {
        return designationLtTypeIntervenant;
    }

    public void setDesignationLtTypeIntervenant(String designationLtTypeIntervenant) {
        this.designationLtTypeIntervenant = designationLtTypeIntervenant;
    }

    public NatureAdmissionDTO getNatureAdmissionDTO() {
        return natureAdmissionDTO;
    }

    public void setNatureAdmissionDTO(NatureAdmissionDTO natureAdmissionDTO) {
        this.natureAdmissionDTO = natureAdmissionDTO;
    }

    public Integer getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(Integer codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public BigDecimal getPrixSelonTypeArriver() {
        return PrixSelonTypeArriver;
    }

    public void setPrixSelonTypeArriver(BigDecimal PrixSelonTypeArriver) {
        this.PrixSelonTypeArriver = PrixSelonTypeArriver;
    }
    
    

}
