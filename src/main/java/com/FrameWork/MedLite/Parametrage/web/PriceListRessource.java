/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Authentification.service.AccessUserService; 
import com.FrameWork.MedLite.Parametrage.domaine.PriceList; 
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import com.FrameWork.MedLite.Parametrage.service.DetailsPriceListService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PriceListService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class PriceListRessource {

    private final PriceListService priceListService;
    private final ParamService paramService;
    private final SocService societeService;
    private final DetailsPriceListService detailsPriceListService;
    private final AccessUserService accessUserService;

    public PriceListRessource(PriceListService priceListService, ParamService paramService, SocService societeService, DetailsPriceListService detailsPriceListService, AccessUserService accessUserService) {
        this.priceListService = priceListService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.detailsPriceListService = detailsPriceListService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("price_list/{code}")
    public ResponseEntity<PriceListDTO> getPriceListByCode(@PathVariable Integer code) {
        PriceListDTO dTO = priceListService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("price_list/all")
    public ResponseEntity<List<PriceListDTO>> getAllPriceList() {
        return ResponseEntity.ok().body(priceListService.findAllPriceList());
    }

    @PostMapping("price_list")
    public ResponseEntity<PriceListDTO> postPriceList(@Valid @RequestBody PriceListDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PriceListDTO result = priceListService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PostMapping("price_lists")
    public ResponseEntity<PriceListDTO> savePriceList(@RequestBody PriceListDTO dTO) {
        try { 
            PriceListDTO savedPriceList = priceListService.savepricelist(dTO);
            return new ResponseEntity<>(savedPriceList, HttpStatus.CREATED);
        } catch (Exception e) { 
            e.printStackTrace(); // Replace with a proper logging mechanism in a production environment
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("price_list/findBy")
    public ResponseEntity<List<PriceListDTO>> getAllPriceListByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(priceListService.findAllPriceListByActif(actif));
    }

    @PutMapping("price_list/update")
    public ResponseEntity<PriceListDTO> updatePriceList(@Valid @RequestBody PriceListDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PriceListDTO result = priceListService.updateNew(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("price_list/delete/{Code}")
    public ResponseEntity<PriceList> deletePriceList(@PathVariable("Code") Integer code) {
        priceListService.deletePriceList(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
