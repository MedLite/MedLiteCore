/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Honoraire.dto;

import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.dto.MedecinDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;
import com.FrameWork.MedLite.Reception.dto.DetailsAdmissionDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class DetailsHonoraireAdmissionDTO {

    private Integer code;

    private boolean paid;

    private AdmissionDTO admissionDTO;

    private Integer codeAdmission;

    private MedecinDTO medecinDTO;

    private Integer codeMedecin;

    private PrestationDTO prestationDTO;

    private Integer codePrestation;

    private boolean pec;

    private String codeBordereau;

    private DetailsAdmissionDTO detailsAdmissionDTO;

    private Integer codeDetailsAdmission;

    private BigDecimal montantPEC;

    private BigDecimal montantPatient;

    public DetailsHonoraireAdmissionDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public AdmissionDTO getAdmissionDTO() {
        return admissionDTO;
    }

    public void setAdmissionDTO(AdmissionDTO admissionDTO) {
        this.admissionDTO = admissionDTO;
    }

    public Integer getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(Integer codeAdmission) {
        this.codeAdmission = codeAdmission;
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

    public PrestationDTO getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(PrestationDTO prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public boolean isPec() {
        return pec;
    }

    public void setPec(boolean pec) {
        this.pec = pec;
    }

    public String getCodeBordereau() {
        return codeBordereau;
    }

    public void setCodeBordereau(String codeBordereau) {
        this.codeBordereau = codeBordereau;
    }

    public DetailsAdmissionDTO getDetailsAdmissionDTO() {
        return detailsAdmissionDTO;
    }

    public void setDetailsAdmissionDTO(DetailsAdmissionDTO detailsAdmissionDTO) {
        this.detailsAdmissionDTO = detailsAdmissionDTO;
    }

    public Integer getCodeDetailsAdmission() {
        return codeDetailsAdmission;
    }

    public void setCodeDetailsAdmission(Integer codeDetailsAdmission) {
        this.codeDetailsAdmission = codeDetailsAdmission;
    }

    public BigDecimal getMontantPEC() {
        return montantPEC;
    }

    public void setMontantPEC(BigDecimal montantPEC) {
        this.montantPEC = montantPEC;
    }

    public BigDecimal getMontantPatient() {
        return montantPatient;
    }

    public void setMontantPatient(BigDecimal montantPatient) {
        this.montantPatient = montantPatient;
    }

}
