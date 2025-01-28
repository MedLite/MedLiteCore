/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import com.FrameWork.MedLite.Parametrage.domaine.BlocOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.FamilleFacturation;
import com.FrameWork.MedLite.Parametrage.domaine.FamilleOperation;
import com.FrameWork.MedLite.Parametrage.domaine.TypeOperation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class OperationDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private FamilleFacturationDTO familleFacturationDTO;

    private Integer codeFamilleFacturation;
    
    private FamilleOperationDTO familleOperationDTO;

    private Integer codeFamilleOperation;

    private BlocOperationDTO blocOperationDTO;

    private Integer codeBlocOperation;
    
        private TypeOperationDTO typeOperationDTO;
 
    private Integer codeTypeOperation;
    

    private BigDecimal coutRevient;

    private BigDecimal prixMoyene;
      private Collection<DetailsOperationDTO> detailsOperationDTOs;

       private List<DetailsPriceListOperationDTO> detailsPriceListOperationDTOs;
  private boolean autoriseModifIntervenant;
    
    public OperationDTO() {
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

    public FamilleOperationDTO getFamilleOperationDTO() {
        return familleOperationDTO;
    }

    public void setFamilleOperationDTO(FamilleOperationDTO familleOperationDTO) {
        this.familleOperationDTO = familleOperationDTO;
    }

    public Integer getCodeFamilleOperation() {
        return codeFamilleOperation;
    }

    public void setCodeFamilleOperation(Integer codeFamilleOperation) {
        this.codeFamilleOperation = codeFamilleOperation;
    }

    public BlocOperationDTO getBlocOperationDTO() {
        return blocOperationDTO;
    }

    public void setBlocOperationDTO(BlocOperationDTO blocOperationDTO) {
        this.blocOperationDTO = blocOperationDTO;
    }

    public Integer getCodeBlocOperation() {
        return codeBlocOperation;
    }

    public void setCodeBlocOperation(Integer codeBlocOperation) {
        this.codeBlocOperation = codeBlocOperation;
    }

    public BigDecimal getCoutRevient() {
        return coutRevient;
    }

    public void setCoutRevient(BigDecimal coutRevient) {
        this.coutRevient = coutRevient;
    }

    public BigDecimal getPrixMoyene() {
        return prixMoyene;
    }

    public void setPrixMoyene(BigDecimal prixMoyene) {
        this.prixMoyene = prixMoyene;
    }

    public List<DetailsPriceListOperationDTO> getDetailsPriceListOperationDTOs() {
        return detailsPriceListOperationDTOs;
    }

    public void setDetailsPriceListOperationDTOs(List<DetailsPriceListOperationDTO> detailsPriceListOperationDTOs) {
        this.detailsPriceListOperationDTOs = detailsPriceListOperationDTOs;
    }

  

    public boolean isAutoriseModifIntervenant() {
        return autoriseModifIntervenant;
    }

    public void setAutoriseModifIntervenant(boolean autoriseModifIntervenant) {
        this.autoriseModifIntervenant = autoriseModifIntervenant;
    }

    public Collection<DetailsOperationDTO> getDetailsOperationDTOs() {
        return detailsOperationDTOs;
    }

    public void setDetailsOperationDTOs(Collection<DetailsOperationDTO> detailsOperationDTOs) {
        this.detailsOperationDTOs = detailsOperationDTOs;
    }

    public TypeOperationDTO getTypeOperationDTO() {
        return typeOperationDTO;
    }

    public void setTypeOperationDTO(TypeOperationDTO typeOperationDTO) {
        this.typeOperationDTO = typeOperationDTO;
    }

    public Integer getCodeTypeOperation() {
        return codeTypeOperation;
    }

    public void setCodeTypeOperation(Integer codeTypeOperation) {
        this.codeTypeOperation = codeTypeOperation;
    }

  
    
    

}
