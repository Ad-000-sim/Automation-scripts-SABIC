package utilities;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtilities {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getPhysicalNumberOfRows() - 1; // exclude header
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            
            if (rowCount < 1 || colCount < 1) {
                throw new IOException("No test data found in Excel");
            }

            Object[][] data = new Object[rowCount][colCount];
            
            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i + 1);
                if (row == null) continue;
                
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j, XSSFRow.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[i][j] = cell.toString();
                }
            }
            return data;
        }
    }
}