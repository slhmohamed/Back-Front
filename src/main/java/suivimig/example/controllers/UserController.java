package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.ERole;
import suivimig.example.models.Role;
import suivimig.example.models.User;
import suivimig.example.payload.request.SignupRequest;
import suivimig.example.repository.RoleRepository;
import suivimig.example.repository.UserRepository;
import suivimig.example.services.UtilisatService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UtilisatService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/roles")
    public List<Role> getAllRoles(){return roleRepository.findAll();}
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
    /*
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody User employeeDetails) throws ResourceNotFoundException {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setUsername(employeeDetails.getUsername());
        final User updatedEmployee = userRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }*/
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody SignupRequest signupRequest) throws ResourceNotFoundException {
      //  System.out.print(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Pas d'utilisateur avec id :: " + userId));
   // System.out.println(user.getRoles());
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
       //user.setPassword(signupRequest.getPassword());
    System.out.println(signupRequest.getRole());

        Set<String> strRoles = signupRequest.getRole();
        System.out.println(strRoles);

        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Erreur: Role n'existe pas."));
            roles.add(userRole);
            user.setRoles(roles);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur: Role n'existe pas."));
                        roles.add(adminRole);
                        user.setRoles(roles);


                        break;

                    case "ROLE_COLLABORATEUR":
                        Role userRole = roleRepository.findByName(ERole.ROLE_COLLABORATEUR)
                                .orElseThrow(() -> new RuntimeException("Erreur: Role n'existe pas."));
                        roles.add(userRole);
                        user.setRoles(roles);
                }
            });
        }
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }




    //delete user
    @DeleteMapping("/deleteUserById/{id}")
    public boolean deleteUserById(@PathVariable long id){
        return userService.deleteUserByID(id);
    }

}
