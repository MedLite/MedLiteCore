/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Taxe;
import com.DevPointSystem.MedLite.Parametrage.dto.TaxeDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.TaxeFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.TaxeRepo;
import com.DevPointSystem.MedLite.web.Util.Helper;
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
public class TaxeService {

    private final TaxeRepo taxeRepo;

    public TaxeService(TaxeRepo taxeRepo) {
        this.taxeRepo = taxeRepo;
    }

    @Transactional(readOnly = true)
    public List<TaxeDTO> findAllTaxe() {
        return TaxeFactory.listTaxeToTaxeDTOs(taxeRepo.findAll());

    }

    @Transactional(readOnly = true)
    public TaxeDTO findOne(Integer code) {
        Taxe domaine = taxeRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.TaxeNotFound");
        return TaxeFactory.taxeToTaxeDTO(domaine);
    }

//
    public TaxeDTO save(TaxeDTO dto) {
        Taxe domaine = TaxeFactory.taxeDTOToTaxe(dto, new Taxe());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = taxeRepo.save(domaine);
        return TaxeFactory.taxeToTaxeDTO(domaine);
    }

    public Taxe update(TaxeDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TaxeNotFound");
        Taxe domaine = taxeRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(true, "error.TaxeNotFound");
        dto.setCode(domaine.getCode());
        TaxeFactory.taxeDTOToTaxe(dto, domaine);
        return taxeRepo.save(domaine);
    }

    public void deleteTaxe(Integer code) {
        Preconditions.checkArgument(taxeRepo.existsById(code), "error.TaxeNotFound");
        taxeRepo.deleteById(code);
    }

}
