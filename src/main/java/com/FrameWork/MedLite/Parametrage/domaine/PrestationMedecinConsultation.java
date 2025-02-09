/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Prestation_Consultation_Par_Nature_Admission", schema = "param")
@Audited
@AuditTable("Prestation_Consultation_Par_Nature_Admission_AUD")
public class PrestationMedecinConsultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Medecin", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Medecin medecin;

    @Column(name = "Code_Medecin", updatable = false, insertable = false, nullable = false)
    private Integer codeMedecin;

    @JoinColumn(name = "Code_Prestation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Prestation prestation;

    @Column(name = "Code_Prestation", updatable = false, insertable = false, nullable = false)
    private Integer codePrestation;

    @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", updatable = false, insertable = false, nullable = false)
    private Integer codeNatureAdmission;
    
        @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;


    public PrestationMedecinConsultation() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public NatureAdmission getNatureAdmission() {
        return natureAdmission;
    }

    public void setNatureAdmission(NatureAdmission natureAdmission) {
        this.natureAdmission = natureAdmission;
    }

    public Integer getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(Integer codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
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
    
    
    
    

}
