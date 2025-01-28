/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Recette.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Caisse;
import com.FrameWork.MedLite.Parametrage.domaine.Devise;
import com.FrameWork.MedLite.Parametrage.domaine.EtatApprouver;
import com.FrameWork.MedLite.Parametrage.domaine.ModeReglement;
import com.FrameWork.MedLite.Parametrage.dto.CaisseDTO;
import com.FrameWork.MedLite.Parametrage.dto.DeviseDTO;
import com.FrameWork.MedLite.Parametrage.dto.EtatApprouverDTO;
import com.FrameWork.MedLite.Parametrage.dto.ModeReglementDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TransfertCaisseDTO {

    private Integer code;

    private String codeSaisie;

    private CaisseDTO caisseDTO;

    private Integer codeCaisse;

    private CaisseDTO caisseTrDTO;

    private Integer codeCaisseTr;

    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private String userCreate;

    private Date dateCreate;

    private String observation;

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;

    private EtatApprouverDTO etatApprouverDTO;

    private Integer codeEtatApprouver;

    private Long codeUserApprouver;

    private Date dateApprouve;

    private BigDecimal montant;

    private BigDecimal montantEnDevise;

    private BigDecimal tauxChange;

    public TransfertCaisseDTO() {
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

    public CaisseDTO getCaisseDTO() {
        return caisseDTO;
    }

    public void setCaisseDTO(CaisseDTO caisseDTO) {
        this.caisseDTO = caisseDTO;
    }

    public Integer getCodeCaisse() {
        return codeCaisse;
    }

    public void setCodeCaisse(Integer codeCaisse) {
        this.codeCaisse = codeCaisse;
    }

    public CaisseDTO getCaisseTrDTO() {
        return caisseTrDTO;
    }

    public void setCaisseTrDTO(CaisseDTO caisseTrDTO) {
        this.caisseTrDTO = caisseTrDTO;
    }

    public Integer getCodeCaisseTr() {
        return codeCaisseTr;
    }

    public void setCodeCaisseTr(Integer codeCaisseTr) {
        this.codeCaisseTr = codeCaisseTr;
    }

    public DeviseDTO getDeviseDTO() {
        return deviseDTO;
    }

    public void setDeviseDTO(DeviseDTO deviseDTO) {
        this.deviseDTO = deviseDTO;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ModeReglementDTO getModeReglementDTO() {
        return modeReglementDTO;
    }

    public void setModeReglementDTO(ModeReglementDTO modeReglementDTO) {
        this.modeReglementDTO = modeReglementDTO;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

    public EtatApprouverDTO getEtatApprouverDTO() {
        return etatApprouverDTO;
    }

    public void setEtatApprouverDTO(EtatApprouverDTO etatApprouverDTO) {
        this.etatApprouverDTO = etatApprouverDTO;
    }

    public Integer getCodeEtatApprouver() {
        return codeEtatApprouver;
    }

    public void setCodeEtatApprouver(Integer codeEtatApprouver) {
        this.codeEtatApprouver = codeEtatApprouver;
    }

    public Long getCodeUserApprouver() {
        return codeUserApprouver;
    }

    public void setCodeUserApprouver(Long codeUserApprouver) {
        this.codeUserApprouver = codeUserApprouver;
    }

    public Date getDateApprouve() {
        return dateApprouve;
    }

    public void setDateApprouve(Date dateApprouve) {
        this.dateApprouve = dateApprouve;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantEnDevise() {
        return montantEnDevise;
    }

    public void setMontantEnDevise(BigDecimal montantEnDevise) {
        this.montantEnDevise = montantEnDevise;
    }

    public BigDecimal getTauxChange() {
        return tauxChange;
    }

    public void setTauxChange(BigDecimal tauxChange) {
        this.tauxChange = tauxChange;
    }
    
    
}
