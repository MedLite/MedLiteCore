/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.domaine;

import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Feuille_Soin_Admission", schema = "dmi")
@Audited
@AuditTable("Feuille_Soin_Admission_AUD")
public class FeuilleSoinOPD {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;
       
       
         @JoinColumn(name = "Code_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Admission admission;

    @Column(name = "Code_Admission", updatable = false, insertable = false)
    private Integer codeAdmission;
    
    
      @JoinColumn(name = "Code_Cheif_Complaint", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private CheifComplaint cheifComplaint;

    @Column(name = "Code_Cheif_Complaint", updatable = false, insertable = false)
    private Integer codeCheifComplaint;
    
        @JoinColumn(name = "Code_Diagnosis", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Diagnosis diagnosis;

    @Column(name = "Code_Diagnosis", updatable = false, insertable = false)
    private Integer codeDiagnosis;
    
    
           @JoinColumn(name = "Code_Allergy", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Allergy allergy;

    @Column(name = "Code_Allergy", updatable = false, insertable = false)
    private Integer codeAllergy;
    
    
    
               @JoinColumn(name = "Code_Past_History", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private PastHistory pastHistory;

    @Column(name = "Code_Past_History", updatable = false, insertable = false)
    private Integer codePastHistory;

    public FeuilleSoinOPD() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    public Integer getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(Integer codeAdmission) {
        this.codeAdmission = codeAdmission;
    }

    public CheifComplaint getCheifComplaint() {
        return cheifComplaint;
    }

    public void setCheifComplaint(CheifComplaint cheifComplaint) {
        this.cheifComplaint = cheifComplaint;
    }

    public Integer getCodeCheifComplaint() {
        return codeCheifComplaint;
    }

    public void setCodeCheifComplaint(Integer codeCheifComplaint) {
        this.codeCheifComplaint = codeCheifComplaint;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Integer getCodeDiagnosis() {
        return codeDiagnosis;
    }

    public void setCodeDiagnosis(Integer codeDiagnosis) {
        this.codeDiagnosis = codeDiagnosis;
    }

    public Allergy getAllergy() {
        return allergy;
    }

    public void setAllergy(Allergy allergy) {
        this.allergy = allergy;
    }

    public Integer getCodeAllergy() {
        return codeAllergy;
    }

    public void setCodeAllergy(Integer codeAllergy) {
        this.codeAllergy = codeAllergy;
    }

    public PastHistory getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(PastHistory pastHistory) {
        this.pastHistory = pastHistory;
    }

    public Integer getCodePastHistory() {
        return codePastHistory;
    }

    public void setCodePastHistory(Integer codePastHistory) {
        this.codePastHistory = codePastHistory;
    }
    
    
    
    
    
    
    
    
}
