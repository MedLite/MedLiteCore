/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import com.DevPointSystem.MedLite.Parametrage.domaine.Medecin;
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import jakarta.persistence.Column;
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

    private MedecinDTO medecinDTO;

    private Integer codeMedecin;

    private PrestationDTO prestationConsultationDTO;

    private Integer codePrestationConsultation;

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

    public PrestationDTO getPrestationConsultationDTO() {
        return prestationConsultationDTO;
    }

    public void setPrestationConsultationDTO(PrestationDTO prestationConsultationDTO) {
        this.prestationConsultationDTO = prestationConsultationDTO;
    }

    public Integer getCodePrestationConsultation() {
        return codePrestationConsultation;
    }

    public void setCodePrestationConsultation(Integer codePrestationConsultation) {
        this.codePrestationConsultation = codePrestationConsultation;
    }
    

}
