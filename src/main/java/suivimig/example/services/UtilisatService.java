package suivimig.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import suivimig.example.models.ERole;
import suivimig.example.models.Role;
import suivimig.example.models.User;
import suivimig.example.payload.request.SignupRequest;
import suivimig.example.payload.response.MessageResponse;
import suivimig.example.repository.RoleRepository;
import suivimig.example.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UtilisatService {



    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;


    /*
    public User addUser(User user){
        return userRepository.save(user);
    }*/

    public ResponseEntity<?> addUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Nom utilisateur existe!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: Email utilisateur existe!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null ) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Erreur: Role n'existe pas."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "collaborateur":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_COLLABORATEUR)
                                .orElseThrow(() -> new RuntimeException("Erreur: Role n'existe pas."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur: Role n'existe pas."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succée!"));
    }



    public Optional<User> getUserByID(long id) {
        return userRepository.findById(id);
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User user) {
        User existingUser= userRepository.findById(user.getId()).orElse(null);
        if(existingUser!=null){
            return userRepository.save(user);
        }else{
            userRepository.deleteById(existingUser.getId());
            userRepository.save(user);
        }
        return user;
    }


    public boolean deleteUserByID(long id) {
        User existingUser= userRepository.getById(id);
        if(existingUser!= null){
            userRepository.deleteById(id);
            return true;
        }
        return false;

    }
}

