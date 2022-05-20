package suivimig.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import suivimig.example.helper.ExcelHelper;
import suivimig.example.models.Proc;
import suivimig.example.repository.ProcRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    ProcRepository procRepository;

    public void save(MultipartFile file) {
        try {
            List<Proc> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            procRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Proc> getAllTutorials() {
        return procRepository.findAll();
    }
}
