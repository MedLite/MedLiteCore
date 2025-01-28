/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.CompteurClassement;
import com.FrameWork.MedLite.Parametrage.dto.CompteurClassementDTO;
import com.FrameWork.MedLite.Parametrage.factory.CompteurClassementFactory;
import com.FrameWork.MedLite.Parametrage.repository.CompteurClassementRepo;
import com.FrameWork.MedLite.web.Util.Helper;
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
public class CompteurClassementService {

    private final CompteurClassementRepo compteurRepo;

    public CompteurClassementService(CompteurClassementRepo compteurRepo) {
        this.compteurRepo = compteurRepo;
    }

    @Transactional(readOnly = true)
    public List<CompteurClassementDTO> findAllCompteur() {
        return CompteurClassementFactory.listCompteurToCompteurDTOs(compteurRepo.findAll());
    }

    @Transactional(readOnly = true)
    public CompteurClassement findOne(String id) {
        CompteurClassement compteur = compteurRepo.findByCompteur(id);
        com.FrameWork.MedLite.web.Util.Preconditions.checkBusinessLogique(compteur != null, "error.parametrageManquant", id);
        return compteur;
    }
//

    public Boolean incrementeSuffixe1(CompteurClassement compteur) {
        if (compteur != null) {
            compteur.setSuffixe(Helper.incrementInteger1(compteur.getSuffixe()));
            compteurRepo.save(compteur);
            return true;
        } else {
            return false;
        }
    }

    public Boolean incrementeSuffixe100(CompteurClassement compteur) {
        if (compteur != null) {
            compteur.setSuffixe(Helper.incrementInteger100(compteur.getSuffixe()));
            compteurRepo.save(compteur);
            return true;
        } else {
            return false;
        }
    }

    public Boolean incrementeSuffixe10000(CompteurClassement compteur) {
        if (compteur != null) {
            compteur.setSuffixe(Helper.incrementInteger10000(compteur.getSuffixe()));
            compteurRepo.save(compteur);
            return true;
        } else {
            return false;
        }
    }

    public Boolean incrementeSuffixe10000000(CompteurClassement compteur) {
        if (compteur != null) {
            compteur.setSuffixe(Helper.incrementInteger10000000(compteur.getSuffixe()));
            compteurRepo.save(compteur);
            return true;
        } else {
            return false;
        }
    }

    public CompteurClassement update(CompteurClassementDTO dTO) {
        Preconditions.checkArgument((dTO.getCode() != null), "error.CompteurNotFound");
        CompteurClassement domaine = compteurRepo.getReferenceById(dTO.getCode());
        Preconditions.checkArgument(true, "error.CompteurNotFound");
        dTO.setCode(domaine.getCode());
        CompteurClassementFactory.compteurDTOToCompteur(dTO, domaine);
        return compteurRepo.save(domaine);
    }

}
