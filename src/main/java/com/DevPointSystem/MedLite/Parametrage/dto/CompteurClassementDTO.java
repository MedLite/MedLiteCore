/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.dto;

/**
 *
 * @author Administrator
 */
public class CompteurClassementDTO {

    private Integer code;

    private String prefixe;

    private Integer suffixe;

    private String compteur;
    
        private Integer niveau;

    public CompteurClassementDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

    public Integer getSuffixe() {
        return suffixe;
    }

    public void setSuffixe(Integer suffixe) {
        this.suffixe = suffixe;
    }

    public String getCompteur() {
        return compteur;
    }

    public void setCompteur(String compteur) {
        this.compteur = compteur;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }
    

}