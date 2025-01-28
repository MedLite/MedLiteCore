/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperationPK;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListPK;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.paramDTO;
import com.FrameWork.MedLite.Parametrage.enumeration.EnumTypeUpdatePrice;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.OperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.FrameWork.MedLite.Parametrage.repository.PriceListRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.FrameWork.MedLite.web.errors.IllegalBusinessLogiqueException;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class PriceListService {

    private final Logger log = LoggerFactory.getLogger(PriceListService.class);

//    @Autowired
//    private EntityManager entityManager; // Inject the EntityManager
    private final PriceListRepo priceListRepo; 
    private final DetailsPriceListRepo detailsPriceListRepo;   
    private final DetailsPriceListOperationRepo detailsPriceListOperationRepo;

    private final DetailsPrestationRepo detailsPrestationRepo;  
    private final DetailsOperationRepo detailsOperationRepo;


    private final CompteurService compteurService;
    private final DetailsPriceListService detailsPriceListService;  
    private final DetailsPriceListOperationService detailsPriceListOperationService;


    private final NatureAdmissionService natureAdmissionService;

    private final DetailsPrestationService detailsPrestationService;

    private final ParamService paramService;

    private static final List<String> VALID_VALEURS = Arrays.stream(EnumTypeUpdatePrice.values())
            .map(EnumTypeUpdatePrice::name)
            .collect(Collectors.toList());

    public PriceListService(PriceListRepo priceListRepo, DetailsPriceListRepo detailsPriceListRepo, DetailsPriceListOperationRepo detailsPriceListOperationRepo, DetailsPrestationRepo detailsPrestationRepo, DetailsOperationRepo detailsOperationRepo, CompteurService compteurService, DetailsPriceListService detailsPriceListService, DetailsPriceListOperationService detailsPriceListOperationService, NatureAdmissionService natureAdmissionService, DetailsPrestationService detailsPrestationService, ParamService paramService) {
        this.priceListRepo = priceListRepo;
        this.detailsPriceListRepo = detailsPriceListRepo;
        this.detailsPriceListOperationRepo = detailsPriceListOperationRepo;
        this.detailsPrestationRepo = detailsPrestationRepo;
        this.detailsOperationRepo = detailsOperationRepo;
        this.compteurService = compteurService;
        this.detailsPriceListService = detailsPriceListService;
        this.detailsPriceListOperationService = detailsPriceListOperationService;
        this.natureAdmissionService = natureAdmissionService;
        this.detailsPrestationService = detailsPrestationService;
        this.paramService = paramService;
    }

    

    @Transactional(readOnly = true)
    public List<PriceListDTO> findAllPriceList() {
        return PriceListFactory.listPriceListToPriceListDTOs(priceListRepo.findAll());

    }

    @Transactional(readOnly = true)
    public List<PriceListDTO> findAllPriceListByActif(Boolean actif) {
        return PriceListFactory.listPriceListToPriceListDTOs(priceListRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public PriceListDTO findOne(Integer code) {
        PriceList domaine = priceListRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PriceListNotFound");

        return PriceListFactory.priceListToPriceListDTO(domaine);
    }

    @Transactional
    public PriceList findPriceListByCode(Integer code) {
        System.out.println("code    " + code);
        PriceList domaine = priceListRepo.findByCode(code);

        if (domaine == null) {
            log.error("PriceList not found for code: {}", code); //Use slf4j or your logging framework
            throw new IllegalBusinessLogiqueException("error.PriceListNotFound");
        }
        return domaine;
    }

//    @Transactional(readOnly = true)
//    public PriceListDTO findIsCash(Boolean isCash) {
//        PriceList domaine = priceListRepo.findByCash(isCash);
//        Preconditions.checkArgument(domaine.getCode() != null, "error.PriceListNotFound");
//        return PriceListFactory.priceListToPriceListDTO(domaine);
//    }
    public PriceListDTO save(PriceListDTO dto) {
        PriceList domaine = PriceListFactory.priceListDTOToPriceList(dto, new PriceList());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisiePL");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = priceListRepo.save(domaine);
        return PriceListFactory.priceListToPriceListDTO(domaine);
    }

    public PriceListDTO savepricelist(PriceListDTO dto) {
        PriceList domaine = PriceListFactory.priceListDTOToPriceList(dto, new PriceList());
        Compteur compteurCodeSaisie = compteurService.findOne("CodeSaisiePL");
        String codeSaisieAC = compteurCodeSaisie.getPrefixe() + compteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(compteurCodeSaisie);
        domaine = priceListRepo.save(domaine);
        paramDTO paramPriceList = paramService.findParamByCodeParamS("PriceListCalcule");
        String valeur = paramPriceList.getValeur();
        if (!VALID_VALEURS.contains(valeur)) {
            throw new IllegalArgumentException("error.ErrorParamPriceList");
        }
        com.FrameWork.MedLite.web.Util.Preconditions.checkBusinessLogique(paramPriceList != null, "error.ParamNotFound");
        paramDTO codeTypeIntervenantClinic = paramService.findParamByCodeParamS("CodeTypeIntervCinic");
        
        //// detila PriceListPrestation
        List<DetailsPriceListDTO> detailsPriceListsListDTOs = dto.getDetailsPriceLists();
        Map<DetailsPriceListPK, DetailsPriceList> existingDetails = new HashMap<>(); // Track existing entries
        List<DetailsPriceList> detailsPriceListsToSave = new ArrayList<>();
        for (DetailsPriceListDTO dtoDetails : detailsPriceListsListDTOs) {
            List<DetailsPrestation> detailsPrestations = detailsPrestationRepo.findByDetailsPrestationPK_CodePrestation(dtoDetails.getCodePrestation());
            for (DetailsPrestation dp : detailsPrestations) {
                if (!matchesCriteriaPrestation(dtoDetails, dp)) {
                    continue;
                }
                DetailsPriceListPK pk = new DetailsPriceListPK(domaine.getCode(), dp.getPrestation().getCode(), dp.getCodeNatureAdmission(), dp.getCodeTypeIntervenant());
                if (!existingDetails.containsKey(pk)) {
                    DetailsPriceList newDetailsPriceListEntry = new DetailsPriceList();
                    newDetailsPriceListEntry.setDateCreate(new Date());
                    newDetailsPriceListEntry.setUsercreate(Helper.getUserAuthenticated());
                    newDetailsPriceListEntry.setPriceList(domaine);
                    newDetailsPriceListEntry.setCodePrestation(dp.getPrestation().getCode());
                    newDetailsPriceListEntry.setPrestation(PrestationFactory.createPrestationByCode(dp.getPrestation().getCode())); //Simplified - assuming no null check needed here

                    newDetailsPriceListEntry.setCodeNatureAdmission(dp.getCodeNatureAdmission());
                    newDetailsPriceListEntry.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dp.getCodeNatureAdmission())); //Simplified - assuming no null check needed here

                    newDetailsPriceListEntry.setCodeTypeIntervenant(dp.getCodeTypeIntervenant());
                    newDetailsPriceListEntry.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(dp.getCodeTypeIntervenant()));//Simplified - assuming no null check needed here

                    newDetailsPriceListEntry.setMontantPere(dtoDetails.getMontantPere());
                    newDetailsPriceListEntry.setRemMaj(dtoDetails.getRemMaj());
                    BigDecimal montantClinic = dtoDetails.getMontant(); // Start with the DTO's montant
                    if (paramPriceList.getValeur().equals(EnumTypeUpdatePrice.MAJCLINI.toString())) {
                        if (dp.getCodeTypeIntervenant() != null) {
                            if (codeTypeIntervenantClinic.getValeur().equals(dp.getCodeTypeIntervenant().toString())) { 
                                if (dp.getMontant() != null && dtoDetails.getMontant() != null) { 
                                    BigDecimal v1 = dp.getMontant();
                                    BigDecimal v2 = dtoDetails.getMontantPere();
                                    BigDecimal v3 = v2.subtract(v1);
                                    BigDecimal montantClinicx = dtoDetails.getMontant().subtract(v3);
                                    newDetailsPriceListEntry.setMontant(montantClinicx);
                                } else {
                                    System.err.println("Warning: Null value encountered during montant calculation for pk: " + pk + " CodeTypeIntervenant: 1");
                                     montantClinic = BigDecimal.ZERO; // Or another appropriate default
                                }
                            } else {
                                 if (dp.getMontant() != null) {
                                    montantClinic = dp.getMontant();
                                    newDetailsPriceListEntry.setMontant(montantClinic);
                                } else {
                                    System.err.println("Warning: Null value encountered for dp.getMontant for pk: " + pk + ". Using original montant.");
                                }
                            }
                        } else {
                            System.err.println("Warning: Null value encountered for dp.getCodeTypeIntervenant for pk: " + pk);
                        }
                    } else if (paramPriceList.getValeur().equals(EnumTypeUpdatePrice.REMCONV.toString())) {
                        BigDecimal MontantTotalPrestation = dtoDetails.getMontantPere(); // 25
                        BigDecimal MontantHonoraireClinic = dp.getMontant(); // 13
                        BigDecimal percentage = calculatePercentage(MontantHonoraireClinic, MontantTotalPrestation);
                        BigDecimal valeurPourcentage = (dtoDetails.getMontant()).multiply(percentage); 
                        newDetailsPriceListEntry.setMontant(valeurPourcentage);
                    }
                    newDetailsPriceListEntry.setMontantPere(dp.getMontant());
                    newDetailsPriceListEntry.setRemMaj(dtoDetails.getRemMaj());
                    detailsPriceListsToSave.add(newDetailsPriceListEntry);
                    existingDetails.put(pk, newDetailsPriceListEntry);
                } else { 
                    System.err.println("Duplicate entry detected (already exists): " + pk);
                }
            }
        } 
        detailsPriceListRepo.saveAll(detailsPriceListsToSave); // Save all at once for efficiency
        
        
        
        //// detailsPriceListOperation
        List<DetailsPriceListOperationDTO> detailsPriceListOperationDTOs = dto.getDetailsPriceListOperationDTOs();
        Map<DetailsPriceListOperationPK, DetailsPriceListOperation> existingDetailsOperation = new HashMap<>(); // Track existing entries
        List<DetailsPriceListOperation> detailsPriceListsOperationToSave = new ArrayList<>();
        for (DetailsPriceListOperationDTO dtoDetailsPriceListOperationDTO : detailsPriceListOperationDTOs) {
            List<DetailsOperation> detailsOperations = detailsOperationRepo.findByDetailsOperationPK_CodeOperation(dtoDetailsPriceListOperationDTO.getCodeOperation());
            for (DetailsOperation dp : detailsOperations) {
                if (!matchesCriteriaOperation(dtoDetailsPriceListOperationDTO, dp)) {
                    continue;
                }
                DetailsPriceListOperationPK pk = new DetailsPriceListOperationPK(domaine.getCode(), dp.getOperation().getCode(), dp.getCodeTypeIntervenant());
                if (!existingDetailsOperation.containsKey(pk)) {
                    DetailsPriceListOperation newDetailsPriceListEntryOperation = new DetailsPriceListOperation();
                    newDetailsPriceListEntryOperation.setDateCreate(new Date());
                    newDetailsPriceListEntryOperation.setUsercreate(Helper.getUserAuthenticated());
                    newDetailsPriceListEntryOperation.setPriceList(domaine);
                    newDetailsPriceListEntryOperation.setCodeOperation(dp.getOperation().getCode());
                    newDetailsPriceListEntryOperation.setOperation(OperationFactory.createOperationByCode(dp.getOperation().getCode())); //Simplified - assuming no null check needed here

                
                    newDetailsPriceListEntryOperation.setCodeTypeIntervenant(dp.getCodeTypeIntervenant());
                    newDetailsPriceListEntryOperation.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(dp.getCodeTypeIntervenant()));//Simplified - assuming no null check needed here

                    newDetailsPriceListEntryOperation.setMontantPere(dtoDetailsPriceListOperationDTO.getMontantPere());
                    newDetailsPriceListEntryOperation.setRemMaj(dtoDetailsPriceListOperationDTO.getRemMaj());
                    BigDecimal montantClinic = dtoDetailsPriceListOperationDTO.getMontant(); // Start with the DTO's montant
                    if (paramPriceList.getValeur().equals(EnumTypeUpdatePrice.MAJCLINI.toString())) {
                        if (dp.getCodeTypeIntervenant() != null) {
                            if (codeTypeIntervenantClinic.getValeur().equals(dp.getCodeTypeIntervenant().toString())) { 
                                if (dp.getMontant() != null && dtoDetailsPriceListOperationDTO.getMontant() != null) { 
                                    BigDecimal v1 = dp.getMontant();
                                    BigDecimal v2 = dtoDetailsPriceListOperationDTO.getMontantPere();
                                    BigDecimal v3 = v2.subtract(v1);
                                    BigDecimal montantClinicx = dtoDetailsPriceListOperationDTO.getMontant().subtract(v3);
                                    newDetailsPriceListEntryOperation.setMontant(montantClinicx);
                                } else {
                                    System.err.println("Warning: Null value encountered during montant calculation for pk: " + pk + " CodeTypeIntervenant: 1");
                                     montantClinic = BigDecimal.ZERO; // Or another appropriate default
                                }
                            } else {
                                 if (dp.getMontant() != null) {
                                    montantClinic = dp.getMontant();
                                    newDetailsPriceListEntryOperation.setMontant(montantClinic);
                                } else {
                                    System.err.println("Warning: Null value encountered for dp.getMontant for pk: " + pk + ". Using original montant.");
                                }
                            }
                        } else {
                            System.err.println("Warning: Null value encountered for dp.getCodeTypeIntervenant for pk: " + pk);
                        }
                    } else if (paramPriceList.getValeur().equals(EnumTypeUpdatePrice.REMCONV.toString())) {
                        BigDecimal MontantTotalPrestation = dtoDetailsPriceListOperationDTO.getMontantPere(); // 25
                        BigDecimal MontantHonoraireClinic = dp.getMontant(); // 13
                        BigDecimal percentage = calculatePercentage(MontantHonoraireClinic, MontantTotalPrestation);
                        BigDecimal valeurPourcentage = (dtoDetailsPriceListOperationDTO.getMontant()).multiply(percentage); 
                        newDetailsPriceListEntryOperation.setMontant(valeurPourcentage);
                    }
                    newDetailsPriceListEntryOperation.setMontantPere(dp.getMontant());
                    newDetailsPriceListEntryOperation.setRemMaj(dtoDetailsPriceListOperationDTO.getRemMaj());
                    detailsPriceListsOperationToSave.add(newDetailsPriceListEntryOperation);
                    existingDetailsOperation.put(pk, newDetailsPriceListEntryOperation);
                } else { 
                    System.err.println("Duplicate entry detected (already exists): " + pk);
                }
            }
        } 
        detailsPriceListOperationRepo.saveAll(detailsPriceListsOperationToSave); // Save all at once for efficiency
        
        
        return PriceListFactory.priceListToPriceListDTO(domaine);
    }

    private boolean matchesCriteriaPrestation(DetailsPriceListDTO dtoDetails, DetailsPrestation dp) {
        return (dtoDetails.getCodeNatureAdmission() == null || dtoDetails.getCodeNatureAdmission().equals(dp.getCodeNatureAdmission()))
                && (dtoDetails.getCodeTypeIntervenant() == null || dtoDetails.getCodeTypeIntervenant().equals(dp.getCodeTypeIntervenant()));
    }
    
    
    private boolean matchesCriteriaOperation(DetailsPriceListOperationDTO dtoDetails, DetailsOperation dp) {
        return (dtoDetails.getCodeTypeIntervenant() == null || dtoDetails.getCodeTypeIntervenant().equals(dp.getCodeTypeIntervenant()));
    }

    public static BigDecimal calculatePercentage(BigDecimal v1, BigDecimal v2) {
        if (v2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // return v1.divide(v2, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));   
        return v1.divide(v2, 2, RoundingMode.HALF_UP);

    }

    public PriceListDTO updateNew(PriceListDTO dto) {
        PriceList inBase = priceListRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.PriceListNotFound");
        inBase = PriceListFactory.priceListDTOToPriceList(dto, inBase);
        inBase = priceListRepo.save(inBase);
        detailsPriceListRepo.deleteByCodePriceList(inBase.getCode());

        paramDTO paramPriceList = paramService.findParamByCodeParamS("PriceListCalcule");
        String valeur = paramPriceList.getValeur();
        if (!VALID_VALEURS.contains(valeur)) {
            throw new IllegalArgumentException("error.ErrorParamPriceList");
        }
        com.FrameWork.MedLite.web.Util.Preconditions.checkBusinessLogique(paramPriceList != null, "error.ParamNotFound");
        paramDTO codeTypeIntervenantClinic = paramService.findParamByCodeParamS("CodeTypeIntervCinic");
        List<DetailsPriceListDTO> detailsPriceListsListDTOs = dto.getDetailsPriceLists();
        Map<DetailsPriceListPK, DetailsPriceList> existingDetails = new HashMap<>(); // Track existing entries

        List<DetailsPriceList> detailsPriceListsToSave = new ArrayList<>();
        for (DetailsPriceListDTO dtoDetails : detailsPriceListsListDTOs) {
            List<DetailsPrestation> detailsPrestations = detailsPrestationRepo.findByDetailsPrestationPK_CodePrestation(dtoDetails.getCodePrestation());

            for (DetailsPrestation dp : detailsPrestations) {
                if (!matchesCriteriaPrestation(dtoDetails, dp)) {
                    continue;
                }
                DetailsPriceListPK pk = new DetailsPriceListPK(inBase.getCode(), dp.getPrestation().getCode(), dp.getCodeNatureAdmission(), dp.getCodeTypeIntervenant());
                if (!existingDetails.containsKey(pk)) {
                    DetailsPriceList newDetailsPriceListEntry = new DetailsPriceList();
                    newDetailsPriceListEntry.setDateCreate(new Date());
                    newDetailsPriceListEntry.setUsercreate(Helper.getUserAuthenticated());
                    newDetailsPriceListEntry.setPriceList(inBase);
                    newDetailsPriceListEntry.setCodePrestation(dp.getPrestation().getCode());
                    newDetailsPriceListEntry.setPrestation(PrestationFactory.createPrestationByCode(dp.getPrestation().getCode())); //Simplified - assuming no null check needed here

                    newDetailsPriceListEntry.setCodeNatureAdmission(dp.getCodeNatureAdmission());
                    newDetailsPriceListEntry.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dp.getCodeNatureAdmission())); //Simplified - assuming no null check needed here

                    newDetailsPriceListEntry.setCodeTypeIntervenant(dp.getCodeTypeIntervenant());
                    newDetailsPriceListEntry.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(dp.getCodeTypeIntervenant()));//Simplified - assuming no null check needed here

                    newDetailsPriceListEntry.setMontantPere(dtoDetails.getMontantPere());
                    newDetailsPriceListEntry.setRemMaj(dtoDetails.getRemMaj());

                    if (paramPriceList.getValeur().equals(EnumTypeUpdatePrice.MAJCLINI.toString())) {
                        if (dp.getCodeTypeIntervenant() != null) {
                            if (codeTypeIntervenantClinic.getValeur().equals(dp.getCodeTypeIntervenant().toString())) {

                                if (dp.getMontant() != null && dtoDetails.getMontant() != null) {

                                    BigDecimal v1 = dp.getMontant();
                                    BigDecimal v2 = dtoDetails.getMontantPere();
                                    BigDecimal v3 = v2.subtract(v1);
                                    BigDecimal montantClinicx = dtoDetails.getMontant().subtract(v3);
                                    newDetailsPriceListEntry.setMontant(montantClinicx);
                                } else {
                                    System.err.println("Warning: Null value encountered during montant calculation for pk: " + pk + " CodeTypeIntervenant: 1");
                                    // Handle nulls -  Set a default value or throw an exception.  e.g.,

                                }
                            } else {
                                // For codeTypeIntervenant != 1, use dp.getMontant if available, otherwise keep the original.
                                if (dp.getMontant() != null) {
                                    newDetailsPriceListEntry.setMontant(dtoDetails.getMontant());
                                } else {
                                    System.err.println("Warning: Null value encountered for dp.getMontant for pk: " + pk + ". Using original montant.");

                                }
                            }
                        } else {
                            System.err.println("Warning: Null value encountered for dp.getCodeTypeIntervenant for pk: " + pk);

                        }
                    } else if (paramPriceList.getValeur().equals(EnumTypeUpdatePrice.REMCONV.toString())) {

                        BigDecimal MontantTotalPrestation = dtoDetails.getMontantPere(); // 25
                        BigDecimal MontantHonoraireClinic = dp.getMontant(); // 13
                        BigDecimal percentage = calculatePercentage(MontantHonoraireClinic, MontantTotalPrestation);
                        BigDecimal valeurPourcentage = (dtoDetails.getMontant()).multiply(percentage);
//                        BigDecimal valeurParTypeIntervenant = dp.getMontant().add(valeurPourcentage);
                        newDetailsPriceListEntry.setMontant(valeurPourcentage);

                    }

                    newDetailsPriceListEntry.setMontantPere(dp.getMontant());
                    newDetailsPriceListEntry.setRemMaj(dtoDetails.getRemMaj());

                    detailsPriceListsToSave.add(newDetailsPriceListEntry);
                    existingDetails.put(pk, newDetailsPriceListEntry);
                } else {
                    // Log a warning or handle the duplicate as needed.
                    System.err.println("Duplicate entry detected (already exists): " + pk);
                }
            }
        }

        detailsPriceListRepo.saveAll(detailsPriceListsToSave);
        PriceListDTO resultDTO = PriceListFactory.priceListToPriceListDTO(inBase);
        return resultDTO;
    }

    public void deletePriceList(Integer code) {
        Preconditions.checkArgument(priceListRepo.existsById(code), "error.PriceListNotFound");
        PriceList domaine = priceListRepo.findByCode(code);
        Preconditions.checkArgument(domaine.isCash() != Boolean.TRUE, "error.PriceListIsCashWeDontAccessToDelete");
        priceListRepo.deleteById(code);
    }

//    @Transactional(readOnly = true)
//    public PriceList findByPriceListCash() {
//        log.debug("Request to get PriceList mere : {}");
////        List<PriceList> listPriceList = priceListRepository.findByActifAndPriceListPereCodeIsNull(true);
//        PriceList listPriceList = priceListRepo.findByCash(Boolean.TRUE);
//        Preconditions.checkArgument(listPriceList != null, "error.PriceListInexistant");
//        return listPriceList;
//    }
}
