/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.dto;

import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PastHistoryDTO {

    private Integer code;

    private Date dateCreate;

    private AdmissionDTO admissionDTO;

    private Integer codeAdmission;

    private String pastHistory;
        private String userCreate;

    public PastHistoryDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }

 

   

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
    
    

}
