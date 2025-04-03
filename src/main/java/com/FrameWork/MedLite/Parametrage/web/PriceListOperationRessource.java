/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.MedLite.Parametrage.domaine.PriceListOperation;
import com.FrameWork.MedLite.Parametrage.dto.PriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.service.DetailsPriceListOperationService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PriceListOperationService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class PriceListOperationRessource {
    
     private final PriceListOperationService priceListOperationService;
    private final ParamService paramService;
    private final SocService societeService;
    private final DetailsPriceListOperationService detailsPriceListOperationService;
//    private final AccessUserService accessUserService;

    public PriceListOperationRessource(PriceListOperationService priceListOperationService, ParamService paramService, SocService societeService, DetailsPriceListOperationService detailsPriceListOperationService) {
        this.priceListOperationService = priceListOperationService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.detailsPriceListOperationService = detailsPriceListOperationService;
    }

 
     

    @GetMapping("price_list_operation/{code}")
    public ResponseEntity<PriceListOperationDTO> getPriceListOperationByCode(@PathVariable Integer code) {
        PriceListOperationDTO dTO = priceListOperationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }
 

    @GetMapping("price_list_operation/all")
    public ResponseEntity<List<PriceListOperationDTO>> getAllPriceListOperation() {
        return ResponseEntity.ok().body(priceListOperationService.findAllPriceListOperation());
    }

    @PostMapping("price_list_operation")
    public ResponseEntity<PriceListOperationDTO> postPriceListOperation(@Valid @RequestBody PriceListOperationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PriceListOperationDTO result = priceListOperationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }
 
    @PutMapping("price_list_operation/update")
    public ResponseEntity<PriceListOperationDTO> updatePriceListOperation(@Valid @RequestBody PriceListOperationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PriceListOperationDTO result = priceListOperationService.updateNew(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("price_list_operation/delete/{Code}")
    public ResponseEntity<PriceListOperation> deletePriceListOperation(@PathVariable("Code") Integer code) {
        priceListOperationService.deletePriceListOperation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
