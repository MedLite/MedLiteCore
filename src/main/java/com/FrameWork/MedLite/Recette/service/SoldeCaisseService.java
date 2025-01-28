/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Recette.service;

import com.FrameWork.MedLite.Recette.domaine.SoldeCaisse;
import com.FrameWork.MedLite.Recette.dto.SoldeCaisseDTO;
import com.FrameWork.MedLite.Recette.factory.SoldeCaisseFactory;
import com.FrameWork.MedLite.Recette.repository.SoldeCaisseRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class SoldeCaisseService {

    private final SoldeCaisseRepo soldeCaisseRepo;

    public SoldeCaisseService(SoldeCaisseRepo soldeCaisseRepo) {
        this.soldeCaisseRepo = soldeCaisseRepo;
    }

    @Transactional(readOnly = true)
    public List<SoldeCaisseDTO> findAllSoldeCaisse() {
        return SoldeCaisseFactory.listSoldeCaisseToSoldeCaisseDTOs(soldeCaisseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public SoldeCaisseDTO findOne(Integer code) {
        SoldeCaisse domaine = soldeCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.SoldeCaisseNotFound");
        return SoldeCaisseFactory.soldeCaisseToSoldeCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<SoldeCaisseDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<SoldeCaisse> result = soldeCaisseRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return SoldeCaisseFactory.CollectionsoldeCaissesTosoldeCaissesDTOsCollection(result);
    }
 
    @Transactional(readOnly = true)
    public SoldeCaisseDTO findByCodeCaisse(Integer codeCaisse) {
        SoldeCaisse domaine = soldeCaisseRepo.findByCodeCaisse(codeCaisse);
        Preconditions.checkArgument(domaine.getCode() != null, "error.SoldeCaisseNotFound");
        return SoldeCaisseFactory.soldeCaisseToSoldeCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<SoldeCaisseDTO> findByCodeDeviseAndCode(Integer codeDevise, Integer codeCaisse) {
        List<SoldeCaisse> result = soldeCaisseRepo.findByCodeDeviseAndCodeCaisse(codeDevise, codeCaisse);
        return SoldeCaisseFactory.listSoldeCaisseToSoldeCaisseDTOs(result);
    }

    public SoldeCaisse updateMontant(SoldeCaisseDTO dTO) {
        SoldeCaisse domaine = soldeCaisseRepo.findByCodeCaisse(dTO.getCodeCaisse());
        Preconditions.checkArgument(true, "error.OrdreAchatNotFound");
        dTO.setCode(domaine.getCode());
        SoldeCaisseFactory.SoldeCaisseDTOToSoldeCaisseUpdated(dTO, domaine);
        return soldeCaisseRepo.save(domaine);
    }

    public void deleteSoldeCaisse(Integer code) {
        Preconditions.checkArgument(soldeCaisseRepo.existsById(code), "error.AlimentationCaisseNotFound");
        soldeCaisseRepo.deleteById(code);
    }

}
