/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsOperation;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsOperationPK;
import com.DevPointSystem.MedLite.Parametrage.domaine.Operation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsOperationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.OperationDTO;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class OperationFactory {
     public static Operation createOperationByCode(int code) {
        Operation domaine = new Operation();
        domaine.setCode(code);
        return domaine;
    }

    public static Operation operationDTOToOperation(OperationDTO dto, Operation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
    
            domaine.setPrixMoyene(dto.getPrixMoyene());       
            domaine.setCoutRevient(dto.getCoutRevient());


            domaine.setCodeFamilleFacturation(dto.getCodeFamilleFacturation());
            if (domaine.getCodeFamilleFacturation() != null) {
                domaine.setFamilleFacturation(FamilleFacturationFactory.createFamilleFacturationByCode(dto.getCodeFamilleFacturation()));
            } 
             domaine.setCodeBlocOperation(dto.getCodeBlocOperation());
            if (domaine.getCodeBlocOperation() != null) {
                domaine.setBlocOperation(BlocOperationFactory.createBlocOperationByCode(dto.getCodeBlocOperation()));
            } 
            
            
            domaine.setCodeFamilleOperation(dto.getCodeFamilleOperation());
            if (domaine.getCodeFamilleOperation() != null) {
                domaine.setFamilleOperation(FamilleOperationFactory.createFamilleOperationByCode(dto.getCodeFamilleOperation()));
            }
            
            if(dto.getDetailsOperationsDTOs().isEmpty()){
                 throw new IllegalArgumentException("error.DetailsRequired");
            }  
            Collection<DetailsOperation> detailsCollections = new ArrayList<>();
            dto.getDetailsOperationsDTOs().forEach(x -> {
                DetailsOperation detailsOperation = new DetailsOperation();

                DetailsOperationPK detailsPK = new DetailsOperationPK();
                Preconditions.checkBusinessLogique(x.getCodeTypeIntervenant()!= null, "error.TypeIntervenantRequired");
                detailsPK.setCodeTypeIntervenant(x.getCodeTypeIntervenant());

                detailsOperation.setDetailsOperationPK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsOperation.setMontant(x.getMontant());    
                 

                detailsOperation.setDateCreate(domaine.getDateCreate());
                detailsOperation.setUsercreate(domaine.getUserCreate());
                detailsOperation.setOperation(domaine);
                detailsCollections.add(detailsOperation);
            });

            if (domaine.getDetailsOperations()!= null) {
                domaine.getDetailsOperations().clear();
                domaine.getDetailsOperations().addAll(detailsCollections);
            } else {
                domaine.setDetailsOperations(detailsCollections);
            }
            

            return domaine;
        } else {
            return null;
        }
    }

    public static OperationDTO operationToOperationDTO(Operation domaine) {

        if (domaine != null) {
            OperationDTO dto = new OperationDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCoutRevient(domaine.getCoutRevient());  
            dto.setPrixMoyene(domaine.getPrixMoyene());


            dto.setFamilleFacturationDTO(FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine.getFamilleFacturation()));
            dto.setCodeFamilleFacturation(domaine.getCodeFamilleFacturation());
            dto.setFamilleOperationDTO(FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine.getFamilleOperation()));
            dto.setCodeFamilleOperation(domaine.getCodeFamilleOperation());

            
            if (domaine.getDetailsOperations()!= null) {
                Collection<DetailsOperationDTO> detailsOperationDTOs = new ArrayList<>();
                domaine.getDetailsOperations().forEach(x -> {
                    DetailsOperationDTO detailsDTO = new DetailsOperationDTO();
                    detailsDTO = DetailsOperationFactory.DetailsOperationToDetailsOperationDTOCollectionForUpdate(x);
                    detailsOperationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsOperationsDTOs()!= null) {
                    dto.getDetailsOperationsDTOs().clear();
                    dto.getDetailsOperationsDTOs().addAll(detailsOperationDTOs);
                } else {
                    dto.setDetailsOperationsDTOs(detailsOperationDTOs);
                }
            }
            
            return dto;
        } else {
            return null;
        }
    }

    public static List<OperationDTO> listOperationToOperationDTOs(List<Operation> operations) {
        List<OperationDTO> list = new ArrayList<>();
        for (Operation operation : operations) {
            list.add(operationToOperationDTO(operation));
        }
        return list;
    }
}
