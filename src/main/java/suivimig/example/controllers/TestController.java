package suivimig.example.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Contenu publique.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('COLLABORATEUR') or hasRole('ADMIN')")
    public String userAccess() {
        return " Dashboard Collaborateur .";
    }



    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return " Dashboard Admin .";
    }
}
