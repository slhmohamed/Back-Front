package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Statut;



@Repository
public interface StatutRepository extends JpaRepository<Statut,Long>, PagingAndSortingRepository<Statut,Long> {

    @Query(value="SELECT * FROM statut q " +
            "WHERE q.valeur =?1", nativeQuery = true)
    Statut findByValeur1(String val);



}
