package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suivimig.example.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    User findByToken(String token);

    Optional<User> findById(Long id);

    User getById(Long id);
}
