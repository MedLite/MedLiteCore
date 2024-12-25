package com.DevPointSystem.MedLite.Parametrage.factory;

import com.DevPointSystem.MedLite.Parametrage.domaine.CostProfitCentre;
import com.DevPointSystem.MedLite.Parametrage.dto.CostProfitCentreDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;
import com.DevPointSystem.MedLite.Parametrage.repository.CostProfitCentreRepository;

@Component
public class CostProfitCentreFactory {

    private static CostProfitCentreRepository costProfitCentreRepository;

    public CostProfitCentreFactory(CostProfitCentreRepository costProfitCentreRepository) {
        this.costProfitCentreRepository = costProfitCentreRepository;
    }

    public static CostProfitCentreDTO lazyCostprofitcentreToCostProfitCentreDTO(CostProfitCentre domaine) {
        CostProfitCentreDTO dto = new CostProfitCentreDTO();
        dto.setCode(domaine.getCode());
        dto.setCodeSaisie(domaine.getCodeSaisie());

        dto.setDesignationLt(domaine.getDesignationAr());
        dto.setDesignationAr(domaine.getDesignation());

        dto.setDesignationAr(domaine.getDesignationAr());
        dto.setDesignationLt(domaine.getDesignation());

        dto.setProfitCentre(domaine.getProfitCentre());
//        dto.setClassement(domaine.getClassement());

        dto.setDetail(domaine.getDetail());
        dto.setActif(domaine.getActif());
//        dto.setNiveau(domaine.getNiveau());

        dto.setTypeCostCentreDTO(TypeCostCentreFactory.TypeCostCentreTOTypeCostCentreDTO(domaine.getTypeCostCentre()));
        dto.setCodeTypeCostCentre(domaine.getCodeTypeCostCentre());

        return dto;
    }

    public static CostProfitCentre createCostCentreByCode(int code) {
        CostProfitCentre domaine = new CostProfitCentre();
        domaine.setCode(code);
        return domaine;
    }

    public static CostProfitCentre costProfitCentreDTOToCostProfitCentreForUpdate(CostProfitCentreDTO dto, CostProfitCentre domaine, CostProfitCentreRepository costProfitCentreRepository) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignation(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setActif(dto.getActif());
            domaine.setDateCreate(dto.getDateAction());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setProfitCentre(dto.getProfitCentre());
            domaine.setDetail(dto.getDetail());
//            domaine.setNiveau(dto.getNiveau());
//            domaine.setClassement(dto.getClassement());

            domaine.setCodeTypeCostCentre(dto.getCodeTypeCostCentre());
            if (domaine.getCodeTypeCostCentre() != null) {
                domaine.setTypeCostCentre(TypeCostCentreFactory.createTypeCostCentreByCode(dto.getCodeTypeCostCentre()));
            }

//            if (dto.getParent() != null) {
//                CostProfitCentre parentCostCentre = costProfitCentreRepository.findById(dto.getParent().getCode()).orElse(null);
//                if (parentCostCentre != null) {
//                    domaine.setCodePere(parentCostCentre);
//                }
//            }

//            if (dto.getParent() != null) {
//                Integer codeParent1 = dto.getParent().getCode();
//
//    
//
////                CostProfitCentre parentCostCentre1 = costProfitCentreRepository.findById(codeParent1).orElse(null);
////                Integer codeParent2 = parentCostCentre1.getCodePere().getCode();
////
////             
//
//                CostProfitCentre parentCostCentre = costProfitCentreRepository.findById(codeParent1).orElse(null);
//                if (parentCostCentre != null) { 
//                    domaine.setCodePere(parentCostCentre);
//                }
//            }
            return domaine;
        } else {
            return null;
        }
    }

//    public static CostProfitCentre costProfitCentreDTOToCostProfitCentreComplex(CostProfitCentreDTO dto, CostProfitCentre domaine, CostProfitCentreRepository costProfitCentreRepository) {
//        if (dto != null) {
//            domaine.setCode(dto.getCode());
//
//            domaine.setDesignation(dto.getDesignationLt());
//            domaine.setDesignationAr(dto.getDesignationAr());
//            domaine.setCodeSaisie(dto.getCodeSaisie());
//
//            domaine.setActif(dto.getActif());
//            domaine.setDateCreate(dto.getDateAction());
//            domaine.setUserCreate(dto.getUserCreate());
//            domaine.setProfitCentre(dto.getProfitCentre());
//            domaine.setDetail(dto.getDetail());
//            domaine.setNiveau(dto.getNiveau());
//            domaine.setClassement(dto.getClassement());
//
//            domaine.setCodeTypeCostCentre(dto.getCodeTypeCostCentre());
//            if (domaine.getCodeTypeCostCentre() != null) {
//                domaine.setTypeCostCentre(TypeCostCentreFactory.createTypeCostCentreByCode(dto.getCodeTypeCostCentre()));
//            }
//
//            if (dto.getParent() != null) {
//                Integer codeParent1 = dto.getParent().getCode();
//                CostProfitCentre parentCostCentre = costProfitCentreRepository.findById(codeParent1).orElse(null);
//                if (parentCostCentre != null) {
//                    domaine.setCodePere(parentCostCentre);
//                }
//            }
//
//            return domaine;
//        } else {
//            return null;
//        }
//    }

    
    public static CostProfitCentre costProfitCentreDTOToCostProfitCentre(CostProfitCentreDTO dto, CostProfitCentre domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignation(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setActif(dto.getActif());
            domaine.setDateCreate(dto.getDateAction());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setProfitCentre(dto.getProfitCentre());
            domaine.setDetail(dto.getDetail());
//            domaine.setNiveau(dto.getNiveau());
//            domaine.setClassement(dto.getClassement());

            domaine.setCodeTypeCostCentre(dto.getCodeTypeCostCentre());
            if (domaine.getCodeTypeCostCentre() != null) {
                domaine.setTypeCostCentre(TypeCostCentreFactory.createTypeCostCentreByCode(dto.getCodeTypeCostCentre()));
            }

            
            return domaine;
        } else {
            return null;
        }
    }
    public static CostProfitCentreDTO costProfitCentreToCostProfitCentreDTO(CostProfitCentre domaine) {

        if (domaine != null) {
            CostProfitCentreDTO dto = new CostProfitCentreDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignation());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.getActif());
            dto.setDateAction(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
//            dto.setNiveau(domaine.getNiveau());

            dto.setProfitCentre(domaine.getProfitCentre());
            dto.setDetail(domaine.getDetail());

//            dto.setClassement(domaine.getClassement());

            dto.setTypeCostCentreDTO(TypeCostCentreFactory.TypeCostCentreTOTypeCostCentreDTO(domaine.getTypeCostCentre()));
            dto.setCodeTypeCostCentre(domaine.getCodeTypeCostCentre());

//            if (domaine.getCodePere() != null && domaine.getCodePere().getCode() != null) {
//                // Get the parent CostProfitCentreDTO using the repository
//                CostProfitCentre parent = costProfitCentreRepository.findById(domaine.getCodePere().getCode())
//                        .orElse(null); // Return null if parent is not found
//                if (parent != null) {
//                    dto.setParent(costProfitCentreToCostProfitCentreDTOLazy(parent));
//                }
//            } else {
//                // Set parent to null if no parent code is available
////                dto.setParent(null);
//
//                CostProfitCentreDTO emptyParentDTO = new CostProfitCentreDTO();
////                emptyParentDTO.setCode("");
//                emptyParentDTO.setDesignationAr("");
//                dto.setParent(emptyParentDTO);
//            }
            return dto;
        } else {
            return null;
        }
    }

    public static CostProfitCentreDTO costProfitCentreToCostProfitCentreDTOLazy(CostProfitCentre domaine) {

        if (domaine != null) {
            CostProfitCentreDTO dto = new CostProfitCentreDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignation());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.getActif());
            dto.setDateAction(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
//            dto.setNiveau(domaine.getNiveau());

            dto.setProfitCentre(domaine.getProfitCentre());
            dto.setDetail(domaine.getDetail());

//            dto.setClassement(domaine.getClassement());

//            dto.setTypeCostCentreDTO(TypeCostCentreFactory.TypeCostCentreTOTypeCostCentreDTO(domaine.getTypeCostCentre()));
//            dto.setCodeTypeCostCentre(domaine.getCodeTypeCostCentre());

            return dto;
        } else {
            return null;
        }
    }

    public static List<CostProfitCentreDTO> listCostProfitCentreToCostProfitCentreDTOs(List<CostProfitCentre> costProfitCentres) {
        List<CostProfitCentreDTO> list = new ArrayList<>();
        for (CostProfitCentre x : costProfitCentres) {
            list.add(costProfitCentreToCostProfitCentreDTO(x));
        }
        return list;
    }

//    public static Collection<CostProfitCentreDTO> costprofitcentreToCostProfitCentreDTOs(Collection<CostProfitCentre> costprofitcentres) {
//        List<CostProfitCentreDTO> listCostprofitcentresDTO = new ArrayList<>();
//        costprofitcentres.forEach(x -> {
//            if (x.getCodePere() == null) {
//                CostProfitCentreDTO costprofitcentresDTO = costProfitCentreToCostProfitCentreDTO(x);
//                listCostprofitcentresDTO.add(costprofitcentresDTO);
//
//            }
//        });
//        return listCostprofitcentresDTO;
//    }

    public static Collection<CostProfitCentreDTO> listCostProfitCentreToCostProfitCentreDTOsCollection(Collection<CostProfitCentre> costProfitCentres) {
        Collection<CostProfitCentreDTO> list = new ArrayList<>();
        for (CostProfitCentre x : costProfitCentres) {
            list.add(costProfitCentreToCostProfitCentreDTO(x));
        }
        return list;
    }
}
