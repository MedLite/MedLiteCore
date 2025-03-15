/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Honoraire.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.FamilleFacturation;
import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Reception.domaine.Admission;
import com.FrameWork.MedLite.Reception.domaine.DetailsAdmission;
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
import java.math.BigDecimal;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Details_Honoraire_Admission", schema = "honoraire")
@Audited
@AuditTable("Details_Honoraire_Admission_AUD")
public class DetailsHonoraireAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Column(name = "Paid", nullable = false)
    private boolean paid;

    @JoinColumn(name = "Code_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Admission admission;

    @Column(name = "Code_Admission", updatable = false, insertable = false)
    private Integer codeAdmission;

    @JoinColumn(name = "Code_Medecin", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Medecin medecin;

    @Column(name = "Code_Intervenant", updatable = false, insertable = false)
    private Integer codeMedecin;

    @JoinColumn(name = "Code_Prestation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Prestation prestation;

    @Column(name = "Code_Prestation", updatable = false, insertable = false)
    private Integer codePrestation;

    @JoinColumn(name = "Code_Details_Admission", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private DetailsAdmission detailsAdmission;

    @Column(name = "Code_Details_Admission", updatable = false, insertable = false)
    private Integer codeDetailsAdmission;

    @Column(name = "PEC", nullable = false)
    private boolean pec;

    @Column(name = "Code_Bordereau", columnDefinition = ("varchar(3)"))
    private String codeBordereau;

    @Column(name = "Montant_PEC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPEC;

    @Column(name = "Montant_Patient", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantPatient;

    public DetailsHonoraireAdmission() {
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

    public DetailsAdmission getDetailsAdmission() {
        return detailsAdmission;
    }

    public void setDetailsAdmission(DetailsAdmission detailsAdmission) {
        this.detailsAdmission = detailsAdmission;
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
