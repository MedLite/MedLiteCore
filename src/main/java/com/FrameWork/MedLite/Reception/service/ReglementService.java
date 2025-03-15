/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.Reception.domaine.Reglement;
import com.FrameWork.MedLite.Reception.dto.ReglementDTO;
import com.FrameWork.MedLite.Reception.factory.ReglementFactory;
import com.FrameWork.MedLite.Reception.repository.ReglementRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ReglementService {

    private final ReglementRepo reglementRepo;
    private final CompteurService compteurService;

    public ReglementService(ReglementRepo reglementRepo, CompteurService compteurService) {
        this.reglementRepo = reglementRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<ReglementDTO> findAllReglement() {
        return ReglementFactory.listReglementToReglementDTOs(reglementRepo.findAll());

    }

    @Transactional(readOnly = true)
    public ReglementDTO findOne(Integer code) {
        Reglement domaine = reglementRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.ReglementNotFound");
        return ReglementFactory.reglementToReglementDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<ReglementDTO> findAllReglementByCodeAdmission(String codeAdmission) {
        return ReglementFactory.listReglementToReglementDTOs(reglementRepo.findByCodeAdmission(codeAdmission));

    }

    @Transactional(readOnly = true)
    public List<ReglementDTO> findAllReglementByCaissier(String codeCaissier) {
        return ReglementFactory.listReglementToReglementDTOs(reglementRepo.findByCaissier(codeCaissier));

    }

    @Transactional(readOnly = true)
    public Collection<ReglementDTO> findAllReglementByDateReglementBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
        return ReglementFactory.collectionReglementToReglementDTOs(
                reglementRepo.findAllByDateReglementBetween(dateDebut, dateFin)
        );

    }

    public ReglementDTO save(ReglementDTO dto) {
        Reglement domaine = ReglementFactory.reglementDTOToReglement(dto, new Reglement());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        Compteur CompteurCodeSaisie = compteurService.findOne("CompteurReglement");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = reglementRepo.save(domaine);
        System.out.println("com.FrameWork.MedLite.Reception.service.ReglementService.save()");
        return ReglementFactory.reglementToReglementDTO(domaine);
    }

    public List<ReglementDTO> saveList(List<ReglementDTO> reglementDTOs) {
        List<ReglementDTO> savedDTOs = new ArrayList<>();

        for (ReglementDTO dto : reglementDTOs) {
            // Validate the DTO before saving (add validation here)
            if (this.isValidDTO(dto)) { //New validation method
                Reglement domaine = ReglementFactory.reglementDTOToReglement(dto, new Reglement());
                domaine.setDateCreate(new Date());
                domaine.setUserCreate(Helper.getUserAuthenticated());
                domaine = reglementRepo.save(domaine);
                savedDTOs.add(ReglementFactory.reglementToReglementDTO(domaine));
            } else {
                // Handle invalid DTO (log error, maybe throw an exception, or skip)
                System.err.println("Invalid ReglementDTO: " + dto);
            }
        }

        return savedDTOs;
    }

    private boolean isValidDTO(ReglementDTO dto) {
        //Check for null or empty values for essential fields
        if (dto == null || dto.getCodeAdmission() == null || dto.getCaissier()== null
                || dto.getDateReglement() == null) {
            return false;
        }

        //Add more validation as needed (e.g., date format, range checks, etc.)
        return true;
    }

    public Reglement update(ReglementDTO dto) {
        Reglement domaine = reglementRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.ReglementNotFound");
        dto.setCode(domaine.getCode());
        ReglementFactory.reglementDTOToReglement(dto, domaine);
        return reglementRepo.save(domaine);
    }

    public List<Reglement> updateList(List<ReglementDTO> planningDTOs) {
        List<Reglement> savedDTOs = new ArrayList<>();

        for (ReglementDTO dto : planningDTOs) {
            // Validate the DTO before saving (add validation here)
            if (this.isValidDTO(dto)) { //New validation method
                Reglement domaine = reglementRepo.findByCode(planningDTOs.iterator().next().getCode());
                dto.setCode(domaine.getCode());
                domaine = reglementRepo.save(domaine);
                savedDTOs.add(ReglementFactory.reglementDTOToReglement(dto, domaine));
            } else {
                // Handle invalid DTO (log error, maybe throw an exception, or skip)
                System.err.println("Invalid ReglementDTO: " + dto);
            }
        }

        return savedDTOs;
    }

    public void deleteReglement(Integer code) {
        Preconditions.checkArgument(reglementRepo.existsById(code), "error.ReglementNotFound");
        reglementRepo.deleteById(code);
    }
}
