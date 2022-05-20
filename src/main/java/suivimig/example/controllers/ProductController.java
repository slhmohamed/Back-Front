package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.*;
import suivimig.example.repository.*;
import suivimig.example.services.ProcedureService;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("/api/auth")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductRepository procRepository;
    @Autowired
    private StatutRepository statutRepository;

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
    ProcedureService procedureService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product _product = productRepository.save(new Product(product.getName()));
        return new ResponseEntity<>(_product, HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) throws ResourceNotFoundException {
        Product _product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));
        _product.setName(product.getName());


        return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
    }
//add multuple
    @PutMapping("/updateProductProc/{id}")
    public void updateProductProc(@PathVariable("id") Long id, @RequestBody Set<Proc> procs) throws ResourceNotFoundException {

        for (Proc procRequest : procs) {
            Proc proc = productRepository.findById(id).map(product -> {
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

                System.out.println(procRequest);
              //  product.setId(procRequest.getId());
  //procRepository.save(procRequest);
                product.addProc(procRequest);
                productRepository.save(product);

return procRequest;

            }).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = "));

        }
    }

        

    /*
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } */
    @DeleteMapping("/deleteAllProducts")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        productRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + productId));

        product.getProcs().removeAll(product.getProcs());
        productRepository.save(product);
        productRepository.deleteById(productId);



        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
