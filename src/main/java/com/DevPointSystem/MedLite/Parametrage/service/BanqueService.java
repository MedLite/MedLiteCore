/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Banque;
import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.dto.BanqueDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.BanqueFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.BanqueRepo;
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
public class BanqueService {

    private final BanqueRepo banqueRepo;
    private final CompteurService compteurService;

    public BanqueService(BanqueRepo banqueRepo, CompteurService compteurService) {
        this.banqueRepo = banqueRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<BanqueDTO> findAllBanque() {
        return BanqueFactory.listBanqueToBanqueDTOs(banqueRepo.findAll());

    }

    @Transactional(readOnly = true)
    public BanqueDTO findOne(Integer code) {
        Banque domaine = banqueRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.BanqueNotFound");
        return BanqueFactory.banqueToBanqueDTO(domaine);
    }

//
    public BanqueDTO save(BanqueDTO dto) {
        Banque domaine = BanqueFactory.banqueDTOToBanque(dto, new Banque());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieBQ");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = banqueRepo.save(domaine);
        return BanqueFactory.banqueToBanqueDTO(domaine);
    }

    public Banque update(BanqueDTO dto) {
        Banque domaine = banqueRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.BanqueNotFound");
        dto.setCode(domaine.getCode());
        BanqueFactory.banqueDTOToBanque(dto, domaine);
        return banqueRepo.save(domaine);
    }

    public void deleteBanque(Integer code) {
        Preconditions.checkArgument(banqueRepo.existsById(code), "error.BanqueNotFound");
        banqueRepo.deleteById(code);
    }
}
