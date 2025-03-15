/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.dto;

import com.FrameWork.MedLite.Parametrage.domaine.EtatPaiement;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.EtatPaiementDTO;
import com.FrameWork.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.Patient;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.PatientDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ExamenDTO {
      private Integer code;

     private String codeSaisie;

     private String userCreate;

     private Date dateCreate;

     private PrestationDTO prestationDTO;

     private Integer codePrestation;

    private PatientDTO patientDTO;

     private Integer codePatient;

     private AdmissionDTO admissionDTO;

     private Integer codeAdmission;

     private NatureAdmissionDTO natureAdmissionDTO;

     private Integer codeNatureAdmission;

     private boolean pret;
     
     private EtatPaiementDTO etatPaiementDTO;

    private Integer codeEtatPaiement;
    private String typeExamen;
    
        private Collection<DetailsExamenDTO> detailsExamenDTOs;
    public ExamenDTO() {
    }

    public String getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(String typeExamen) {
        this.typeExamen = typeExamen;
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

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public Integer getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(Integer codePatient) {
        this.codePatient = codePatient;
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

    public boolean isPret() {
        return pret;
    }

    public void setPret(boolean pret) {
        this.pret = pret;
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

    public Collection<DetailsExamenDTO> getDetailsExamenDTOs() {
        return detailsExamenDTOs;
    }

    public void setDetailsExamenDTOs(Collection<DetailsExamenDTO> detailsExamenDTOs) {
        this.detailsExamenDTOs = detailsExamenDTOs;
    }

    
}
