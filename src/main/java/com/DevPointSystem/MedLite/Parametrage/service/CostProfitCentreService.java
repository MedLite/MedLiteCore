package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.CompteurClassement;
import com.DevPointSystem.MedLite.Parametrage.domaine.CostProfitCentre;
import com.DevPointSystem.MedLite.Parametrage.dto.CostProfitCentreDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.CostProfitCentreFactory;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.DevPointSystem.MedLite.Parametrage.repository.CostProfitCentreRepository;
import org.springframework.data.domain.Sort;

/**
 * Service Implementation for managing CostProfitCentre.
 */
@Service
@Transactional
public class CostProfitCentreService {

    private final Logger log = LoggerFactory.getLogger(CostProfitCentreService.class);

    @Autowired
    EntityManager entityManager;
    private static final String ENTITY_NAME = "Code";   
  


    private final CostProfitCentreRepository costprofitcentreRepository;
    private final CompteurService compteurService;
    private final CompteurClassementService compteurClassementService;

    public CostProfitCentreService(CostProfitCentreRepository costprofitcentreRepository, CompteurService compteurService, CompteurClassementService compteurClassementService) {
        this.costprofitcentreRepository = costprofitcentreRepository;
        this.compteurService = compteurService;
        this.compteurClassementService = compteurClassementService;
    }

    /**
     * Save a costprofitcentreDTO.
     *
     * @param costprofitcentreDTO
     * @return the persisted entity
     */
//    public CostProfitCentreDTO save(CostProfitCentreDTO costprofitcentreDTO) {
//        log.debug("Request to save CostProfitCentre: {}", costprofitcentreDTO);
//        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieCC");
//        String codeSaisieCC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
//        costprofitcentreDTO.setCodeSaisie(codeSaisieCC);
//        compteurService.incrementeSuffixe(CompteurCodeSaisie);
//
//        Integer codeParent1 = costprofitcentreDTO.getParent().getCode();
//
//        System.out.println("pere 1 save " + codeParent1);
//
//        CostProfitCentre parentCostCentre1 = costprofitcentreRepository.findById(codeParent1).orElse(null);
//
//        Integer codeParent2 = parentCostCentre1.getCodePere().getCode();
//
//        if (codeParent2 == 1) {
//            // code pere 1 ==> principal
//
//            Integer codePere = codeParent1;
//
//            CompteurClassement CostCenterNV1_1 = compteurClassementService.findOne("CostCenterNV2_1");
//            Integer codeSaisieNV1 = CostCenterNV1_1.getSuffixe();
//            CompteurClassement CostCenterNV1_2 = compteurClassementService.findOne("CostCenterNV2_2");
//            Integer codeSaisieNV2 = CostCenterNV1_2.getSuffixe();
//            CompteurClassement CostCenterNV1_3 = compteurClassementService.findOne("CostCenterNV2_3");
//            Integer codeSaisieNV3 = CostCenterNV1_3.getSuffixe();
//            CompteurClassement CostCenterNV1_4 = compteurClassementService.findOne("CostCenterNV2_4");
//            Integer codeSaisieNV4 = CostCenterNV1_4.getSuffixe();
//
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 2) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV1);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV1_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 2 111 ");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codePere == 2) {
//
//                int nv1 = codeSaisieNV1;
//                int nv2 = codeSaisieNV2;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV2 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV2);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV1_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 2 11 ");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codePere == 2) {
//
////                int nv1 = codeSaisieNV1;
////                int nv2 = codeSaisieNV2;
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv3 = codeSaisieNV3;
//
//                int codeSaisie3 = tempclass + nv3;
//                System.out.println("codeSaisie3 " + codeSaisie3);
//                Integer CodeSaisieFinNV3 = codeSaisie3;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV3);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV1_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 2  11 ");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codePere == 2) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv4 = codeSaisieNV4;
//
//                int codeSaisie4 = tempclass + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie4);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV1_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 2 11 ");
//            }
//
//            /// parent 3 
//            CompteurClassement CostCenterNV3_1 = compteurClassementService.findOne("CostCenterNV3_1");
//            Integer codeSaisieNV31 = CostCenterNV3_1.getSuffixe();
//            CompteurClassement CostCenterNV31_2 = compteurClassementService.findOne("CostCenterNV3_2");
//            Integer codeSaisieNV32 = CostCenterNV31_2.getSuffixe();
//            CompteurClassement CostCenterNV31_3 = compteurClassementService.findOne("CostCenterNV3_3");
//            Integer codeSaisieNV33 = CostCenterNV31_3.getSuffixe();
//            CompteurClassement CostCenterNV31_4 = compteurClassementService.findOne("CostCenterNV3_4");
//            Integer codeSaisieNV34 = CostCenterNV31_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 3) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV31);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV1_1);
//
//                System.out.println("getNiveau() == 1 && codeParent2 == 3  11 ");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codePere == 3) {
//
//                int nv1 = codeSaisieNV31;
//                int nv2 = codeSaisieNV32;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV2 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV2);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV31_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 3  11");
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codePere == 3) {
//
//                int nv1 = codeSaisieNV31;
//                int nv2 = codeSaisieNV32;
//                int nv3 = codeSaisieNV33;
//
//                int codeSaisie3 = nv1 + nv2 + nv3;
//
//                Integer CodeSaisieFinNV33 = codeSaisie3;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV33);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV31_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 3  11");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codePere == 3) {
//
//                int nv1 = codeSaisieNV31;
//                int nv2 = codeSaisieNV32;
//                int nv3 = codeSaisieNV33;
//                int nv4 = codeSaisieNV34;
//
//                int codeSaisie4 = nv1 + nv2 + nv3 + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie4);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV31_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 3  11");
//            }
//
//            /// parent 15 
//            CompteurClassement CostCenterNV15_1 = compteurClassementService.findOne("CostCenterNV15_1");
//            Integer codeSaisieNV151 = CostCenterNV15_1.getSuffixe();
//            CompteurClassement CostCenterNV151_2 = compteurClassementService.findOne("CostCenterNV15_2");
//            Integer codeSaisieNV152 = CostCenterNV151_2.getSuffixe();
//            CompteurClassement CostCenterNV151_3 = compteurClassementService.findOne("CostCenterNV15_3");
//            Integer codeSaisieNV153 = CostCenterNV151_3.getSuffixe();
//            CompteurClassement CostCenterNV151_4 = compteurClassementService.findOne("CostCenterNV15_4");
//            Integer codeSaisieNV154 = CostCenterNV151_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 15) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV151);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV15_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 15  11");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codePere == 15) {
//
//                int nv1 = codeSaisieNV151;
//                int nv2 = codeSaisieNV152;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV152 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV152);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV151_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 15 11");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codePere == 15) {
//
//                int nv1 = codeSaisieNV151;
//                int nv2 = codeSaisieNV152;
//                int nv3 = codeSaisieNV153;
//
//                int codeSaisie153 = nv1 + nv2 + nv3;
//
//                Integer CodeSaisieFinNV153 = codeSaisie153;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV153);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV151_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 15  11");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codePere == 15) {
//
//                int nv1 = codeSaisieNV151;
//                int nv2 = codeSaisieNV152;
//                int nv3 = codeSaisieNV153;
//                int nv4 = codeSaisieNV154;
//
//                int codeSaisie154 = nv1 + nv2 + nv3 + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie154);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV151_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 15  11");
//            }
//
//            /// parent 16 
//            CompteurClassement CostCenterNV16_1 = compteurClassementService.findOne("CostCenterNV16_1");
//            Integer codeSaisieNV161 = CostCenterNV16_1.getSuffixe();
//            CompteurClassement CostCenterNV161_2 = compteurClassementService.findOne("CostCenterNV16_2");
//            Integer codeSaisieNV162 = CostCenterNV161_2.getSuffixe();
//            CompteurClassement CostCenterNV161_3 = compteurClassementService.findOne("CostCenterNV16_3");
//            Integer codeSaisieNV163 = CostCenterNV161_3.getSuffixe();
//            CompteurClassement CostCenterNV161_4 = compteurClassementService.findOne("CostCenterNV16_4");
//            Integer codeSaisieNV164 = CostCenterNV161_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 16) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV161);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV16_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 16 11");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codePere == 16) {
//
//                int nv1 = codeSaisieNV161;
//                int nv2 = codeSaisieNV162;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV162 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV162);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV161_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 16 11");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codePere == 16) {
//
//                int nv1 = codeSaisieNV161;
//                int nv2 = codeSaisieNV162;
//                int nv3 = codeSaisieNV163;
//
//                int codeSaisie163 = nv1 + nv2 + nv3;
//
//                Integer CodeSaisieFinNV163 = codeSaisie163;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV163);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV161_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 16 11");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codePere == 16) {
//
//                int nv1 = codeSaisieNV161;
//                int nv2 = codeSaisieNV162;
//                int nv3 = codeSaisieNV163;
//                int nv4 = codeSaisieNV164;
//
//                int codeSaisie164 = nv1 + nv2 + nv3 + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie164);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV161_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 16 11");
//            }
//
//            /// parent 17 
//            CompteurClassement CostCenterNV17_1 = compteurClassementService.findOne("CostCenterNV17_1");
//            Integer codeSaisieNV171 = CostCenterNV17_1.getSuffixe();
//            CompteurClassement CostCenterNV171_2 = compteurClassementService.findOne("CostCenterNV17_2");
//            Integer codeSaisieNV172 = CostCenterNV171_2.getSuffixe();
//            CompteurClassement CostCenterNV171_3 = compteurClassementService.findOne("CostCenterNV17_3");
//            Integer codeSaisieNV173 = CostCenterNV171_3.getSuffixe();
//            CompteurClassement CostCenterNV171_4 = compteurClassementService.findOne("CostCenterNV17_4");
//            Integer codeSaisieNV174 = CostCenterNV171_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codePere == 17) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV171);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV17_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 17 11");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codePere == 17) {
//
//                int nv1 = codeSaisieNV171;
//                int nv2 = codeSaisieNV172;
//                int codeSaisie172 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV172 = codeSaisie172;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV172);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV171_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 17 11");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codePere == 17) {
//
//                int nv1 = codeSaisieNV171;
//                int nv2 = codeSaisieNV172;
//                int nv3 = codeSaisieNV173;
//
//                int codeSaisie173 = nv1 + nv2 + nv3;
//
//                Integer CodeSaisieFinNV173 = codeSaisie173;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV173);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV171_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 17 11");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codeParent2 == 17) {
//
//                int nv1 = codeSaisieNV171;
//                int nv2 = codeSaisieNV172;
//                int nv3 = codeSaisieNV173;
//                int nv4 = codeSaisieNV174;
//
//                int codeSaisie174 = nv1 + nv2 + nv3 + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie174);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV171_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 17 11");
//            }
//
//        } else {
//// code pere diff 1 ==> principal
//            System.out.println("pere 2 save " + codeParent2);
//
//            CompteurClassement CostCenterNV1_1 = compteurClassementService.findOne("CostCenterNV2_1");
//            Integer codeSaisieNV1 = CostCenterNV1_1.getSuffixe();
//            CompteurClassement CostCenterNV1_2 = compteurClassementService.findOne("CostCenterNV2_2");
//            Integer codeSaisieNV2 = CostCenterNV1_2.getSuffixe();
//            CompteurClassement CostCenterNV1_3 = compteurClassementService.findOne("CostCenterNV2_3");
//            Integer codeSaisieNV3 = CostCenterNV1_3.getSuffixe();
//            CompteurClassement CostCenterNV1_4 = compteurClassementService.findOne("CostCenterNV2_4");
//            Integer codeSaisieNV4 = CostCenterNV1_4.getSuffixe();
//
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 2) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV1);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV1_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 2  22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codeParent2 == 2) {
//
//                int nv1 = codeSaisieNV1;
//                int nv2 = codeSaisieNV2;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV2 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV2);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV1_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 2 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codeParent2 == 2) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//
//                int nv3 = codeSaisieNV3;
//
//                int codeSaisie3 = tempclass + nv3;
//
//                Integer CodeSaisieFinNV3 = codeSaisie3;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV3);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV1_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 2  22");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codeParent2 == 2) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv4 = codeSaisieNV4;
//
//                int codeSaisie4 = tempclass + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie4);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV1_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 2 22");
//            }
//
//            /// parent 3 
//            CompteurClassement CostCenterNV3_1 = compteurClassementService.findOne("CostCenterNV3_1");
//            Integer codeSaisieNV31 = CostCenterNV3_1.getSuffixe();
//            CompteurClassement CostCenterNV31_2 = compteurClassementService.findOne("CostCenterNV3_2");
//            Integer codeSaisieNV32 = CostCenterNV31_2.getSuffixe();
//            CompteurClassement CostCenterNV31_3 = compteurClassementService.findOne("CostCenterNV3_3");
//            Integer codeSaisieNV33 = CostCenterNV31_3.getSuffixe();
//            CompteurClassement CostCenterNV31_4 = compteurClassementService.findOne("CostCenterNV3_4");
//            Integer codeSaisieNV34 = CostCenterNV31_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 3) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV31);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV1_1);
//
//                System.out.println("getNiveau() == 1 && codeParent2 == 3 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codeParent2 == 3) {
//
//                int nv1 = codeSaisieNV31;
//                int nv2 = codeSaisieNV32;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV2 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV2);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV31_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 3 22");
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codeParent2 == 3) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv3 = codeSaisieNV33;
//
//                int codeSaisie3 = tempclass + nv3;
//
//                Integer CodeSaisieFinNV33 = codeSaisie3;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV33);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV31_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 3 22");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codeParent2 == 3) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv4 = codeSaisieNV34;
//
//                int codeSaisie4 = tempclass + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie4);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV31_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 3 22");
//            }
//
//            /// parent 15 
//            CompteurClassement CostCenterNV15_1 = compteurClassementService.findOne("CostCenterNV15_1");
//            Integer codeSaisieNV151 = CostCenterNV15_1.getSuffixe();
//            CompteurClassement CostCenterNV151_2 = compteurClassementService.findOne("CostCenterNV15_2");
//            Integer codeSaisieNV152 = CostCenterNV151_2.getSuffixe();
//            CompteurClassement CostCenterNV151_3 = compteurClassementService.findOne("CostCenterNV15_3");
//            Integer codeSaisieNV153 = CostCenterNV151_3.getSuffixe();
//            CompteurClassement CostCenterNV151_4 = compteurClassementService.findOne("CostCenterNV15_4");
//            Integer codeSaisieNV154 = CostCenterNV151_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 15) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV151);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV15_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 15 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codeParent2 == 15) {
//
//                int nv1 = codeSaisieNV151;
//                int nv2 = codeSaisieNV152;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV152 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV152);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV151_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 15 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codeParent2 == 15) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv3 = codeSaisieNV153;
//
//                int codeSaisie153 = tempclass + nv3;
//
//                Integer CodeSaisieFinNV153 = codeSaisie153;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV153);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV151_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 15 22");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codeParent2 == 15) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv4 = codeSaisieNV154;
//
//                int codeSaisie154 = tempclass + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie154);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV151_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 15 22");
//            }
//
//            /// parent 16 
//            CompteurClassement CostCenterNV16_1 = compteurClassementService.findOne("CostCenterNV16_1");
//            Integer codeSaisieNV161 = CostCenterNV16_1.getSuffixe();
//            CompteurClassement CostCenterNV161_2 = compteurClassementService.findOne("CostCenterNV16_2");
//            Integer codeSaisieNV162 = CostCenterNV161_2.getSuffixe();
//            CompteurClassement CostCenterNV161_3 = compteurClassementService.findOne("CostCenterNV16_3");
//            Integer codeSaisieNV163 = CostCenterNV161_3.getSuffixe();
//            CompteurClassement CostCenterNV161_4 = compteurClassementService.findOne("CostCenterNV16_4");
//            Integer codeSaisieNV164 = CostCenterNV161_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 16) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV161);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV16_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 16 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codeParent2 == 16) {
//
//                int nv1 = codeSaisieNV161;
//                int nv2 = codeSaisieNV162;
//                int codeSaisie2 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV162 = codeSaisie2;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV162);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV161_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 16 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codeParent2 == 16) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv3 = codeSaisieNV163;
//
//                int codeSaisie163 = tempclass + nv3;
//
//                Integer CodeSaisieFinNV163 = codeSaisie163;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV163);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV161_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 16 22");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codeParent2 == 16) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv4 = codeSaisieNV164;
//
//                int codeSaisie164 = tempclass + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie164);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV161_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 16 22");
//            }
//
//            /// parent 17 
//            CompteurClassement CostCenterNV17_1 = compteurClassementService.findOne("CostCenterNV17_1");
//            Integer codeSaisieNV171 = CostCenterNV17_1.getSuffixe();
//            CompteurClassement CostCenterNV171_2 = compteurClassementService.findOne("CostCenterNV17_2");
//            Integer codeSaisieNV172 = CostCenterNV171_2.getSuffixe();
//            CompteurClassement CostCenterNV171_3 = compteurClassementService.findOne("CostCenterNV17_3");
//            Integer codeSaisieNV173 = CostCenterNV171_3.getSuffixe();
//            CompteurClassement CostCenterNV171_4 = compteurClassementService.findOne("CostCenterNV17_4");
//            Integer codeSaisieNV174 = CostCenterNV171_4.getSuffixe();
//            if (costprofitcentreDTO.getNiveau() == 1 && codeParent2 == 17) {
//
//                costprofitcentreDTO.setClassement(codeSaisieNV171);
//                compteurClassementService.incrementeSuffixe10000000(CostCenterNV17_1);
//                System.out.println("getNiveau() == 1 && codeParent2 == 17 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 2 && codeParent2 == 17) {
//
//                int nv1 = codeSaisieNV171;
//                int nv2 = codeSaisieNV172;
//                int codeSaisie172 = nv1 + nv2;
//
//                Integer CodeSaisieFinNV172 = codeSaisie172;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV172);
//                compteurClassementService.incrementeSuffixe10000(CostCenterNV171_2);
//                System.out.println("getNiveau() == 2 && codeParent2 == 17 22");
//
//            } else if (costprofitcentreDTO.getNiveau() == 3 && codeParent2 == 17) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv3 = codeSaisieNV173;
//
//                int codeSaisie173 = tempclass + nv3;
//
//                Integer CodeSaisieFinNV173 = codeSaisie173;
//                costprofitcentreDTO.setClassement(CodeSaisieFinNV173);
//                compteurClassementService.incrementeSuffixe100(CostCenterNV171_3);
//                System.out.println("getNiveau() == 3 && codeParent2 == 17 22");
//            } else if (costprofitcentreDTO.getNiveau() == 4 && codeParent2 == 17) {
//
//                int tempclass = costprofitcentreDTO.getClassement();
//                int nv4 = codeSaisieNV174;
//
//                int codeSaisie174 = tempclass + nv4;
//                costprofitcentreDTO.setClassement(codeSaisie174);
//                compteurClassementService.incrementeSuffixe1(CostCenterNV171_4);
//                System.out.println("getNiveau() == 4 && codeParent2 == 17 22");
//            }
//
//        }
//
//        Preconditions.checkArgument(codeParent2 != null, "The parent cost profit centre code cannot be null.");
//        CostProfitCentre costprofitcentre = CostProfitCentreFactory.costProfitCentreDTOToCostProfitCentreComplex(costprofitcentreDTO, new CostProfitCentre(), costprofitcentreRepository);
//        costprofitcentre.setCode(null);
//        costprofitcentre.setUserCreate(Helper.getUserAuthenticated());
//        costprofitcentre.setDateCreate(new Date());
//        costprofitcentre.setCodeSaisie(costprofitcentreDTO.getCodeSaisie());
//
////        costprofitcentre.setClassement(costprofitcentreDTO.getClassement());
////        System.out.println("com.DevPointSystem.MedLite.Parametrage.service.CostProfitCentreService.save()" + costprofitcentreDTO.getClassement());
//        costprofitcentre = costprofitcentreRepository.save(costprofitcentre);
//
//        CostProfitCentreDTO resultDTO = CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(costprofitcentre);
//        return resultDTO;
//    }

    /**
     * Update a costprofitcentreDTO.
     *
     * @param costprofitcentreDTO
     * @return the updated entity
     */
    public CostProfitCentreDTO update(CostProfitCentreDTO costprofitcentreDTO) {
        log.debug("Request to update CostProfitCentre: {}", costprofitcentreDTO);
        CostProfitCentre costprofitcentre = costprofitcentreRepository.getReferenceById(costprofitcentreDTO.getCode());
        Preconditions.checkArgument(costprofitcentre.getCode() != null, "costprofitcentre.NotFound");
//        Preconditions.checkArgument(!costprofitcentre.getCode().equals(costprofitcentreDTO.getParent().getCode()), "costprofitcentre.verif-parent-node");
        costprofitcentre = CostProfitCentreFactory.costProfitCentreDTOToCostProfitCentreForUpdate(costprofitcentreDTO, costprofitcentre, costprofitcentreRepository);
        costprofitcentre = costprofitcentreRepository.save(costprofitcentre);
        CostProfitCentreDTO resultDTO = CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(costprofitcentre);
        return resultDTO;
    }

    /**
     * Get one costprofitcentreDTO by id.
     *
     * @param id the id of the entity
     * @return the entity DTO
     */
    @Transactional(
            readOnly = true
    )
    public CostProfitCentreDTO findOne(Integer id) {
        log.debug("Request to get CostProfitCentre: {}", id);
        CostProfitCentre costprofitcentre = costprofitcentreRepository.getReferenceById(id);
        Preconditions.checkArgument(costprofitcentre != null, "costprofitcentre.NotFound");
        CostProfitCentreDTO dto = CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(costprofitcentre);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public CostProfitCentre findCostProfitCentre(Integer id) {
        log.debug("Request to get CostProfitCentre: {}", id);
        CostProfitCentre costprofitcentre = costprofitcentreRepository.getReferenceById(id);
        Preconditions.checkArgument(costprofitcentre != null, "costprofitcentre.NotFound");
        return costprofitcentre;
    }

    @Transactional(
            readOnly = true
    ) 
    public Collection<CostProfitCentreDTO> findAll() {
        log.debug("Request to get All CostProfitCentres");
        List<CostProfitCentre> result = costprofitcentreRepository.findAll(Sort.by(ENTITY_NAME).ascending());   
        
 

        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findByActifIn(Collection<Boolean> actif) {
        log.debug("Request to get CostProfitCentres by actif ");
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByActifIn(actif);
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    public void delete(Integer id) {
        log.debug("Request to delete CostProfitCentre: {}", id);
        Preconditions.checkArgument(costprofitcentreRepository.existsById(id) == true, "costprofitcentre.NotFound");
        costprofitcentreRepository.deleteById(id);
    }

    /**
     * Get find Last CodeSaisie.
     *
     * @return
     */
//    public String findLastCodeSaisie() {
//        return compteurService.findOne("codeSaisieCostProfitCentre").getValeur();
//    }
    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findByDetail(Collection<Boolean> detail) {
        log.debug("Request to findFilsActif by code pere :{} ", detail);
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByDetailIn(detail);
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    /**
     * Get costprofitcentres by actif .
     *
     * @param codes
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findByCodesIn(Collection<Integer> codes) {
        log.debug("Request to get CostProfitCentres by codes {}", (Object) codes);
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByCodeIn(Helper.removeNullValueFromCollection(codes));
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    /**
     * Get costprofitcentres by actif .
     *
     * @param actif
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentre> findByActifInForEdition(Collection<Boolean> actif) {
        log.debug("Request to get findByActifInForedition by actif in ");
        return costprofitcentreRepository.findByActifIn(actif);
    }

    @Transactional(readOnly = true)
    public Collection<CostProfitCentreDTO> findByCodeSaisieBetween(String codeDu, String codeAu) {
        log.debug("Request to findByCodeBetween :{} and :{}  ", codeDu, codeAu);
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByActifAndCodeSaisieBetween(true, codeDu, codeAu);
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentre> findByActif(Boolean actif) {
        log.debug("Request to get CostProfitCentres by actif ");
        return costprofitcentreRepository.findByActif(actif);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentre> findAllCostProfitCentre() {
        log.debug("Request to get All CostProfitCentres");
        return costprofitcentreRepository.findAll();
    }
    
    
        public CostProfitCentreDTO save(CostProfitCentreDTO dto) {
        CostProfitCentre domaine = CostProfitCentreFactory.costProfitCentreDTOToCostProfitCentre(dto, new CostProfitCentre());
  
        domaine = costprofitcentreRepository.save(domaine);

        return CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(domaine);
    }

}
