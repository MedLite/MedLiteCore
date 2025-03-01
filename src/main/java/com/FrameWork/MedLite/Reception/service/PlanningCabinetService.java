/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.dto.PrestationMedecinConsultationDTO;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.Parametrage.service.PrestationMedecinConsultationService;
import com.FrameWork.MedLite.Reception.domaine.PlanningCabinet;
import com.FrameWork.MedLite.Reception.dto.PlanningCabinetDTO;
import com.FrameWork.MedLite.Reception.factory.PlanningCabinetFactory;
import com.FrameWork.MedLite.Reception.repository.PlanningCabinetRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class PlanningCabinetService {

    private final PlanningCabinetRepo planningCabinetRepo;
    private final PrestationMedecinConsultationService prestationMedecinConsultationService;

    public PlanningCabinetService(PlanningCabinetRepo planningCabinetRepo, PrestationMedecinConsultationService prestationMedecinConsultationService) {
        this.planningCabinetRepo = planningCabinetRepo;
        this.prestationMedecinConsultationService = prestationMedecinConsultationService;
    }

    

    
    @Transactional(readOnly = true)
    public List<PlanningCabinetDTO> findAllPlanningCabinet() {
        return PlanningCabinetFactory.listPlanningCabinetToPlanningCabinetDTOs(planningCabinetRepo.findAll());

    }

    @Transactional(readOnly = true)
    public PlanningCabinetDTO findOne(Integer code) {
        PlanningCabinet domaine = planningCabinetRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.PlanningCabinetNotFound");
        return PlanningCabinetFactory.planningCabinetToPlanningCabinetDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<PlanningCabinetDTO> findAllPlanningCabinetByActif(Boolean actif) {
        return PlanningCabinetFactory.listPlanningCabinetToPlanningCabinetDTOs(planningCabinetRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public List<PlanningCabinetDTO> findAllPlanningCabinetByCodeMedecin(Integer codeMedecin) {
        return PlanningCabinetFactory.listPlanningCabinetToPlanningCabinetDTOs(planningCabinetRepo.findByCodeMedecin(codeMedecin));

    }

    @Transactional(readOnly = true)
    public Collection<PlanningCabinetDTO> findAllPlanningCabinetByActifAndDateExisteBetween(Boolean actif,
            LocalDate dateDebut,
            LocalDate dateFin) {
        return PlanningCabinetFactory.collectionPlanningCabinetToPlanningCabinetDTOs(
                planningCabinetRepo.findByActifAndDateExisteBetween(actif, dateDebut, dateFin)
        );

    }

//    @Transactional(readOnly = true)
//    public Collection<PlanningCabinetDTO> findAllPlanningCabinetByDateExisteBetween(
//            LocalDate dateDebut,
//            LocalDate dateFin) {
//        return PlanningCabinetFactory.collectionPlanningCabinetToPlanningCabinetDTOs(
//                planningCabinetRepo.findAllByDateExisteBetween(dateDebut, dateFin) //New method in repo
//        );
//    }
    @Transactional(readOnly = true)
    public Collection<PlanningCabinetDTO> findAllPlanningCabinetByDateExisteBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
        Collection<PlanningCabinet> planningCabinets = planningCabinetRepo.findAllByDateExisteBetween(dateDebut, dateFin);

        Collection<PlanningCabinetDTO> planningCabinetDTOs = new ArrayList<>();
        for (PlanningCabinet planningCabinet : planningCabinets) {
            PlanningCabinetDTO dto = PlanningCabinetFactory.planningCabinetToPlanningCabinetDTO(planningCabinet);
            if (dto != null && dto.getMedecinDTO() != null) { //Check for nulls to prevent exceptions
                PrestationMedecinConsultationDTO prestationDTO = prestationMedecinConsultationService.findByCodeMedecin(dto.getMedecinDTO().getCode());
                if (prestationDTO != null) {
                    dto.getMedecinDTO().setPrestationConsultationDTO(prestationDTO);
                }
            }
            planningCabinetDTOs.add(dto);
        }
        return planningCabinetDTOs;
    }

    @Transactional(readOnly = true)
    public List<PlanningCabinetDTO> findAllPlanningCabinetByCodeCabinet(Integer codeCabinet) {
        return PlanningCabinetFactory.listPlanningCabinetToPlanningCabinetDTOs(planningCabinetRepo.findByCodeCabinet(codeCabinet));

    }

    @Transactional(readOnly = true)
    public List<PlanningCabinetDTO> findAllPlanningCabinetByCodeMedecinAndCodeCabinet(Integer codeCabinet, Integer codeMedecin) {
        return PlanningCabinetFactory.listPlanningCabinetToPlanningCabinetDTOs(planningCabinetRepo.findByCodeCabinetAndCodeMedecin(codeCabinet, codeMedecin));

    }

    public PlanningCabinetDTO save(PlanningCabinetDTO dto) {
        PlanningCabinet domaine = PlanningCabinetFactory.planningCabinetDTOToPlanningCabinet(dto, new PlanningCabinet());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = planningCabinetRepo.save(domaine);
        System.out.println("com.FrameWork.MedLite.Reception.service.PlanningCabinetService.save()");
        return PlanningCabinetFactory.planningCabinetToPlanningCabinetDTO(domaine);
    }

    public List<PlanningCabinetDTO> saveList(List<PlanningCabinetDTO> planningDTOs) {
        List<PlanningCabinetDTO> savedDTOs = new ArrayList<>();

        for (PlanningCabinetDTO dto : planningDTOs) {
            // Validate the DTO before saving (add validation here)
            if (this.isValidDTO(dto)) { //New validation method
                PlanningCabinet domaine = PlanningCabinetFactory.planningCabinetDTOToPlanningCabinet(dto, new PlanningCabinet());
                domaine.setDateCreate(new Date());
                domaine.setUserCreate(Helper.getUserAuthenticated());
                domaine = planningCabinetRepo.save(domaine);
                savedDTOs.add(PlanningCabinetFactory.planningCabinetToPlanningCabinetDTO(domaine));
            } else {
                // Handle invalid DTO (log error, maybe throw an exception, or skip)
                System.err.println("Invalid PlanningCabinetDTO: " + dto);
            }
        }

        return savedDTOs;
    }

    private boolean isValidDTO(PlanningCabinetDTO dto) {
        //Check for null or empty values for essential fields
        if (dto == null || dto.getCodeMedecin() == null || dto.getCodeCabinet() == null
                || dto.getDateExiste() == null || dto.getNbrePlaceDispo() == null) {
            return false;
        }

        //Add more validation as needed (e.g., date format, range checks, etc.)
        return true;
    }

    public PlanningCabinet update(PlanningCabinetDTO dto) {
        PlanningCabinet domaine = planningCabinetRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.PlanningCabinetNotFound");
        dto.setCode(domaine.getCode());
        PlanningCabinetFactory.planningCabinetDTOToPlanningCabinet(dto, domaine);
        return planningCabinetRepo.save(domaine);
    }

    public List<PlanningCabinet> updateList(List<PlanningCabinetDTO> planningDTOs) {
        List<PlanningCabinet> savedDTOs = new ArrayList<>();

        for (PlanningCabinetDTO dto : planningDTOs) {
            // Validate the DTO before saving (add validation here)
            if (this.isValidDTO(dto)) { //New validation method
                PlanningCabinet domaine = planningCabinetRepo.findByCode(planningDTOs.iterator().next().getCode());
                dto.setCode(domaine.getCode());
                domaine = planningCabinetRepo.save(domaine);
                savedDTOs.add(PlanningCabinetFactory.planningCabinetDTOToPlanningCabinet(dto, domaine));
            } else {
                // Handle invalid DTO (log error, maybe throw an exception, or skip)
                System.err.println("Invalid PlanningCabinetDTO: " + dto);
            }
        }

        return savedDTOs;
    }

    public void deletePlanningCabinet(Integer code) {
        Preconditions.checkArgument(planningCabinetRepo.existsById(code), "error.PlanningCabinetNotFound");
        planningCabinetRepo.deleteById(code);
    }
}
