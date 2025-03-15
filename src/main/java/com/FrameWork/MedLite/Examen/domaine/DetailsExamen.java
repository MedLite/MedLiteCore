/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestationPK;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "Details_Examen", schema = "examen")
@Audited
@AuditTable("Details_Examen_AUD")
public class DetailsExamen {

    @EmbeddedId
    protected DetailsExamenPK detailsExamenPK;

    @MapsId("codeExamen")
    @JoinColumn(name = "Code_Examen", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false)
    private Examen examen;

    @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false, updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", updatable = false, insertable = false)
    private Integer codeNatureAdmission;

    @JoinColumn(name = "Code_Patient", referencedColumnName = "Code", nullable = false, updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Patient patient;

    @Column(name = "Code_Patient", updatable = false, insertable = false)
    private Integer codePatient;

    @JoinColumn(name = "Code_Admission", referencedColumnName = "Code", nullable = false, updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Admission admission;

    @Column(name = "Code_Admission", updatable = false, insertable = false)
    private Integer codeAdmission;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Prestation", referencedColumnName = "Code", nullable = false, updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Prestation prestation;

    @Column(name = "Code_Prestation", updatable = false, insertable = false)
    private Integer codePrestation;

    public DetailsExamen() {
    }

    public DetailsExamenPK getDetailsExamenPK() {
        return detailsExamenPK;
    }

    public void setDetailsExamenPK(DetailsExamenPK detailsExamenPK) {
        this.detailsExamenPK = detailsExamenPK;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getCodePatient() {
        return codePatient;
    }

    public void setCodePatient(Integer codePatient) {
        this.codePatient = codePatient;
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
    
    

}
