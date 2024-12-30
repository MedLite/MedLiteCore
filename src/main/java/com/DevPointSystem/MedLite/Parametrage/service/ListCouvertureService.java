/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

import com.DevPointSystem.MedLite.Parametrage.domaine.Compteur;
import com.DevPointSystem.MedLite.Parametrage.domaine.DetailsListCouverture;
import com.DevPointSystem.MedLite.Parametrage.domaine.ListCouverture;
import com.DevPointSystem.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.DevPointSystem.MedLite.Parametrage.dto.ListCouvertureDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.DetailsListCouvertureFactory;
import com.DevPointSystem.MedLite.Parametrage.factory.ListCouvertureFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.DetailsListCouvertureRepo;
import com.DevPointSystem.MedLite.Parametrage.repository.ListCouvertureRepo;
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
public class ListCouvertureService {

    private final ListCouvertureRepo listCouvertureRepo;

    private final CompteurService compteurService;

    private final DetailsListCouvertureRepo detailsListCouvertureRepo;

    public ListCouvertureService(ListCouvertureRepo listCouvertureRepo, CompteurService compteurService, DetailsListCouvertureRepo detailsListCouvertureRepo) {
        this.listCouvertureRepo = listCouvertureRepo;
        this.compteurService = compteurService;
        this.detailsListCouvertureRepo = detailsListCouvertureRepo;
    }

    @Transactional(readOnly = true)
    public List<ListCouvertureDTO> findAllListCouverture() {
        return ListCouvertureFactory.listListCouvertureToListCouvertureDTOs(listCouvertureRepo.findAll());

    }

    @Transactional(readOnly = true)
    public ListCouvertureDTO findOne(Integer code) {
        ListCouverture domaine = listCouvertureRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.ListCouvertureNotFound");
// 

        return ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine);
    }

    public ListCouvertureDTO save(ListCouvertureDTO dto) {

        ListCouverture domaine = ListCouvertureFactory.listCouvertureDTOToListCouverture(dto, new ListCouverture());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieAC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = listCouvertureRepo.save(domaine);
        return ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine);
    }

    public ListCouvertureDTO updateNewWithFlush(ListCouvertureDTO dto) {
        ListCouverture domaine = listCouvertureRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.ListCouvertureNotFound");
        detailsListCouvertureRepo.deleteByCodeListCouverture(dto.getCode());
        domaine = ListCouvertureFactory.listCouvertureDTOToListCouverture(dto, domaine);
        domaine = listCouvertureRepo.save(domaine);
        ListCouvertureDTO resultDTO = ListCouvertureFactory.listCouvertureToListCouvertureDTO(domaine);
        return resultDTO;
    }

    public void deleteListCouverture(Integer code) {
        Preconditions.checkArgument(listCouvertureRepo.existsById(code), "error.ListCouvertureNotFound");
        detailsListCouvertureRepo.deleteByCodeListCouverture(code);
        listCouvertureRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsListCouvertureDTO> findOneWithDetails(Integer code) {
        Collection<DetailsListCouverture> domaine = detailsListCouvertureRepo.findByDetailsListCouverturePK_codeListCouverture(code);
        return DetailsListCouvertureFactory.detailsListCouvertureTodetailsListCouvertureDTOCollections(domaine);
    }
}
