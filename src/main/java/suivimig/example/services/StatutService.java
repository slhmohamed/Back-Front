package suivimig.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suivimig.example.models.Proc;
import suivimig.example.models.Statut;
import suivimig.example.repository.StatutRepository;
@Service
public class StatutService {
    @Autowired
    StatutRepository statutRepository;

    public Statut addStatut(Statut statut) {
        return statutRepository.save(statut);
    }
}
