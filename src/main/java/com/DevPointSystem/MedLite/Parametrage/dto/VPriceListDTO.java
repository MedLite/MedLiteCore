/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import com.DevPointSystem.MedLite.Parametrage.domaine.Prestation;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class VPriceListDTO {

    private BigDecimal montant;

    private int codePriceList;
//    private int codePrestation;

    @NotNull
    private Prestation prestationDTO;
    private String codeSaisiePrestation;
    private Integer codePrestation;
    private String designationArPrestation;
    private String designationLtPrestation;

    @NotNull
    private NatureAdmission natureAdmissionDTO;
    private String codeSaisieNatureAdmission;
    private Integer codeNatureAdmission;
    private String designationArNatureAdmission;
    private String designationLtNatureAdmission;

    public VPriceListDTO() {
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public int getCodePriceList() {
        return codePriceList;
    }

    public void setCodePriceList(int codePriceList) {
        this.codePriceList = codePriceList;
    }

    public Prestation getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(Prestation prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    public String getCodeSaisiePrestation() {
        return codeSaisiePrestation;
    }

    public void setCodeSaisiePrestation(String codeSaisiePrestation) {
        this.codeSaisiePrestation = codeSaisiePrestation;
    }

    public Integer getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(Integer codePrestation) {
        this.codePrestation = codePrestation;
    }

    
    public NatureAdmission getNatureAdmissionDTO() {
        return natureAdmissionDTO;
    }

    public void setNatureAdmissionDTO(NatureAdmission natureAdmissionDTO) {
        this.natureAdmissionDTO = natureAdmissionDTO;
    }

    public String getCodeSaisieNatureAdmission() {
        return codeSaisieNatureAdmission;
    }

    public void setCodeSaisieNatureAdmission(String codeSaisieNatureAdmission) {
        this.codeSaisieNatureAdmission = codeSaisieNatureAdmission;
    }

    public Integer getCodeNatureAdmission() {
        return codeNatureAdmission;
    }

    public void setCodeNatureAdmission(Integer codeNatureAdmission) {
        this.codeNatureAdmission = codeNatureAdmission;
    }

    public String getDesignationArNatureAdmission() {
        return designationArNatureAdmission;
    }

    public void setDesignationArNatureAdmission(String designationArNatureAdmission) {
        this.designationArNatureAdmission = designationArNatureAdmission;
    }

    public String getDesignationLtNatureAdmission() {
        return designationLtNatureAdmission;
    }

    public void setDesignationLtNatureAdmission(String designationLtNatureAdmission) {
        this.designationLtNatureAdmission = designationLtNatureAdmission;
    }

    public String getDesignationArPrestation() {
        return designationArPrestation;
    }

    public void setDesignationArPrestation(String designationArPrestation) {
        this.designationArPrestation = designationArPrestation;
    }

    public String getDesignationLtPrestation() {
        return designationLtPrestation;
    }

    public void setDesignationLtPrestation(String designationLtPrestation) {
        this.designationLtPrestation = designationLtPrestation;
    }

}
