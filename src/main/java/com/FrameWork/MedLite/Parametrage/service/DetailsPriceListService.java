/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceList;
import com.FrameWork.MedLite.Parametrage.domaine.Prestation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.EditionPriceListParTypeIntervenant;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.PrestationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPrestationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListRepo;
import com.FrameWork.MedLite.Parametrage.repository.PrestationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsPriceListService {

    private final Logger log = LoggerFactory.getLogger(DetailsPriceListService.class);

    private final DetailsPriceListRepo detailsPriceListRepo;
    private final DetailsPrestationRepo detailsPrestationRepo;
    private final PrestationRepo prestationRepo;

    public DetailsPriceListService(DetailsPriceListRepo detailsPriceListRepo, DetailsPrestationRepo detailsPrestationRepo, PrestationRepo prestationRepo) {
        this.detailsPriceListRepo = detailsPriceListRepo;
        this.detailsPrestationRepo = detailsPrestationRepo;
        this.prestationRepo = prestationRepo;
    }

//    public Boolean deleteByCodePriceList(Integer codePriceList) {
//        detailsPriceListRepo.deleteByCodePriceList(codePriceList);
//        return true;
//    }
//
    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO>  findOneWithCodePriceListAndCodePrestationAndCodeNatureAdmission(Integer codePriceList, Integer codePrestation, Integer codeNatureAdmission) {
         List<DetailsPriceList>  domaine = detailsPriceListRepo.findByCodePriceListAndCodePrestationAndCodeNatureAdmission(codePriceList, codePrestation, codeNatureAdmission);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOs(domaine);
    }

    
//        @Transactional(readOnly = true)
//    public DetailsPriceListDTO  findOneWithCodePriceListAndCodePrestationAndCodeNatureAdmissionGrouped(Integer codePriceList, Integer codePrestation, Integer codeNatureAdmission) {
//         DetailsPriceList  domaine = detailsPriceListRepo.findByCodePriceListAndCodePrestationAndCodeNatureAdmission(codePriceList, codePrestation, codeNatureAdmission);
//        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
//        return DetailsPriceListFactory.DetailsPriceListToDetailsPriceListDTO(domaine);
//    }
    
    
    
    
    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO> findOneWithCodePriceList(Integer codePriceList) {
        // Fetch existing DetailsPriceList entries for the given codePriceList
        List<DetailsPriceList> domaine = detailsPriceListRepo.findByCodePriceList(codePriceList);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound"); 
        List<Prestation> prestationListDTO = prestationRepo.findByActif(true); 
        List<DetailsPriceListDTO> existingDetailsDTOs = DetailsPriceListFactory.createDTOs(domaine);
         
        List<DetailsPriceListDTO> missingPrestationDTOs = findMissingPrestations(prestationListDTO, existingDetailsDTOs, codePriceList); 
        
        List<DetailsPriceListDTO> allDetailsDTOs = new ArrayList<>(existingDetailsDTOs);
        allDetailsDTOs.addAll(missingPrestationDTOs); 
        return allDetailsDTOs;
    }

  
    private List<DetailsPriceListDTO> findMissingPrestations(List<Prestation> prestationListDTO, List<DetailsPriceListDTO> existingDetailsDTOs, Integer codePriceList) {
        // Extract codes of existing prestations in DetailsPriceList
        Set<Integer> existingPrestationCodes = existingDetailsDTOs.stream()
                .map(DetailsPriceListDTO::getCodePrestation)
                .collect(Collectors.toSet());

        // Find prestations that are not in the existing DetailsPriceList
        List<DetailsPriceListDTO> missingPrestationDTOs = new ArrayList<>();
        for (Prestation prestationDTO : prestationListDTO) {
            if (!existingPrestationCodes.contains(prestationDTO.getCode())) {
                // Create a new DetailsPriceListDTO for the missing prestation
                DetailsPriceListDTO newDetailsPriceListDTO = new DetailsPriceListDTO();
                newDetailsPriceListDTO.setCodePrestation(prestationDTO.getCode()); 
                newDetailsPriceListDTO.setPrestationDTO(PrestationFactory.prestationToPrestationDTO(prestationDTO));
                newDetailsPriceListDTO.setMontant(prestationDTO.getPrixPrestation()); // Set montant to prestation's montant
                newDetailsPriceListDTO.setMontantPere(prestationDTO.getPrixPrestation()); // Set montantPere to prestation's montant
                newDetailsPriceListDTO.setCodePriceList(codePriceList);
                newDetailsPriceListDTO.setMntApresMaj(prestationDTO.getPrixPrestation()); // Set mntApresMaj to prestation's montant

                missingPrestationDTOs.add(newDetailsPriceListDTO);
            }
        }

        return missingPrestationDTOs;
    }

    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO> findOneWithCodePriceListAndCodePrestation(Integer codePriceList, Integer codePrestation) {
        List<DetailsPriceList> domaine = detailsPriceListRepo.findByCodePriceListAndCodePrestation(codePriceList, codePrestation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsPriceListDTO> findOneWithCodePriceListAndCodeNatureAdmission(Integer codePriceList, Integer codeNatureAdmission) {
        List<DetailsPriceList> domaine = detailsPriceListRepo.findByCodePriceListAndCodeNatureAdmission(codePriceList, codeNatureAdmission);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListNotFound");
        return DetailsPriceListFactory.detailsPriceListTodetailsPriceListDTOs(domaine);
    }

    public DetailsPriceListDTO save(DetailsPriceListDTO dto) {
        DetailsPriceList domaine = DetailsPriceListFactory.DetailspriceListDTOToDetailsPriceList(dto, new DetailsPriceList());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsPriceListRepo.save(domaine);

        return DetailsPriceListFactory.DetailsPriceListToDetailsPriceListDTO(domaine);
    }

    public DetailsPriceList update(DetailsPriceListDTO dto) {
        DetailsPriceList domaine = detailsPriceListRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceList");
        dto.setCode(domaine.getCode());
        DetailsPriceListFactory.DetailspriceListDTOToDetailsPriceList(dto, domaine);

        return detailsPriceListRepo.save(domaine);
    }

    public void deleteDetailsPriceList(Integer codePriceList) {
//          Preconditions.checkArgument(detailsPriceListRepo.existsById(codePriceList), "error.DetailsPriceListPrestationNotFound");
        detailsPriceListRepo.deleteByCodePriceList(codePriceList);
    }
    
      @Transactional(readOnly = true)
    public List<EditionPriceListParTypeIntervenant> findDetailsForEditionByCodePriceList(Integer codePriceList) {
        log.debug("Request to find DetailsForEdition By CodePriceList : {}", codePriceList);
        List<DetailsPriceList> detail = new ArrayList<>(detailsPriceListRepo.findByCodePriceList(codePriceList));
        return DetailsPriceListFactory.listDetailsPriceListTOEditionPriceListDetailsParTypeIntervenant(detail);
    }


}
