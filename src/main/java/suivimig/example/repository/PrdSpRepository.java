package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.PrdSp;

@Repository
public interface PrdSpRepository extends JpaRepository<PrdSp,Long> {
    PrdSp findByValeur(String val);
}
