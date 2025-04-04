/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;
 
import com.FrameWork.MedLite.Parametrage.domaine.Operation;
import com.FrameWork.MedLite.Parametrage.dto.DetailsOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.OperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.PrestationDTO;
import com.FrameWork.MedLite.Parametrage.service.DetailsOperationService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.OperationService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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
public class OperationRessource {

    private final OperationService operationService;
    private final ParamService paramService;
    private final SocService societeService;
    private final DetailsOperationService detailsOperationService;

//    private final AccessUserService accessUserService;

    public OperationRessource(OperationService operationService, ParamService paramService, SocService societeService, DetailsOperationService detailsOperationService) {
        this.operationService = operationService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.detailsOperationService = detailsOperationService;
    }

   

    @GetMapping("operation/{code}")
    public ResponseEntity<OperationDTO> getOperationByCode(@PathVariable Integer code) {
        OperationDTO dTO = operationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("operation/all")
    public ResponseEntity<List<OperationDTO>> getAllOperation() {
        return ResponseEntity.ok().body(operationService.findAllOperation());
    }

    @GetMapping("operation/findBy")
    public ResponseEntity<List<OperationDTO>> getAllOperationByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(operationService.findAllOperationByActif(actif));
    }

    @PostMapping("operation")
    public ResponseEntity<OperationDTO> postOperation(@Valid @RequestBody OperationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        OperationDTO result = operationService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @GetMapping("details_operation/{code}")
    public ResponseEntity<Collection<DetailsOperationDTO>> getDetailsOperationByCode(@PathVariable Integer code) {
        Collection<DetailsOperationDTO> dTO = detailsOperationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @PutMapping("operation/update")
    public ResponseEntity<OperationDTO> updateOperation(@Valid @RequestBody OperationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        OperationDTO result = operationService.updateNewWithFlush(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("operation/delete/{Code}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable("Code") Integer code) {
        operationService.deleteOperation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
