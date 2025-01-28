/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsPrestationConsultationDTO {

    private int codePrestConsult;
    private String codeSaisieFKPrestationConsultation;

    @NotNull
    private TypeIntervenantDTO typeIntervenantDTO;
    private String codeSaisieTypeIntervenant;
    private Integer codeTypeIntervenant;
    private String designationArTypeIntervenant;
    private String designationLtTypeIntervenant;

    @NotNull
    private PrestationDTO prestationDTO;
    private String codeSaisiePrestation;
    private Integer codePrestation;
    private String designationArPrestation;
    private String designationLtPrestation;

    private BigDecimal montant;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public DetailsPrestationConsultationDTO() {
    }

    public int getCodePrestConsult() {
        return codePrestConsult;
    }

    public void setCodePrestConsult(int codePrestConsult) {
        this.codePrestConsult = codePrestConsult;
    }

 
   
    public String getCodeSaisieFKPrestationConsultation() {
        return codeSaisieFKPrestationConsultation;
    }

    public void setCodeSaisieFKPrestationConsultation(String codeSaisieFKPrestationConsultation) {
        this.codeSaisieFKPrestationConsultation = codeSaisieFKPrestationConsultation;
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
    
    
}
