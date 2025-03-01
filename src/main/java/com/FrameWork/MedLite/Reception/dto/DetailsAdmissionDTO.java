/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsAdmissionDTO {
    private Integer code;

     private AdmissionDTO admissionDTO;

     private Integer codeAdmission;

    private PrestationDTO prestationDTO;

     private Integer codePrestation;

     private BigDecimal montant;

     private BigDecimal montantPatient;

    private BigDecimal montantPEC;

    private String usercreate;

     private Date dateCreate;
     
    private boolean etatPaiement;

    public DetailsAdmissionDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AdmissionDTO getAdmissionDTO() {
        return admissionDTO;
    }

    public void setAdmissionDTO(AdmissionDTO admissionDTO) {
        this.admissionDTO = admissionDTO;
    }

    public Integer getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(Integer codeAdmission) {
        this.codeAdmission = codeAdmission;
    }

    public PrestationDTO getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(PrestationDTO prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantPatient() {
        return montantPatient;
    }

    public void setMontantPatient(BigDecimal montantPatient) {
        this.montantPatient = montantPatient;
    }

    public BigDecimal getMontantPEC() {
        return montantPEC;
    }

    public void setMontantPEC(BigDecimal montantPEC) {
        this.montantPEC = montantPEC;
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

    public boolean isEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(boolean etatPaiement) {
        this.etatPaiement = etatPaiement;
    }
    
    
}
