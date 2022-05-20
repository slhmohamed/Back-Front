package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.Couv;
import suivimig.example.repository.CouvertureRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class CouvertureController {

    @Autowired
    CouvertureRepository couvertureRepository;

    @GetMapping("/getAllCouvertures")
    public List<Couv> getAllCouverture() {
        return couvertureRepository.findAll();
    }

    @GetMapping("/getCouverture/{id}")
    public ResponseEntity<Couv> getCouvertureById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Couv couverture = couvertureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found couverture with id = " + id));
        return new ResponseEntity<>(couverture, HttpStatus.OK);
    }

    @PostMapping("/createCouverture")
    public ResponseEntity<Couv> createCouverture(@RequestBody Couv couverture) {
        System.out.println(couverture.toString());
        Couv _couverture= couvertureRepository.save(new Couv(couverture.getValeur()));
        return new ResponseEntity<>(_couverture, HttpStatus.CREATED);
    }

    @PutMapping("/updateCouverture/{id}")
    public ResponseEntity<Couv> updateCouverture(@PathVariable("id") long id, @RequestBody Couv couverture) throws ResourceNotFoundException {
        Couv _couverture = couvertureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found couverture with id = " + id));
       // _couverture.setOp(couverture.getOp());
       // _couverture.setProg(couverture.getProg());
        _couverture.setValeur(couverture.getValeur());
        return new ResponseEntity<>(couvertureRepository.save(_couverture),HttpStatus.OK);
    }
    @DeleteMapping("/deleteCouverture/{id}")
    public ResponseEntity<HttpStatus> deleteCouverture(@PathVariable("id") long id) {
        couvertureRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteAllCouvertures")
    public ResponseEntity<HttpStatus> deleteAllCouvertures() {
        couvertureRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
