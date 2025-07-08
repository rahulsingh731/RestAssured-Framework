package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class utilityReader {

    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IOException("Sheet '" + sheetName + "' not found in the Excel file.");
            }

            // Get header row (first row)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IOException("Header row is missing in the sheet.");
            }

            // Store header names
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                String header = getCellValue(cell);
                headers.add(header.isEmpty() ? "Column" + cell.getColumnIndex() : header);
            }

            // Read data rows (skip header row)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || isRowEmpty(row)) continue; // Skip empty rows

                Map<String, String> rowData = new LinkedHashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(j), getCellValue(cell));
                }
                data.add(rowData);
            }
        }
        return data;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                // Format numbers to avoid scientific notation
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (int) numericValue) {
                    return String.valueOf((int) numericValue);
                }
                return String.valueOf(numericValue);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private static boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell.getCellType() != CellType.BLANK && !getCellValue(cell).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}