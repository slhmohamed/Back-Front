package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.ProfilProc;

@Repository
public interface ProfilProcRepository extends JpaRepository<ProfilProc,Long> {
    ProfilProc findByValeur(String val);
}


