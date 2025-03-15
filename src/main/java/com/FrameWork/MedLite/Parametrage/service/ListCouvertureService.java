/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.Convention;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouvertureOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverturePK;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.ListCouverture;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.ListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.paramDTO;
import com.FrameWork.MedLite.Parametrage.enumeration.EnumTypeUpdatePrice;
import com.FrameWork.MedLite.Parametrage.factory.ListCouvertureFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.OperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.ConventionRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsListCouvertureOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsListCouvertureRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.FrameWork.MedLite.Parametrage.repository.ListCouvertureRepo;
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
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ListCouvertureService {

    private final Logger log = LoggerFactory.getLogger(ListCouvertureService.class);

    private final ListCouvertureRepo listCouvertureRepo;
    private final DetailsListCouvertureRepo detailsListCouvertureRepo;
    private final DetailsListCouvertureOperationRepo detailsListCouvertureOperationRepo;

    private final DetailsPrestationRepo detailsPrestationRepo;
    private final DetailsOperationRepo detailsOperationRepo;
    private final CompteurService compteurService;
    private final DetailsListCouvertureService detailsListCouvertureService;
    private final DetailsListCouvertureOperationService detailsListCouvertureOperationService;

    private final ParamService paramService;
    private final DetailsPriceListRepo detailsPriceListRepo;
    private final ConventionRepo conventionRepo;

    private static final List<String> VALID_VALEURS = Arrays.stream(EnumTypeUpdatePrice.values())
            .map(EnumTypeUpdatePrice::name)
            .collect(Collectors.toList());

    public ListCouvertureService(ListCouvertureRepo listCouvertureRepo, DetailsListCouvertureRepo detailsListCouvertureRepo, DetailsListCouvertureOperationRepo detailsListCouvertureOperationRepo, DetailsPrestationRepo detailsPrestationRepo, DetailsOperationRepo detailsOperationRepo, CompteurService compteurService, DetailsListCouvertureService detailsListCouvertureService, DetailsListCouvertureOperationService detailsListCouvertureOperationService, ParamService paramService, DetailsPriceListRepo detailsPriceListRepo, ConventionRepo conventionRepo) {
        this.listCouvertureRepo = listCouvertureRepo;
        this.detailsListCouvertureRepo = detailsListCouvertureRepo;
        this.detailsListCouvertureOperationRepo = detailsListCouvertureOperationRepo;
        this.detailsPrestationRepo = detailsPrestationRepo;
        this.detailsOperationRepo = detailsOperationRepo;
        this.compteurService = compteurService;
        this.detailsListCouvertureService = detailsListCouvertureService;
        this.detailsListCouvertureOperationService = detailsListCouvertureOperationService;
        this.paramService = paramService;
        this.detailsPriceListRepo = detailsPriceListRepo;
        this.conventionRepo = conventionRepo;
    }

    @Transactional(readOnly = true)
    public List<ListCouvertureDTO> findAllListCouverture() {
        return ListCouvertureFactory.listListCouvertureToListCouvertureDTOs(listCouvertureRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<ListCouvertureDTO> findAllListCouvertureByActif(Boolean actif) {
        return ListCouvertureFactory.listListCouvertureToListCouvertureDTOs(listCouvertureRepo.findByActifOrderByCodeSaisieDesc(actif));

    }

    @Transactional(readOnly = true)
    public ListCouvertureDTO findOne(Integer code) {
        ListCouverture domaine = listCouvertureRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.ListCouvertureNotFound");

        return ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine);
    }

    @Transactional
    public ListCouverture findListCouvertureByCode(Integer code) {
        System.out.println("code    " + code);
        ListCouverture domaine = listCouvertureRepo.findByCode(code);

        if (domaine == null) {
            log.error("ListCouverture not found for code: {}", code); //Use slf4j or your logging framework
            throw new IllegalBusinessLogiqueException("error.ListCouvertureNotFound");
        }
        return domaine;
    }

    public ListCouvertureDTO SaveListCouverture(ListCouvertureDTO dto) {
        ListCouverture domaine = ListCouvertureFactory.listCouvertureDTOToListCouverture(dto, new ListCouverture());
        Compteur compteurCodeSaisie = compteurService.findOne("CodeSaisiePL");
        String codeSaisieAC = compteurCodeSaisie.getPrefixe() + compteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(compteurCodeSaisie);
        domaine = listCouvertureRepo.save(domaine);
        List<DetailsListCouvertureDTO> detailsListCouverturesListDTOs = dto.getDetailsListCouvertureDTOs();
        // Use a simpler map key that ignores codeTypeIntervenant
        Map<SimplifiedDetailsKey, DetailsListCouverture> existingDetails = new HashMap<>();
        List<DetailsListCouverture> detailsListCouverturesToSave = new ArrayList<>();

        Map<Integer, List<DetailsPrestation>> prestationsByCode = detailsPrestationRepo.findByDetailsPrestationPK_CodePrestationIn(
                detailsListCouverturesListDTOs.stream()
                        .map(DetailsListCouvertureDTO::getCodePrestation)
                        .distinct()
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(dp -> dp.getPrestation().getCode()));

        for (DetailsListCouvertureDTO dtoDetails : detailsListCouverturesListDTOs) {
            List<DetailsPrestation> detailsPrestations = prestationsByCode.get(dtoDetails.getCodePrestation());
            if (detailsPrestations == null) {
                continue; //Handle case where no matching DetailsPrestation is found
            }
            for (DetailsPrestation dp : detailsPrestations) {
                if (!matchesCriteriaPrestation(dtoDetails, dp)) {
                    continue;
                }
                // Create a simplified key ignoring codeTypeIntervenant
                SimplifiedDetailsKey pk = new SimplifiedDetailsKey(domaine.getCode(), dtoDetails.getCodePrestation(), dp.getCodeNatureAdmission());
                if (!existingDetails.containsKey(pk)) {
                    DetailsListCouverture newDetailsListCouvertureEntry = createDetailsListCouvertureEntry(domaine, dtoDetails, dp);
                    detailsListCouverturesToSave.add(newDetailsListCouvertureEntry);
                    existingDetails.put(pk, newDetailsListCouvertureEntry);
                } else {
                    System.err.println("Duplicate ListCouverture entry detected Prestation (already exists): " + pk.codePrestation);
                }
            }
        }
        detailsListCouvertureRepo.saveAll(detailsListCouverturesToSave);

        ////// detailsListCouvertureOperation
        List<DetailsListCouvertureOperationDTO> detailsListCouverturesListOperationDTOs = dto.getDetailsListCouvertureOperationDTOs();
        List<DetailsListCouvertureOperation> detailsListCouverturesToSaveOperation = new ArrayList<>();
        Map<SimplifiedDetailsKeyOperation, DetailsListCouvertureOperation> existingDetailsOperation = new HashMap<>();

        Map<Integer, List<DetailsOperation>> operationByCode = detailsOperationRepo.findByDetailsOperationPK_CodeOperationIn(
                detailsListCouverturesListOperationDTOs.stream()
                        .map(DetailsListCouvertureOperationDTO::getCodeOperation)
                        .distinct()
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(dp -> dp.getOperation().getCode()));

        for (DetailsListCouvertureOperationDTO dtoDetails : detailsListCouverturesListOperationDTOs) {
            List<DetailsOperation> detailsOperations = operationByCode.get(dtoDetails.getCodeOperation());
            if (detailsOperations == null) {
                continue; //Handle case where no matching DetailsPrestation is found
            }
            for (DetailsOperation dp : detailsOperations) {
                if (!matchesCriteriaOperation(dtoDetails, dp)) {
                    continue;
                }
                // Create a simplified key ignoring codeTypeIntervenant
                SimplifiedDetailsKeyOperation pk = new SimplifiedDetailsKeyOperation(domaine.getCode(), dtoDetails.getCodeOperation(), dp.getCodeNatureAdmission());
                if (!existingDetailsOperation.containsKey(pk)) {
                    DetailsListCouvertureOperation newDetailsListCouvertureEntryOperation = createDetailsListCouvertureEntryOperation(domaine, dtoDetails, dp);
                    detailsListCouverturesToSaveOperation.add(newDetailsListCouvertureEntryOperation);
                    existingDetailsOperation.put(pk, newDetailsListCouvertureEntryOperation);
                } else {
                    System.err.println("Duplicate ListCouverture entry detected Operation (already exists): " + pk.codeOperation);
                }
            }
        }
        detailsListCouvertureOperationRepo.saveAll(detailsListCouverturesToSaveOperation);

        return ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine);
    }

    private DetailsListCouverture createDetailsListCouvertureEntry(ListCouverture domaine, DetailsListCouvertureDTO dtoDetails, DetailsPrestation dp) {
        DetailsListCouverture newDetailsListCouvertureEntry = new DetailsListCouverture();
        newDetailsListCouvertureEntry.setDateCreate(new Date());
        newDetailsListCouvertureEntry.setUsercreate(Helper.getUserAuthenticated());
        newDetailsListCouvertureEntry.setListCouverture(domaine);
        newDetailsListCouvertureEntry.setCodePrestation(dtoDetails.getCodePrestation());
        newDetailsListCouvertureEntry.setPrestation(PrestationFactory.createPrestationByCode(dtoDetails.getCodePrestation()));
        newDetailsListCouvertureEntry.setCodeNatureAdmission(dp.getCodeNatureAdmission());
        newDetailsListCouvertureEntry.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dp.getCodeNatureAdmission()));
 
//           
        newDetailsListCouvertureEntry.setMontantPere(dtoDetails.getMontantPere());
        newDetailsListCouvertureEntry.setTauxCouverPec(dtoDetails.getTauxCouverPec());
        newDetailsListCouvertureEntry.setMontantPatient(dtoDetails.getMontantPatient());
        newDetailsListCouvertureEntry.setMontantPEC(dtoDetails.getMontantPEC());
        return newDetailsListCouvertureEntry;
    }

    private DetailsListCouvertureOperation createDetailsListCouvertureEntryOperation(ListCouverture domaine, DetailsListCouvertureOperationDTO dtoDetails, DetailsOperation dp) {
        DetailsListCouvertureOperation newDetailsListCouvertureEntry = new DetailsListCouvertureOperation();
        newDetailsListCouvertureEntry.setDateCreate(new Date());
        newDetailsListCouvertureEntry.setUsercreate(Helper.getUserAuthenticated());
        newDetailsListCouvertureEntry.setListCouverture(domaine);
        newDetailsListCouvertureEntry.setCodeOperation(dtoDetails.getCodeOperation());
        newDetailsListCouvertureEntry.setOperation(OperationFactory.createOperationByCode(dtoDetails.getCodeOperation()));
        newDetailsListCouvertureEntry.setCodeNatureAdmission(dp.getCodeNatureAdmission());
        newDetailsListCouvertureEntry.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(dp.getCodeNatureAdmission()));
        newDetailsListCouvertureEntry.setMontantPere(dtoDetails.getMontantPere());
        newDetailsListCouvertureEntry.setTauxCouverPec(dtoDetails.getTauxCouverPec());
        newDetailsListCouvertureEntry.setMontantPatient(dtoDetails.getMontantPatient());
        newDetailsListCouvertureEntry.setMontantPEC(dtoDetails.getMontantPEC());
        return newDetailsListCouvertureEntry;
    }

    class SimplifiedDetailsKey {

        private final int listCouvertureCode;
        private final int codePrestation;
        private final int codeNatureAdmission;

        public SimplifiedDetailsKey(int listCouvertureCode, int codePrestation, int codeNatureAdmission) {
            this.listCouvertureCode = listCouvertureCode;
            this.codePrestation = codePrestation;
            this.codeNatureAdmission = codeNatureAdmission;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SimplifiedDetailsKey that = (SimplifiedDetailsKey) o;
            return listCouvertureCode == that.listCouvertureCode && codePrestation == that.codePrestation && codeNatureAdmission == that.codeNatureAdmission;
        }

        @Override
        public int hashCode() {
            return Objects.hash(listCouvertureCode, codePrestation, codeNatureAdmission);
        }
    }

    class SimplifiedDetailsKeyOperation {

        private final int listCouvertureCode;
        private final int codeOperation;
        private final int codeNatureAdmission;

        public SimplifiedDetailsKeyOperation(int listCouvertureCode, int codeOperation, int codeNatureAdmission) {
            this.listCouvertureCode = listCouvertureCode;
            this.codeOperation = codeOperation;
            this.codeNatureAdmission = codeNatureAdmission;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SimplifiedDetailsKeyOperation that = (SimplifiedDetailsKeyOperation) o;
            return listCouvertureCode == that.listCouvertureCode && codeOperation == that.codeOperation && codeNatureAdmission == that.codeNatureAdmission;
        }

        @Override
        public int hashCode() {
            return Objects.hash(listCouvertureCode, codeOperation, codeNatureAdmission);
        }
    }

    private boolean matchesCriteriaPrestation(DetailsListCouvertureDTO dtoDetails, DetailsPrestation dp) {
        return (dtoDetails.getCodeNatureAdmission() == null || dtoDetails.getCodeNatureAdmission().equals(dp.getCodeNatureAdmission()));
    }

    private boolean matchesCriteriaOperation(DetailsListCouvertureOperationDTO dtoDetails, DetailsOperation dp) {
        return (dtoDetails.getCodeNatureAdmission() == null || dtoDetails.getCodeNatureAdmission().equals(dp.getCodeNatureAdmission()));
    }

    public static BigDecimal calculatePercentage(BigDecimal v1, BigDecimal v2) {
        if (v2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // return v1.divide(v2, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));   
        return v1.divide(v2, 2, RoundingMode.HALF_UP);

    }

    public ListCouvertureDTO updateNew(ListCouvertureDTO dto) {
        ListCouverture inBase = listCouvertureRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.ListCouvertureNotFound");
        inBase = ListCouvertureFactory.listCouvertureDTOToListCouverture(dto, inBase);
        inBase = listCouvertureRepo.save(inBase);
        detailsListCouvertureOperationRepo.deleteByCodeListCouverture(inBase.getCode());
        detailsListCouvertureRepo.deleteByCodeListCouverture(inBase.getCode());

        List<DetailsListCouvertureDTO> detailsListCouverturesListDTOs = dto.getDetailsListCouvertureDTOs();
        // Use a simpler map key that ignores codeTypeIntervenant
        Map<SimplifiedDetailsKey, DetailsListCouverture> existingDetails = new HashMap<>();
        List<DetailsListCouverture> detailsListCouverturesToSave = new ArrayList<>();

        Map<Integer, List<DetailsPrestation>> prestationsByCode = detailsPrestationRepo.findByDetailsPrestationPK_CodePrestationIn(
                detailsListCouverturesListDTOs.stream()
                        .map(DetailsListCouvertureDTO::getCodePrestation)
                        .distinct()
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(dp -> dp.getPrestation().getCode()));

        for (DetailsListCouvertureDTO dtoDetails : detailsListCouverturesListDTOs) {
            List<DetailsPrestation> detailsPrestations = prestationsByCode.get(dtoDetails.getCodePrestation());
            if (detailsPrestations == null) {
                continue; //Handle case where no matching DetailsPrestation is found
            }
            for (DetailsPrestation dp : detailsPrestations) {
                if (!matchesCriteriaPrestation(dtoDetails, dp)) {
                    continue;
                }
                // Create a simplified key ignoring codeTypeIntervenant
                SimplifiedDetailsKey pk = new SimplifiedDetailsKey(inBase.getCode(), dtoDetails.getCodePrestation(), dp.getCodeNatureAdmission());
                if (!existingDetails.containsKey(pk)) {
                    DetailsListCouverture newDetailsListCouvertureEntry = createDetailsListCouvertureEntry(inBase, dtoDetails, dp);
                    detailsListCouverturesToSave.add(newDetailsListCouvertureEntry);
                    existingDetails.put(pk, newDetailsListCouvertureEntry);
                } else {
                    System.err.println("Duplicate ListCouverture entry detected Prestation (already exists): " + pk.codePrestation);
                }
            }
        }
        detailsListCouvertureRepo.saveAll(detailsListCouverturesToSave);

        ////// detailsListCouvertureOperation
        List<DetailsListCouvertureOperationDTO> detailsListCouverturesListOperationDTOs = dto.getDetailsListCouvertureOperationDTOs();
        List<DetailsListCouvertureOperation> detailsListCouverturesToSaveOperation = new ArrayList<>();
        Map<SimplifiedDetailsKeyOperation, DetailsListCouvertureOperation> existingDetailsOperation = new HashMap<>();

        Map<Integer, List<DetailsOperation>> operationByCode = detailsOperationRepo.findByDetailsOperationPK_CodeOperationIn(
                detailsListCouverturesListOperationDTOs.stream()
                        .map(DetailsListCouvertureOperationDTO::getCodeOperation)
                        .distinct()
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.groupingBy(dp -> dp.getOperation().getCode()));

        for (DetailsListCouvertureOperationDTO dtoDetails : detailsListCouverturesListOperationDTOs) {
            List<DetailsOperation> detailsOperations = operationByCode.get(dtoDetails.getCodeOperation());
            if (detailsOperations == null) {
                continue; //Handle case where no matching DetailsPrestation is found
            }
            for (DetailsOperation dp : detailsOperations) {
                if (!matchesCriteriaOperation(dtoDetails, dp)) {
                    continue;
                }
                // Create a simplified key ignoring codeTypeIntervenant
                SimplifiedDetailsKeyOperation pk = new SimplifiedDetailsKeyOperation(inBase.getCode(), dtoDetails.getCodeOperation(), dp.getCodeNatureAdmission());
                if (!existingDetailsOperation.containsKey(pk)) {
                    DetailsListCouvertureOperation newDetailsListCouvertureEntryOperation = createDetailsListCouvertureEntryOperation(inBase, dtoDetails, dp);
                    detailsListCouverturesToSaveOperation.add(newDetailsListCouvertureEntryOperation);
                    existingDetailsOperation.put(pk, newDetailsListCouvertureEntryOperation);
                } else {
                    System.err.println("Duplicate ListCouverture entry detected Operation (already exists): " + pk.codeOperation);
                }
            }
        }
        detailsListCouvertureOperationRepo.saveAll(detailsListCouverturesToSaveOperation);

        ListCouvertureDTO resultDTO = ListCouvertureFactory.listCouvertureToListCouvertureDTO(inBase);
        return resultDTO;
    }

    public void deleteListCouverture(Integer code) {
        Preconditions.checkArgument(listCouvertureRepo.existsById(code), "error.ListCouvertureNotFound"); 
        Convention domaine = conventionRepo.findByCodeListCouverture(code);
      Preconditions.checkArgument(domaine == null, "error.ListCouvertureUsedInConvention");
        detailsListCouvertureService.deleteByCodeListCouverture(code);
        detailsListCouvertureOperationService.deleteByCodeListCouverture(code);

        listCouvertureRepo.deleteById(code);
    }

}
