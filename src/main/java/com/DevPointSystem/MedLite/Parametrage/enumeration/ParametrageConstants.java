/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.enumeration;

/**
 *
 * @author Administrator
 */
public enum ParametrageConstants {
    CODE_SOUS_FAMILLE_AJUSTEMENT("codeSousFamPrestAjust"),
    CODE_SOUS_FAMILLE_MEDICALE("codeSousFamPrestMedicale"),
    CODE_FAMILLE_FACTURATION_MEDICALE("codeFamilleFacturationMedicale"),
    CODE_TVA_ZERO("codeTVAZero"),
    POURCENTAGE_MEDICALE("pourcentageMedicale"),
    CODE_TYPE_ARRIVE_CLINIQUE("1"),
    CODE_PRESTATION_DENTAIRE("codePrestationDentaire"),
    CODE_TYPE_INTERVENANT_DELIVERY("codeTypeIntervDelivery"),
    CODE_FAMILLE_PRESTATION_DELIVERY("codeFamillePrestationDelivery"),
    
    CODE_TYPE_PRESTATION_CONSULTATION("typePrestConsultation"),
    CODE_TYPE_PRESTATION_SERVICE("typePrestationService"),
    CODE_TYPE_PRESTATION_HEART("typePrestationHeart"),
    CODE_TYPE_PRESTATION_LABO("typePrestationLabo"),
    CODE_TYPE_PRESTATION_RADIO("typePrestationRadio"),
    DIFF_PRIX_NATURE_ADMISSION("diffPrixNatureAdm"),
    PRIX_CATEGORIE_VARIE("prixCategorieVarie"),
    CODE_BLOC_ENDOSCOPY("codeBlocEndoscopy"),
    CODE_CLINIQUE("1"),
    MODE_REGLEMENT_CAISSE_DEPENSE("modeReglementCaisseDepense"),
    MODE_REGLEMENT_CASH("modeReglementEspece"),
    MODE_REGLEMENT_VISA("modeReglementVISA"),
    MODE_REGLEMENT_Wallet("modeReglementWallet"),
    COMPTEUR_MPL("compteurMpl"),
    HOSPITAL("Hospital"),
    NATURE_INTERVENANT_HONORAIRE("natureIntervHonoraire"),
    CODE_NATURE_ADMISSION_INPATIENT("codeNatureInPatient"),
    CODE_NATURE_ADMISSION_ER("codeNatureER"),
    CODE_NATURE_ADMISSION_OPD("codeNatureOPD"),
    TYPE_FORMULE("F"),
    IS_CASH_WITH_CO_PAIEMENT_ZERO("isCashWithCoPaiementZero"),
    LIEN_PARENTE_HIM_SELF("codeLienParenteHimSelf"),
    CODE_SAISIE_TAXE("codeSaisieTaxe"),
    CODES_MEDECIN_INTERNE("codesMedecinInterne"),
    ROUND_PRICE("roundPrice"),
    UPDATE_PRICE("updatePrice"),
    VALUE_APPLIQUE_RETENUE("appliqueRetenue"),
    APPLIQUE_MAJORATION_CLINIQUE("appliqueMajorationClinique"),
    EXCEPTION_CATEGORIE_OPERATION("excepCategorieOperation"),
    LC_SOUS_CATEGRIE_ARTICLE("LcParSousCategorieArticle"),
    NEW_DICTIONARY("newDictionary"),
    CODE_FAMILLE_ENDO("162"),

    CODE_TYPE_PRESTATION_OPERATION("60"),
    MODE_REGLEMENT_EMPLOYEE_ADVANCED("modeReglementEmployeeAdvances");
    private String name = "";

    //Constructeur
    ParametrageConstants(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
