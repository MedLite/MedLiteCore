/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.TypeIntervenant;
import com.DevPointSystem.MedLite.Parametrage.dto.TypeIntervenantDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.TypeIntervenantRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class TypeIntervenantService {
   private final TypeIntervenantRepo typeIntervenantRepo;

    public TypeIntervenantService(TypeIntervenantRepo typeIntervenantRepo) {
        this.typeIntervenantRepo = typeIntervenantRepo;
    }

    @Transactional(readOnly = true)
    public List<TypeIntervenantDTO> findAllTypeIntervenant() {
        return TypeIntervenantFactory.listTypeIntervenantToTypeIntervenantDTOs(typeIntervenantRepo.findAll());

    }

    @Transactional(readOnly = true)
    public TypeIntervenantDTO findOne(Integer code) {
        TypeIntervenant domaine = typeIntervenantRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.TypeIntervenantNotFound");
        return TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine);
    }

    public TypeIntervenantDTO save(TypeIntervenantDTO dto) {
        TypeIntervenant domaine = TypeIntervenantFactory.typeIntervenantDTOToTypeIntervenant(dto, new TypeIntervenant());

        domaine = typeIntervenantRepo.save(domaine);

        return TypeIntervenantFactory.typeIntervenantToTypeIntervenantDTO(domaine);
    }

    public TypeIntervenant update(TypeIntervenantDTO dto) {
        TypeIntervenant domaine = typeIntervenantRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TypeIntervenantNotFound");
        dto.setCode(domaine.getCode());
        TypeIntervenantFactory.typeIntervenantDTOToTypeIntervenant(dto, domaine);
        return typeIntervenantRepo.save(domaine);
    }

    public void deleteTypeIntervenant(Integer code) {
        Preconditions.checkArgument(typeIntervenantRepo.existsById(code), "error.TypeIntervenantNotFound");
        typeIntervenantRepo.deleteById(code);
    }  
}
