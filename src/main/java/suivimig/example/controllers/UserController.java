package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.User;
import suivimig.example.payload.request.SignupRequest;
import suivimig.example.repository.UserRepository;
import suivimig.example.services.UtilisatService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UtilisatService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //add new user
    @PostMapping("/addUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest){
        return userService.addUser(signUpRequest);
    }



    //get user by id
    @GetMapping("/getUserByID/{id}")
    public Optional<User> getUserById(@PathVariable long id){
        return userService.getUserByID(id);
    }

    //get user by firstname
    @GetMapping("/getUserByUsername/{username}")
    public Optional<User> getUserByFirstname(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }


    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody User employeeDetails) throws ResourceNotFoundException {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setUsername(employeeDetails.getUsername());
        final User updatedEmployee = userRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }



    //delete user
    @DeleteMapping("/deleteUserById/{id}")
    public boolean deleteUserById(@PathVariable long id){
        return userService.deleteUserByID(id);
    }

}
