package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.*;
import suivimig.example.repository.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class ProcController {


    @Autowired
    private StatutRepository statutRepository;
    @Autowired
    private ProcRepository procRepository;
    @Autowired
    ScrumRepository scrumRepository;
    @Autowired
    PrdSpRepository prdSpRepository;
    @Autowired
    PrioriteRepository prioriteRepository;
    @Autowired
    ProfilProcRepository profilProcRepository;
    @Autowired
    CouvertureRepository couvertureRepository;


    @GetMapping("/procedures")
    public List<Proc> getAllProcs() {
        return procRepository.findAll();
    }

    @GetMapping("/getProcedure/{id}")
    public ResponseEntity<Proc> getProcedureById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Proc proc = procRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found procedure with id = " + id));
        return new ResponseEntity<>(proc, HttpStatus.OK);
    }



    @PostMapping("/createProcedure")
    public Proc createProcedure(@RequestBody Proc proc) throws ResourceNotFoundException {
        Proc myProcedure = procRepository.findByName(proc.getName())
                .orElse(new Proc());

        myProcedure.setName(proc.getName());

        ProfilProc f= profilProcRepository.findByValeur(proc.getProfilProc().getValeur());
        if(f==null){
            f=new ProfilProc(proc.getProfilProc().getValeur());
        }
        myProcedure.setProfilProc(f);
        myProcedure.setTraitement(proc.getTraitement());

        myProcedure.setSprint(proc.getSprint());
        myProcedure.setJiraDev(proc.getJiraDev());
        myProcedure.setQuiDev(proc.getQuiDev());
        myProcedure.setJiraQa(proc.getJiraQa());
        myProcedure.setQuiQa(proc.getQuiQa());
        myProcedure.setJiraJas(proc.getJiraJas());
        myProcedure.setCommentaireJas(proc.getCommentaireJas());
        myProcedure.setDateMAJ(proc.getDateMAJ());
        myProcedure.setCommentaireMig(proc.getCommentaireMig());

        Statut s1= statutRepository.findByValeur1(proc.getStatutDev().getValeur());
        if(s1==null) {
            s1=new Statut(proc.getStatutDev().getValeur());
        }
        myProcedure.setStatutDev(s1);

        Statut s2= statutRepository.findByValeur1(proc.getStatutQa().getValeur());
        if(s2==null) {
            s2=new Statut(proc.getStatutQa().getValeur());
        }
        myProcedure.setStatutQa(s2);

        Statut s3= statutRepository.findByValeur1(proc.getStatutTrad().getValeur());
        if(s3==null) {
            s3=new Statut(proc.getStatutTrad().getValeur());
        }
        myProcedure.setStatutTrad(s3);

        Statut s4= statutRepository.findByValeur1(proc.getStatutJasper().getValeur());
        if(s4==null) {
            s4=new Statut(proc.getStatutJasper().getValeur());
        }
        myProcedure.setStatutJasper(s4);

        Scrum s5=scrumRepository.findByValeur(proc.getScrum().getValeur());
        if(s5==null){
            s5=new Scrum(proc.getScrum().getValeur());
        }
        myProcedure.setScrum(s5);

        PrdSp s6= prdSpRepository.findByValeur(proc.getPrdSp().getValeur());
        if(s6==null){
            s6=new PrdSp(proc.getPrdSp().getValeur());
        }
        myProcedure.setPrdSp(s6);

        Priorite p1=prioriteRepository.findByValeur(proc.getPrio().getValeur());
        if(p1==null){
            p1=new Priorite(proc.getPrio().getValeur());
        }
        myProcedure.setPrio(p1);

        Priorite p2=prioriteRepository.findByValeur((proc.getPrioJas().getValeur()));
        if(p2==null){
            p2=new Priorite(proc.getPrioJas().getValeur());
        }
        myProcedure.setPrioJas(p2);

        Couv c=couvertureRepository.findByProg(proc.getCouverture().getProg());
        if(c==null){
            c=new Couv(proc.getCouverture().getOp(),proc.getCouverture().getProg(),proc.getCouverture().getCouv());
        }
        myProcedure.setCouverture(c);

        return procRepository.save(myProcedure);

    }



    @PutMapping("updateProcedure/{id}")
    public ResponseEntity<Proc> updateProcedure(@PathVariable(value="id") long id, @RequestBody Proc procDetails)
            throws ResourceNotFoundException
    {   Proc procedure= procRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("La procédure ayant l'ID "+id+" est introuvable"));
        procedure.setName(procDetails.getName());

        ProfilProc f=profilProcRepository.findByValeur(procDetails.getProfilProc().getValeur());
        procedure.setProfilProc(f);
        procedure.setTraitement(procDetails.getTraitement());
        procedure.setSprint(procDetails.getSprint());
        procedure.setJiraDev(procDetails.getJiraDev());
        procedure.setQuiDev(procDetails.getQuiDev());
        procedure.setJiraQa(procDetails.getJiraQa());
        procedure.setQuiQa(procDetails.getQuiQa());
        procedure.setJiraJas(procDetails.getJiraJas());
        procedure.setCommentaireJas(procDetails.getCommentaireJas());
        procedure.setDateMAJ(procDetails.getDateMAJ());
        procedure.setCommentaireMig(procDetails.getCommentaireMig());
        Statut s= statutRepository.findByValeur1(procDetails.getStatutDev().getValeur());
        procedure.setStatutDev(s);
        Statut s1= statutRepository.findByValeur1(procDetails.getStatutQa().getValeur());
        procedure.setStatutQa(s1);
        Statut s2= statutRepository.findByValeur1(procDetails.getStatutTrad().getValeur());
        procedure.setStatutTrad(s2);
        Statut s3= statutRepository.findByValeur1(procDetails.getStatutJasper().getValeur());
        procedure.setStatutJasper(s3);
        Scrum s4= scrumRepository.findByValeur(procDetails.getScrum().getValeur());
        procedure.setScrum(s4);
        PrdSp s5=prdSpRepository.findByValeur(procDetails.getPrdSp().getValeur());
        procedure.setPrdSp(s5);
        procRepository.save(procedure);
        Priorite p1=prioriteRepository.findByValeur(procDetails.getPrio().getValeur());
        procedure.setPrio(p1);
        Priorite p2=prioriteRepository.findByValeur(procDetails.getPrioJas().getValeur());
        procedure.setPrioJas(p2);
        Couv c=couvertureRepository.findByProg((procDetails.getCouverture().getProg()));
        procedure.setCouverture(c);
        return ResponseEntity.ok().body(procedure);

    }


    @DeleteMapping("/deleteProcedure/{id}")
    public ResponseEntity<HttpStatus> deleteProcedure(@PathVariable("id") long id) {
        procRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/deleteAllProcedures")
    public ResponseEntity<HttpStatus> deleteProcedure() {
        procRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
