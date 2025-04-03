/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.dto;

//import com.FrameWork.MedLite.Authentification.dto.AccessUserDTO;
import com.FrameWork.MedLite.Parametrage.domaine.ModeReglement;
import com.FrameWork.MedLite.Parametrage.dto.ModeReglementDTO;
import com.FrameWork.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author Administrator
 */
public class ReglementDTO {

    private Integer code;
    private String codeSaisie;

    private Integer codeBanque;

    private String numPiece;

    private String userCreate;

    private Date dateCreate;

    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateReglement;

    private String caissier;

    private String codeAdmission;

    private BigDecimal montantReglement;

    private BigDecimal montantBon;

    private NatureAdmissionDTO natureAdmissionDTO;

    private Integer codeNatureAdmission;

    private String typeReglement;

    private String codeSession;

    private PrestationDTO prestationDTO;

    private Integer codePrestation;

    private BigDecimal montantPEC;

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;

    public ReglementDTO() {
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

    public NatureAdmissionDTO getNatureAdmissionDTO() {
        return natureAdmissionDTO;
    }

    public void setNatureAdmissionDTO(NatureAdmissionDTO natureAdmissionDTO) {
        this.natureAdmissionDTO = natureAdmissionDTO;
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

    public PrestationDTO getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(PrestationDTO prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    public BigDecimal getMontantPEC() {
        return montantPEC;
    }

    public void setMontantPEC(BigDecimal montantPEC) {
        this.montantPEC = montantPEC;
    }

    public ModeReglementDTO getModeReglementDTO() {
        return modeReglementDTO;
    }

    public void setModeReglementDTO(ModeReglementDTO modeReglementDTO) {
        this.modeReglementDTO = modeReglementDTO;
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
