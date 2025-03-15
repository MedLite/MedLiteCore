/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.SignatureMedecin;
import com.FrameWork.MedLite.Parametrage.dto.SignatureMedecinDTO;
import com.FrameWork.MedLite.Parametrage.factory.SignatureMedecinFactory;
import com.FrameWork.MedLite.Parametrage.repository.SignatureMedecinRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class SignatureMedecinService {
    private final SignatureMedecinRepo signatureMedecinRepo;

    public SignatureMedecinService(SignatureMedecinRepo signatureMedecinRepo) {
        this.signatureMedecinRepo = signatureMedecinRepo;
    }

    @Transactional(readOnly = true)
    public List<SignatureMedecinDTO> findAllSignatureMedecin() {
        return SignatureMedecinFactory.listSignatureMedecinToSignatureMedecinDTOs(signatureMedecinRepo.findAll());

    }

    @Transactional(readOnly = true)
    public SignatureMedecinDTO findOne(Integer code) {
        SignatureMedecin domaine = signatureMedecinRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SignatureMedecinNotFound");
        return SignatureMedecinFactory.signatureMedecinTosignatureMedecinDTO(domaine);
    }
    
     @Transactional(readOnly = true)
    public SignatureMedecinDTO findOneByCodeMedecin(Integer codeMdecin) {
        SignatureMedecin domaine = signatureMedecinRepo.findByCodeMedecin(codeMdecin);
        Preconditions.checkArgument(domaine != null, "error.SignatureMedecinNotFound");
        return SignatureMedecinFactory.signatureMedecinTosignatureMedecinDTO(domaine);
    }

//
    public SignatureMedecinDTO save(SignatureMedecinDTO dto) {
        SignatureMedecin domaine = SignatureMedecinFactory.accessSignatureMedecinDTOToSignatureMedecinx(dto, new SignatureMedecin());
            domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = signatureMedecinRepo.save(domaine);
        return SignatureMedecinFactory.signatureMedecinTosignatureMedecinDTO(domaine);
    }

    public SignatureMedecin update(SignatureMedecinDTO dto) {
        SignatureMedecin domaine = signatureMedecinRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SignatureMedecinNotFound");
        dto.setCode(domaine.getCode());
        SignatureMedecinFactory.accessSignatureMedecinDTOToSignatureMedecinx(dto, domaine);
        return signatureMedecinRepo.save(domaine);
    }

    public void deleteSignatureMedecin(Integer code) {
        Preconditions.checkArgument(signatureMedecinRepo.existsById(code), "error.SignatureMedecinNotFound");
        signatureMedecinRepo.deleteById(code);
    }
}
