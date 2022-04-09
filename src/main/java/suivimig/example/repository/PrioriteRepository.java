package suivimig.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Priorite;

@Repository
public interface PrioriteRepository extends JpaRepository<Priorite,Long> {
    Priorite findByValeur(String val);
}
