package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.*;
import suivimig.example.repository.*;
import suivimig.example.services.ProcedureService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProcedureService procedureService;


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

        Couv c=couvertureRepository.findByValeur((proc.getCouverture().getValeur()));
        if(c==null){
            c=new Couv(proc.getCouverture().getValeur());
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
        Priorite p1=prioriteRepository.findByValeur(procDetails.getPrio().getValeur());
        procedure.setPrio(p1);
        Priorite p2=prioriteRepository.findByValeur(procDetails.getPrioJas().getValeur());
        procedure.setPrioJas(p2);
        Couv c=couvertureRepository.findByValeur((procDetails.getCouverture().getValeur()));
        procedure.setCouverture(c);
        procRepository.save(procedure);
        return ResponseEntity.ok().body(procedure);

    }


    @DeleteMapping("/deleteProcedure/{id}")
    public ResponseEntity<HttpStatus> deleteProcedure(@PathVariable("id") long id) {
        List<Product> products= productRepository.findProductsByProcsId(id);

        for(Product product: products){

            product.removeProc(id);
            productRepository.save(product);

        }

        procRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //jawek behy
    @DeleteMapping("/products/{productId}/procs/{procId}")
    public ResponseEntity<HttpStatus> deleteProcFromProduct(@PathVariable(value = "productId") Long productId, @PathVariable(value = "procId") Long procId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + productId));

        product.removeProc(procId);
        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @DeleteMapping("/deleteAllProcedures")
    public ResponseEntity<HttpStatus> deleteProcedure() {
        procRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllProcsByProductId/{productId}")
    public ResponseEntity<List<Proc>> getAllProcsByProductId(@PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Not found Product with id = " + productId);
        }
        List<Proc> procs = procRepository.findProcsByProductsId(productId);
        return new ResponseEntity<>(procs, HttpStatus.OK);
    }
    @DeleteMapping("/deleteProc/{productId}")
    public void deleteProc(@PathVariable(value = "productId") Set<Integer> procs) throws ResourceNotFoundException {

       for(int id:procs){


       }
    }
    @GetMapping("/getAllProductsByProcId/{tagId}")
    public ResponseEntity<List<Product>> getAllProductsByProcId(@PathVariable(value = "tagId") Long procId) throws ResourceNotFoundException {
        if (!procRepository.existsById(procId)) {
            throw new ResourceNotFoundException("Not found proc with id = " + procId);
        }
        List<Product> products = productRepository.findProductsByProcsId(procId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    //te5dem
    @PostMapping("/addProcedure/{productId}")
    public ResponseEntity<Proc> addProc(@PathVariable(value = "productId") long productId, @RequestBody Proc procRequest) throws ResourceNotFoundException {
        Proc proc = productRepository.findById(productId).map(product -> {
            Proc pr=procRepository.findByName(procRequest.getName())
                    .orElse(null);
                long procId=pr.getId();

            if (procId != 0L) {

                try {
                    Proc  _proc = procRepository.findById(procId)
                            .orElseThrow(() -> new ResourceNotFoundException("Not found Procedure with id = " + procId));
                    product.addProc(_proc);
                    productRepository.save(product);
                    return _proc;
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }

            }

            // add and create new proc
            product.addProc(procRequest);
            return procRepository.save(procRequest);


        }).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + productId));
        return new ResponseEntity<>(proc, HttpStatus.CREATED);
    }



    @PostMapping("/addProcedures/{productId}")
    public HashSet<Proc> addProcs(@PathVariable(value = "productId") long productId, @RequestBody HashSet<Proc> procs) throws ResourceNotFoundException {
     Product p=productRepository.findById(productId)
             .orElseThrow(()-> new ResourceNotFoundException("Not found product with id = " + productId));
        for (Proc pc:procs
             ) {
            System.out.println(pc.toString());
            p.addProc(pc);
            productRepository.save(p);
        }

       return procs;
        //return new ResponseEntity<HashSet<Proc>>(procs,HttpStatus.CREATED);
    }

   // it works sa7ittt

    @PostMapping("/createProcedure/{productId}")
    public ResponseEntity<Proc> createProcForProduct(@PathVariable(value = "productId") long productId, @RequestBody Proc procRequest) throws ResourceNotFoundException {
        Proc proc = productRepository.findById(productId).map(product -> {
                     procRequest.setName(procRequest.getName());
                        ProfilProc f=profilProcRepository.findByValeur(procRequest.getProfilProc().getValeur());
                        procRequest.setProfilProc(f);
                        procRequest.setTraitement(procRequest.getTraitement());
                        procRequest.setSprint(procRequest.getSprint());
                        procRequest.setJiraDev(procRequest.getJiraDev());
                        procRequest.setQuiDev(procRequest.getQuiDev());
                        procRequest.setJiraQa(procRequest.getJiraQa());
                        procRequest.setQuiQa(procRequest.getQuiQa());
                        procRequest.setJiraJas(procRequest.getJiraJas());
                        procRequest.setCommentaireJas(procRequest.getCommentaireJas());
                        procRequest.setDateMAJ(procRequest.getDateMAJ());
                        procRequest.setCommentaireMig(procRequest.getCommentaireMig());
                        procRequest.setCouverture(procRequest.getCouverture());
                        Statut s= statutRepository.findByValeur1(procRequest.getStatutDev().getValeur());
                        procRequest.setStatutDev(s);
                        Statut s1= statutRepository.findByValeur1(procRequest.getStatutQa().getValeur());
                        procRequest.setStatutQa(s1);
                        Statut s2= statutRepository.findByValeur1(procRequest.getStatutTrad().getValeur());
                        procRequest.setStatutTrad(s2);
                        Statut s3= statutRepository.findByValeur1(procRequest.getStatutJasper().getValeur());
                        procRequest.setStatutJasper(s3);
                        Scrum s4= scrumRepository.findByValeur(procRequest.getScrum().getValeur());
                        procRequest.setScrum(s4);
                        PrdSp s5=prdSpRepository.findByValeur(procRequest.getPrdSp().getValeur());
                        procRequest.setPrdSp(s5);

                        Priorite p1=prioriteRepository.findByValeur(procRequest.getPrio().getValeur());
                        procRequest.setPrio(p1);
                        Priorite p2=prioriteRepository.findByValeur(procRequest.getPrioJas().getValeur());
                        procRequest.setPrioJas(p2);
                        Couv c=couvertureRepository.findByValeur(procRequest.getCouverture().getValeur());
                        procRequest.setCouverture(c);
                        procRepository.save(procRequest);

                    product.addProc(procRequest);
                    productRepository.save(product);
            return procRepository.save(procRequest);


        }).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + productId));
        return new ResponseEntity<>(proc, HttpStatus.CREATED);
    }

//delete multiole
    @DeleteMapping("/deleteFromAllProducts/{procList}")
    public void deleteFromAllProducts(@PathVariable("procList") Set <Integer> procList) {

    List <Product> products=productRepository.findAll();
    System.out.println(products);
    for(Product proc:products) {
    System.out.println(proc.getName());
        for (long id : procList) {

            //System.out.println(id);
          //  System.out.println();
             for(Proc pr:proc.getProcs()){
                 System.out.println(pr);
                 if(pr.getId()==id){
               System.out.println(" Before"+ proc.getProcs());
                 proc.getProcs().remove(pr);
                 System.out.println("after"+ proc.getProcs());
                     System.out.println("delete");
       proc.setProcs(proc.getProcs());
Product prodct=productRepository.save(proc);
System.out.println(prodct);

           System.out.println("after all"+proc.getProcs());
                 }

             }


        }
       // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   /*     Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + productId));

        product.removeProc(procId);
        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
*/

    }

}
