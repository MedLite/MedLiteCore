/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

import com.FrameWork.MedLite.Parametrage.domaine.Compteur;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsOperation;
import com.FrameWork.MedLite.Parametrage.domaine.DetailsPriceListOperation;
import com.FrameWork.MedLite.Parametrage.domaine.Operation;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.domaine.PriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.OperationDTO;
import com.FrameWork.MedLite.Parametrage.factory.DetailsOperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.DetailsPriceListOperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.NatureAdmissionFactory;
import com.FrameWork.MedLite.Parametrage.factory.OperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListFactory;
import com.FrameWork.MedLite.Parametrage.factory.PriceListOperationFactory;
import com.FrameWork.MedLite.Parametrage.factory.TypeIntervenantFactory;
import com.FrameWork.MedLite.Parametrage.repository.DetailsOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.DetailsPriceListOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.OperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PriceListOperationRepo;
import com.FrameWork.MedLite.Parametrage.repository.PriceListRepo;
import com.FrameWork.MedLite.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
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
public class OperationService {

    private final OperationRepo operationRepo;

    private final CompteurService compteurService;

    private final DetailsOperationRepo detailsOperationRepo;

    private final PriceListRepo priceListRepo;
    private final DetailsPriceListOperationService detailsPriceListOperationService;
    private final PriceListOperationService priceListOperationService;
    private final DetailsPriceListOperationRepo detailsPriceListOperationRepo;

    public OperationService(OperationRepo operationRepo, CompteurService compteurService, DetailsOperationRepo detailsOperationRepo, PriceListRepo priceListRepo, DetailsPriceListOperationService detailsPriceListOperationService, PriceListOperationService priceListOperationService, DetailsPriceListOperationRepo detailsPriceListOperationRepo) {
        this.operationRepo = operationRepo;
        this.compteurService = compteurService;
        this.detailsOperationRepo = detailsOperationRepo;
        this.priceListRepo = priceListRepo;
        this.detailsPriceListOperationService = detailsPriceListOperationService;
        this.priceListOperationService = priceListOperationService;
        this.detailsPriceListOperationRepo = detailsPriceListOperationRepo;
    }

   
    @Transactional(readOnly = true)
    public List<OperationDTO> findAllOperation() {
        return OperationFactory.listOperationToOperationDTOs(operationRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public List<OperationDTO> findAllOperationByActif(Boolean actif) {
        return OperationFactory.listOperationToOperationDTOs(operationRepo.findByActifOrderByCodeSaisieDesc(actif));

    }

    @Transactional(readOnly = true)
    public OperationDTO findOne(Integer code) {
        Operation domaine = operationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.OperationNotFound");
// 

        return OperationFactory.operationToOperationDTO(domaine);
    }

    public OperationDTO save(OperationDTO dto) {

        Operation domaine = OperationFactory.operationDTOToOperation(dto, new Operation());
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieOperation");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = operationRepo.save(domaine);

        PriceList plCash = priceListRepo.findByCash(Boolean.TRUE);
        Preconditions.checkArgument(plCash != null, "error.PriceListOperationCashNotFound");

        if (dto.getDetailsPriceListOperationDTOs() != null) {
            for (DetailsPriceListOperationDTO detailsDto : dto.getDetailsPriceListOperationDTOs()) {
                DetailsPriceListOperation detailsDomaine = DetailsPriceListOperationFactory.DetailspriceListOperationDTOToDetailsPriceOperationList(detailsDto, new DetailsPriceListOperation());
                detailsDomaine.setCodeOperation(domaine.getCode());
                if (domaine.getCode() != null) {
                    detailsDomaine.setOperation(OperationFactory.createOperationByCode(domaine.getCode()));

                }
                detailsDomaine.setCodePriceList(plCash.getCode());
                if (plCash.getCode() != null) {
                    detailsDomaine.setPriceList(PriceListFactory.createPriceListByCode(plCash.getCode()));

                }

                detailsDomaine.setCodeTypeIntervenant(detailsDto.getCodeTypeIntervenant());
                if (detailsDto.getCodeTypeIntervenant() != null) {
                    detailsDomaine.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(detailsDto.getCodeTypeIntervenant()));

                }
                
                 detailsDomaine.setCodeNatureAdmission(detailsDto.getCodeNatureAdmission());
                if (detailsDto.getCodeNatureAdmission() != null) {
                    detailsDomaine.setNatureAdmission(NatureAdmissionFactory.createNatureAdmissionByCode(detailsDto.getCodeNatureAdmission()));

                }
                
                
                detailsDomaine.setRemMaj("REM");  
                detailsDomaine.setMontantPere(detailsDto.getMontantPere());

                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setOperation(domaine); // Set the relationship to the saved Prestation
                detailsPriceListOperationRepo.save(detailsDomaine);
            }
        }
        return OperationFactory.operationToOperationDTO(domaine);
    }

    public OperationDTO updateNewWithFlush(OperationDTO dto) {
        Operation domaine = operationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.OperationNotFound");
        detailsOperationRepo.deleteByCodeOperation(dto.getCode());
        domaine = OperationFactory.operationDTOToOperation(dto, domaine);
        domaine = operationRepo.save(domaine);

        detailsPriceListOperationRepo.deleteByCodeOperation(domaine.getCode());
        PriceList plCash = priceListRepo.findByCash(Boolean.TRUE);
        Preconditions.checkArgument(plCash != null, "error.PriceListOperationCashNotFound");
        if (dto.getDetailsPriceListOperationDTOs() != null) {
            for (DetailsPriceListOperationDTO detailsDto : dto.getDetailsPriceListOperationDTOs()) {
                DetailsPriceListOperation detailsDomaine = DetailsPriceListOperationFactory.DetailspriceListOperationDTOToDetailsPriceOperationList(detailsDto, new DetailsPriceListOperation());
                detailsDomaine.setCodeOperation(domaine.getCode());
                if (domaine.getCode() != null) {
                    detailsDomaine.setOperation(OperationFactory.createOperationByCode(domaine.getCode()));
                }
                detailsDomaine.setCodePriceList(plCash.getCode());
                if (plCash.getCode() != null) {
                    detailsDomaine.setPriceList(PriceListFactory.createPriceListByCode(plCash.getCode()));
                }

                detailsDomaine.setCodeTypeIntervenant(detailsDto.getCodeTypeIntervenant());
                if (detailsDto.getCodeTypeIntervenant() != null) {
                    detailsDomaine.setTypeIntervenant(TypeIntervenantFactory.createTypeIntervenantByCode(detailsDto.getCodeTypeIntervenant()));
                }
                detailsDomaine.setRemMaj("REM");
                detailsDomaine.setMontantPere(detailsDto.getMontantPere());
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setOperation(domaine); // Set the relationship to the saved Prestation
                detailsPriceListOperationRepo.save(detailsDomaine);
            }
        }

        OperationDTO resultDTO = OperationFactory.operationToOperationDTO(domaine);
        return resultDTO;
    }

    public void deleteOperation(Integer code) {
        Preconditions.checkArgument(operationRepo.existsById(code), "error.OperationNotFound");
        detailsPriceListOperationService.deleteDetailsPriceListOperationByCodeOperation(code);
        
        detailsOperationRepo.deleteByCodeOperation(code);
        operationRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public Collection<DetailsOperationDTO> findOneWithDetails(Integer code) {
        Collection<DetailsOperation> domaine = detailsOperationRepo.findByDetailsOperationPK_codeOperation(code);
        return DetailsOperationFactory.detailsOperationTodetailsOperationDTOCollections(domaine);
    }
}
