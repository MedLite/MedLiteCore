/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import java.util.Date;

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
    
        private PrestationDTO prestationConsultationDTO;
 
    private Integer codePrestationConsultation;
    

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
