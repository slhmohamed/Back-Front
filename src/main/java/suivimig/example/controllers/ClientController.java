package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.*;
import suivimig.example.repository.ClientRepository;
import suivimig.example.repository.ProcRepository;
import suivimig.example.repository.ProductRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProcRepository procRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/getClient/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found client with id = " + id));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/createClient")
    public Client createClient(@RequestBody Client client)  {
       // client=clientRepository.findByName(client.getName()).orElse(new Client());
        Client myClient= new Client();


        myClient.setName(client.getName());

        Product p=productRepository.findByName(client.getProduct().getName());
        if(p==null){
            p=new Product(client.getProduct().getName());
        }
        myClient.setProduct(p);

       return clientRepository.save(myClient);


    }

    @PutMapping("updateClient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value="id") long id, @RequestBody Client clientDetails)
            throws ResourceNotFoundException
    {   Client client= clientRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Le client ayant l'ID "+id+" est introuvable"));
        client.setName(clientDetails.getName());


       Product product=productRepository.findByName(clientDetails.getProduct().getName());
       client.setProduct(product);
       clientRepository.save(client);
        return ResponseEntity.ok().body(client);

    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/deleteAllClients")
    public ResponseEntity<HttpStatus> deleteClients() {
        clientRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
