/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.dto;

import com.FrameWork.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.dto.TypeIntervenantDTO;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.PatientDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsExamenDTO {

    private int codeExamen;
    private String CodeSaisieExamen;

    @NotNull
    private PrestationDTO prestationDTO;
    private String codeSaisiePrestation;
    private Integer codePrestation;
    private String designationArPrestation;
    private String designationLtPrestation;

    @NotNull
    private NatureAdmissionDTO natureAdmissionDTO;
    private Integer codeNatureAdmission;
    private String codeSaisieNatureAdmission;
    private String designationArNatureAdmission;
    private String designationLtNatureAdmission;
    
        @NotNull
    private PatientDTO patientDTO;
    private Integer codePatient;
    private String codeSaisiePatient;
    private String designationArPatient;
    private String designationLtPatient;
    
          @NotNull
    private AdmissionDTO admissionDTO;
    private Integer codeAdmission;
    private String codeSaisieAdmission; 

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public int getCodeExamen() {
        return codeExamen;
    }

    public void setCodeExamen(int codeExamen) {
        this.codeExamen = codeExamen;
    }

    public String getCodeSaisieExamen() {
        return CodeSaisieExamen;
    }

    public void setCodeSaisieExamen(String CodeSaisieExamen) {
        this.CodeSaisieExamen = CodeSaisieExamen;
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

    public String getCodeSaisieNatureAdmission() {
        return codeSaisieNatureAdmission;
    }

    public void setCodeSaisieNatureAdmission(String codeSaisieNatureAdmission) {
        this.codeSaisieNatureAdmission = codeSaisieNatureAdmission;
    }

    public String getDesignationArNatureAdmission() {
        return designationArNatureAdmission;
    }

    public void setDesignationArNatureAdmission(String designationArNatureAdmission) {
        this.designationArNatureAdmission = designationArNatureAdmission;
    }

    public String getDesignationLtNatureAdmission() {
        return designationLtNatureAdmission;
    }

    public void setDesignationLtNatureAdmission(String designationLtNatureAdmission) {
        this.designationLtNatureAdmission = designationLtNatureAdmission;
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

    public String getCodeSaisiePatient() {
        return codeSaisiePatient;
    }

    public void setCodeSaisiePatient(String codeSaisiePatient) {
        this.codeSaisiePatient = codeSaisiePatient;
    }

    public String getDesignationArPatient() {
        return designationArPatient;
    }

    public void setDesignationArPatient(String designationArPatient) {
        this.designationArPatient = designationArPatient;
    }

    public String getDesignationLtPatient() {
        return designationLtPatient;
    }

    public void setDesignationLtPatient(String designationLtPatient) {
        this.designationLtPatient = designationLtPatient;
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

    public String getCodeSaisieAdmission() {
        return codeSaisieAdmission;
    }

    public void setCodeSaisieAdmission(String codeSaisieAdmission) {
        this.codeSaisieAdmission = codeSaisieAdmission;
    }
    
    
}
