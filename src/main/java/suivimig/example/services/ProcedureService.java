package suivimig.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import suivimig.example.models.Proc;
import suivimig.example.repository.ProcRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProcedureService {
    @Autowired
    ProcRepository procedureRepository;




    /*
    public Proc addProc(Proc proc) {
        return procedureRepository.save(proc);
    }

    public List<Proc> addAllProcedures(List<Proc> procedures) {
        return procedureRepository.saveAll(procedures);
    }

    public Proc getProcById(Long id) {
        return procedureRepository.findById(id).orElse(null);
    }

    public boolean deleteProcById(long id) {
        Proc existingProc= procedureRepository.getById(id);
        if(existingProc!= null){
            procedureRepository.deleteById(id);
            return true;
        }
        return false;

    }*/




}
