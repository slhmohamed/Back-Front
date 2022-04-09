package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.Priorite;
import suivimig.example.models.ProfilProc;
import suivimig.example.repository.ProfilProcRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class ProfilProcController {

    @Autowired
    ProfilProcRepository profilProcRepository;

    @GetMapping("/getAllProfils")
    public List<ProfilProc> getAllProfils() {
        return profilProcRepository.findAll();
    }

    @GetMapping("/getProfil/{id}")
    public ResponseEntity<ProfilProc> getProfilById(@PathVariable("id") long id) throws ResourceNotFoundException {
        ProfilProc profilProc= profilProcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found profil proc with id = " + id));
        return new ResponseEntity<>(profilProc, HttpStatus.OK);
    }

    @PostMapping("/createProfil")
    public ResponseEntity<ProfilProc> createPrdSp(@RequestBody ProfilProc profilProc) {
        ProfilProc _profilProc= profilProcRepository.save(new ProfilProc(profilProc.getValeur()));
        return new ResponseEntity<>(_profilProc, HttpStatus.CREATED);
    }

    @PutMapping("/updateProfil/{id}")
    public ResponseEntity<ProfilProc> updateProfil(@PathVariable("id") long id, @RequestBody Priorite priorite) throws ResourceNotFoundException {
        ProfilProc _profilProc= profilProcRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Profil with id = " + id));
        _profilProc.setValeur(priorite.getValeur());
        return new ResponseEntity<>(profilProcRepository.save(_profilProc),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProfil/{id}")
    public ResponseEntity<HttpStatus> deleteProfil(@PathVariable("id") long id) {
       profilProcRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAllProfils")
    public ResponseEntity<HttpStatus> deleteAllProfils() {
        profilProcRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
