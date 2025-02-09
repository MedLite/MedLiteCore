/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PrestationMedecinConsultationDTO {
      private Integer code;

     private MedecinDTO medecinDTO;

     private Integer codeMedecin;

     private PrestationDTO prestationDTO;

     private Integer codePrestation;

     private NatureAdmissionDTO natureAdmissionDTO;
 
    private Integer codeNatureAdmission;
     
    private String userCreate;
 
    private Date dateCreate;

    

    public PrestationMedecinConsultationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MedecinDTO getMedecinDTO() {
        return medecinDTO;
    }

    public void setMedecinDTO(MedecinDTO medecinDTO) {
        this.medecinDTO = medecinDTO;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
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
