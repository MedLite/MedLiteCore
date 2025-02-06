/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import com.FrameWork.MedLite.Parametrage.domaine.Operation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPriceListOperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.OperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.OperationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
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
public class DetailsPriceListOperationService {

    private final Logger log = LoggerFactory.getLogger(DetailsPriceListOperationService.class);

    private final DetailsPriceListOperationRepo detailsPriceListOperationRepo;
    private final OperationRepo operationRepo;

    public DetailsPriceListOperationService(DetailsPriceListOperationRepo detailsPriceListOperationRepo, OperationRepo operationRepo) {
        this.detailsPriceListOperationRepo = detailsPriceListOperationRepo;
        this.operationRepo = operationRepo;
    }

    

    @Transactional(readOnly = true)
    public List<DetailsPriceListOperationDTO> findOneWithCodePriceListOperationAndCodeOperation(Integer codePriceListOperation, Integer codeOperation) {
        List<DetailsPriceListOperation> domaine = detailsPriceListOperationRepo.findByCodePriceListAndCodeOperation(codePriceListOperation, codeOperation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
        return DetailsPriceListOperationFactory.detailsPriceListOperationTodetailsPriceListOperationDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsPriceListOperationDTO> findOneWithCodeOperation(Integer codeOperation) {
        List<DetailsPriceListOperation> domaine = detailsPriceListOperationRepo.findByCodeOperation(codeOperation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
        return DetailsPriceListOperationFactory.detailsPriceListOperationTodetailsPriceListOperationDTOs(domaine);
    }

//       @Transactional(readOnly = true)
//    public List<DetailsPriceListOperationDTO> findOneWithCodePriceListOperation(Integer codePriceListOperation) {
//        List<DetailsPriceListOperation> domaine = detailsPriceListOperationRepo.findByCodePriceList(codePriceListOperation);
//        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
//        return DetailsPriceListOperationFactory.createDTOs(domaine);
//    }
    @Transactional(readOnly = true)
    public List<DetailsPriceListOperationDTO> findOneWithCodePriceListOperation(Integer codePriceListOperation) {
        List<DetailsPriceListOperation> domaine = detailsPriceListOperationRepo.findByCodePriceList(codePriceListOperation);
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"messages\":[{\"destinations\":[{\"to\":\"21654328336\"}],\"from\":\"09100002020\",\"text\":\"New Admission Has Been Created With Number  'OPD25000011' .\"}]}");
//        Request request = new Request.Builder()
//                 .url("https://m3zxv6.api.infobip.com/whatsapp/1/message/template")
//                .method("POST", body)
//                .addHeader("Authorization", "App a78a8288131df12b9cd8a5ff75d7f732-c6ac8ca3-b2ce-4f75-9578-e2a8254587ce")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .build();
//        try {
//        Response response = client.newCall(request).execute();
//        // Process the response (check for success, get the body, etc.)
//        if (!response.isSuccessful()) {
//            // Handle unsuccessful response (e.g., log error, throw custom exception)
//            log.error("SMS sending failed: {} {}", response.code(), response.message());
//            // You might choose to re-throw a custom exception or return an empty list here.
//            return Collections.emptyList(); // Or throw a custom exception
//        }
//
//        // Process successful response...
//
//
//    } catch (IOException e) {
//        // Handle the IOException. Log it, or throw a more specific exception.
//        log.error("Error sending SMS:", e);
//        // Choose appropriate error handling (e.g., return an empty list or throw a custom exception)
//        return Collections.emptyList(); //Or throw new RuntimeException("Failed to send SMS", e);
//    }
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"messages\":[{\"from\":\"447860099299\",\"to\":\"21654328336\",\"messageId\":\"f6e48e06-0a79-44b2-84cf-84e47fd18706\",\"content\"cccccc");
//        Request request = new Request.Builder()
//                .url("https://m3zxv6.api.infobip.com/whatsapp/1/message/template")
//                .method("POST", body)
//                .addHeader("Authorization", "App a78a8288131df12b9cd8a5ff75d7f732-c6ac8ca3-b2ce-4f75-9578-e2a8254587ce")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            // Process the response (check for success, get the body, etc.)
//            if (!response.isSuccessful()) {
//                // Handle unsuccessful response (e.g., log error, throw custom exception)
//                log.error("SMS sending failed: {} {}", response.code(), response.message());
//                // You might choose to re-throw a custom exception or return an empty list here.
//                return Collections.emptyList(); // Or throw a custom exception
//            }
//
//            // Process successful response...
//        } catch (IOException e) {
//            // Handle the IOException. Log it, or throw a more specific exception.
//            log.error("Error sending SMS:", e);
//            // Choose appropriate error handling (e.g., return an empty list or throw a custom exception)
//            return Collections.emptyList(); //Or throw new RuntimeException("Failed to send SMS", e);
//        }
//        return DetailsPriceListOperationFactory.createDTOs(domaine);


  // Fetch all active prestations
        List<Operation> operations = operationRepo.findByActifOrderByCodeSaisieDesc(true); 

        // Convert existing DetailsPriceList entries to DTOs
        List<DetailsPriceListOperationDTO> existingDetailsDTOs = DetailsPriceListOperationFactory.createDTOs(domaine);
        
        // Find prestations that are not in the existing DetailsPriceList
        List<DetailsPriceListOperationDTO> missingOperationDTOs = findMissingOperation(operations, existingDetailsDTOs, codePriceListOperation); 
        
        List<DetailsPriceListOperationDTO> allDetailsDTOs = new ArrayList<>(existingDetailsDTOs);
        allDetailsDTOs.addAll(missingOperationDTOs);
  
        return allDetailsDTOs;
    }
    
    
  
    private List<DetailsPriceListOperationDTO> findMissingOperation(List<Operation> operations, List<DetailsPriceListOperationDTO> existingDetailsDTOs, Integer codePriceList) {
        // Extract codes of existing prestations in DetailsPriceList
        Set<Integer> existingPrestationCodes = existingDetailsDTOs.stream()
                .map(DetailsPriceListOperationDTO::getCodeOperation)
                .collect(Collectors.toSet());

        // Find prestations that are not in the existing DetailsPriceList
        List<DetailsPriceListOperationDTO> missingPrestationDTOs = new ArrayList<>();
        for (Operation operationDtos : operations) {
            if (!existingPrestationCodes.contains(operationDtos.getCode())) {
                // Create a new DetailsPriceListDTO for the missing prestation
                DetailsPriceListOperationDTO newDetailsPriceListDTO = new DetailsPriceListOperationDTO();
                newDetailsPriceListDTO.setCodeOperation(operationDtos.getCode()); 
                newDetailsPriceListDTO.setOperationDTO(OperationFactory.operationToOperationDTO(operationDtos));
                newDetailsPriceListDTO.setMontant(operationDtos.getCoutRevient()); // Set montant to prestation's montant
                newDetailsPriceListDTO.setMontantPere(operationDtos.getCoutRevient()); // Set montantPere to prestation's montant
                newDetailsPriceListDTO.setCodePriceList(codePriceList);
                newDetailsPriceListDTO.setMntApresMaj(operationDtos.getCoutRevient()); // Set mntApresMaj to prestation's montant

                missingPrestationDTOs.add(newDetailsPriceListDTO);
            }
        }

        return missingPrestationDTOs;
    }

    public DetailsPriceListOperationDTO save(DetailsPriceListOperationDTO dto) {
        DetailsPriceListOperation domaine = DetailsPriceListOperationFactory.DetailspriceListOperationDTOToDetailsPriceListOperation(dto, new DetailsPriceListOperation());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsPriceListOperationRepo.save(domaine);

        return DetailsPriceListOperationFactory.DetailsPriceListOperationToDetailsPriceListOperationDTO(domaine);
    }

    public DetailsPriceListOperation update(DetailsPriceListOperationDTO dto) {
        DetailsPriceListOperation domaine = detailsPriceListOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DetailsPriceListOperationNotFound");
        dto.setCode(domaine.getCode());
        DetailsPriceListOperationFactory.DetailspriceListOperationDTOToDetailsPriceListOperation(dto, domaine);

        return detailsPriceListOperationRepo.save(domaine);
    }

    public void deleteDetailsPriceListOperation(Integer codePriceList) {
//        Preconditions.checkArgument(detailsPriceListOperationRepo.existsById(codePriceList), "error.DetailsPriceListOperationNotFound");
        detailsPriceListOperationRepo.deleteByCodePriceList(codePriceList);
    }

    public void deleteDetailsPriceListOperationByCodeOperation(Integer code) {
        detailsPriceListOperationRepo.deleteByCodeOperation(code);
    }

}
