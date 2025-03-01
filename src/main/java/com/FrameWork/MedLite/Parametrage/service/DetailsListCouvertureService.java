/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsListCouvertureFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsListCouvertureRepo;
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
public class DetailsListCouvertureService {

    private final DetailsListCouvertureRepo detailsListCouvertureRepo;
    private final PrestationRepo prestationRepo;

    public DetailsListCouvertureService(DetailsListCouvertureRepo detailsListCouvertureRepo, PrestationRepo prestationRepo) {
        this.detailsListCouvertureRepo = detailsListCouvertureRepo;
        this.prestationRepo = prestationRepo;
    }

//    public Boolean deleteByCodeListCouverture(Integer codeListCouverture) {
//        detailsListCouvertureRepo.deleteByCodeListCouverture(codeListCouverture);
//        return true;
//    }
    public void deleteByCodeListCouverture(Integer codeListCouverture) {
//        Preconditions.checkArgument(detailsPriceListOperationRepo.existsById(codePriceList), "error.DetailsPriceListOperationNotFound");
        detailsListCouvertureRepo.deleteByCodeListCouverture(codeListCouverture);
    }

//    @Transactional(readOnly = true)
//    public List<DetailsListCouvertureDTO> findOneWithCodeListCouverture(Integer codeListCouverture) {
//        // Fetch existing DetailsListCouverture entries for the given codeListCouverture
//        List<DetailsListCouverture> domaine = detailsListCouvertureRepo.findByCodeListCouverture(codeListCouverture);
//        Preconditions.checkArgument(domaine != null, "error.DetailsListCouvertureNotFound");
//
//        // Fetch all active prestations
//        List<Prestation> prestationListDTO = prestationRepo.findByActif(true);
////        System.out.println("list prestaion " + prestationListDTO.size());
//
//        // Convert existing DetailsListCouverture entries to DTOs
//        List<DetailsListCouvertureDTO> existingDetailsDTOs = DetailsListCouvertureFactory.createDTOs(domaine);
//        
//        // Find prestations that are not in the existing DetailsListCouverture
//        List<DetailsListCouvertureDTO> missingPrestationDTOs = findMissingPrestations(prestationListDTO, existingDetailsDTOs, codeListCouverture); 
//        
//        List<DetailsListCouvertureDTO> allDetailsDTOs = new ArrayList<>(existingDetailsDTOs);
//        allDetailsDTOs.addAll(missingPrestationDTOs);
//  
//        return allDetailsDTOs;
//    }
//
//  
//    private List<DetailsListCouvertureDTO> findMissingPrestations(List<Prestation> prestationListDTO, List<DetailsListCouvertureDTO> existingDetailsDTOs, Integer codeListCouverture) {
//        // Extract codes of existing prestations in DetailsListCouverture
//        Set<Integer> existingPrestationCodes = existingDetailsDTOs.stream()
//                .map(DetailsListCouvertureDTO::getCodePrestation)
//                .collect(Collectors.toSet());
//
//        // Find prestations that are not in the existing DetailsListCouverture
//        List<DetailsListCouvertureDTO> missingPrestationDTOs = new ArrayList<>();
//        for (Prestation prestationDTO : prestationListDTO) {
//            if (!existingPrestationCodes.contains(prestationDTO.getCode())) {
//                // Create a new DetailsListCouvertureDTO for the missing prestation
//                DetailsListCouvertureDTO newDetailsListCouvertureDTO = new DetailsListCouvertureDTO();
//                newDetailsListCouvertureDTO.setCodePrestation(prestationDTO.getCode()); 
//                newDetailsListCouvertureDTO.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(prestationDTO));
//                newDetailsListCouvertureDTO.setMontantPEC(BigDecimal.ZERO); // Set montant to prestation's montant      
//                newDetailsListCouvertureDTO.setMontantPatient(prestationDTO.getPrixPrestation()); // Set montant to prestation's montant
//
//                newDetailsListCouvertureDTO.setMontantPere(prestationDTO.getPrixPrestation()); // Set montantPere to prestation's montant
//                newDetailsListCouvertureDTO.setCodeListCouverture(codeListCouverture);
//                newDetailsListCouvertureDTO.setMntApresMaj(prestationDTO.getPrixPrestation()); // Set mntApresMaj to prestation's montant
//
//                missingPrestationDTOs.add(newDetailsListCouvertureDTO);
//            }
//        }
//
//        return missingPrestationDTOs;
//    }
    @Transactional(readOnly = true)
    public List<DetailsListCouvertureDTO> findOneWithCodeListCouverture(Integer codeListCouverture) {
        // Fetch existing DetailsPriceList entries for the given codePriceList
        List<DetailsListCouverture> domaine = detailsListCouvertureRepo.findByCodeListCouverture(codeListCouverture);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        List<Prestation> prestationListDTO = prestationRepo.findByActif(true);
        List<DetailsListCouvertureDTO> existingDetailsDTOs = DetailsListCouvertureFactory.createDTOs(domaine);

        List<DetailsListCouvertureDTO> missingPrestationDTOs = findMissingPrestations(prestationListDTO, existingDetailsDTOs, codeListCouverture);

        List<DetailsListCouvertureDTO> allDetailsDTOs = new ArrayList<>(existingDetailsDTOs);
        allDetailsDTOs.addAll(missingPrestationDTOs);
        return allDetailsDTOs;
    }

    private List<DetailsListCouvertureDTO> findMissingPrestations(List<Prestation> prestationListDTO, List<DetailsListCouvertureDTO> existingDetailsDTOs, Integer codeListCouverture) {
        // Extract codes of existing prestations in DetailsPriceList
        Set<Integer> existingPrestationCodes = existingDetailsDTOs.stream()
                .map(DetailsListCouvertureDTO::getCodePrestation)
                .collect(Collectors.toSet());

        // Find prestations that are not in the existing DetailsPriceList
        List<DetailsListCouvertureDTO> missingPrestationDTOs = new ArrayList<>();
        for (Prestation prestationDTO : prestationListDTO) {
            if (!existingPrestationCodes.contains(prestationDTO.getCode())) {
                // Create a new DetailsPriceListDTO for the missing prestation
                DetailsListCouvertureDTO newDetailsPriceListDTO = new DetailsListCouvertureDTO();
                newDetailsPriceListDTO.setCodePrestation(prestationDTO.getCode());
                newDetailsPriceListDTO.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(prestationDTO));
                newDetailsPriceListDTO.setMontantPEC(BigDecimal.ZERO); // Set montant to prestation's montant    
                newDetailsPriceListDTO.setMontantPatient(prestationDTO.getPrixPrestation()); // Set montant to prestation's montant
                newDetailsPriceListDTO.setTauxCouverPec(BigDecimal.ZERO); // Set montant to prestation's montant    

                newDetailsPriceListDTO.setMontantPere(prestationDTO.getPrixPrestation()); // Set montantPere to prestation's montant
                newDetailsPriceListDTO.setCodeListCouverture(codeListCouverture);
                newDetailsPriceListDTO.setMntApresMaj(prestationDTO.getPrixPrestation()); // Set mntApresMaj to prestation's montant

                missingPrestationDTOs.add(newDetailsPriceListDTO);
            }
        }

        return missingPrestationDTOs;
    }

    @Transactional(readOnly = true)
    public List<DetailsListCouvertureDTO> findOneWithCodeListCouvertureAndCodePrestation(Integer codeListCouverture, Integer codePrestation) {
        // Fetch existing DetailsPriceList entries for the given codePriceList
        List<DetailsListCouverture> domaine = detailsListCouvertureRepo.findByCodeListCouvertureAndCodePrestation(codeListCouverture, codePrestation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        List<DetailsListCouvertureDTO> existingDetailsDTOs = DetailsListCouvertureFactory.createDTOs(domaine);

        return existingDetailsDTOs;
    }
    
    
     @Transactional(readOnly = true)
    public  DetailsListCouvertureDTO  findOneWithCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(Integer codeListCouverture, Integer codePrestation,Integer codeNatureAdmission) {
        // Fetch existing DetailsPriceList entries for the given codePriceList
        DetailsListCouverture  domaine = detailsListCouvertureRepo.findByCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(codeListCouverture, codePrestation,codeNatureAdmission);
        Preconditions.checkArgument(domaine != null, "error.DetailsListCouvertureNotFound");
         DetailsListCouvertureDTO  existingDetailsDTOs = DetailsListCouvertureFactory.DetailsListCouvertureToDetailsListCouvertureDTONew(domaine);

        return existingDetailsDTOs;
    }
     
}
