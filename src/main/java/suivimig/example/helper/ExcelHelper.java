package suivimig.example.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import suivimig.example.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "commentaire_jas","commentaire_mig", "datemaj", "jira_dev",
    "jira_jas","jira_qa","name","qui_dev","qui_qa","sprint","traitement","couverture_id","prd_sp_id",
            "prio_id","prio_jas_id","profil_proc_id","scrum_id","statut_dev_id","statut_jasper_id","statut_qa_id",
            "statut_trad_id"
    };
    static String SHEET = "proc";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Proc> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Proc> tutorials = new ArrayList<Proc>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Proc tutorial = new Proc();
                Couv couv=new Couv();
                PrdSp prdSp=new PrdSp();
                Priorite prio=new Priorite();
                Priorite prioJas=new Priorite();
                ProfilProc profilProc=new ProfilProc();
                Scrum scrum=new Scrum();
                Statut statutDev=new Statut();
                Statut statutJas=new Statut();
                Statut statutQa=new Statut();
                Statut statutTrad=new Statut();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            tutorial.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            tutorial.setCommentaireJas(currentCell.getStringCellValue());
                            break;
                        case 2:
                            tutorial.setCommentaireMig(currentCell.getStringCellValue());
                            break;
                        case 3:
                            tutorial.setDateMAJ(currentCell.getStringCellValue());
                            break;
                        case 4:
                            tutorial.setJiraDev(currentCell.getStringCellValue());
                            break;
                        case 5:
                            tutorial.setJiraJas(currentCell.getStringCellValue());
                            break;
                        case 6:
                            tutorial.setJiraQa(currentCell.getStringCellValue());
                            break;
                        case 7:
                            tutorial.setName(currentCell.getStringCellValue());
                            break;
                        case 8:
                            tutorial.setQuiDev(currentCell.getStringCellValue());
                            break;
                        case 9:
                            tutorial.setQuiQa(currentCell.getStringCellValue());
                            break;
                        case 10:
                            tutorial.setSprint(currentCell.getStringCellValue());
                            break;
                        case 11:
                            tutorial.setTraitement(currentCell.getStringCellValue());
                            break;
                        case 12:
                            couv.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 13:
                            prdSp.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 14:
                            prio.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 15:
                            prioJas.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 16:
                            profilProc.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 17:
                            scrum.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 18:
                            statutDev.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 19:
                            statutJas.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 20:
                            statutQa.setId((long) currentCell.getNumericCellValue());
                            break;
                        case 21:
                            statutTrad.setId((long) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                tutorials.add(tutorial);
            }
            workbook.close();
            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
