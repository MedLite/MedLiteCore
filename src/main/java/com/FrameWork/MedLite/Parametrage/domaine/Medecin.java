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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Medecin", schema = "param")
@Audited
@AuditTable("Medecin_AUD")
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Size(max = 200)
    @Column(name = "Nom_Intervenant_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String nomIntervAr;

    @Size(max = 200)
    @Column(name = "Nom_Intervenant_Lt", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String nomIntervLt;

    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Type_Intervenant", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeIntervenant typeIntervenant;

    @Column(name = "Code_Type_Intervenant", updatable = false, insertable = false, nullable = false)
    private Integer codeTypeIntervenant;

    @JoinColumn(name = "Code_Specialite", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private SpecialiteMedecin specialiteMedecin;

    @Column(name = "Code_Specialite", updatable = false, insertable = false, nullable = false)
    private Integer codeSpecialiteMedecin;
    @Column(name = "Autorise_Frais", nullable = false, columnDefinition = "bit default (0)")
    private boolean autoriseFrais;

    @Column(name = "Autoris_Consultation", nullable = false, columnDefinition = "bit default (0)")
    private boolean autorisConsultation;

    @Column(name = "OPD", nullable = false, columnDefinition = "bit default (0)")
    private boolean opd;

    @Column(name = "ER", nullable = false, columnDefinition = "bit default (0)")
    private boolean er;
    
     @Column(name = "Have_Sig", nullable = false, columnDefinition = "bit default (0)")
    private boolean haveSig;

    public Medecin() {
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

    public TypeIntervenant getTypeIntervenant() {
        return typeIntervenant;
    }

    public void setTypeIntervenant(TypeIntervenant typeIntervenant) {
        this.typeIntervenant = typeIntervenant;
    }

    public Integer getCodeTypeIntervenant() {
        return codeTypeIntervenant;
    }

    public void setCodeTypeIntervenant(Integer codeTypeIntervenant) {
        this.codeTypeIntervenant = codeTypeIntervenant;
    }

    public SpecialiteMedecin getSpecialiteMedecin() {
        return specialiteMedecin;
    }

    public void setSpecialiteMedecin(SpecialiteMedecin specialiteMedecin) {
        this.specialiteMedecin = specialiteMedecin;
    }

    public Integer getCodeSpecialiteMedecin() {
        return codeSpecialiteMedecin;
    }

    public void setCodeSpecialiteMedecin(Integer codeSpecialiteMedecin) {
        this.codeSpecialiteMedecin = codeSpecialiteMedecin;
    }

//    public Prestation getPrestationConsult() {
//        return prestationConsult;
//    }
//
//    public void setPrestationConsult(Prestation prestationConsult) {
//        this.prestationConsult = prestationConsult;
//    }
//    public Integer getCodePrestationConsult() {
//        return codePrestationConsult;
//    }
//
//    public void setCodePrestationConsult(Integer codePrestationConsult) {
//        this.codePrestationConsult = codePrestationConsult;
//    }
    public boolean isAutoriseFrais() {
        return autoriseFrais;
    }

    public void setAutoriseFrais(boolean autoriseFrais) {
        this.autoriseFrais = autoriseFrais;
    }

    public boolean isAutorisConsultation() {
        return autorisConsultation;
    }

    public void setAutorisConsultation(boolean autorisConsultation) {
        this.autorisConsultation = autorisConsultation;
    }

    public boolean isOpd() {
        return opd;
    }

    public void setOpd(boolean opd) {
        this.opd = opd;
    }

    public boolean isEr() {
        return er;
    }

    public void setEr(boolean er) {
        this.er = er;
    }

    public boolean isHaveSig() {
        return haveSig;
    }

    public void setHaveSig(boolean haveSig) {
        this.haveSig = haveSig;
    }

    
    
}
