/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import com.FrameWork.MedLite.Authentification.domaine.User;
import com.FrameWork.MedLite.Parametrage.domaine.ModeReglement;
import com.FrameWork.MedLite.Parametrage.domaine.NatureAdmission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Reglement", schema = "caisse")
@Audited
@AuditTable("Reglement_AUD")
public class Reglement {

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

    @Column(name = "Caissier", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String caissier;

 
    @Column(name = "Code_Admission", nullable = false, columnDefinition = "nvarchar(11)")
    private String codeAdmission;

    @Column(name = "Motant_Reglement", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantReglement;

    @Column(name = "Montant_Bon", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantBon;

    @JoinColumn(name = "Code_Nature_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private NatureAdmission natureAdmission;

    @Column(name = "Code_Nature_Admission", updatable = false, insertable = false, nullable = false)
    private Integer codeNatureAdmission;

    @JoinColumn(name = "Code_Mode_Reglemnt", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "Code_Mode_Reglemnt", updatable = false, insertable = false, nullable = false)
    private Integer codeModeReglement;

    @Column(name = "Type_Reglement", nullable = false, length = 200, columnDefinition = "nvarchar(200)")
    private String typeReglement;

    @Column(name = "Code_Session",  length = 200, columnDefinition = "nvarchar(200)")
    private String codeSession;

    @Column(name = "Code_Banque")
    private Integer codeBanque;
    
      @Column(name = "Num_Piece", nullable = false, length = 200, columnDefinition = "nvarchar(30)")
    private String numPiece;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Reglement", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateReglement;

    @Column(name = "Montant_PEC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPEC;

    public Reglement() {
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

    public String getCaissier() {
        return caissier;
    }

    public void setCaissier(String caissier) {
        this.caissier = caissier;
    }

    public String getCodeAdmission() {
        return codeAdmission;
    }

    public void setCodeAdmission(String codeAdmission) {
        this.codeAdmission = codeAdmission;
    }

    public BigDecimal getMontantReglement() {
        return montantReglement;
    }

    public void setMontantReglement(BigDecimal montantReglement) {
        this.montantReglement = montantReglement;
    }

    public BigDecimal getMontantBon() {
        return montantBon;
    }

    public void setMontantBon(BigDecimal montantBon) {
        this.montantBon = montantBon;
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

    public String getTypeReglement() {
        return typeReglement;
    }

    public void setTypeReglement(String typeReglement) {
        this.typeReglement = typeReglement;
    }

    public String getCodeSession() {
        return codeSession;
    }

    public void setCodeSession(String codeSession) {
        this.codeSession = codeSession;
    }

    public LocalDate getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(LocalDate dateReglement) {
        this.dateReglement = dateReglement;
    }

    public BigDecimal getMontantPEC() {
        return montantPEC;
    }

    public void setMontantPEC(BigDecimal montantPEC) {
        this.montantPEC = montantPEC;
    }

    public ModeReglement getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(ModeReglement modeReglement) {
        this.modeReglement = modeReglement;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

    public Integer getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    

    
}
