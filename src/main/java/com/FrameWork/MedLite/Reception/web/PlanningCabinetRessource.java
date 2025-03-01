/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Reception.web;

import com.FrameWork.MedLite.Reception.domaine.PlanningCabinet;
import com.FrameWork.MedLite.Reception.dto.PlanningCabinetDTO;
import com.FrameWork.MedLite.Reception.service.PlanningCabinetService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
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
@RequestMapping("/api/reception/")
public class PlanningCabinetRessource {

    private final PlanningCabinetService planningCabinetService;

    public PlanningCabinetRessource(PlanningCabinetService planningCabinetService) {
        this.planningCabinetService = planningCabinetService;
    }

    @GetMapping("planning_cabinet/{code}")
    public ResponseEntity<PlanningCabinetDTO> getPlanningCabinetByCode(@PathVariable Integer code) {
        PlanningCabinetDTO dto = planningCabinetService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("planning_cabinet/medecin")
    public ResponseEntity<List<PlanningCabinetDTO>> getAllPlanningCabinetByCodeMedecin(@PathVariable Integer codeMedecin) {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinetByCodeMedecin(codeMedecin));
    }

    @GetMapping("planning_cabinet/cabinet")
    public ResponseEntity<List<PlanningCabinetDTO>> getAllPlanningCabinetByCodeCabinet(@PathVariable Integer codeCabinet) {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinetByCodeCabinet(codeCabinet));
    }

    @GetMapping("planning_cabinet/ByMixed")
    public ResponseEntity<List<PlanningCabinetDTO>> getAllPlanningCabinetByCodeCabinetAndCodeMedecin(@RequestParam Integer codeCabinet, @RequestParam Integer codeMedecin) {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinetByCodeMedecinAndCodeCabinet(codeMedecin, codeCabinet));
    }

    @GetMapping("planning_cabinet/all")
    public ResponseEntity<List<PlanningCabinetDTO>> getAllPlanningCabinet() {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinet());
    }

    @GetMapping("planning_cabinet/findBy")
    public ResponseEntity<List<PlanningCabinetDTO>> getAllPlanningCabinetByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinetByActif(actif));
    }

    @GetMapping("planning_cabinet/findByDateAndActif")
    public ResponseEntity<Collection<PlanningCabinetDTO>> getAllPlanningCabinetByActifAndDateExiste(@RequestParam(required = false) Boolean actif, @RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinetByActifAndDateExisteBetween(actif, dateDebut, dateFin));
    }

    @GetMapping("planning_cabinet/findByDate")
    public ResponseEntity<Collection<PlanningCabinetDTO>> getAllPlanningCabinetByDateExiste(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(planningCabinetService.findAllPlanningCabinetByDateExisteBetween(dateDebut, dateFin));
    }

    @PostMapping("planning_cabinet")
    public ResponseEntity<PlanningCabinetDTO> postPlanningCabinet(@Valid @RequestBody PlanningCabinetDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PlanningCabinetDTO result = planningCabinetService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PostMapping("planning_cabinet/List")
    public ResponseEntity<String> savePlanning(@RequestBody List<PlanningCabinetDTO> planningDTOs) {
        List<PlanningCabinetDTO> result = planningCabinetService.saveList(planningDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("planning_cabinet/update")
    public ResponseEntity<String> updatePlanningCabinet(@RequestBody @Valid PlanningCabinetDTO dto) throws URISyntaxException {
        PlanningCabinet result = planningCabinetService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("planning_cabinet/delete/{Code}")
    public ResponseEntity<PlanningCabinet> deletePlanningCabinet(@PathVariable("Code") Integer code) {
        planningCabinetService.deletePlanningCabinet(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
