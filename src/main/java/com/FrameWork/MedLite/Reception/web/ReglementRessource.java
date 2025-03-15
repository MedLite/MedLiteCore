/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.web;

import com.FrameWork.MedLite.Reception.domaine.Reglement;
import com.FrameWork.MedLite.Reception.dto.ReglementDTO;
import com.FrameWork.MedLite.Reception.service.ReglementService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
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
@RequestMapping("/api/caisse/")
public class ReglementRessource {

    private final ReglementService reglementService;

    public ReglementRessource(ReglementService reglementService) {
        this.reglementService = reglementService;
    }

    @GetMapping("reglement/{code}")
    public ResponseEntity<ReglementDTO> getReglementByCode(@PathVariable Integer code) {
        ReglementDTO dto = reglementService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("reglement/findByDate")
    public ResponseEntity<Collection<ReglementDTO>> getAllReglementByDateExiste(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(reglementService.findAllReglementByDateReglementBetween(dateDebut, dateFin));
    }

    
        @GetMapping("reglement/findByCaissier")
    public ResponseEntity<Collection<ReglementDTO>> getAllReglementByCaissier(@RequestParam String caissier) {
        return ResponseEntity.ok().body(reglementService.findAllReglementByCaissier(caissier));
    }
    
    
    @GetMapping("reglement/all")
    public ResponseEntity<List<ReglementDTO>> getAllReglement() {
        return ResponseEntity.ok().body(reglementService.findAllReglement());
    }

    @PostMapping("reglement")
    public ResponseEntity<ReglementDTO> postReglement(@Valid @RequestBody ReglementDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ReglementDTO result = reglementService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PostMapping("reglement/List")
    public ResponseEntity<String> saveReglementList(@RequestBody List<ReglementDTO> planningDTOs) {
        List<ReglementDTO> result = reglementService.saveList(planningDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("reglement/update")
    public ResponseEntity<Reglement> updateReglement(@RequestBody @Valid ReglementDTO dto) throws URISyntaxException {
        Reglement result = reglementService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("reglement/delete/{Code}")
    public ResponseEntity<Reglement> deleteReglement(@PathVariable("Code") Integer code) {
        reglementService.deleteReglement(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
