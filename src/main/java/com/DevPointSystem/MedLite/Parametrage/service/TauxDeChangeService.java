/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.TauxDeChange;
import com.DevPointSystem.MedLite.Parametrage.dto.DeviseDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.TauxDeChangeDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.TauxDeChangeFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.TauxDeChangeRepo;
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
public class TauxDeChangeService {

    private final TauxDeChangeRepo tauxDeChangeRepo;
    private final DeviseService deviseService;

    public TauxDeChangeService(TauxDeChangeRepo tauxDeChangeRepo, DeviseService deviseService) {
        this.tauxDeChangeRepo = tauxDeChangeRepo;
        this.deviseService = deviseService;
    }

    @Transactional(readOnly = true)
    public List<TauxDeChangeDTO> findAllTauxDeChange() {
        return TauxDeChangeFactory.listTauxDeChangeToTauxDeChangeDTOs(tauxDeChangeRepo.findAll());

    }

    @Transactional(readOnly = true)
    public TauxDeChangeDTO findOne(Integer code) {
        TauxDeChange domaine = tauxDeChangeRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.TauxDeChangeNotFound");
        return TauxDeChangeFactory.tauxDeChangeToTauxDeChangeDTO(domaine);
    }

    @Transactional(readOnly = true)
    public TauxDeChangeDTO findOneByCodeDevise(Integer codeDevise) {
        TauxDeChange domaine = tauxDeChangeRepo.findByCodeDevise(codeDevise);
        Preconditions.checkArgument(domaine.getCode() != null, "error.TauxDeChangeNotFound");
        return TauxDeChangeFactory.tauxDeChangeToTauxDeChangeDTO(domaine);
    }

//
    public TauxDeChangeDTO save(TauxDeChangeDTO dto) {
        TauxDeChange domaine = TauxDeChangeFactory.tauxDeChangeDTOToTauxDeChange(dto, new TauxDeChange());

        DeviseDTO deviseDTOs = deviseService.findOne(dto.getCodeDevise());
        deviseDTOs.setHasTaux(true);
        deviseDTOs.setTauxChange(dto.getTauxChange());
        deviseService.updateHasTaux(deviseDTOs);
        domaine = tauxDeChangeRepo.save(domaine);
        return TauxDeChangeFactory.tauxDeChangeToTauxDeChangeDTO(domaine);
    }

    public TauxDeChange update(TauxDeChangeDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TauxDeChangeNotFound");
        TauxDeChange domaine = tauxDeChangeRepo.getReferenceById(dto.getCode());
        Preconditions.checkArgument(true, "error.TauxDeChangeNotFound");
        dto.setCode(domaine.getCode());

        DeviseDTO deviseDTOs = deviseService.findOne(dto.getCodeDevise());
        deviseDTOs.setHasTaux(true);
        deviseDTOs.setTauxChange(dto.getTauxChange());
        deviseService.updateHasTaux(deviseDTOs);
        domaine = tauxDeChangeRepo.save(domaine);

        TauxDeChangeFactory.tauxDeChangeDTOToTauxDeChange(dto, domaine);
        return tauxDeChangeRepo.save(domaine);
    }

    public void deleteTauxDeChange(Integer code) {
        Preconditions.checkArgument(tauxDeChangeRepo.existsById(code), "error.TauxDeChangeNotFound");
        tauxDeChangeRepo.deleteById(code);
    }
}
