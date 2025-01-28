/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Recette.web;

import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import com.FrameWork.MedLite.Recette.dto.MouvementCaisseDTO;
import com.FrameWork.MedLite.Recette.service.MouvementCaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/recette/")
public class MouvementCaisseRessource {

    private final MouvementCaisseService mouvementCaisseService;
    private final ParamService paramService;
    private final SocService societeService;

    public MouvementCaisseRessource(MouvementCaisseService mouvementCaisseService, ParamService paramService, SocService societeService) {
        this.mouvementCaisseService = mouvementCaisseService;
        this.paramService = paramService;
        this.societeService = societeService;
    }

    @GetMapping("mouvement_caisse/{code}")
    public ResponseEntity<MouvementCaisseDTO> getMouvementCaisseByCode(@PathVariable Integer code) {
        MouvementCaisseDTO dTO = mouvementCaisseService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("mouvement_caisse/caisse/{codeCaisse}")
    public ResponseEntity<List<MouvementCaisseDTO>> getMouvementCaisseByCodeCaisse(@PathVariable Integer codeCaisse) {
        List<MouvementCaisseDTO> dTO = mouvementCaisseService.findByCodeCaisse(codeCaisse);
        return ResponseEntity.ok().body(dTO);
    }
    
    

    @GetMapping("mouvement_caisse/all")
    public ResponseEntity<List<MouvementCaisseDTO>> getAllMouvementCaisse() {
        return ResponseEntity.ok().body(mouvementCaisseService.findAllMouvementCaisse());
    }

//     @GetMapping("mouvement_caisse/allGrouped")
//    public ResponseEntity<List<MouvementCaisseDTO>> getAllMouvementCaisseGrouped() {
//        return ResponseEntity.ok().body(mouvementCaisseService.findAllMouvementCaisseGroupeed());
//    }
//     
    @PostMapping("mouvement_caisse")
    public ResponseEntity<MouvementCaisseDTO> postMouvementCaisse(@Valid @RequestBody MouvementCaisseDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        MouvementCaisseDTO result = mouvementCaisseService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

}
