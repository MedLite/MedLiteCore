/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Nationalite;
import com.DevPointSystem.MedLite.Parametrage.dto.NationaliteDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.NationaliteFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.NationaliteRepo;
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
public class NationaliteService {

    private final NationaliteRepo nationaliteRepo;

    public NationaliteService(NationaliteRepo nationaliteRepo) {
        this.nationaliteRepo = nationaliteRepo;
    }

    @Transactional(readOnly = true)
    public List<NationaliteDTO> findAllNationalite() {
        return NationaliteFactory.listNationaliteToNationaliteDTOs(nationaliteRepo.findAll());

    }

    @Transactional(readOnly = true)
    public NationaliteDTO findOne(Integer code) {
        Nationalite domaine = nationaliteRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.NationaliteNotFound");
        return NationaliteFactory.nationaliteToNationaliteDTO(domaine);
    }

//
    public NationaliteDTO save(NationaliteDTO dto) {
        Nationalite domaine = NationaliteFactory.nationaliteDTOToNationalite(dto, new Nationalite());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = nationaliteRepo.save(domaine);
        return NationaliteFactory.nationaliteToNationaliteDTO(domaine);
    }

    public Nationalite update(NationaliteDTO dto) {
        Nationalite domaine = nationaliteRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.NationaliteNotFound");
        dto.setCode(domaine.getCode());
        NationaliteFactory.nationaliteDTOToNationalite(dto, domaine);
        return nationaliteRepo.save(domaine);
    }

    public void deleteNationalite(Integer code) {
        Preconditions.checkArgument(nationaliteRepo.existsById(code), "error.NationaliteNotFound");
        nationaliteRepo.deleteById(code);
    }
}
