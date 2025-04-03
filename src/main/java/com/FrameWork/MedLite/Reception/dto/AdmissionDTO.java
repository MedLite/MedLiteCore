/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.dto;

import com.FrameWork.MedLite.Examen.dto.ExamenDTO;
import com.FrameWork.MedLite.Parametrage.domaine.MotifAdmission;
import com.FrameWork.MedLite.Parametrage.dto.CabinetDTO;
import com.FrameWork.MedLite.Parametrage.dto.MedecinDTO;
import com.FrameWork.MedLite.Parametrage.dto.MotifAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class AdmissionDTO {
    private Integer code;
 
    private String codeSaisie;
 
    private String userCreate;
 
    private Date dateCreate;
 
    private PatientDTO patientDTO;
 
    private Integer codePatient;
 
    private NatureAdmissionDTO natureAdmissionDTO;
 
    private Integer codeNatureAdmission;
 
    private MedecinDTO medecinDTO;
 
    private Integer codeMedecin;

 
    private MotifAdmissionDTO motifAdmissionDTO;
 
    private Integer codeMotifAdmission;
 
    private Integer codeSociete;
 
    private Integer codeConvention;
 
    private Integer codeListCouverture;
 
    private Integer codeCabinet;
    private CabinetDTO cabinetDTO;
 
    private List<DetailsAdmissionDTO> detailsAdmissionDTOs;
        private List<AdmissionFacturationDTO> admissionFacturationDTOs;

    
        private List<ReglementDTO> reglementDTOs;
 
    private boolean etatPaiement;
    
       private PriceListDTO priceListDTO;
 
    private Integer codePriceList;
    
        private boolean regDeferral;
        
            private boolean HavePrestationNotPaied;         
            private boolean HaveLaboNotPaied;  
            private boolean HaveRadioNotPaied;

   private List<ExamenDTO> examenDTO;


    public AdmissionDTO() {
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

    public PatientDTO getPatientDTO() {
        return patientDTO;
    }

    public void setPatientDTO(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public Integer getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(Integer codePatient) {
        this.codePatient = codePatient;
    }

    public boolean isRegDeferral() {
        return regDeferral;
    }

    public void setRegDeferral(boolean regDeferral) {
        this.regDeferral = regDeferral;
    }

    
    public NatureAdmissionDTO getNatureAdmissionDTO() {
        return natureAdmissionDTO;
    }

    public void setNatureAdmissionDTO(NatureAdmissionDTO natureAdmissionDTO) {
        this.natureAdmissionDTO = natureAdmissionDTO;
    }

    public Integer getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(Integer codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
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

    public MotifAdmissionDTO getMotifAdmissionDTO() {
        return motifAdmissionDTO;
    }

    public void setMotifAdmissionDTO(MotifAdmissionDTO motifAdmissionDTO) {
        this.motifAdmissionDTO = motifAdmissionDTO;
    }

    

    public Integer getCodeMotifAdmission() {
        return codeMotifAdmission;
    }

    public void setCodeMotifAdmission(Integer codeMotifAdmission) {
        this.codeMotifAdmission = codeMotifAdmission;
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

    public Integer getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(Integer codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
    }

    public Integer getCodeCabinet() {
        return codeCabinet;
    }

    public void setCodeCabinet(Integer codeCabinet) {
        this.codeCabinet = codeCabinet;
    }

    public List<DetailsAdmissionDTO> getDetailsAdmissionDTOs() {
        return detailsAdmissionDTOs;
    }

    public void setDetailsAdmissionDTOs(List<DetailsAdmissionDTO> detailsAdmissionDTOs) {
        this.detailsAdmissionDTOs = detailsAdmissionDTOs;
    }

    public boolean isEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(boolean etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

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

    public List<ReglementDTO> getReglementDTOs() {
        return reglementDTOs;
    }

    public void setReglementDTOs(List<ReglementDTO> reglementDTOs) {
        this.reglementDTOs = reglementDTOs;
    }

    public List<AdmissionFacturationDTO> getAdmissionFacturationDTOs() {
        return admissionFacturationDTOs;
    }

    public void setAdmissionFacturationDTOs(List<AdmissionFacturationDTO> admissionFacturationDTOs) {
        this.admissionFacturationDTOs = admissionFacturationDTOs;
    }

    public CabinetDTO getCabinetDTO() {
        return cabinetDTO;
    }

    public void setCabinetDTO(CabinetDTO cabinetDTO) {
        this.cabinetDTO = cabinetDTO;
    }

    public boolean isHavePrestationNotPaied() {
        return HavePrestationNotPaied;
    }

    public void setHavePrestationNotPaied(boolean HavePrestationNotPaied) {
        this.HavePrestationNotPaied = HavePrestationNotPaied;
    }

    public boolean isHaveLaboNotPaied() {
        return HaveLaboNotPaied;
    }

    public void setHaveLaboNotPaied(boolean HaveLaboNotPaied) {
        this.HaveLaboNotPaied = HaveLaboNotPaied;
    }

    public boolean isHaveRadioNotPaied() {
        return HaveRadioNotPaied;
    }

    public void setHaveRadioNotPaied(boolean HaveRadioNotPaied) {
        this.HaveRadioNotPaied = HaveRadioNotPaied;
    }

    public List<ExamenDTO> getExamenDTO() {
        return examenDTO;
    }

    public void setExamenDTO(List<ExamenDTO> examenDTO) {
        this.examenDTO = examenDTO;
    }

 
    
    
    
}
