
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.TypeCaisse;
import com.FrameWork.MedLite.Parametrage.dto.DeviseDTO;
import com.FrameWork.MedLite.Parametrage.dto.TypeCaisseDTO;
import com.FrameWork.MedLite.Parametrage.factory.TypeCaisseFactory;
import com.FrameWork.MedLite.Parametrage.repository.TypeCaisseRepo;
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
public class TypeCaisseService {

    private final TypeCaisseRepo typeCaisseRepo;

    public TypeCaisseService(TypeCaisseRepo typeCaisseRepo) {
        this.typeCaisseRepo = typeCaisseRepo;
    }

    @Transactional(readOnly = true)
    public List<TypeCaisseDTO> findAllTypeCaisse() {
        return TypeCaisseFactory.listTypeCaisseToTypeCaisseDTOs(typeCaisseRepo.findAll());

    }

    @Transactional(readOnly = true)
    public TypeCaisseDTO findOne(Integer code) {
        TypeCaisse domaine = typeCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine  != null, "error.TypeCaisseNotFound");
        return TypeCaisseFactory.typeCaisseToTypeCaisseDTO(domaine);
    }

//
    public TypeCaisseDTO save(TypeCaisseDTO dto) {
        TypeCaisse domaine = TypeCaisseFactory.typeCaisseDTOToTypeCaisse(dto, new TypeCaisse());
 
        domaine = typeCaisseRepo.save(domaine);
        return TypeCaisseFactory.typeCaisseToTypeCaisseDTO(domaine);
    }

    public TypeCaisse update(TypeCaisseDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TypeCaisseNotFound");
        TypeCaisse domaine = typeCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(true, "error.TypeCaisseNotFound");
        dto.setCode(domaine.getCode());
        TypeCaisseFactory.typeCaisseDTOToTypeCaisse(dto, domaine);
        return typeCaisseRepo.save(domaine);
    }

    public void deleteTypeCaisse(Integer code) {
        Preconditions.checkArgument(typeCaisseRepo.existsById(code), "error.TypeCaisseNotFound");
        typeCaisseRepo.deleteById(code);
    }
    
    

}
