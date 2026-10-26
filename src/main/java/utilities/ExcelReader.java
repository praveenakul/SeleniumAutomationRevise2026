package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] getExcelData(String filePath, String sheetName) {

        Object[][] data = null;

        try {

            FileInputStream file = new FileInputStream(filePath);

            Workbook workbook = WorkbookFactory.create(file);

            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();

            int columns = sheet.getRow(0).getPhysicalNumberOfCells();

            // Exclude header row
            data = new Object[rows - 1][columns];

            // Start from row 1 because row 0 is the header
            for (int i = 1; i < rows; i++) {

                for (int j = 0; j < columns; j++) {

                    data[i - 1][j] =
                            sheet.getRow(i).getCell(j).toString();
                }
            }

            workbook.close();
            file.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return data;
    }
}