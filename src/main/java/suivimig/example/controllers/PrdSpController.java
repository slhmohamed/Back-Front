package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.PrdSp;
import suivimig.example.repository.PrdSpRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class PrdSpController {

    @Autowired
    PrdSpRepository prdSpRepository;

    @GetMapping("/getAllPrdSp")
    public List<PrdSp> getAllPrdSp() {
        return prdSpRepository.findAll();
    }

    @GetMapping("/getPrdSp/{id}")
    public ResponseEntity<PrdSp> getPrdSpById(@PathVariable("id") long id) throws ResourceNotFoundException {
        PrdSp prd = prdSpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found prdSp with id = " + id));
        return new ResponseEntity<>(prd, HttpStatus.OK);
    }

    @PostMapping("/createPrdSp")
    public ResponseEntity<PrdSp> createPrdSp(@RequestBody PrdSp prdSp) {
        PrdSp _prd= prdSpRepository.save(new PrdSp(prdSp.getValeur()));
        return new ResponseEntity<>(_prd, HttpStatus.CREATED);
    }

    @PutMapping("/updatePrdSp/{id}")
    public ResponseEntity<PrdSp> updatePrdSp(@PathVariable("id") long id, @RequestBody PrdSp prdSp) throws ResourceNotFoundException {
        PrdSp _prd = prdSpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found PrdSp with id = " + id));
        _prd.setValeur(prdSp.getValeur());
        return new ResponseEntity<>(prdSpRepository.save(_prd),HttpStatus.OK);
    }
    @DeleteMapping("/deletePrdSp/{id}")
    public ResponseEntity<HttpStatus> deletePrdSp(@PathVariable("id") long id) {
        prdSpRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAllPrdSp")
    public ResponseEntity<HttpStatus> deleteAllScrums() {
        prdSpRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
