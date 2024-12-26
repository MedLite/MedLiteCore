/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PrestationConsultationDTO {

    private Integer code;

    private String userCreate;

    private Date dateCreate;

    private BigDecimal montant;

    private Collection<DetailsPrestationConsultationDTO> detailsPrestationConsultationDTOs;

    public PrestationConsultationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Collection<DetailsPrestationConsultationDTO> getDetailsPrestationConsultationDTOs() {
        return detailsPrestationConsultationDTOs;
    }

    public void setDetailsPrestationConsultationDTOs(Collection<DetailsPrestationConsultationDTO> detailsPrestationConsultationDTOs) {
        this.detailsPrestationConsultationDTOs = detailsPrestationConsultationDTOs;
    }

   

}
