/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Nationalite;
import com.FrameWork.MedLite.Parametrage.dto.ConventionDTO;
import com.FrameWork.MedLite.Parametrage.dto.NationaliteDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
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
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.core.Conventions;

/**
 *
 * @author Administrator
 */
public class PatientDTO {
     private Integer code;

   private String codeSaisie;

    private String nomCompltAr;

    private String nomCompltLt;
 

    
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateNaissance;
    
    
    private String numTel;

    private Integer codeSociete;

    private Integer codeConvention;

    private boolean actif;

   private String userCreate;

    private Date dateCreate;

        private PriceListDTO priceListDTO;
 
    private Integer codePriceList;
    
//    private NationaliteDTO nationaliteDTO;
//
//    private Integer codeNationalite;
    
//        private ConventionDTO conventionDTO;


    public PatientDTO() {
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

    public String getNomCompltAr() {
        return nomCompltAr;
    }

    public void setNomCompltAr(String nomCompltAr) {
        this.nomCompltAr = nomCompltAr;
    }

    public String getNomCompltLt() {
        return nomCompltLt;
    }

    public void setNomCompltLt(String nomCompltLt) {
        this.nomCompltLt = nomCompltLt;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

     

     

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
    }

    public Integer getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(Integer codeConvention) {
        this.codeConvention = codeConvention;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
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

//    public NationaliteDTO getNationaliteDTO() {
//        return nationaliteDTO;
//    }
//
//    public void setNationaliteDTO(NationaliteDTO nationaliteDTO) {
//        this.nationaliteDTO = nationaliteDTO;
//    }
//
//    public Integer getCodeNationalite() {
//        return codeNationalite;
//    }
//
//    public void setCodeNationalite(Integer codeNationalite) {
//        this.codeNationalite = codeNationalite;
//    }
//
//    public void setConventionDTO(ConventionDTO conventionDTO) {
//        this.conventionDTO = conventionDTO;
//    }

    public PriceListDTO getPriceListDTO() {
        return priceListDTO;
    }

    public void setPriceListDTO(PriceListDTO priceListDTO) {
        this.priceListDTO = priceListDTO;
    }

    public Integer getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(Integer codePriceList) {
        this.codePriceList = codePriceList;
    }
    
    
    
}
