/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.dto;

import com.FrameWork.MedLite.Parametrage.dto.ConventionDTO;
import com.FrameWork.MedLite.Parametrage.dto.EtatPaiementDTO;
import com.FrameWork.MedLite.Parametrage.dto.EtatPatientDTO;
import com.FrameWork.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.dto.SocieteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class AdmissionFacturationDTO {

    private Integer code;

    private NatureAdmissionDTO natureAdmissionDTO;

    private Integer codeNatureAdmission;

    private AdmissionDTO admissionDTO;

    private Integer codeAdmission;

    private EtatPaiementDTO etatPaiementDTO;

    private Integer codeEtatPaiement;

    private EtatPatientDTO etatPatientDTO;

    private Integer codeEtatPatient;
    private String codeFactureAdmission;
    
    
        private ConventionDTO conventionDTO;

      private Integer codeConvention;

    private SocieteDTO societeDTO;
 
    private Integer codeSociete;
    
     private String userCreate;
 
    private Date dateCreate;
    

    public AdmissionFacturationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public EtatPaiementDTO getEtatPaiementDTO() {
        return etatPaiementDTO;
    }

    public void setEtatPaiementDTO(EtatPaiementDTO etatPaiementDTO) {
        this.etatPaiementDTO = etatPaiementDTO;
    }

    public Integer getCodeEtatPaiement() {
        return codeEtatPaiement;
    }

    public void setCodeEtatPaiement(Integer codeEtatPaiement) {
        this.codeEtatPaiement = codeEtatPaiement;
    }

    public EtatPatientDTO getEtatPatientDTO() {
        return etatPatientDTO;
    }

    public void setEtatPatientDTO(EtatPatientDTO etatPatientDTO) {
        this.etatPatientDTO = etatPatientDTO;
    }

    public Integer getCodeEtatPatient() {
        return codeEtatPatient;
    }

    public void setCodeEtatPatient(Integer codeEtatPatient) {
        this.codeEtatPatient = codeEtatPatient;
    }

    public String getCodeFactureAdmission() {
        return codeFactureAdmission;
    }

    public void setCodeFactureAdmission(String codeFactureAdmission) {
        this.codeFactureAdmission = codeFactureAdmission;
    }

    public ConventionDTO getConventionDTO() {
        return conventionDTO;
    }

    public void setConventionDTO(ConventionDTO conventionDTO) {
        this.conventionDTO = conventionDTO;
    }

    public Integer getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(Integer codeConvention) {
        this.codeConvention = codeConvention;
    }

    public SocieteDTO getSocieteDTO() {
        return societeDTO;
    }

    public void setSocieteDTO(SocieteDTO societeDTO) {
        this.societeDTO = societeDTO;
    }

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
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
    
    
    

}
