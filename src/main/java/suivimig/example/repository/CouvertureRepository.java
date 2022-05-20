package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Couv;
import suivimig.example.models.Priorite;

@Repository
public interface CouvertureRepository extends JpaRepository<Couv,Long> {



    Couv findByValeur(String val);
}
