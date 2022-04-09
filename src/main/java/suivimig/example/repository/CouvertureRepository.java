package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Couv;

@Repository
public interface CouvertureRepository extends JpaRepository<Couv,Long> {

    Couv findByProg(String val);
}
