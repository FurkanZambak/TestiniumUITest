import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class DataOps {

    public String returnData(int rowNo, int colNo)  {
        XSSFSheet sheet = null;
        try {
            File src = new File("src/main/resources/data.xlsx");
            FileInputStream fileInputStream = new FileInputStream(src);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("Data");
            workbook.close();
            log.info("Excel dosya başarıyla okundu");
        }
        catch (IOException e) {
            log.error("Excel dosya okuma işleminde hata var!!!", e.getMessage());
        }
        return sheet.getRow(rowNo - 1).getCell(colNo - 1).toString();
    }

    public void writeDataToFile(String text) {
        try {
            Path fileName = Path.of("src/main/resources/data.txt");
            Files.writeString(fileName, text);
            log.info("Txt dosyaya yazma işlemi başarılı");
        }
        catch (IOException e) {
            log.error("Txt dosya yazma işleminde hata", e.getMessage());
        }
    }

    public String readFile() {
        String fileString = "";
        try {
            Path fileName = Path.of("src/main/resources/data.txt");
            fileString = Files.readString(fileName);
            log.info("Txt dosya okuma işlemi başarılı");
        }
        catch (IOException e) {
            log.error("Txt dosya okuma işleminde hata var", e.getMessage());
        }
        return fileString;
    }
}
