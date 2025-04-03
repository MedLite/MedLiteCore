/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MedecinDTO {

    private Integer code;

    private String codeSaisie;

    private String nomIntervAr;

    private String nomIntervLt;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private TypeIntervenantDTO typeIntervenantDTO;

    private Integer codeTypeIntervenant;

    private SpecialiteMedecinDTO specialiteMedecinDTO;

    private Integer codeSpecialiteMedecin;

    private PrestationMedecinConsultationDTO prestationConsultationDTO;

    private List<PrestationMedecinConsultationDTO> prestationMedecinConsultationDTOs;

    private boolean autoriseFrais;

    private boolean autorisConsultation;

    private Integer codePrestationConsultation;

    private boolean opd;

    private boolean er;

    private boolean haveSig;

    public MedecinDTO() {
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

    public String getNomIntervAr() {
        return nomIntervAr;
    }

    public void setNomIntervAr(String nomIntervAr) {
        this.nomIntervAr = nomIntervAr;
    }

    public String getNomIntervLt() {
        return nomIntervLt;
    }

    public void setNomIntervLt(String nomIntervLt) {
        this.nomIntervLt = nomIntervLt;
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

    public TypeIntervenantDTO getTypeIntervenantDTO() {
        return typeIntervenantDTO;
    }

    public void setTypeIntervenantDTO(TypeIntervenantDTO typeIntervenantDTO) {
        this.typeIntervenantDTO = typeIntervenantDTO;
    }

    public Integer getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(Integer codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public SpecialiteMedecinDTO getSpecialiteMedecinDTO() {
        return specialiteMedecinDTO;
    }

    public void setSpecialiteMedecinDTO(SpecialiteMedecinDTO specialiteMedecinDTO) {
        this.specialiteMedecinDTO = specialiteMedecinDTO;
    }

    public Integer getCodeSpecialiteMedecin() {
        return codeSpecialiteMedecin;
    }

    public void setCodeSpecialiteMedecin(Integer codeSpecialiteMedecin) {
        this.codeSpecialiteMedecin = codeSpecialiteMedecin;
    }

    public PrestationMedecinConsultationDTO getPrestationConsultationDTO() {
        return prestationConsultationDTO;
    }

    public void setPrestationConsultationDTO(PrestationMedecinConsultationDTO prestationConsultationDTO) {
        this.prestationConsultationDTO = prestationConsultationDTO;
    }

    public boolean isAutoriseFrais() {
        return autoriseFrais;
    }

    public void setAutoriseFrais(boolean autoriseFrais) {
        this.autoriseFrais = autoriseFrais;
    }

    public List<PrestationMedecinConsultationDTO> getPrestationMedecinConsultationDTOs() {
        return prestationMedecinConsultationDTOs;
    }

    public void setPrestationMedecinConsultationDTOs(List<PrestationMedecinConsultationDTO> prestationMedecinConsultationDTOs) {
        this.prestationMedecinConsultationDTOs = prestationMedecinConsultationDTOs;
    }

    public boolean isAutorisConsultation() {
        return autorisConsultation;
    }

    public void setAutorisConsultation(boolean autorisConsultation) {
        this.autorisConsultation = autorisConsultation;
    }

    public Integer getCodePrestationConsultation() {
        return codePrestationConsultation;
    }

    public void setCodePrestationConsultation(Integer codePrestationConsultation) {
        this.codePrestationConsultation = codePrestationConsultation;
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

    public boolean isHaveSig() {
        return haveSig;
    }

    public void setHaveSig(boolean haveSig) {
        this.haveSig = haveSig;
    }

    
}
