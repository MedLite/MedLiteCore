/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Ville;
import com.DevPointSystem.MedLite.Parametrage.dto.VilleDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.VilleFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.VilleRepo;
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
public class VilleService {

    private final VilleRepo villeRepo;

    public VilleService(VilleRepo villeRepo) {
        this.villeRepo = villeRepo;
    }

    @Transactional(readOnly = true)
    public List<VilleDTO> findAllVille() {
        return VilleFactory.listVilleToVilleDTOs(villeRepo.findAll());

    }

    @Transactional(readOnly = true)
    public VilleDTO findOne(Integer code) {
        Ville domaine = villeRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.VilleNotFound");
        return VilleFactory.villeToVilleDTO(domaine);
    }

//
    public VilleDTO save(VilleDTO dto) {
        Ville domaine = VilleFactory.villeDTOToVille(dto, new Ville());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = villeRepo.save(domaine);
        return VilleFactory.villeToVilleDTO(domaine);
    }

    public Ville update(VilleDTO dto) {
        Ville domaine = villeRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.VilleNotFound");
        dto.setCode(domaine.getCode());
        VilleFactory.villeDTOToVille(dto, domaine);
        return villeRepo.save(domaine);
    }

    public void deleteVille(Integer code) {
        Preconditions.checkArgument(villeRepo.existsById(code), "error.VilleNotFound");
        villeRepo.deleteById(code);
    }
}
