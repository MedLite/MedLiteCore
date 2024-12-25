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
import com.DevPointSystem.MedLite.Recette.domaine.TransfertCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.MouvementCaisse;
import com.DevPointSystem.MedLite.Recette.domaine.SoldeCaisse;
import com.DevPointSystem.MedLite.Recette.dto.TransfertCaisseDTO;
import com.DevPointSystem.MedLite.Recette.dto.SoldeCaisseDTO;
import com.DevPointSystem.MedLite.Recette.factory.TransfertCaisseFactory;
import com.DevPointSystem.MedLite.Recette.repository.TransfertCaisseRepo;
import com.DevPointSystem.MedLite.Recette.repository.MouvementCaisseRepo;
import com.DevPointSystem.MedLite.Recette.repository.SoldeCaisseRepo;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class TransfertCaisseService {

    private final TransfertCaisseRepo transfertCaisseRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final MouvementCaisseService mouvementCaisseSerivce;

    private final SoldeCaisseRepo soldeCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;

    public TransfertCaisseService(TransfertCaisseRepo transfertCaisseRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, MouvementCaisseService mouvementCaisseSerivce, SoldeCaisseRepo soldeCaisseRepo, SoldeCaisseService soldeCaisseService) {
        this.transfertCaisseRepo = transfertCaisseRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.mouvementCaisseSerivce = mouvementCaisseSerivce;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
    }

    @Transactional(readOnly = true)
    public List<TransfertCaisseDTO> findAllTransfertCaisse() {
        return TransfertCaisseFactory.listTransfertCaisseToTransfertCaisseDTOs(transfertCaisseRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public TransfertCaisseDTO findOne(Integer code) {
        TransfertCaisse domaine = transfertCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.TransfertCaisseNotFound");
        return TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<TransfertCaisseDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return TransfertCaisseFactory.listTransfertCaisseToTransfertCaisseDTOs(transfertCaisseRepo.findTransfertCaisseByCodeEtatApprouver(CodeEtatApprouver));
    }

    public TransfertCaisseDTO save(TransfertCaisseDTO dto) {

        //control solde caisse 
        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisseTr());

        BigDecimal mnt = dto.getMontant();
        BigDecimal debit = soldeCaisse.getDebit();

        BigDecimal credit = soldeCaisse.getCredit();
        BigDecimal DebitCredit = debit.subtract(credit);

        BigDecimal soldeTransfered = DebitCredit.subtract(mnt);
        if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("error.SoldeCaisseNegative");

        }
        TransfertCaisse domaine = TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(new TransfertCaisse(), dto);

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieTR");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = transfertCaisseRepo.save(domaine);

        return TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
    }
//
//    public TransfertCaisse update(TransfertCaisseDTO dTO) { 
//        TransfertCaisse domaine = transfertCaisseRepo.findByCode(dTO.getCode());
//        Preconditions.checkArgument(true, "error.TransfertCaisseNotFound");
//    
//        domaine.getDetailsTransfertCaisses().clear();
//        transfertCaisseRepo.flush();
//        TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(dTO, domaine);
//        return transfertCaisseRepo.save(domaine);
//    }
//    

//    public TransfertCaisseDTO update(TransfertCaisseDTO dto) {
//
//        TransfertCaisse inBase = transfertCaisseRepo.findByCode(dto.getCode());
//        Preconditions.checkArgument(inBase != null, "error.TransfertCaisseNotFound");
////        inBase.getDetailsTransfertCaisses().clear();
//
//        detailsTransfertCaisseRepo.deleteByCodeTransfertCaisse(inBase.getCode());
//        transfertCaisseRepo.deleteById(inBase.getCode());
//        inBase = TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(inBase, dto);
//        inBase = transfertCaisseRepo.save(inBase);
//        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(inBase);
//        return resultDTO;
//    }
    public TransfertCaisseDTO updateNewWithFlush(TransfertCaisseDTO dto) {

        //control solde caisse 
        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisseTr());

        BigDecimal mnt = dto.getMontant();
        BigDecimal debit = soldeCaisse.getDebit();

        BigDecimal credit = soldeCaisse.getCredit();
        BigDecimal DebitCredit = debit.subtract(credit);

        BigDecimal soldeTransfered = DebitCredit.subtract(mnt);
        if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("error.SoldeCaisseNegative");

        }

        TransfertCaisse inBase = transfertCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.TransfertCaisseNotFound");
        inBase = TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(inBase, dto);
        inBase = transfertCaisseRepo.save(inBase);
        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(inBase);
        return resultDTO;
    }

    public void deleteTransfertCaisse(Integer code) {
        Preconditions.checkArgument(transfertCaisseRepo.existsById(code), "error.TransfertCaisseNotFound");
        transfertCaisseRepo.deleteById(code);
    }

    public TransfertCaisseDTO approuveAC(TransfertCaisseDTO dto) {

        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisseTr());

        BigDecimal mnt = dto.getMontant();

        BigDecimal debit = soldeCaisse.getDebit();

        BigDecimal credit = soldeCaisse.getCredit();
        BigDecimal DebitCredit = debit.subtract(credit);

        BigDecimal soldeTransfered = DebitCredit.subtract(mnt);

        TransfertCaisse inBase = transfertCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.TransfertCaisseNotFound");

        MouvementCaisse mvtCaisse = new MouvementCaisse();
        if (dto.getCodeEtatApprouver() == 2) { 
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
            mvtCaisse.setCodeCaisseTr(inBase.getCodeCaisseTr());

            mvtCaisse.setCodeDevise(inBase.getCodeDevise());
            if (mvtCaisse.getCodeDevise() != null) {
                mvtCaisse.setDevise(DeviseFactory.createDeviseByCode(inBase.getCodeDevise()));
            }

            mvtCaisse.setCodeModeReglement(inBase.getCodeModeReglement());
            if (mvtCaisse.getCodeModeReglement() != null) {
                mvtCaisse.setModeReglement(ModeReglementFactory.createModeReglementByCode(inBase.getCodeModeReglement()));
            }
            mvtCaisse = mouvementCaisseRepo.save(mvtCaisse);
            if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("error.SoldeCaisseNegative");

            }
        }

        /// mvt caisseTR
        MouvementCaisse mvtCaisseTR = new MouvementCaisse();
        if (dto.getCodeEtatApprouver() == 2) {
//            mvtCaisse.setCode(dto.getCode());
            System.out.println(" mvt caisseTR OK");
            mvtCaisseTR.setCodeSaisie(inBase.getCodeSaisie());
            mvtCaisseTR.setDebit(BigDecimal.ZERO);
            mvtCaisseTR.setMntDevise(inBase.getMontantEnDevise());
            mvtCaisseTR.setCredit(inBase.getMontant());
            mvtCaisseTR.setDateCreate(inBase.getDateCreate());
            mvtCaisseTR.setUserCreate(inBase.getUserCreate());
            mvtCaisseTR.setCodeTier("");
            mvtCaisseTR.setCodeCaisse(inBase.getCodeCaisseTr());
            if (mvtCaisseTR.getCodeCaisse() != null) {
                mvtCaisseTR.setCaisse(CaisseFactory.createCaisseByCode(inBase.getCodeCaisseTr()));
            }
            mvtCaisseTR.setCodeDevise(inBase.getCodeDevise());
            if (mvtCaisseTR.getCodeDevise() != null) {
                mvtCaisseTR.setDevise(DeviseFactory.createDeviseByCode(inBase.getCodeDevise()));
            }

            mvtCaisseTR.setCodeModeReglement(inBase.getCodeModeReglement());
            if (mvtCaisseTR.getCodeModeReglement() != null) {
                mvtCaisseTR.setModeReglement(ModeReglementFactory.createModeReglementByCode(inBase.getCodeModeReglement()));
            }
            mvtCaisseTR = mouvementCaisseRepo.save(mvtCaisseTR);
            //debit solde caisse 
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
            BigDecimal qteOldDebit = soldeCaisseDTOs.getDebit();
            BigDecimal qteLivree = inBase.getMontant();
            BigDecimal sumQteLivred = qteOldDebit.add(qteLivree);
            soldeCaisseDTOs.setDebit(sumQteLivred);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            //credit solde caisse tr
            SoldeCaisseDTO soldeCaisseDTOTR = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisseTr());
            BigDecimal qteOldCreditTR = soldeCaisseDTOTR.getCredit();
            BigDecimal qteMnTTR = inBase.getMontant();
            BigDecimal sumCreditTR = qteOldCreditTR.add(qteMnTTR);
            soldeCaisseDTOTR.setCredit(sumCreditTR);
            soldeCaisseService.updateMontant(soldeCaisseDTOTR);
            if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("error.SoldeCaisseNegative");

            }

        }

        Integer oldEtatApprouve = inBase.getCodeEtatApprouver();
        Integer NewEtatApprouve = dto.getCodeEtatApprouver();
        if (oldEtatApprouve == 2 && NewEtatApprouve == 3) {

            //annule operation trans caisse
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
            BigDecimal mntOld = soldeCaisseDTOs.getDebit();
            BigDecimal mntNew = inBase.getMontant();
            BigDecimal sumMnt = mntOld.subtract(mntNew);
            soldeCaisseDTOs.setDebit(sumMnt);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            //annule operation trans caisse TR
            SoldeCaisseDTO soldeCaisseDTOsTR = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisseTr());
            BigDecimal mntOldTR = soldeCaisseDTOsTR.getCredit();
            BigDecimal mntNewTR = inBase.getMontant();
            BigDecimal sumMntTR = mntOldTR.subtract(mntNewTR);
            soldeCaisseDTOsTR.setCredit(sumMntTR);
            soldeCaisseService.updateMontant(soldeCaisseDTOsTR);
            mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
        }

        inBase = TransfertCaisseFactory.ApprouveTransfertCaisseDTOToTransfertCaisse(inBase, dto);
        inBase = transfertCaisseRepo.save(inBase);

        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(inBase);
        return resultDTO;
    }

    public TransfertCaisseDTO CancelapprouveAC(TransfertCaisseDTO dto) {
        TransfertCaisse inBase = transfertCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.TransfertCaisseNotFound");

        Integer oldEtatApprouve = inBase.getCodeEtatApprouver();
        Integer NewEtatApprouve = dto.getCodeEtatApprouver();
        System.out.println("etat approuve old CancelapprouveAC " + oldEtatApprouve + " newww approuve  CancelapprouveAC " + NewEtatApprouve);
//        if (oldEtatApprouve == 3 && NewEtatApprouve == 1 || oldEtatApprouve == 2 && NewEtatApprouve == 1) {  
        if (oldEtatApprouve == 2 && NewEtatApprouve == 1) {

            //annule operation trans caisse
            System.out.println("etat update solde ");
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisse());
            BigDecimal mntOld = soldeCaisseDTOs.getDebit();
            BigDecimal mntNew = inBase.getMontant();
            BigDecimal sumMnt = mntOld.subtract(mntNew);
            soldeCaisseDTOs.setDebit(sumMnt);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            //annule operation trans caisse TR
            SoldeCaisseDTO soldeCaisseDTOsTR = soldeCaisseService.findByCodeCaisse(inBase.getCodeCaisseTr());
            BigDecimal mntOldTR = soldeCaisseDTOsTR.getCredit();
            BigDecimal mntNewTR = inBase.getMontant();
            BigDecimal sumMntTR = mntOldTR.subtract(mntNewTR);
            soldeCaisseDTOsTR.setCredit(sumMntTR);
            soldeCaisseService.updateMontant(soldeCaisseDTOsTR);
        }
        inBase = TransfertCaisseFactory.CancelTransfertCaisseDTOToTransfertCaisse(inBase, dto);
        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());

        inBase = transfertCaisseRepo.save(inBase);
        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(inBase);
        return resultDTO;
    }

}
