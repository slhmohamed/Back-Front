package suivimig.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import suivimig.example.models.Proc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface ProcRepository extends JpaRepository<Proc, Long>, PagingAndSortingRepository<Proc,Long> {


    Optional<Proc> findByName(String name);





}
