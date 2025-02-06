/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;

import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperationPK;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPrestationPK;
import com.FrameWork.MedLite.Parametrage.domaine.Operation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.OperationDTO;
import com.FrameWork.MedLite.Parametrage.enumeration.ParametrageConstants;
import com.FrameWork.MedLite.web.Util.Helper;
import com.FrameWork.MedLite.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

            domaine.setAutoriseModifIntervenant(dto.isAutoriseModifIntervenant());

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

            domaine.setCodeTypeOperation(dto.getCodeTypeOperation());
            if (domaine.getCodeTypeOperation() != null) {
                domaine.setTypeOperation(TypeOperationFactory.createTypeOperationByCode(dto.getCodeTypeOperation()));
            }

            domaine.setCodeFamilleOperation(dto.getCodeFamilleOperation());
            if (domaine.getCodeFamilleOperation() != null) {
                domaine.setFamilleOperation(FamilleOperationFactory.createFamilleOperationByCode(dto.getCodeFamilleOperation()));
            }

            if (dto.getDetailsPriceListOperationDTOs().isEmpty()) {
                throw new IllegalArgumentException("error.DetailsRequired");
            }
            Collection<DetailsOperation> detailsCollections = new ArrayList<>();
            dto.getDetailsPriceListOperationDTOs().forEach(x -> {
                DetailsOperation detailsPrestation = new DetailsOperation();

                DetailsOperationPK detailsPK = new DetailsOperationPK();
                
                
                Preconditions.checkBusinessLogique(x.getCodeTypeIntervenant() != null, "error.TypeIntervenantRequired"); 
                detailsPK.setCodeTypeIntervenant(x.getCodeTypeIntervenant());
                
//                    Preconditions.checkBusinessLogique(x.getCodeNatureAdmission()!= null, "error.NatureAdmissionRequired"); 
                detailsPK.setCodeNatureAdmission(1);
                
                
                detailsPrestation.setDetailsOperationPK(detailsPK);

                Preconditions.checkBusinessLogique(x.getMontant() != null, "error.MontantRequired");
                detailsPrestation.setMontant(x.getMontant());

                detailsPrestation.setDateCreate(new Date());
                detailsPrestation.setUsercreate(Helper.getUserAuthenticated());
                detailsPrestation.setOperation(domaine);
                detailsCollections.add(detailsPrestation);

            });

            if (domaine.getDetailsOperations() != null) {
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
            dto.setAutoriseModifIntervenant(domaine.isAutoriseModifIntervenant());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCoutRevient(domaine.getCoutRevient());
            dto.setPrixMoyene(domaine.getPrixMoyene());
            dto.setBlocOperationDTO(BlocOperationFactory.blocOperationToBlocOperationDTO(domaine.getBlocOperation()));
            dto.setCodeBlocOperation(domaine.getCodeBlocOperation());
            dto.setFamilleFacturationDTO(FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine.getFamilleFacturation()));
            dto.setCodeFamilleFacturation(domaine.getCodeFamilleFacturation());
            dto.setFamilleOperationDTO(FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine.getFamilleOperation()));
            dto.setCodeFamilleOperation(domaine.getCodeFamilleOperation());

            dto.setTypeOperationDTO(TypeOperationFactory.typeOperationToTypeOperationDTO(domaine.getTypeOperation()));
            dto.setCodeTypeOperation(domaine.getCodeTypeOperation());

            if (domaine.getDetailsOperations() != null) {
                Collection<DetailsOperationDTO> detailsPrestationDTOs = new ArrayList<>();
                domaine.getDetailsOperations().forEach(x -> {
                    DetailsOperationDTO detailsDTO = new DetailsOperationDTO();
                    detailsDTO = DetailsOperationFactory.DetailsOperationToDetailsOperationDTOCollection(x);
                    detailsPrestationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsOperationDTOs() != null) {
                    dto.getDetailsOperationDTOs().clear();
                    dto.getDetailsOperationDTOs().addAll(detailsPrestationDTOs);
                } else {
                    dto.setDetailsOperationDTOs(detailsPrestationDTOs);
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

    public static OperationDTO operationToOperationDTOforUpdate(Operation domaine) {

        if (domaine != null) {
            OperationDTO dto = new OperationDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setAutoriseModifIntervenant(domaine.isAutoriseModifIntervenant());

            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCoutRevient(domaine.getCoutRevient());
            dto.setPrixMoyene(domaine.getPrixMoyene());

            dto.setFamilleFacturationDTO(FamilleFacturationFactory.familleFacturationToFamilleFacturationDTO(domaine.getFamilleFacturation()));
            dto.setCodeFamilleFacturation(domaine.getCodeFamilleFacturation());
            dto.setFamilleOperationDTO(FamilleOperationFactory.familleOperationToFamilleOperationDTO(domaine.getFamilleOperation()));
            dto.setCodeFamilleOperation(domaine.getCodeFamilleOperation());

            dto.setBlocOperationDTO(BlocOperationFactory.blocOperationToBlocOperationDTO(domaine.getBlocOperation()));
            dto.setCodeBlocOperation(domaine.getCodeBlocOperation());

            dto.setTypeOperationDTO(TypeOperationFactory.typeOperationToTypeOperationDTO(domaine.getTypeOperation()));
            dto.setCodeTypeOperation(domaine.getCodeTypeOperation());

            if (domaine.getDetailsOperations() != null) {
                Collection<DetailsOperationDTO> detailsPrestationDTOs = new ArrayList<>();
                domaine.getDetailsOperations().forEach(x -> {
                    DetailsOperationDTO detailsDTO = new DetailsOperationDTO();
                    detailsDTO = DetailsOperationFactory.DetailsOperationToDetailsOperationDTOCollectionForUpdate(x);
                    detailsPrestationDTOs.add(detailsDTO);
                });
                if (dto.getDetailsOperationDTOs() != null) {
                    dto.getDetailsOperationDTOs().clear();
                    dto.getDetailsOperationDTOs().addAll(detailsPrestationDTOs);
                } else {
                    dto.setDetailsOperationDTOs(detailsPrestationDTOs);
                }
            }

            return dto;
        } else {
            return null;
        }
    }
}
