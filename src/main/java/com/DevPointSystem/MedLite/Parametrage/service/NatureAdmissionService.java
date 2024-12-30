/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.NatureAdmission;
import com.DevPointSystem.MedLite.Parametrage.dto.NatureAdmissionDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.NatureAdmissionRepo;
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
public class NatureAdmissionService {

    private final NatureAdmissionRepo natureAdmissionRepo;

    public NatureAdmissionService(NatureAdmissionRepo natureAdmissionRepo) {
        this.natureAdmissionRepo = natureAdmissionRepo;
    }

    @Transactional(readOnly = true)
    public List<NatureAdmissionDTO> findAllNatureAdmission() {
        return NatureAdmissionFactory.listNatureAdmissionToNatureAdmissionDTOs(natureAdmissionRepo.findAll());

    }

    @Transactional(readOnly = true)
    public NatureAdmissionDTO findOne(Integer code) {
        NatureAdmission domaine = natureAdmissionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.NatureAdmissionNotFound");
        return NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine);
    }

//    public NatureAdmissionDTO save(NatureAdmissionDTO dto) {
//        NatureAdmission domaine = NatureAdmissionFactory.natureAdmissionDTOToNatureAdmission(dto, new NatureAdmission());
//        domaine = natureAdmissionRepo.save(domaine);
//        return NatureAdmissionFactory.natureAdmissionToNatureAdmissionDTO(domaine);
//    }
//
//    public NatureAdmission update(NatureAdmissionDTO dto) {
//        NatureAdmission domaine = natureAdmissionRepo.findByCode(dto.getCode());
//        Preconditions.checkArgument(domaine != null, "error.NatureAdmissionNotFound");
//        dto.setCode(domaine.getCode());
//        NatureAdmissionFactory.natureAdmissionDTOToNatureAdmission(dto, domaine);
//        return natureAdmissionRepo.save(domaine);
//    }
//
//    public void deleteNatureAdmission(Integer code) {
//        Preconditions.checkArgument(natureAdmissionRepo.existsById(code), "error.NatureAdmissionNotFound");
//        natureAdmissionRepo.deleteById(code);
//    }
}
