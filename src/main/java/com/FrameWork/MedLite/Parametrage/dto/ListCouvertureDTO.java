/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author Administrator
 */
public class ListCouvertureDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private List<DetailsListCouvertureDTO> detailsListCouvertureDTOs;

    private List<DetailsListCouvertureOperationDTO> detailsListCouvertureOperationDTOs;

    
        private Integer codePriceList;

        
        
            private Integer codeNatureAdmission;

            
    public ListCouvertureDTO() {
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

    public List<DetailsListCouvertureDTO> getDetailsListCouvertureDTOs() {
        return detailsListCouvertureDTOs;
    }

    public void setDetailsListCouvertureDTOs(List<DetailsListCouvertureDTO> detailsListCouvertureDTOs) {
        this.detailsListCouvertureDTOs = detailsListCouvertureDTOs;
    }

    public List<DetailsListCouvertureOperationDTO> getDetailsListCouvertureOperationDTOs() {
        return detailsListCouvertureOperationDTOs;
    }

    public void setDetailsListCouvertureOperationDTOs(List<DetailsListCouvertureOperationDTO> detailsListCouvertureOperationDTOs) {
        this.detailsListCouvertureOperationDTOs = detailsListCouvertureOperationDTOs;
    }

}
