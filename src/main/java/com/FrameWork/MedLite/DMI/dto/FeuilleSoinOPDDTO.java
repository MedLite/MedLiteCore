/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.dto;

import com.FrameWork.MedLite.Reception.dto.AdmissionDTO;

/**
 *
 * @author Administrator
 */
public class FeuilleSoinOPDDTO {

    private Integer code;

    private AdmissionDTO admissionDTO;

    private Integer codeAdmission;

    private CheifComplaintDTO cheifComplaintDTO;

    private Integer codeCheifComplaint;

    private DiagnosisDTO diagnosisDTO;

    private Integer codeDiagnosis;

    private AllergyDTO allergyDTO;

    private Integer codeAllergy;

    private PastHistoryDTO pastHistoryDTO;

    private Integer codePastHistory;

    public FeuilleSoinOPDDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public CheifComplaintDTO getCheifComplaintDTO() {
        return cheifComplaintDTO;
    }

    public void setCheifComplaintDTO(CheifComplaintDTO cheifComplaintDTO) {
        this.cheifComplaintDTO = cheifComplaintDTO;
    }

    public Integer getCodeCheifComplaint() {
        return codeCheifComplaint;
    }

    public void setCodeCheifComplaint(Integer codeCheifComplaint) {
        this.codeCheifComplaint = codeCheifComplaint;
    }

    public DiagnosisDTO getDiagnosisDTO() {
        return diagnosisDTO;
    }

    public void setDiagnosisDTO(DiagnosisDTO diagnosisDTO) {
        this.diagnosisDTO = diagnosisDTO;
    }

    public Integer getCodeDiagnosis() {
        return codeDiagnosis;
    }

    public void setCodeDiagnosis(Integer codeDiagnosis) {
        this.codeDiagnosis = codeDiagnosis;
    }

    public AllergyDTO getAllergyDTO() {
        return allergyDTO;
    }

    public void setAllergyDTO(AllergyDTO allergyDTO) {
        this.allergyDTO = allergyDTO;
    }

    public Integer getCodeAllergy() {
        return codeAllergy;
    }

    public void setCodeAllergy(Integer codeAllergy) {
        this.codeAllergy = codeAllergy;
    }

    public PastHistoryDTO getPastHistoryDTO() {
        return pastHistoryDTO;
    }

    public void setPastHistoryDTO(PastHistoryDTO pastHistoryDTO) {
        this.pastHistoryDTO = pastHistoryDTO;
    }

    public Integer getCodePastHistory() {
        return codePastHistory;
    }

    public void setCodePastHistory(Integer codePastHistory) {
        this.codePastHistory = codePastHistory;
    }

}
