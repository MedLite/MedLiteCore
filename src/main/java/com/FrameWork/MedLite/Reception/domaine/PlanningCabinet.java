/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.domaine;

import com.FrameWork.MedLite.Parametrage.domaine.Cabinet;
import com.FrameWork.MedLite.Parametrage.domaine.Medecin;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
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
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Planning_Cabinet", schema = "recept")
@Audited
@AuditTable("Planning_Cabinet_AUD")
public class PlanningCabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Existe", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateExiste;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Medecin", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Medecin medecin;

    @Column(name = "Code_Medecin", updatable = false, insertable = false, nullable = false)
    private Integer codeMedecin;

    @JoinColumn(name = "Code_Cabinet", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Cabinet cabinet;

    @Column(name = "Code_Cabinet", updatable = false, insertable = false, nullable = false)
    private Integer codeCabinet;

    @Column(name = "Actif", nullable = false, columnDefinition = "bit default(0)")
    private boolean actif;

    @Column(name = "Nbre_Place_Disponible", nullable = false)
    private Integer nbrePlaceDispo;

    @Column(name = "Nbre_Place_Utiliser", nullable = false)
    private Integer nbrePlaceUtiliser;

    public PlanningCabinet() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDate getDateExiste() {
        return dateExiste;
    }

    public void setDateExiste(LocalDate dateExiste) {
        this.dateExiste = dateExiste;
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

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public Integer getCodeCabinet() {
        return codeCabinet;
    }

    public void setCodeCabinet(Integer codeCabinet) {
        this.codeCabinet = codeCabinet;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Integer getNbrePlaceDispo() {
        return nbrePlaceDispo;
    }

    public void setNbrePlaceDispo(Integer nbrePlaceDispo) {
        this.nbrePlaceDispo = nbrePlaceDispo;
    }

    public Integer getNbrePlaceUtiliser() {
        return nbrePlaceUtiliser;
    }

    public void setNbrePlaceUtiliser(Integer nbrePlaceUtiliser) {
        this.nbrePlaceUtiliser = nbrePlaceUtiliser;
    }

    
}
