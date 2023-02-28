package framework;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.*;

public class ReadExcel {

    public void readExcel() throws IOException {
        File src = new File("C:\\Users\\yahav\\IdeaProjects\\Automation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println(data);
        wb.close();
    }

    public XSSFWorkbook readExcel2() throws IOException {
        File src = new File("C:\\Users\\yahav\\IdeaProjects\\Automation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println(data);
        wb.close();
        return wb;
    }

    @SneakyThrows
    @DataProvider
    public static Object[][] readSheetByIndex(int sheetNumber){
        File src = new File("C:\\Users\\yahav\\IdeaProjects\\Automation\\src\\main\\resources\\Data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sheetNumber);
        int numberRows = sheet.getLastRowNum();
        int numberCols = sheet.getRow(0).getLastCellNum();
        String[][] interviewData = new String[numberRows][numberCols];
        for (int r = 1; r<=numberRows; r++) {
            XSSFRow row = sheet.getRow(r);
            for (int c = 0; c < numberCols; c++) {
                XSSFCell cell = row.getCell(c);
                interviewData[r-1][c] = cell.getStringCellValue();
                System.out.println(interviewData[r-1][c]);
            }
        }
        wb.close();
        return interviewData;
    }

    @SneakyThrows
    @DataProvider(name = "InterviewData")
    public static Object[][] getInterviewData() {
        return readSheetByIndex(0);
    }

    @SneakyThrows
    @DataProvider(name = "CommanderNoteData")
    public static Object[][] getCommanderNoteData() {
        return readSheetByIndex(1);
    }


//    public void readUsers2() throws IOException {
//        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
//        FileInputStream fis = new FileInputStream(src);
//        XSSFWorkbook wb = new XSSFWorkbook(fis);
//        XSSFSheet sheet1 = wb.getSheetAt(0);
//
//        int rows = sheet1.getLastRowNum();
//        int cols = sheet1.getRow(0).getLastCellNum();
//
//        for (int r=0;r <= rows;r++){
//            XSSFRow row = sheet1.getRow(r);
//
//            for (int c=0;c < cols;c++) {
//               XSSFCell cell = row.getCell(c);
//
//                switch (cell.getCellType()) {
//                    case STRING -> System.out.println(cell.getStringCellValue());
//                    case NUMERIC -> System.out.println(cell.getNumericCellValue());
//                    case BOOLEAN -> System.out.println(cell.getBooleanCellValue());
//                }
//            }
//        }
//        wb.close();
//    }
//
//    @SneakyThrows
//    @DataProvider(name = "InterviewData")
//    public static Object[][] getInterviewData() {
//        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
//        FileInputStream fis = new FileInputStream(src);
//        XSSFWorkbook wb = new XSSFWorkbook(fis);
//        XSSFSheet sheet1 = wb.getSheetAt(0);
//
//        int numberRows = sheet1.getLastRowNum();
//        int numberCols = sheet1.getRow(0).getLastCellNum();
//
//        String[][] interviewData = new String[numberRows][numberCols];
//
//        for (int r = 1; r <= numberRows; r++) {
//            XSSFRow row = sheet1.getRow(r);
//
//            for (int c = 0; c < numberCols; c++) {
//                XSSFCell cell = row.getCell(c);
//
//                interviewData[r - 1][c] = cell.getStringCellValue();
//            }
//        }
//        wb.close();
//        return interviewData;
//    }
//
//    @SneakyThrows
//    @DataProvider(name = "CommentData")
//    public static Object[][] getCommentData() {
//        File src = new File("C:\\KodkodAutomation\\src\\main\\resources\\Data.xlsx");
//        FileInputStream fis = new FileInputStream(src);
//        XSSFWorkbook wb = new XSSFWorkbook(fis);
//        XSSFSheet sheet2 = wb.getSheetAt(1);
//
//        int numberRows = sheet2.getLastRowNum();
//        int numberCols = sheet2.getRow(0).getLastCellNum();
//
//        String[][] commentData = new String[numberRows][numberCols];
//
//        for (int r = 1; r <= numberRows; r++) {
//            XSSFRow row = sheet2.getRow(r);
//
//            for (int c = 0; c < numberCols; c++) {
//                XSSFCell cell = row.getCell(c);
//
//                commentData[r - 1][c] = cell.getStringCellValue();
//            }
//        }
//        wb.close();
//        return commentData;
//    }
}
