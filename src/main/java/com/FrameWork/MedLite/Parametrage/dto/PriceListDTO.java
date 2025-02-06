/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PriceListDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;
    private String userCreate;

    private Date dateCreate;

//    private Collection<DetailsPriceListDTO> detailsPriceListsListDTOs;

    private SocieteDTO societeDTO;

    private Integer codeSociete;
    
        private boolean cash;
        
            private List<DetailsPriceListDTO> detailsPriceLists;     
            private List<DetailsPriceListOperationDTO> detailsPriceListOperationDTOs;


    public PriceListDTO() {
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

//    public Collection<DetailsPriceListDTO> getDetailsPriceListsListDTOs() {
//        return detailsPriceListsListDTOs;
//    }
//
//    public void setDetailsPriceListsListDTOs(Collection<DetailsPriceListDTO> detailsPriceListsListDTOs) {
//        this.detailsPriceListsListDTOs = detailsPriceListsListDTOs;
//    }

    public SocieteDTO getSocieteDTO() {
        return societeDTO;
    }

    public void setSocieteDTO(SocieteDTO societeDTO) {
        this.societeDTO = societeDTO;
    }

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public List<DetailsPriceListDTO> getDetailsPriceLists() {
        return detailsPriceLists;
    }

    public void setDetailsPriceLists(List<DetailsPriceListDTO> detailsPriceLists) {
        this.detailsPriceLists = detailsPriceLists;
    }

    public List<DetailsPriceListOperationDTO> getDetailsPriceListOperationDTOs() {
        return detailsPriceListOperationDTOs;
    }

    public void setDetailsPriceListOperationDTOs(List<DetailsPriceListOperationDTO> detailsPriceListOperationDTOs) {
        this.detailsPriceListOperationDTOs = detailsPriceListOperationDTOs;
    }
    
    
    
    
}
