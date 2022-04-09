package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Proc;
import suivimig.example.models.Scrum;

@Repository
public interface ScrumRepository extends JpaRepository<Scrum,Long>, PagingAndSortingRepository<Scrum,Long> {
    Scrum findByValeur(String val);
}
