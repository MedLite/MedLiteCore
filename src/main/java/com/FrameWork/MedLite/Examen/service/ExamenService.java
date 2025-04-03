/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Examen.service;

import com.FrameWork.MedLite.Examen.domaine.DetailsExamen;
import com.FrameWork.MedLite.Examen.domaine.Examen;
import com.FrameWork.MedLite.Examen.dto.DetailsExamenDTO;
import com.FrameWork.MedLite.Examen.dto.ExamenDTO;
import com.FrameWork.MedLite.Examen.factory.DetailsExamenFactory;
import com.FrameWork.MedLite.Examen.factory.ExamenFactory;
import com.FrameWork.MedLite.Examen.repository.DetailsExamenRepo;
import com.FrameWork.MedLite.Examen.repository.ExamenRepo;
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.service.CompteurService;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ExamenService {

    private final ExamenRepo examenRepo;
    private final DetailsExamenRepo detailsExamenRepo;

    private final CompteurService compteurService;

    public ExamenService(ExamenRepo examenRepo, DetailsExamenRepo detailsExamenRepo, CompteurService compteurService) {
        this.examenRepo = examenRepo;
        this.detailsExamenRepo = detailsExamenRepo;
        this.compteurService = compteurService;
    }

    

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamen() {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByEtatPaiement(Integer codeEtatPaiement) {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findByCodeEtatPaiement(codeEtatPaiement));
    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByCodeAdmission(Integer codeAdmission) {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findByCodeAdmission(codeAdmission));
    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByCodePatient(Integer codePatient) {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findByCodePatient(codePatient));
    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByEtatPaiementAndCodePatient(Integer codeEtatPaiement, Integer codePatient) {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findByCodeEtatPaiementAndCodePatient(codeEtatPaiement, codePatient));
    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByEtatPaiementAndCodeAdmission(Integer codeEtatPaiement, Integer codeAdmission) {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findByCodeEtatPaiementAndCodeAdmission(codeEtatPaiement, codeAdmission));
    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByTypeExmaneAndCodePatient(String typeExamen, Integer codePatient) {
        return ExamenFactory.listExamenToExamenDTOs(examenRepo.findByTypeExamenAndCodePatient(typeExamen, codePatient));
    }

    @Transactional(readOnly = true)
    public List<ExamenDTO> findAllExamenByTypeExmaneAndCodeAdmission(String typeExamen, Integer codeAdmission) {
        return ExamenFactory.listExamenToExamenDTOsWithDetails(examenRepo.findByTypeExamenAndCodeAdmission(typeExamen, codeAdmission));
    }

    @Transactional(readOnly = true)
    public ExamenDTO findOne(Integer code) {
        Examen domaine = examenRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.ExamenNotFound");
        return ExamenFactory.examenToExamenDTO(domaine);
    }

    public ExamenDTO save(ExamenDTO dto) {
        Examen domaine = ExamenFactory.examenDTOToExamen(dto, new Examen());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CompteurExamen");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = examenRepo.save(domaine);
        return ExamenFactory.examenToExamenDTO(domaine);
    }

    public List<ExamenDTO> saveList(List<ExamenDTO> examenDTOs) {
        List<ExamenDTO> savedDTOs = new ArrayList<>();

        for (ExamenDTO dto : examenDTOs) {
            // Validate the DTO before saving (add validation here)
            if (this.isValidDTO(dto)) { //New validation method
                Examen domaine = ExamenFactory.examenDTOToExamen(dto, new Examen());
                domaine.setDateCreate(new Date());
                domaine.setUserCreate(Helper.getUserAuthenticated());

                Compteur CompteurCodeSaisie = compteurService.findOne("CompteurExamen");
                String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
                domaine.setCodeSaisie(codeSaisieAC);
                compteurService.incrementeSuffixe(CompteurCodeSaisie);

                domaine = examenRepo.save(domaine);
                savedDTOs.add(ExamenFactory.examenToExamenDTO(domaine));
            } else {
                // Handle invalid DTO (log error, maybe throw an exception, or skip)
                System.err.println("Invalid ReglementDTO: " + dto);
            }
        }

        return savedDTOs;
    }

    private boolean isValidDTO(ExamenDTO dto) {
        //Check for null or empty values for essential fields
        if (dto == null || dto.getCodeAdmission() == null || dto.getCodePrestation() == null
                || dto.getCodeNatureAdmission() == null) {
            return false;
        }

        //Add more validation as needed (e.g., date format, range checks, etc.)
        return true;
    }

    public Examen update(ExamenDTO dto) {
        Examen domaine = examenRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.ExamenNotFound");
        dto.setCode(domaine.getCode());
        ExamenFactory.examenDTOToExamen(dto, domaine);
        return examenRepo.save(domaine);
    }

    public void deleteExamen(Integer code) {
        Preconditions.checkArgument(examenRepo.existsById(code), "error.ExamenNotFound");
        examenRepo.deleteById(code);
    }
    
    
      @Transactional(readOnly = true)
    public Collection<DetailsExamenDTO> findOneWithDetails(Integer code) {
        Collection<DetailsExamen> domaine = detailsExamenRepo.findByDetailsExamenPK_CodeExamen(code);
        return DetailsExamenFactory.detailsExamenTodetailsExamenDTOCollections(domaine);
    }
}
