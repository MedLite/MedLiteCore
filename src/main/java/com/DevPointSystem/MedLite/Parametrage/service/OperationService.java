/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsOperation;
import com.DevPointSystem.MedLite.Parametrage.domaine.Operation;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsOperationDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.OperationDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsOperationFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.OperationFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsOperationRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.OperationRepo;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class OperationService {
    private final OperationRepo operationRepo;

    private final CompteurService compteurService;

    private final DetailsOperationRepo detailsOperationRepo;

    public OperationService(OperationRepo operationRepo, CompteurService compteurService, DetailsOperationRepo detailsOperationRepo) {
        this.operationRepo = operationRepo;
        this.compteurService = compteurService;
        this.detailsOperationRepo = detailsOperationRepo;
    }

    @Transactional(readOnly = true)
    public List<OperationDTO> findAllOperation() {
        return OperationFactory.listOperationToOperationDTOs(operationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public OperationDTO findOne(Integer code) {
        Operation domaine = operationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.OperationNotFound");
// 

        return OperationFactory.operationToOperationDTO(domaine);
    }

    public OperationDTO save(OperationDTO dto) {

        Operation domaine = OperationFactory.operationDTOToOperation(dto, new Operation());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieAC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = operationRepo.save(domaine);
        return OperationFactory.operationToOperationDTO(domaine);
    }

    public OperationDTO updateNewWithFlush(OperationDTO dto) {
        Operation inBase = operationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(inBase != null, "error.OperationNotFound");
        detailsOperationRepo.deleteByCodeOperation(dto.getCode());
        inBase = OperationFactory.operationDTOToOperation(dto, inBase);
        inBase = operationRepo.save(inBase);
        OperationDTO resultDTO = OperationFactory.operationToOperationDTO(inBase);
        return resultDTO;
    }

    public void deleteOperation(Integer code) {
        Preconditions.checkArgument(operationRepo.existsById(code), "error.OperationNotFound");
        operationRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsOperationDTO> findOneWithDetails(Integer code) {
        Collection<DetailsOperation> domaine = detailsOperationRepo.findByDetailsOperationPK_codeOperation(code);
        return DetailsOperationFactory.detailsOperationTodetailsOperationDTOCollections(domaine);
    }
}
