package framwork;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.*;

public class ReadExcel {


    public void readExcel() throws IOException {
        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println(data);
        wb.close();
    }

    public void readUsers() throws IOException {
        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
        FileReader fileReader = new FileReader(src);
        BufferedReader reader = new BufferedReader(fileReader);

        while (reader.readLine()!= null) {
            System.out.println(reader.readLine());
        }
        reader.close();
    }

    public void readUsers2() throws IOException {
        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);

        int rows = sheet1.getLastRowNum();
        int cols = sheet1.getRow(0).getLastCellNum();

        for (int r=0;r <= rows;r++){
            XSSFRow row = sheet1.getRow(r);

            for (int c=0;c < cols;c++) {
               XSSFCell cell = row.getCell(c);

                switch (cell.getCellType()) {
                    case STRING -> System.out.println(cell.getStringCellValue());
                    case NUMERIC -> System.out.println(cell.getNumericCellValue());
                    case BOOLEAN -> System.out.println(cell.getBooleanCellValue());
                }
            }
        }
        wb.close();
    }

    @SneakyThrows
    @DataProvider(name = "InterviewData")
    public static Object[][] getInterviewData() {
        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);

        int numberRows = sheet1.getLastRowNum();
        int numberCols = sheet1.getRow(0).getLastCellNum();

        String[][] interviewData = new String[numberRows][numberCols];

        for (int r = 1; r <= numberRows; r++) {
            XSSFRow row = sheet1.getRow(r);

            for (int c = 0; c < numberCols; c++) {
                XSSFCell cell = row.getCell(c);

                interviewData[r - 1][c] = cell.getStringCellValue();
            }
        }
        wb.close();
        return interviewData;
    }

    @SneakyThrows
    @DataProvider(name = "CommentData")
    public static Object[][] getCommentData() {
        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet2 = wb.getSheetAt(1);

        int numberRows = sheet2.getLastRowNum();
        int numberCols = sheet2.getRow(0).getLastCellNum();

        String[][] commentData = new String[numberRows][numberCols];

        for (int r = 1; r <= numberRows; r++) {
            XSSFRow row = sheet2.getRow(r);

            for (int c = 0; c < numberCols; c++) {
                XSSFCell cell = row.getCell(c);

                commentData[r - 1][c] = cell.getStringCellValue();
            }
        }
        wb.close();
        return commentData;
    }
}
