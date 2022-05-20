package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>, PagingAndSortingRepository<Client,Long> {
    Client findByName(String val);
}
