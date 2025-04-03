/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.DMI.service;

import com.FrameWork.MedLite.DMI.domaine.CheifComplaint;
import com.FrameWork.MedLite.DMI.dto.CheifComplaintDTO;
import com.FrameWork.MedLite.DMI.dto.Icd10DTO;
import com.FrameWork.MedLite.DMI.factory.CheifComplaintFactory;
import com.FrameWork.MedLite.DMI.factory.Icd10Factory;
import com.FrameWork.MedLite.DMI.repository.CheifComplaintRepo;
import com.FrameWork.MedLite.DMI.repository.Icd10Repo;
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
public class Icd10Service {

    private final Icd10Repo icd10Repo;

    public Icd10Service(Icd10Repo icd10Repo) {
        this.icd10Repo = icd10Repo;
    }

    @Transactional(readOnly = true)
    public List<Icd10DTO> findAll() {
        return Icd10Factory.listICD10ToICD10DTOs(icd10Repo.findAll());
    }

    @Transactional(readOnly = true)
    public List<Icd10DTO> findByCodeOrDesignation(String searchTerm) {
        return Icd10Factory.listICD10ToICD10DTOs(icd10Repo.findByCodeContainingIgnoreCaseOrShortdescriptionContainingIgnoreCaseOrLongdescriptionContainingIgnoreCase(searchTerm, searchTerm,searchTerm));
    }

}
