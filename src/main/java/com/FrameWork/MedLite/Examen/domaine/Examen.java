/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.EtatPaiement;
import com.FrameWork.MedLite.Parametrage.domaine.FamilleFacturation;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Demande_Examen", schema = "examen")
@Audited
@AuditTable("Demande_Examen_AUD")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Size(max = 1)
    @NotNull
    @Column(name = "Type_Examen", length = 1)
    private String typeExamen;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Patient", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Patient patient;

    @Column(name = "Code_Patient", updatable = false, insertable = false)
    private Integer codePatient;

    @JoinColumn(name = "Code_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Admission admission;

    @Column(name = "Code_Admission", updatable = false, insertable = false)
    private Integer codeAdmission;

    @Column(name = "Pret", nullable = false, columnDefinition = ("bit default 0"))
    private boolean pret;

    @JoinColumn(name = "Etat_Paiement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatPaiement etatPaiement;

    @Column(name = "Etat_Paiement", updatable = false, insertable = false)
    private Integer codeEtatPaiement;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examen")
    private Collection<DetailsExamen> detailsExamens;

    @Column(name = "Code_Medecin_Demander", nullable = false)
    private Long codeMedecinDemande;

    public Examen() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(String typeExamen) {
        this.typeExamen = typeExamen;
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

    public boolean isPret() {
        return pret;
    }

    public void setPret(boolean pret) {
        this.pret = pret;
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

    public Collection<DetailsExamen> getDetailsExamens() {
        return detailsExamens;
    }

    public void setDetailsExamens(Collection<DetailsExamen> detailsExamens) {
        this.detailsExamens = detailsExamens;
    }

    public Long getCodeMedecinDemande() {
        return codeMedecinDemande;
    }

    public void setCodeMedecinDemande(Long codeMedecinDemande) {
        this.codeMedecinDemande = codeMedecinDemande;
    }

    
    
}
