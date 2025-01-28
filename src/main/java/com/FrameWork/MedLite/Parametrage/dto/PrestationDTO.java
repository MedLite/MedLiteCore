/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import com.FrameWork.MedLite.Parametrage.domaine.SousFamillePrestation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PrestationDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private FamilleFacturationDTO familleFacturationDTO;
    private Integer codeFamilleFacturation;

    private FamillePrestationDTO famillePrestationDTO;

    private Integer codeFamillePrestation;

    private SousFamillePrestationDTO sousFamillePrestationDTO;

    private Integer codeSousFamillePrestation;

    private BigDecimal montantOPD;

    private BigDecimal montantER;

    private BigDecimal montantIP;

    private BigDecimal prixPrestation;

    private boolean opd;
    private boolean er;
    private boolean ip;

    private Integer codePriceListCash;

    private Collection<DetailsPrestationDTO> detailsPrestationDTOs;

    private List<DetailsPriceListDTO> detailsPriceLists;

    public PrestationDTO() {
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

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
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

    public FamilleFacturationDTO getFamilleFacturationDTO() {
        return familleFacturationDTO;
    }

    public void setFamilleFacturationDTO(FamilleFacturationDTO familleFacturationDTO) {
        this.familleFacturationDTO = familleFacturationDTO;
    }

    public Integer getCodeFamilleFacturation() {
        return codeFamilleFacturation;
    }

    public void setCodeFamilleFacturation(Integer codeFamilleFacturation) {
        this.codeFamilleFacturation = codeFamilleFacturation;
    }

    public FamillePrestationDTO getFamillePrestationDTO() {
        return famillePrestationDTO;
    }

    public void setFamillePrestationDTO(FamillePrestationDTO famillePrestationDTO) {
        this.famillePrestationDTO = famillePrestationDTO;
    }

    public Integer getCodeFamillePrestation() {
        return codeFamillePrestation;
    }

    public void setCodeFamillePrestation(Integer codeFamillePrestation) {
        this.codeFamillePrestation = codeFamillePrestation;
    }

    public BigDecimal getMontantOPD() {
        return montantOPD;
    }

    public void setMontantOPD(BigDecimal montantOPD) {
        this.montantOPD = montantOPD;
    }

    public BigDecimal getMontantER() {
        return montantER;
    }

    public void setMontantER(BigDecimal montantER) {
        this.montantER = montantER;
    }

    public BigDecimal getMontantIP() {
        return montantIP;
    }

    public void setMontantIP(BigDecimal montantIP) {
        this.montantIP = montantIP;
    }

    public Collection<DetailsPrestationDTO> getDetailsPrestationDTOs() {
        return detailsPrestationDTOs;
    }

    public void setDetailsPrestationDTOs(Collection<DetailsPrestationDTO> detailsPrestationDTOs) {
        this.detailsPrestationDTOs = detailsPrestationDTOs;
    }

    public boolean isOpd() {
        return opd;
    }

    public void setOpd(boolean opd) {
        this.opd = opd;
    }

    public boolean isEr() {
        return er;
    }

    public void setEr(boolean er) {
        this.er = er;
    }

    public boolean isIp() {
        return ip;
    }

    public void setIp(boolean ip) {
        this.ip = ip;
    }

    public BigDecimal getPrixPrestation() {
        return prixPrestation;
    }

    public void setPrixPrestation(BigDecimal prixPrestation) {
        this.prixPrestation = prixPrestation;
    }

    public Integer getCodePriceListCash() {
        return codePriceListCash;
    }

    public void setCodePriceListCash(Integer codePriceListCash) {
        this.codePriceListCash = codePriceListCash;
    }

    public List<DetailsPriceListDTO> getDetailsPriceLists() {
        return detailsPriceLists;
    }

    public void setDetailsPriceLists(List<DetailsPriceListDTO> detailsPriceLists) {
        this.detailsPriceLists = detailsPriceLists;
    }

    public SousFamillePrestationDTO getSousFamillePrestationDTO() {
        return sousFamillePrestationDTO;
    }

    public void setSousFamillePrestationDTO(SousFamillePrestationDTO sousFamillePrestationDTO) {
        this.sousFamillePrestationDTO = sousFamillePrestationDTO;
    }

    public Integer getCodeSousFamillePrestation() {
        return codeSousFamillePrestation;
    }

    public void setCodeSousFamillePrestation(Integer codeSousFamillePrestation) {
        this.codeSousFamillePrestation = codeSousFamillePrestation;
    }
    
    

}
