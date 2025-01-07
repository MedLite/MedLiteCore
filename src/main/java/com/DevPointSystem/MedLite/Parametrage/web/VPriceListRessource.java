/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Parametrage.web;

import com.DevPointSystem.MedLite.Parametrage.dto.VPriceListDTO;
import com.DevPointSystem.MedLite.Parametrage.service.VPriceListService;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class VPriceListRessource {
     private final VPriceListService vPriceListService;

    public VPriceListRessource(VPriceListService vPriceListService) {
        this.vPriceListService = vPriceListService;
    }

    @GetMapping("VpriceList/{code}")
    public ResponseEntity<Collection<VPriceListDTO>> getVPriceListByCode(@PathVariable Integer code) {
        Collection<VPriceListDTO> dto = vPriceListService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("VpriceList/all")
    public ResponseEntity<List<VPriceListDTO>> getAllVPriceList() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(vPriceListService.findAllVPriceList());
    }

    
}
