/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.FamilleFacturation;
import com.FrameWork.MedLite.Parametrage.domaine.FamillePrestation;
import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.domaine.MotifAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.domaine.Societe;
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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Admission", schema = "recept")
@Audited
@AuditTable("Admission_AUD")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Patient", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Patient patient;

    @Column(name = "Code_Patient", updatable = false, insertable = false, nullable = false)
    private Integer codePatient;

    @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", updatable = false, insertable = false, nullable = false)
    private Integer codeNatureAdmission;

    @JoinColumn(name = "Code_Medecin", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Medecin medecin;

    @Column(name = "Code_Medecin", updatable = false, insertable = false, nullable = false)
    private Integer codeMedecin;

    @JoinColumn(name = "Code_Price_List", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private PriceList priceList;

    @Column(name = "Code_Price_List", updatable = false, insertable = false, nullable = false)
    private Integer codePriceList;

    @JoinColumn(name = "Code_Motif_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private MotifAdmission motifAdmission;

    @Column(name = "Code_Motif_Admission", updatable = false, insertable = false, nullable = false)
    private Integer codeMotifAdmission;

    @Column(name = "Code_Societe")
    private Integer codeSociete;

    @Column(name = "Code_Convention")
    private Integer codeConvention;

    @Column(name = "Code_List_Couverture")
    private Integer codeListCouverture;

    @Column(name = "Code_Cabinet")
    private Integer codeCabinet;

    @OneToMany(mappedBy = "codeAdmission", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("lisAdmission") // Unique name
    private List<DetailsAdmission> detailsAdmissions;
    
        @OneToMany(mappedBy = "codeAdmission", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listAdmission") // Unique name
    private List<Reglement> detailsReglements;
        
              @OneToMany(mappedBy = "codeAdmission", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listAdmission") // Unique name
    private List<AdmissionFacturation> admissionFacturations;  

    @Column(name = "Etat_Paiement", nullable = false)
    private boolean etatPaiement;
    
    
    
    @Column(name = "Reglement_Deferral", nullable = false)
    private boolean regDeferral;
    
    

    public Admission() {
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

    public MotifAdmission getMotifAdmission() {
        return motifAdmission;
    }

    public void setMotifAdmission(MotifAdmission motifAdmission) {
        this.motifAdmission = motifAdmission;
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

    public List<DetailsAdmission> getDetailsAdmissions() {
        return detailsAdmissions;
    }

    public void setDetailsAdmissions(List<DetailsAdmission> detailsAdmissions) {
        this.detailsAdmissions = detailsAdmissions;
    }

    public boolean isEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(boolean etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public Integer getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(Integer codePriceList) {
        this.codePriceList = codePriceList;
    }

    public List<Reglement> getDetailsReglements() {
        return detailsReglements;
    }

    public void setDetailsReglements(List<Reglement> detailsReglements) {
        this.detailsReglements = detailsReglements;
    }

    public boolean isRegDeferral() {
        return regDeferral;
    }

    public void setRegDeferral(boolean regDeferral) {
        this.regDeferral = regDeferral;
    }

    public List<AdmissionFacturation> getAdmissionFacturations() {
        return admissionFacturations;
    }

    public void setAdmissionFacturations(List<AdmissionFacturation> admissionFacturations) {
        this.admissionFacturations = admissionFacturations;
    }
    
    
    

}
