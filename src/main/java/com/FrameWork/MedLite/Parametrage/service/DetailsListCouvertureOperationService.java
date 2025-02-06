/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouvertureOperation;
import com.FrameWork.MedLite.Parametrage.domaine.Operation;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsListCouvertureFactory;
import com.FrameWork.MedLite.Parametrage.factory.DetailsListCouvertureOperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.OperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsListCouvertureOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsListCouvertureRepo;
import com.FrameWork.MedLite.Parametrage.repository.OperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PrestationRepo;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsListCouvertureOperationService {

    private final DetailsListCouvertureOperationRepo detailsListCouvertureOperationRepo;
   private final OperationRepo operationRepo;

    public DetailsListCouvertureOperationService(DetailsListCouvertureOperationRepo detailsListCouvertureOperationRepo, OperationRepo operationRepo) {
        this.detailsListCouvertureOperationRepo = detailsListCouvertureOperationRepo;
        this.operationRepo = operationRepo;
    }

    
 
        public void deleteByCodeListCouverture(Integer codeListCouverture) { 
        detailsListCouvertureOperationRepo.deleteByCodeListCouverture(codeListCouverture);
    }

    
//    @Transactional(readOnly = true)
//    public List<DetailsListCouvertureOperationDTO> findOneWithCodeListCouverture(Integer codeListCouverture) {
//        // Fetch existing DetailsListCouverture entries for the given codeListCouverture
//        List<DetailsListCouvertureOperation> domaine = detailsListCouvertureOperationRepo.findByCodeListCouverture(codeListCouverture);
//        Preconditions.checkArgument(domaine != null, "error.DetailsListCouvertureNotFound");
//
//        // Fetch all active prestations
//        List<Operation> operations = operationRepo.findByActifOrderByCodeSaisieDesc(true); 
//
//        // Convert existing DetailsListCouverture entries to DTOs
//        List<DetailsListCouvertureOperationDTO> existingDetailsDTOs = DetailsListCouvertureOperationFactory.createDTOs(domaine);
//        
//        // Find prestations that are not in the existing DetailsListCouverture
//        List<DetailsListCouvertureOperationDTO> missingPrestationDTOs = findMissingOperations(operations, existingDetailsDTOs, codeListCouverture); 
//        
//        List<DetailsListCouvertureOperationDTO> allDetailsDTOs = new ArrayList<>(existingDetailsDTOs);
//        allDetailsDTOs.addAll(missingPrestationDTOs);
//  
//        return allDetailsDTOs;
//    }
//
//  
//    private List<DetailsListCouvertureOperationDTO> findMissingOperations(List<Operation> opertaionListDTO, List<DetailsListCouvertureOperationDTO> existingDetailsDTOs, Integer codeListCouverture) {
//        // Extract codes of existing prestations in DetailsListCouverture
//        Set<Integer> existingOperationCodes = existingDetailsDTOs.stream()
//                .map(DetailsListCouvertureOperationDTO::getCodeOperation)
//                .collect(Collectors.toSet());
//
//        // Find prestations that are not in the existing DetailsListCouverture
//        List<DetailsListCouvertureOperationDTO> missingPrestationDTOs = new ArrayList<>();
//        for (Operation operation : opertaionListDTO) {
//            if (!existingOperationCodes.contains(operation.getCode())) {
//                // Create a new DetailsListCouvertureDTO for the missing prestation
//                DetailsListCouvertureOperationDTO newDetailsListCouvertureOperationDTO = new DetailsListCouvertureOperationDTO();
//                newDetailsListCouvertureOperationDTO.setCodeOperation(operation.getCode()); 
//                newDetailsListCouvertureOperationDTO.setOperationDTO(OperationFactory.operationToOperationDTO(operation));
//                newDetailsListCouvertureOperationDTO.setMontantPatient(operation.getCoutRevient()); // Set montant to prestation's montant    
//                newDetailsListCouvertureOperationDTO.setMontantPEC(BigDecimal.ZERO); // Set montant to prestation's montant
//
//                newDetailsListCouvertureOperationDTO.setMontantPere(operation.getCoutRevient()); // Set montantPere to prestation's montant
//                newDetailsListCouvertureOperationDTO.setCodeListCouverture(codeListCouverture);
//                newDetailsListCouvertureOperationDTO.setMntApresMaj(operation.getCoutRevient()); // Set mntApresMaj to prestation's montant
//
//                missingPrestationDTOs.add(newDetailsListCouvertureOperationDTO);
//            }
//        }
//
//        return missingPrestationDTOs;
//    }
        
        
        
         @Transactional(readOnly = true)
    public List<DetailsListCouvertureOperationDTO> findOneWithCodeListCouvertureOperation(Integer codeListCouvertureOperation) {
        List<DetailsListCouvertureOperation> domaine = detailsListCouvertureOperationRepo.findByCodeListCouverture(codeListCouvertureOperation);
        Preconditions.checkArgument(domaine != null, "error.DetailsListCouvertureOperationNotFound");
 
  // Fetch all active prestations
        List<Operation> operations = operationRepo.findByActifOrderByCodeSaisieDesc(true); 

        // Convert existing DetailsPriceList entries to DTOs
        List<DetailsListCouvertureOperationDTO> existingDetailsDTOs = DetailsListCouvertureOperationFactory.createDTOs(domaine);
        
        // Find prestations that are not in the existing DetailsPriceList
        List<DetailsListCouvertureOperationDTO> missingOperationDTOs = findMissingOperation(operations, existingDetailsDTOs, codeListCouvertureOperation); 
        
        List<DetailsListCouvertureOperationDTO> allDetailsDTOs = new ArrayList<>(existingDetailsDTOs);
        allDetailsDTOs.addAll(missingOperationDTOs);
  
        return allDetailsDTOs;
    }
    
  
    private List<DetailsListCouvertureOperationDTO> findMissingOperation(List<Operation> operations, List<DetailsListCouvertureOperationDTO> existingDetailsDTOs, Integer codeListCouverture) {
        // Extract codes of existing prestations in DetailsPriceList
        Set<Integer> existingPrestationCodes = existingDetailsDTOs.stream()
                .map(DetailsListCouvertureOperationDTO::getCodeOperation)
                .collect(Collectors.toSet());

        // Find prestations that are not in the existing DetailsPriceList
        List<DetailsListCouvertureOperationDTO> missingPrestationDTOs = new ArrayList<>();
        for (Operation operationDtos : operations) {
            if (!existingPrestationCodes.contains(operationDtos.getCode())) {
                // Create a new DetailsPriceListDTO for the missing prestation
                DetailsListCouvertureOperationDTO newDetailsPriceListDTO = new DetailsListCouvertureOperationDTO();
                newDetailsPriceListDTO.setCodeOperation(operationDtos.getCode()); 
                newDetailsPriceListDTO.setOperationDTO(OperationFactory.operationToOperationDTO(operationDtos));
                newDetailsPriceListDTO.setMontantPEC(BigDecimal.ZERO); // Set montant to prestation's montant    
                newDetailsPriceListDTO.setMontantPatient(operationDtos.getCoutRevient()); // Set montant to prestation's montant
                newDetailsPriceListDTO.setTauxCouverPec(BigDecimal.ZERO); // Set montant to prestation's montant

                newDetailsPriceListDTO.setMontantPere(operationDtos.getCoutRevient()); // Set montantPere to prestation's montant
                newDetailsPriceListDTO.setCodeListCouverture(codeListCouverture);
                newDetailsPriceListDTO.setMntApresMaj(operationDtos.getCoutRevient()); // Set mntApresMaj to prestation's montant

                missingPrestationDTOs.add(newDetailsPriceListDTO);
            }
        }

        return missingPrestationDTOs;
    }

}
