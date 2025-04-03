/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.domaine.EtatPaiement;
import com.FrameWork.MedLite.Parametrage.domaine.EtatPatient;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.Societe;
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
@Table(name = "Admission_Facturation", schema = "recept")
@Audited
@AuditTable("Admission_Facturation_AUD")
public class AdmissionFacturation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", updatable = false, insertable = false, nullable = false)
    private Integer codeNatureAdmission;

    @JoinColumn(name = "Code_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Admission admission;

    @Column(name = "Code_Admission", updatable = false, insertable = false, nullable = false)
    private Integer codeAdmission;

    @JoinColumn(name = "Code_Etat_Paiement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatPaiement etatPaiement;

    @Column(name = "Code_Etat_Paiement", updatable = false, insertable = false, nullable = false)
    private Integer codeEtatPaiement;

    @JoinColumn(name = "Code_Etat_Patient", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatPatient etatPatient;

    @Column(name = "Code_Etat_Patient", updatable = false, insertable = false, nullable = false)
    private Integer codeEtatPatient;

    @Column(name = "Code_Convention")
    private Integer codeConvention;
    @Column(name = "Code_Societe")
    private Integer codeSociete;

    @Column(name = "Code_Facture_Admission", columnDefinition = ("varchar(20)"))
    private String codeFactureAdmission;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    public AdmissionFacturation() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public EtatPaiement getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(EtatPaiement etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public Integer getCodeEtatPaiement() {
        return codeEtatPaiement;
    }

    public void setCodeEtatPaiement(Integer codeEtatPaiement) {
        this.codeEtatPaiement = codeEtatPaiement;
    }

    public EtatPatient getEtatPatient() {
        return etatPatient;
    }

    public void setEtatPatient(EtatPatient etatPatient) {
        this.etatPatient = etatPatient;
    }

    public Integer getCodeEtatPatient() {
        return codeEtatPatient;
    }

    public void setCodeEtatPatient(Integer codeEtatPatient) {
        this.codeEtatPatient = codeEtatPatient;
    }

    public String getCodeFactureAdmission() {
        return codeFactureAdmission;
    }

    public void setCodeFactureAdmission(String codeFactureAdmission) {
        this.codeFactureAdmission = codeFactureAdmission;
    }

    public Integer getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(Integer codeConvention) {
        this.codeConvention = codeConvention;
    }

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
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
