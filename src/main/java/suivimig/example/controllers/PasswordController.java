package suivimig.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import suivimig.example.Email.service.UserService;
import suivimig.example.models.User;
import suivimig.example.payload.request.ResetRequest;
import suivimig.example.repository.EmailService;
import suivimig.example.repository.RoleRepository;
import suivimig.example.repository.UserRepository;
import suivimig.example.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class PasswordController implements EmailService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender javaMailSender;


    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody User user) {


        User existingUser=userRepository.findByEmail(user.getEmail());

        String response = userService.forgotPassword(user.getEmail());

        if (!response.startsWith("Invalid")) {


            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Réinitialisation de mot de passe!");
            mailMessage.setFrom("meknimaram5@gmail.com");
            mailMessage.setText("Pour compléter le processus de réinitialisation de mot de passe, cliquez ici: "
                    + "http://localhost:4200/reset?token="+response);

            // Send the email
            javaMailSender.send(mailMessage);

            response = "http://localhost:8080/api/auth/reset-password?token=" + response;


        }


        return response;
    }

    @PutMapping("/reset-password")
	/*public String resetPassword(@RequestParam  String token ,
								@RequestParam String password) {
		String p=encoder.encode(password);
		return userService.resetPassword(token, p);
	}*/

    public String resetPassword(@RequestBody ResetRequest resetRequest) {
        String p=encoder.encode(resetRequest.getPassword());
        return userService.resetPassword(resetRequest.getToken(), p);
    }

}
