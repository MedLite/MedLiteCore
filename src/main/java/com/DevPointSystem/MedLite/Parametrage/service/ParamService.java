/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.service;

 
import com.DevPointSystem.MedLite.Parametrage.domaine.param;
import com.DevPointSystem.MedLite.Parametrage.dto.paramDTO;
import com.DevPointSystem.MedLite.Parametrage.factory.paramFactory;
import com.DevPointSystem.MedLite.Parametrage.repository.ParamRepo;
import com.DevPointSystem.MedLite.web.Util.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class ParamService {



    private final ParamRepo paramRepo;

    public ParamService(ParamRepo paramRepo) {
        this.paramRepo = paramRepo;
    }

//    @Transactional(readOnly = true)
//    public Collection<paramDTO> findParamByCodeParam(Collection<String> codeParam) {
//        Collection<param> result = paramRepo.findParamByCodeParamIn(Helper.removeNullValueFromCollection(codeParam));
//        Preconditions.checkBusinessLogique(result != null, "error.RegionNotFound");
//        return paramFactory.CollectionparamToparamDTOs(result);
//    }
    @Transactional(readOnly = true)
    public paramDTO findParamByCodeParamS(String codeParam) {
        param result = paramRepo.findParamByCodeParam(codeParam); 
        Preconditions.checkBusinessLogique(result != null, "error.ParamNotFound");
        return paramFactory.paramToparamDTO(result);
    }

}
