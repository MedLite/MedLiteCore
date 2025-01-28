/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.service;

 
import com.FrameWork.MedLite.Parametrage.domaine.EtatApprouver;
import com.FrameWork.MedLite.Parametrage.dto.EtatApprouverDTO;
import com.FrameWork.MedLite.Parametrage.factory.EtatApprouverFactory;
import com.FrameWork.MedLite.Parametrage.repository.EtatApprouverRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class EtatApprouverService {
    private final EtatApprouverRepo etatApprouverOrdreAchatRepo;

    public EtatApprouverService(EtatApprouverRepo etatApprouverOrdreAchatRepo) {
        this.etatApprouverOrdreAchatRepo = etatApprouverOrdreAchatRepo;
    }

    @Transactional(readOnly = true)
    public List<EtatApprouverDTO> findAllEtatApprouverOrdreAchat() {
        return EtatApprouverFactory.listEtatApprouverToEtatApprouverDTOs(etatApprouverOrdreAchatRepo.findAll());

    }

    @Transactional(readOnly = true)
    public EtatApprouverDTO findOne(Integer code) {
        EtatApprouver domaine = etatApprouverOrdreAchatRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.EtatApprouverOrdreAchatNotFound");
        return EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine);
    }

 
 

  
}
