/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.dto.CabinetDTO;
import com.FrameWork.MedLite.Parametrage.dto.MedecinDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PlanningCabinetDTO {

    private Integer code;
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateExiste;

    private String userCreate;
    private Date dateCreate;

    private MedecinDTO medecinDTO;

    private Integer codeMedecin;

    private CabinetDTO cabinetDTO;

    private Integer codeCabinet;

    private boolean actif;
    private Integer nbrePlaceDispo;
    private Integer nbrePlaceUtiliser;

    public PlanningCabinetDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDate getDateExiste() {
        return dateExiste;
    }

    public void setDateExiste(LocalDate dateExiste) {
        this.dateExiste = dateExiste;
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

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
    }

    public Integer getCodeCabinet() {
        return codeCabinet;
    }

    public void setCodeCabinet(Integer codeCabinet) {
        this.codeCabinet = codeCabinet;
    }

    public MedecinDTO getMedecinDTO() {
        return medecinDTO;
    }

    public void setMedecinDTO(MedecinDTO medecinDTO) {
        this.medecinDTO = medecinDTO;
    }

    public CabinetDTO getCabinetDTO() {
        return cabinetDTO;
    }

    public void setCabinetDTO(CabinetDTO cabinetDTO) {
        this.cabinetDTO = cabinetDTO;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Integer getNbrePlaceDispo() {
        return nbrePlaceDispo;
    }

    public void setNbrePlaceDispo(Integer nbrePlaceDispo) {
        this.nbrePlaceDispo = nbrePlaceDispo;
    }

    public Integer getNbrePlaceUtiliser() {
        return nbrePlaceUtiliser;
    }

    public void setNbrePlaceUtiliser(Integer nbrePlaceUtiliser) {
        this.nbrePlaceUtiliser = nbrePlaceUtiliser;
    }

    
}
