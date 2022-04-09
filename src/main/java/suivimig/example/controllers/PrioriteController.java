package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.Priorite;
import suivimig.example.repository.PrioriteRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class PrioriteController {
    @Autowired
    PrioriteRepository prioriteRepository;

    @GetMapping("/getAllPriorites")
    public List<Priorite> getAllPriorite() {
        return prioriteRepository.findAll();
    }

    @GetMapping("/getPriorite/{id}")
    public ResponseEntity<Priorite> getPrdSpById(@PathVariable("id") long id) throws ResourceNotFoundException {
            Priorite priorite= prioriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found priorite with id = " + id));
        return new ResponseEntity<>(priorite, HttpStatus.OK);
    }

    @PostMapping("/createPriorite")
    public ResponseEntity<Priorite> createPrdSp(@RequestBody Priorite priorite ) {
        Priorite _priorite= prioriteRepository.save(new Priorite(priorite.getValeur()));
        return new ResponseEntity<>(_priorite, HttpStatus.CREATED);
    }

    @PutMapping("/updatePriorite/{id}")
    public ResponseEntity<Priorite> updatePriorite(@PathVariable("id") long id, @RequestBody Priorite priorite) throws ResourceNotFoundException {
        Priorite _priorite= prioriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Priorite with id = " + id));
        _priorite.setValeur(priorite.getValeur());
        return new ResponseEntity<>(prioriteRepository.save(_priorite),HttpStatus.OK);
    }
    @DeleteMapping("/deletePriorite/{id}")
    public ResponseEntity<HttpStatus> deletePriorite(@PathVariable("id") long id) {
        prioriteRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAllPriorites")
    public ResponseEntity<HttpStatus> deleteAllPriorites() {
        prioriteRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
