package suivimig.example.payload.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import suivimig.example.exceptions.ResourceNotFoundException;
import suivimig.example.models.Statut;
import suivimig.example.repository.StatutRepository;

public class ProcRequest {
    @Autowired
    StatutRepository statutRepository;
    String name;
    long statutDev_Id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStatutDev_Id() {
        return statutDev_Id;
    }

    public void setStatutDev_Id(long statutDev_Id) {
        this.statutDev_Id = statutDev_Id;
    }

    public Statut getStatutById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Statut statut = statutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found statut with id = " + id));
        return statut;
    }
}
