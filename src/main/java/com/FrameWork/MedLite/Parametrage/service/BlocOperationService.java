/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.BlocOperation;
import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.dto.BlocOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.FamilleOperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.BlocOperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.FamilleOperationFactory;
import com.FrameWork.MedLite.Parametrage.repository.BlocOperationRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
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
public class BlocOperationService {

    private final BlocOperationRepo blocOperationRepo;
    
    private final CompteurService compteurService;

    public BlocOperationService(BlocOperationRepo blocOperationRepo, CompteurService compteurService) {
        this.blocOperationRepo = blocOperationRepo;
        this.compteurService = compteurService;
    }

    

    @Transactional(readOnly = true)
    public List<BlocOperationDTO> findAllBlocOperation() {
        return BlocOperationFactory.listBlocOperationToBlocOperationDTOs(blocOperationRepo.findAll());

    }
    
    @Transactional(readOnly = true)
    public List<BlocOperationDTO> findAllBlocOperationByActif(Boolean actif) {
        return BlocOperationFactory.listBlocOperationToBlocOperationDTOs(blocOperationRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public BlocOperationDTO findOne(Integer code) {
        BlocOperation domaine = blocOperationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.BlocOperationNotFound");
        return BlocOperationFactory.blocOperationToBlocOperationDTO(domaine);
    }

    public BlocOperationDTO save(BlocOperationDTO dto) {
        BlocOperation domaine = BlocOperationFactory.blocOperationDTOToBlocOperation(dto, new BlocOperation());
       Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieBlocOperation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine.setDateCreate(new Date());  
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = blocOperationRepo.save(domaine);

        return BlocOperationFactory.blocOperationToBlocOperationDTO(domaine);
    }

    public BlocOperation update(BlocOperationDTO dto) {
        BlocOperation domaine = blocOperationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.BlocOperationNotFound");
        dto.setCode(domaine.getCode());
        BlocOperationFactory.blocOperationDTOToBlocOperation(dto, domaine);
        return blocOperationRepo.save(domaine);
    }

    public void deleteBlocOperation(Integer code) {
        Preconditions.checkArgument(blocOperationRepo.existsById(code), "error.BlocOperationNotFound");
        blocOperationRepo.deleteById(code);
    }
}
