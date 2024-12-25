/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

 
import com.DevPointSystem.MedLite.Parametrage.domaine.Soc;
import com.DevPointSystem.MedLite.Parametrage.dto.SocDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.SocFactory;
import com.DevPointSystem.MedLite.web.Util.Helper;
import com.DevPointSystem.MedLite.web.Util.RestPreconditions;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.DevPointSystem.MedLite.Parametrage.repository.SocRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class SocService {

    private final SocRepo societeRepo;

    public SocService(SocRepo societeRepo) {
        this.societeRepo = societeRepo;
    }

  
    @Transactional(readOnly = true)
    public SocDTO findOne(Integer id) {
        Soc societe = societeRepo.findByCode(id);
        RestPreconditions.checkFound(societe, "societe.NotFound");
        SocDTO dto = SocFactory.societeToSocieteDTO(societe, false);
        return dto;
    }

    @Transactional(readOnly = true)
    public Soc findSociete(Integer id) {
        Soc societe = societeRepo.findByCode(id);
        RestPreconditions.checkFound(societe, "societe.NotFound");
        return societe;
    }

//    @ApiOperation("always returns a list of single object, we cannot modify return type since api is used")
    @Transactional(readOnly = true)
    public Collection<SocDTO> findAll(Boolean withoutLogo) {
        Soc societe = societeRepo.findFirstBy();
        SocDTO societeDTO = SocFactory.societeToSocieteDTO(societe, withoutLogo); 
        return (Collection<SocDTO>) Collections.singleton(societeDTO);
    }

    @Transactional(
            readOnly = true
    )
    public SocDTO findFirst() {
        Soc result = societeRepo.findFirstBy();
        return SocFactory.societeToSocieteDTO(result, false);
    }

    @Transactional(
            readOnly = true
    )
    public SocDTO findOne(Integer id, Boolean withoutLogo) {
        Soc societe = societeRepo.findByCode(id);
        RestPreconditions.checkFound(societe, "societe.NotFound");
        SocDTO dto = SocFactory.societeToSocieteDTO(societe, withoutLogo);
        return dto;
    }

    public SocDTO save(SocDTO dTO) throws IOException {
        Soc domaine = SocFactory.societeDTOToSociete(dTO);
        if (!dTO.getImagePath().isEmpty()) {
            domaine.setLogo(Helper.extractBytes(dTO.getImagePath(), "png"));
        }
        domaine = societeRepo.save(domaine);
        SocDTO resultDTO = SocFactory.societeToSocieteDTO(domaine, false);
        return resultDTO;
    }

    public SocDTO update(SocDTO dTO) throws IOException {
        Soc inBase = societeRepo.findByCode(dTO.getCode());
        Preconditions.checkArgument(inBase != null, "Societe does not exist");
        SocDTO result = save(dTO);
        return result;
    }

    public void delete(Integer id) { 
        societeRepo.deleteById(id); }
     
        

}
