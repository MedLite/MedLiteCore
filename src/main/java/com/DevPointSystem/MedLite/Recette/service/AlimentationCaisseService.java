/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Recette.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.factory.CaisseFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.DeviseFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.ModeReglementFactory;
import com.DevPointSystem.MedLite.Parametrage.service.CompteurService;
import com.DevPointSystem.MedLite.Recette.domaine.AlimentationCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.DetailsAlimentationCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.MedLite.Recette.dto.AlimentationCaisseDTO;
import com.DevPointSystem.MedLite.Recette.dto.DetailsAlimentationCaisseDTO;
import com.DevPointSystem.MedLite.Recette.dto.SoldeCaisseDTO;
import com.DevPointSystem.MedLite.Recette.factory.AlimentationCaisseFactory;
import com.DevPointSystem.MedLite.Recette.factory.DetailsAlimentationCaisseFactory;
import com.DevPointSystem.MedLite.Recette.repository.AlimentationCaisseRepo;
import com.DevPointSystem.MedLite.Recette.repository.DetailsAlimentationCaisseRepo;
import com.DevPointSystem.MedLite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.MedLite.Recette.repository.SoldeCaisseRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class AlimentationCaisseService {

    private final AlimentationCaisseRepo alimentationCaisseRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final DetailsAlimentationCaisseRepo detailsAlimentationCaisseRepo;
    private final MouvementCaisseService mouvementCaisseSerivce;

    private final SoldeCaisseRepo soldeCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;

    public AlimentationCaisseService(AlimentationCaisseRepo alimentationCaisseRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, DetailsAlimentationCaisseRepo detailsAlimentationCaisseRepo, MouvementCaisseService mouvementCaisseSerivce, SoldeCaisseRepo soldeCaisseRepo, SoldeCaisseService soldeCaisseService) {
        this.alimentationCaisseRepo = alimentationCaisseRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.detailsAlimentationCaisseRepo = detailsAlimentationCaisseRepo;
        this.mouvementCaisseSerivce = mouvementCaisseSerivce;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
    }

    @Transactional(readOnly = true)
    public List<AlimentationCaisseDTO> findAllAlimentationCaisse() {
        return AlimentationCaisseFactory.listAlimentationCaisseToAlimentationCaisseDTOs(alimentationCaisseRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public AlimentationCaisseDTO findOne(Integer code) {
        AlimentationCaisse domaine = alimentationCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AlimentationCaisseNotFound");
// 

        return AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<AlimentationCaisseDTO> findByCodeCaisse(Collection<Integer> codeCaisse) {
        Collection<AlimentationCaisse> result = alimentationCaisseRepo.findByCodeCaisseIn(Helper.removeNullValueFromCollection(codeCaisse));
        return AlimentationCaisseFactory.CollectionalimentationCaissesToalimentationCaissesDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public Collection<AlimentationCaisseDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<AlimentationCaisse> result = alimentationCaisseRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return AlimentationCaisseFactory.CollectionalimentationCaissesToalimentationCaissesDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<AlimentationCaisseDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return AlimentationCaisseFactory.listAlimentationCaisseToAlimentationCaisseDTOs(alimentationCaisseRepo.findAlimentationCaisseByCodeEtatApprouver(CodeEtatApprouver));
    }

    public AlimentationCaisseDTO save(AlimentationCaisseDTO dto) {

        AlimentationCaisse domaine = AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(new AlimentationCaisse(), dto);
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieAC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = alimentationCaisseRepo.save(domaine);
        return AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
    }
//
//    public AlimentationCaisse update(AlimentationCaisseDTO dTO) { 
//        AlimentationCaisse domaine = alimentationCaisseRepo.getReferenceById(dTO.getCode());
//        Preconditions.checkArgument(true, "error.AlimentationCaisseNotFound");
//    
//        domaine.getDetailsAlimentationCaisses().clear();
//        alimentationCaisseRepo.flush();
//        AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(dTO, domaine);
//        return alimentationCaisseRepo.save(domaine);
//    }
//    

//    public AlimentationCaisseDTO update(AlimentationCaisseDTO dto) {
//
//        AlimentationCaisse inBase = alimentationCaisseRepo.getReferenceById(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.AlimentationCaisseNotFound");
////        inBase.getDetailsAlimentationCaisses().clear();
//
//        detailsAlimentationCaisseRepo.deleteByCodeAlimentationCaisse(inBase.getCode());
//        alimentationCaisseRepo.deleteById(inBase.getCode());
//        inBase = AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(inBase, dto);
//        inBase = alimentationCaisseRepo.save(inBase);
//        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(inBase);
//        return resultDTO;
//    }
    public AlimentationCaisseDTO updateNewWithFlush(AlimentationCaisseDTO dto) {
        AlimentationCaisse inBase = alimentationCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.AlimentationCaisseNotFound");
        detailsAlimentationCaisseRepo.deleteByCodeAlimentationCaisse(dto.getCode());
        inBase = AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(inBase, dto);
        inBase = alimentationCaisseRepo.save(inBase);
        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(inBase);
        return resultDTO;
    }

    public void deleteAlimentationCaisse(Integer code) {
        Preconditions.checkArgument(alimentationCaisseRepo.existsById(code), "error.AlimentationCaisseNotFound");
        alimentationCaisseRepo.deleteById(code);
    }

    public AlimentationCaisseDTO approuveAC(AlimentationCaisseDTO dto) {
        AlimentationCaisse inBase = alimentationCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.AlimentationCaisseNotFound");
        inBase = AlimentationCaisseFactory.ApprouveAlimentationCaisseDTOToAlimentationCaisse(inBase, dto);

        MouvementCaisse mvtCaisse = new MouvementCaisse();
        if (dto.getCodeEtatApprouver() == 2) {
//            mvtCaisse.setCode(dto.getCode());
            mvtCaisse.setCodeSaisie(inBase.getCodeSaisie());
            mvtCaisse.setDebit(inBase.getMontant());
            mvtCaisse.setMntDevise(inBase.getMontantEnDevise());

            mvtCaisse.setCredit(BigDecimal.ZERO);
            mvtCaisse.setDateCreate(inBase.getDateCreate());
            mvtCaisse.setUserCreate(inBase.getUserCreate());
            mvtCaisse.setCodeTier("");
            mvtCaisse.setCodeCaisse(inBase.getCodeCaisse());
            if (mvtCaisse.getCodeCaisse() != null) {
                mvtCaisse.setCaisse(CaisseFactory.createCaisseByCode(inBase.getCodeCaisse()));
            }

//            mvtCaisse.setCodeCaisseTr(0);
            mvtCaisse.setCodeDevise(inBase.getCodeDevise());
            if (mvtCaisse.getCodeDevise() != null) {
                mvtCaisse.setDevise(DeviseFactory.createDeviseByCode(inBase.getCodeDevise()));

            }

            mvtCaisse.setCodeModeReglement(inBase.getCodeModeReglement());
            if (mvtCaisse.getCodeModeReglement() != null) {
                mvtCaisse.setModeReglement(ModeReglementFactory.createModeReglementByCode(inBase.getCodeModeReglement()));

            }
            mvtCaisse = mouvementCaisseRepo.save(mvtCaisse);
        }

        inBase = alimentationCaisseRepo.save(inBase);

        SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
        BigDecimal qteOldDebit = soldeCaisseDTOs.getDebit();
        BigDecimal qteLivree = inBase.getMontant();
        BigDecimal sumQteLivred = qteOldDebit.add(qteLivree);
        soldeCaisseDTOs.setDebit(sumQteLivred);
        soldeCaisseService.updateMontant(soldeCaisseDTOs);
        System.out.println("ok marche");
        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(inBase);
        return resultDTO;
    }

    public AlimentationCaisseDTO CancelapprouveAC(AlimentationCaisseDTO dto) {
        AlimentationCaisse inBase = alimentationCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.AlimentationCaisseNotFound");
        inBase = AlimentationCaisseFactory.CancelAlimentationCaisseDTOToAlimentationCaisse(inBase, dto);

        SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
        BigDecimal mntOld = soldeCaisseDTOs.getDebit();
        BigDecimal mntNew = inBase.getMontant();
        BigDecimal sumMnt = mntOld.subtract(mntNew);
        soldeCaisseDTOs.setDebit(sumMnt);
        soldeCaisseService.updateMontant(soldeCaisseDTOs);

        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());

        inBase = alimentationCaisseRepo.save(inBase);
        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(inBase);
        return resultDTO;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsAlimentationCaisseDTO> findOneWithDetails(Integer code) {
        Collection<DetailsAlimentationCaisse> domaine = detailsAlimentationCaisseRepo.findByDetailsAlimentationCaissePK_codeAlimentationCaisse(code);
        return DetailsAlimentationCaisseFactory.detailsAlimentationCaisseTodetailsAlimentationCaisseDTOCollections(domaine);
    }

}
