package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.Statut;
import suivimig.example.repository.StatutRepository;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class StatutController {
    @Autowired
    StatutRepository statutRepository;
    @GetMapping("/getAllStatuts")
    public ResponseEntity<List<Statut>> getAllStatuts(@RequestParam(required = false) String name) {
        List<Statut> statuts = new ArrayList<Statut>();
        if (name == null)
            statutRepository.findAll().forEach(statuts::add);

        if (statuts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statuts, HttpStatus.OK);
    }
    @GetMapping("/getStatut/{id}")
    public ResponseEntity<Statut> getStatutById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Statut statut = statutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found statut with id = " + id));
        return new ResponseEntity<>(statut, HttpStatus.OK);
    }
    @PostMapping("/createStatut")
    public ResponseEntity<Statut> createStatut(@RequestBody Statut statut) {
        Statut _statut = statutRepository.save(new Statut(statut.getValeur()));
        return new ResponseEntity<>(_statut, HttpStatus.CREATED);
    }
    @PutMapping("/updateStatut/{id}")
    public ResponseEntity<Statut> updateStatut(@PathVariable("id") long id, @RequestBody Statut statut) throws ResourceNotFoundException {
        Statut _statut = statutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found statut with id = " + id));
        _statut.setValeur(statut.getValeur());


        return new ResponseEntity<>(statutRepository.save(_statut), HttpStatus.OK);
    }
    @DeleteMapping("/deleteStatut/{id}")
    public ResponseEntity<HttpStatus> deleteStatut(@PathVariable("id") long id) {
        statutRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAllStatuts")
    public ResponseEntity<HttpStatus> deleteAllStatuts() {
        statutRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
