package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.Scrum;
import suivimig.example.repository.ScrumRepository;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class ScrumController {
    @Autowired
    ScrumRepository scrumRepository;

    @GetMapping("/getAllScrums")
    public List<Scrum> getAllScrums() {
       return scrumRepository.findAll();
    }

    @GetMapping("/getScrum/{id}")
    public ResponseEntity<Scrum> getStatutById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Scrum scrum = scrumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found scrum with id = " + id));
        return new ResponseEntity<>(scrum,HttpStatus.OK);
    }

    @PostMapping("/createScrum")
    public ResponseEntity<Scrum> createScrum(@RequestBody Scrum scrum) {
        Scrum _scrum= scrumRepository.save(new Scrum(scrum.getValeur()));
        return new ResponseEntity<>(_scrum, HttpStatus.CREATED);
    }

    @PutMapping("/updateScrum/{id}")
    public ResponseEntity<Scrum> updateScrum(@PathVariable("id") long id, @RequestBody Scrum scrum) throws ResourceNotFoundException {
        Scrum _scrum = scrumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found scrum with id = " + id));
        _scrum.setValeur(scrum.getValeur());
        return new ResponseEntity<>(scrumRepository.save(_scrum),HttpStatus.OK);
    }
    @DeleteMapping("/deleteScrum/{id}")
    public ResponseEntity<HttpStatus> deleteScrum(@PathVariable("id") long id) {
        scrumRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAllScrums")
    public ResponseEntity<HttpStatus> deleteAllScrums() {
        scrumRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
